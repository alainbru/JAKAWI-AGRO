package com.alpha.jakawiagro.data.repository

import com.alpha.jakawiagro.data.model.Parcela
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class ParcelasRepository(
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
) {
    private val col = db.collection("parcelas")

    suspend fun obtenerParcelas(ownerId: String): List<Parcela> {
        val snap = col.whereEqualTo("ownerId", ownerId).get().await()
        return snap.documents.mapNotNull { it.toObject(Parcela::class.java) }
    }

    suspend fun crearParcela(parcela: Parcela): String {
        val doc = col.document() // genera ID
        val now = System.currentTimeMillis()

        val data = parcela.copy(
            id = doc.id,
            createdAt = now,
            updatedAt = now
        )

        doc.set(data).await()
        return doc.id
    }

    suspend fun actualizarParcela(parcela: Parcela) {
        require(parcela.id.isNotBlank()) { "Parcela id vacío" }
        col.document(parcela.id)
            .set(parcela.copy(updatedAt = System.currentTimeMillis()))
            .await()
    }

    suspend fun eliminarParcela(id: String) {
        require(id.isNotBlank()) { "Parcela id vacío" }
        col.document(id).delete().await()
    }
}
