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

    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = {  }, modifier = Modifier.fillMaxWidth()) { // y'a la logique du bouton a faire
            Text("Lancer les dés")
        }
        Spacer(Modifier.height(24.dp))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Nombre de dés : $nbDes")
            Slider(
                value = nbDes.toFloat(),
                onValueChange = { nbDes = it.toInt().coerceIn(1, 10) },
                valueRange = 1f..10f,
                steps = 8
            )
        }
        Spacer(Modifier.height(16.dp))

        val options = listOf(4, 6, 8, 10, 12, 20)
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Nombre de faces : d$faces")
            Row {
                options.forEach { f ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        RadioButton(selected = faces == f, onClick = { faces = f })
                        Text("d$f")
                    }
                }
            }
        }
        Spacer(Modifier.height(16.dp))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Tri des résultats")
            listOf(Tri.Croissant, Tri.Decroissant, Tri.Aucun).forEach { t ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(selected = tri == t, onClick = { tri = t })
                    Text(
                        when (t) { Tri.Croissant -> "Croissant"; Tri.Decroissant -> "Décroissant"; Tri.Aucun -> "Aucun" }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable fun Preview() { LanceurDesApp() }
