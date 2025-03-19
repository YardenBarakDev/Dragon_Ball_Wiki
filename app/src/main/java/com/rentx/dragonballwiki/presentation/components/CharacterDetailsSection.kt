package com.rentx.dragonballwiki.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rentx.core.local_db.DragonBallCharacterEntity

@Composable
fun CharactersDetailsSection(modifier: Modifier = Modifier, character: DragonBallCharacterEntity) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = character.name,
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.W800,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "${character.race} - ${character.gender}",
            fontSize = 20.sp,
            fontWeight = FontWeight.W700,
            color = Color(0xFFFBC02C),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Base Ki:",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.W600,
        )
        Text(
            text = character.ki,
            fontSize = 20.sp,
            fontWeight = FontWeight.W700,
            color = Color(0xFFFBC02C),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Total Ki:",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.W600,
        )
        Text(
            text = character.maxKi,
            fontSize = 20.sp,
            fontWeight = FontWeight.W700,
            color = Color(0xFFFBC02C),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Affiliation:",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.W600,
        )
        Text(
            text = character.affiliation,
            fontSize = 20.sp,
            fontWeight = FontWeight.W700,
            color = Color(0xFFFBC02C),
            modifier = Modifier.padding(bottom = 12.dp)
        )
    }
}