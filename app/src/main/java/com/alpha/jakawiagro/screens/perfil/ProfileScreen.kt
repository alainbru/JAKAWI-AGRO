package com.alpha.jakawiagro.screens.perfil

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.alpha.jakawiagro.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Composable
fun ProfileScreen(
    onBack: () -> Unit
) {
    val colors = MaterialTheme.colorScheme
    val scope = rememberCoroutineScope()

    val uid = FirebaseAuth.getInstance().currentUser?.uid
    val emailAuth = FirebaseAuth.getInstance().currentUser?.email ?: ""

    var loading by remember { mutableStateOf(true) }

    // Datos perfil
    var nombreCompleto by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var region by remember { mutableStateOf("") }
    var rol by remember { mutableStateOf("AGRICULTOR") }
    var photoUrl by remember { mutableStateOf<String?>(null) }

    // Estados foto
    var uploadingPhoto by remember { mutableStateOf(false) }
    var localPhotoUri by remember { mutableStateOf<Uri?>(null) } // preview

    // Dialog edición
    var editField by remember { mutableStateOf<EditField?>(null) }

    val picker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        if (uri != null && uid != null) {
            // Preview inmediato
            localPhotoUri = uri

            scope.launch {
                uploadingPhoto = true
                try {
                    // ✅ subir a la ruta que coincide con tus reglas:
                    // profile_photos/{uid}/profile.jpg
                    val (downloadUrl, storagePath) = uploadProfilePhotoAndGetUrl(uid, uri)

                    // ✅ guardar URL (y path opcional) en Firestore para persistencia
                    FirebaseFirestore.getInstance()
                        .collection("users")
                        .document(uid)
                        .update(
                            mapOf(
                                "photoUrl" to downloadUrl,
                                "photoPath" to storagePath, // opcional pero recomendado
                                "updatedAt" to System.currentTimeMillis()
                            )
                        )
                        .await()

                    // ✅ refrescar UI con lo persistente
                    photoUrl = downloadUrl
                    localPhotoUri = null
                } catch (_: Exception) {
                    // si falla, vuelvo al estado anterior
                    localPhotoUri = null
                } finally {
                    uploadingPhoto = false
                }
            }
        }
    }

    // Cargar datos desde Firestore
    LaunchedEffect(uid) {
        if (uid == null) return@LaunchedEffect
        try {
            val snap = FirebaseFirestore.getInstance()
                .collection("users")
                .document(uid)
                .get()
                .await()

            nombreCompleto = snap.getString("nombreCompleto") ?: ""
            telefono = snap.getString("telefono") ?: ""
            region = snap.getString("region") ?: ""
            rol = snap.getString("rol") ?: "AGRICULTOR"
            photoUrl = snap.getString("photoUrl")
        } catch (_: Exception) {
        } finally {
            loading = false
        }
    }

    if (loading) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    val bg = Brush.verticalGradient(
        colors = listOf(
            colors.background,
            colors.primary.copy(alpha = 0.06f),
            colors.background
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bg)
            .padding(16.dp)
    ) {

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(26.dp),
            colors = CardDefaults.cardColors(containerColor = colors.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box(
                    modifier = Modifier
                        .size(112.dp)
                        .shadow(10.dp, CircleShape)
                        .clip(CircleShape)
                        .background(colors.surfaceVariant.copy(alpha = 0.35f))
                        .clickable(enabled = !uploadingPhoto) { picker.launch("image/*") },
                    contentAlignment = Alignment.Center
                ) {
                    when {
                        localPhotoUri != null -> {
                            Image(
                                painter = rememberAsyncImagePainter(localPhotoUri),
                                contentDescription = "Foto perfil",
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                        !photoUrl.isNullOrBlank() -> {
                            Image(
                                painter = rememberAsyncImagePainter(photoUrl),
                                contentDescription = "Foto perfil",
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                        else -> {
                            Image(
                                painter = painterResource(id = R.drawable.icono_bienvenida2),
                                contentDescription = "Avatar",
                                modifier = Modifier.size(86.dp)
                            )
                        }
                    }

                    if (uploadingPhoto) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(colors.surface.copy(alpha = 0.45f)),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }

                Spacer(Modifier.height(12.dp))

                Text(
                    text = (nombreCompleto.ifBlank { "USUARIO" }).uppercase(),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.ExtraBold,
                    color = colors.onSurface
                )

                Spacer(Modifier.height(4.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = null,
                        tint = colors.onSurfaceVariant,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(Modifier.width(6.dp))
                    Text(
                        text = emailAuth,
                        style = MaterialTheme.typography.bodySmall,
                        color = colors.onSurfaceVariant
                    )
                }

                Spacer(Modifier.height(10.dp))

                AssistChip(
                    onClick = { editField = EditField.Rol(rol) },
                    label = { Text(rol) }
                )

                Spacer(Modifier.height(6.dp))

                Text(
                    text = if (uploadingPhoto) "Guardando foto..." else "Toca la foto para cambiarla",
                    style = MaterialTheme.typography.bodySmall,
                    color = colors.onSurfaceVariant.copy(alpha = 0.75f)
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = colors.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Datos personales",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.ExtraBold
                )
                Spacer(Modifier.height(12.dp))

                ProfileRow(
                    icon = Icons.Default.Person,
                    label = "Nombre completo",
                    value = nombreCompleto.ifBlank { "No registrado" },
                    onEdit = { editField = EditField.Nombre(nombreCompleto) }
                )

                Divider(Modifier.padding(vertical = 10.dp))

                ProfileRow(
                    icon = Icons.Default.Phone,
                    label = "Teléfono",
                    value = telefono.ifBlank { "No registrado" },
                    onEdit = { editField = EditField.Telefono(telefono) }
                )

                Divider(Modifier.padding(vertical = 10.dp))

                ProfileRow(
                    icon = Icons.Default.LocationOn,
                    label = "Región",
                    value = region.ifBlank { "No registrada" },
                    onEdit = { editField = EditField.Region(region) }
                )

                Divider(Modifier.padding(vertical = 10.dp))

                ProfileRow(
                    icon = Icons.Default.Work,
                    label = "Rol",
                    value = rol.ifBlank { "No definido" },
                    onEdit = { editField = EditField.Rol(rol) }
                )
            }
        }

        Spacer(Modifier.height(14.dp))

        Text(
            text = "Tip: edita campo por campo usando ✏️",
            style = MaterialTheme.typography.bodySmall,
            color = colors.onSurfaceVariant,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }

    // ===================== DIALOG EDITAR =====================
    if (editField != null) {
        val current = editField!!
        var saving by remember { mutableStateOf(false) }

        when (current) {
            is EditField.Rol -> {
                var selected by remember { mutableStateOf(current.initial.ifBlank { "AGRICULTOR" }) }

                AlertDialog(
                    onDismissRequest = { if (!saving) editField = null },
                    title = { Text("Selecciona tu rol", fontWeight = FontWeight.ExtraBold) },
                    text = {
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            RoleOption("AGRICULTOR", selected) { selected = it }
                            RoleOption("TECNICO", selected) { selected = it }
                            RoleOption("PROPIETARIO", selected) { selected = it }
                            RoleOption("OTRO", selected) { selected = it }
                        }
                    },
                    confirmButton = {
                        Button(
                            enabled = !saving,
                            onClick = {
                                if (uid == null) return@Button
                                scope.launch {
                                    saving = true
                                    try {
                                        rol = selected
                                        FirebaseFirestore.getInstance()
                                            .collection("users")
                                            .document(uid)
                                            .update(
                                                mapOf(
                                                    "rol" to rol,
                                                    "updatedAt" to System.currentTimeMillis()
                                                )
                                            )
                                            .await()
                                        editField = null
                                    } catch (_: Exception) {
                                    } finally {
                                        saving = false
                                    }
                                }
                            }
                        ) {
                            Text(if (saving) "Guardando..." else "Guardar")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { if (!saving) editField = null }) {
                            Text("Cancelar")
                        }
                    }
                )
            }

            else -> {
                var tempValue by remember(current) { mutableStateOf(current.initial) }

                AlertDialog(
                    onDismissRequest = { if (!saving) editField = null },
                    title = { Text(current.title, fontWeight = FontWeight.ExtraBold) },
                    text = {
                        OutlinedTextField(
                            value = tempValue,
                            onValueChange = { tempValue = it },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = current.keyboard)
                        )
                    },
                    confirmButton = {
                        Button(
                            enabled = !saving,
                            onClick = {
                                if (uid == null) return@Button
                                scope.launch {
                                    saving = true
                                    try {
                                        val updates = mutableMapOf<String, Any>(
                                            "updatedAt" to System.currentTimeMillis()
                                        )

                                        when (current) {
                                            is EditField.Nombre -> {
                                                nombreCompleto = tempValue.trim()
                                                updates["nombreCompleto"] = nombreCompleto
                                            }
                                            is EditField.Telefono -> {
                                                telefono = tempValue.trim()
                                                updates["telefono"] = telefono
                                            }
                                            is EditField.Region -> {
                                                region = tempValue.trim()
                                                updates["region"] = region
                                            }
                                            else -> {}
                                        }

                                        FirebaseFirestore.getInstance()
                                            .collection("users")
                                            .document(uid)
                                            .update(updates)
                                            .await()

                                        editField = null
                                    } catch (_: Exception) {
                                    } finally {
                                        saving = false
                                    }
                                }
                            }
                        ) { Text(if (saving) "Guardando..." else "Guardar") }
                    },
                    dismissButton = {
                        TextButton(onClick = { if (!saving) editField = null }) {
                            Text("Cancelar")
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun RoleOption(
    value: String,
    selected: String,
    onSelect: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .clickable { onSelect(value) }
            .padding(vertical = 10.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected == value,
            onClick = { onSelect(value) }
        )
        Spacer(Modifier.width(10.dp))
        Text(text = value, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
private fun ProfileRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    value: String,
    onEdit: () -> Unit
) {
    val colors = MaterialTheme.colorScheme

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(38.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(colors.primary.copy(alpha = 0.12f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = null, tint = colors.primary)
        }

        Spacer(Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
                color = colors.onSurfaceVariant
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                color = colors.onSurface
            )
        }

        IconButton(onClick = onEdit) {
            Icon(Icons.Default.Edit, contentDescription = "Editar", tint = colors.primary)
        }
    }
}

private sealed class EditField(
    val title: String,
    val initial: String,
    val keyboard: KeyboardType
) {
    class Nombre(initial: String) : EditField("Editar nombre completo", initial, KeyboardType.Text)
    class Telefono(initial: String) : EditField("Editar teléfono", initial, KeyboardType.Phone)
    class Region(initial: String) : EditField("Editar región", initial, KeyboardType.Text)
    class Rol(initial: String) : EditField("Editar rol", initial, KeyboardType.Text)
}

/**
 * ✅ Sube la foto a Firebase Storage y devuelve:
 * - URL de descarga (photoUrl)
 * - Path en storage (photoPath)
 *
 * Guardamos SIEMPRE en:
 * profile_photos/{uid}/profile.jpg
 */
private suspend fun uploadProfilePhotoAndGetUrl(uid: String, uri: Uri): Pair<String, String> {
    val path = "profile_photos/$uid/profile.jpg"
    val storageRef = FirebaseStorage.getInstance()
        .reference
        .child(path)

    storageRef.putFile(uri).await()
    val url = storageRef.downloadUrl.await().toString()
    return url to path
}
