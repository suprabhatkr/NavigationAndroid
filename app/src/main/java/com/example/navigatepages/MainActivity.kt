package com.example.navigatepages

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import com.example.navigatepages.ui.theme.NavigatePagesTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigatePagesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    StartNavigation(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    )
                }
            }
        }
    }
}

enum class Page {
    ONE,
    TWO,
    THREE
}


@Composable
fun StartNavigation(modifier: Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Page.ONE.name) {
        composable(Page.ONE.name) {
            firstPage(modifier = modifier) {name ->
                navController.navigate(Page.TWO.name + "/$name")
            }
        }
        composable(Page.TWO.name + "/{name}") {
            val name = it.arguments?.getString("name") ?: "no name"
            secondPage(name, modifier = modifier) {copiedName, age ->
                navController.navigate(Page.THREE.name + "/$copiedName/$age")
            }
        }
        composable(Page.THREE.name + "/{name}/{age}") {
            val age : Int = (it.arguments?.getString("age") ?: "0").toInt()
            val name = it.arguments?.getString("name") ?: "no name"
            thirdPage(name, age, modifier = modifier) {
                navController.navigate(Page.ONE.name)
            }
        }
    }
}
