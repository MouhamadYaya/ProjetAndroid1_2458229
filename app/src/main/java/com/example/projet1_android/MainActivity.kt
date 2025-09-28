package com.example.projet1_android
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
            .fillMaxSize()
            .padding(16.dp),
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


        ChoixNombreDes()

        Spacer(modifier = Modifier.height(20.dp))

        ChoixNombreFaces()

        Spacer(modifier = Modifier.height(20.dp))

        ChoixTri()

        Spacer(modifier = Modifier.height(20.dp))

        ZoneResultats()
    }
}

@Composable
fun ChoixNombreDes() {
    val nbDes = 3f
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Nombre de dés : ${nbDes.toInt()}")
        Slider(
            value = nbDes,
            onValueChange = { Log.d("Affichage", "Slider déplacé (valeur fixe : ${nbDes.toInt()})") },
            valueRange = 1f..6f,
            enabled = false
        )
    }
}

@Composable
fun ChoixNombreFaces() {
    val faces = "d6"
    val options = listOf("d4", "d6", "d8", "d10", "d12", "d20")

    Column {
        Text("Nombre de faces : $faces")
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            options.forEach { option ->
                RadioButton(
                    selected = (faces == option),
                    onClick = { Log.d("Affichage", "Option faces cliquée : $option") },
                    enabled = false
                )
                Text(option, modifier = Modifier.align(Alignment.CenterVertically))
            }
        }
    }
}

@Composable
fun ChoixTri() {
    val tri = "Aucun"
    val options = listOf("Aucun", "Croissant", "Décroissant")

    Column {
        Text("Option de tri : $tri")
        options.forEach { option ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (tri == option),
                        onClick = { Log.d("Affichage", "Option tri cliquée : $option ") },
                        enabled = false
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (tri == option),
                    onClick = null,
                    enabled = false
                )
                Text(text = option)
            }
        }
    }
}

@Composable
fun ZoneResultats() {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Résultats : 3, 5, 2")
            Text("Somme : 10")
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
