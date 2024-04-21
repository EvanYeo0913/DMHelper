package com.evanyeomans.dmhelper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.evanyeomans.dmhelper.data.ViewModelInterface
import com.evanyeomans.dmhelper.data.ViewModelRoom
import com.evanyeomans.dmhelper.models.ScreenInfo
import com.evanyeomans.dmhelper.screens.AddItemScreen
import com.evanyeomans.dmhelper.screens.HomeScreen
import com.evanyeomans.dmhelper.screens.RandomItemScreen
import com.evanyeomans.dmhelper.screens.ViewAllScreen
import com.evanyeomans.dmhelper.ui.theme.DMHelperTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DMHelperTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainApp()

                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainApp(){
    val navController = rememberNavController()
    val context = LocalContext.current
    val viewModel: ViewModelRoom = remember{ViewModelRoom(context)}

    val screens = listOf(
        ScreenInfo(
            "Home",
            "Home",
            Icons.Default.Home,
            composable = {HomeScreen(navController)}
        ),
        ScreenInfo(
            "View All",
            "View All",
            Icons.Default.Menu,
            composable = {ViewAllScreen(navController, viewModel)}
        ),
        ScreenInfo(
            "Random",
            "Random Item",
            Icons.Default.Refresh,
            composable = { RandomItemScreen(navController)}
        ),
        ScreenInfo(
            "Search",
            "Search Item",
            Icons.Default.Search,
            composable ={}
        ),
        ScreenInfo(
            "Add Item",
            "Add Item",
            Icons.Default.Add,
            composable = { AddItemScreen(navController, viewModel)}
        )
    )
    Scaffold(
        bottomBar = { BottomBar(navController, screens)},
        modifier = Modifier.padding(bottom=10.dp)
    ){
        innerPadding -> Column(modifier = Modifier.padding(innerPadding)){
            NavigationGraph(navController, screens)
    }// end column
    }

}

@Composable
fun BottomBar(navController: NavHostController, screens: List<ScreenInfo>) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    NavigationBar() {
        screens.forEach { screen ->
            //navigation bar item is composable for each in bottom bar
            NavigationBarItem(
                label = { Text(text = screen.title) },
                icon = {
                    Icon(
                        imageVector = screen.navIcon,
                        contentDescription = "Navigation Icon"
                    )
                },
                selected = currentDestination?.hierarchy?.any {
                    it.route == screen.routeName
                } == true, // if destination in nav hierarchy is this destination, return type
                onClick = {
                    navController.navigate(screen.routeName){
                        popUpTo(navController.graph.findStartDestination().id)

                        launchSingleTop = true
                    } // navigate
                } // onClick
            ) // nav bar item
        } // for each
    } // nav bar
} // bottom bar

@Composable
fun NavigationGraph(navController: NavHostController, screens: List<ScreenInfo>) {
    // collection of composable destinations
    NavHost(
        navController = navController,
        startDestination = screens[0].routeName
    ){
        screens.forEach(){screen ->
            composable(route = screen.routeName){screen.composable()}
        }
    }

}


