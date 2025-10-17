package com.example.projet1_android

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

    val orientation = LocalConfiguration.current.orientation

    val lancer = {
        val tirages = List(nbDes) { Random.nextInt(1, faces + 1) }
        resultats = when (tri) {
            Tri.Aucun -> tirages
            Tri.Croissant -> tirages.sorted()
            Tri.Decroissant -> tirages.sortedDescending()
        }
    }

    if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
        Row(modifier.fillMaxSize(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Column(Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                ConfigurationSection(nbDes, faces, tri,
                    onNbDesChange = { nbDes = it },
                    onFacesChange = { faces = it },
                    onTriChange = { tri = it },
                    onLancer = lancer
                )
            }
            ResultatsSection(resultats, Modifier.weight(1f))
        }
    } else {
        Column(modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            ConfigurationSection(nbDes, faces, tri,
                onNbDesChange = { nbDes = it },
                onFacesChange = { faces = it },
                onTriChange = { tri = it },
                onLancer = lancer
            )
            Spacer(Modifier.height(16.dp))
            ResultatsSection(resultats)
        }
    }
}

@Composable
private fun ConfigurationSection(
    nbDes: Int,
    faces: Int,
    tri: Tri,
    onNbDesChange: (Int) -> Unit,
    onFacesChange: (Int) -> Unit,
    onTriChange: (Tri) -> Unit,
    onLancer: () -> Unit
) {
    Button(onClick = onLancer, modifier = Modifier.fillMaxWidth()) {
        Text("Lancer les dés")
    }
    Spacer(Modifier.height(24.dp))
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Nombre de dés : $nbDes")
        Slider(
            value = nbDes.toFloat(),
            onValueChange = { onNbDesChange(it.toInt().coerceIn(1, 10)) },
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
                    RadioButton(selected = faces == f, onClick = { onFacesChange(f) })
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
                RadioButton(selected = tri == t, onClick = { onTriChange(t) })
                Text(when (t) { Tri.Croissant -> "Croissant"; Tri.Decroissant -> "Décroissant"; Tri.Aucun -> "Aucun" })
            }
        }
    }
}

@Composable
private fun ResultatsSection(resultats: List<Int>, modifier: Modifier = Modifier) {
    Card(modifier.fillMaxWidth(), elevation = CardDefaults.cardElevation(4.dp)) {
        Column(Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            if (resultats.isEmpty()) Text("Lancez les dés pour voir les résultats")
            else {
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

@Composable
fun DeView(valeur: Int) {
    Box(Modifier.size(86.dp), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.de_background),
            contentDescription = "Dé",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.28f)))
        Text(
            text = valeur.toString(),
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
                shadow = Shadow(color = Color.Black.copy(alpha = 0.9f), blurRadius = 8f)
            )
        )
    }
}

@Preview(showBackground = true)
@Composable fun Preview() { LanceurDesApp() }
