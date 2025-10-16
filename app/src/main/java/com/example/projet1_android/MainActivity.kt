package com.example.projet1_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projet1_android.ui.theme.Projet1_androidTheme
import kotlin.random.Random
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.style.TextAlign

enum class Tri { Aucun, Croissant, Decroissant }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState); enableEdgeToEdge()
        setContent { LanceurDesApp() }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanceurDesApp() {
    Projet1_androidTheme {
        Scaffold(topBar = { TopAppBar(title = { Text("Lanceur de dés") }) }) { inner ->
            LanceurDesScreen(Modifier.padding(inner).padding(16.dp))
        }
    }
}

@Composable
fun LanceurDesScreen(modifier: Modifier = Modifier) {
    var nbDes by rememberSaveable { mutableStateOf(3) }
    var faces by rememberSaveable { mutableStateOf(6) }
    var tri by rememberSaveable { mutableStateOf(Tri.Aucun) }
    var resultats by rememberSaveable { mutableStateOf<List<Int>>(emptyList()) }

    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            onClick = {
                val tirages = List(nbDes) { Random.nextInt(1, faces + 1) }
                resultats = when (tri) {
                    Tri.Aucun -> tirages
                    Tri.Croissant -> tirages.sorted()
                    Tri.Decroissant -> tirages.sortedDescending()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Lancer les dés") }


        Spacer(Modifier.height(24.dp))
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                if (resultats.isEmpty()) {
                    Text("Lancez les dés pour voir les résultats")
                } else {
                    Text("Résultats : ${resultats.joinToString(", ")}")
                    Text("Somme : ${resultats.sum()}")
                    Spacer(Modifier.height(16.dp))
                    LazyHorizontalGrid(
                        rows = GridCells.Fixed(2),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.height(200.dp).fillMaxWidth()
                    ) {
                        items(resultats) { v -> DeView(v) }
                    }
                }
            }
        }
    }
}

@Composable
fun DeView(valeur: Int) {
    Box(Modifier.size(86.dp), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.de_background),
            contentDescription = "Dé",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.25f))
        )

        Text(
            text = valeur.toString(),
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                shadow = Shadow(
                    color = Color.Black.copy(alpha = 0.8f),
                    blurRadius = 6f
                )
            )
        )
    }
}

@Preview(showBackground = true)
@Composable fun Preview() { LanceurDesApp() }
