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
                if (resultats.isEmpty()) Text("Lancez les dés pour voir les résultats")
                else {
                    Text("Résultats : ${resultats.joinToString(", ")}")
                    Text("Somme : ${resultats.sum()}")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable fun Preview() { LanceurDesApp() }
