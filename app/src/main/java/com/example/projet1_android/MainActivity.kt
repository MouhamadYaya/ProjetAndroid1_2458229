package com.example.projet1_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projet1_android.ui.theme.Projet1_androidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { LanceurDesApp() }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanceurDesApp() {
    Projet1_androidTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Lanceur de dés") }
                )
            }
        ) { inner ->
            Box(Modifier.padding(inner).padding(16.dp)) {
                Text("préparation")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable fun Preview() { LanceurDesApp() }
