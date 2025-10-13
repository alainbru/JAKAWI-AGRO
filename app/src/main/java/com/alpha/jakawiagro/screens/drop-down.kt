package com.alpha.jakawiagro.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun DropPreview() {
    JakawiSidebarMenu()
}

@Composable
fun JakawiSidebarMenu() {
    Column(
        modifier = Modifier
            .fillMaxHeight(1f)
            .fillMaxWidth(0.50f)
            .background(Color(0xFF00796B))
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(
            text = "JAKAWI AGRO",
            color = Color(0xFFFFF8E1),
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        val menuItems = listOf(
            "Inicio",
            "Clima",
            "Calendario",
            "Diagnóstico",
            "Parcelas",
            "Plan de trabajo",
            "Perfil"
        )

        menuItems.forEachIndexed { index, item ->
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = item,
                    color = Color(0xFFFFF8E1),
                    fontSize = 8.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { /* Acción */ }
                        .padding(vertical = 4.dp, horizontal = 2.dp)
                )
                if (index < menuItems.lastIndex) {
                    Divider(
                        color = Color.White,
                        thickness = 1.dp,
                        modifier = Modifier.fillMaxWidth()                    )
                }
           } } } }
