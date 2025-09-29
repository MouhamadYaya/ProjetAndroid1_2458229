package com.example.projet1_android
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projet1_android.ui.theme.Projet1_androidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Projet1_androidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MonEcranPrincipal(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MonEcranPrincipal(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Lanceur de Dés")

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { Log.d("UI", "Bouton Lancer cliqué") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Lancer les dés")
        }

        Spacer(modifier = Modifier.height(20.dp))


        ChoixDes()

        Spacer(modifier = Modifier.height(20.dp))

        ChoixFaces()

        Spacer(modifier = Modifier.height(20.dp))

        ChoixTri()

        Spacer(modifier = Modifier.height(20.dp))

        ZoneResultats()
    }
}

@Composable
fun ChoixDes() {
    var nbDes by remember { mutableStateOf(3f) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Dés : $nbDes")
        Slider(
            value = nbDes,
            onValueChange = { nbDes = it
                Log.d("Affichage", "Nombre de dés choisi : $nbDes") },
            valueRange = 1f..6f
        )
    }
}

@Composable
fun ChoixFaces() {
    var faces by remember { mutableStateOf("6") }
    val maListe = listOf("4", "6", "8", "10", "12", "20")

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Faces : $faces")
        Row {
            for (i in maListe) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    RadioButton(
                        selected = faces == i,
                        onClick = { faces = i
                            Log.d("Affichage", "Nombre de faces choisi : $i")
                        }
                    )
                    Text(i)
                }
            }
        }
    }
}

@Composable
fun ChoixTri() {
    var tri by remember { mutableStateOf("aucun") }
    val maListe = listOf("Croissant", "Décroissant","aucun")

    Column {
        Text("Tri : $tri")
        for (i in maListe) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = tri == i,
                    onClick = { tri = i
                        Log.d("Affichage", "Tri choisi : $i")
                    }
                )
                Text(i)
            }
        }
    }
}

@Composable
fun ZoneResultats() {
    Card(
        shape = RoundedCornerShape(19.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Text("Résultats : 2, 5, 2")
            Text("Somme : 9")
            Log.d("Affichage", "ZoneResultats affichée")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEcran() {
    Projet1_androidTheme {
        MonEcranPrincipal()
    }
}
