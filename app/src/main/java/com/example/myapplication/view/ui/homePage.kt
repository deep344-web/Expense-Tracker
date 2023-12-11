package com.example.myapplication.view.ui

import android.util.Log
import android.view.MenuItem
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myapplication.R
import com.example.myapplication.view.model.Screen
import com.example.myapplication.view.model.SpendModel
import com.example.myapplication.view.model.Type
import com.example.myapplication.view.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date
import java.util.TimeZone

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun homePage(viewModel: MainViewModel = hiltViewModel(),
             onLogOutClick: () -> Unit,
             navController: NavController,
             items : List<MenuItemModel>) {

    val list = viewModel.viewState.collectAsState().value
    var drawerState = rememberDrawerState(
        DrawerValue.Closed
    )

    var selectedItemIndx by rememberSaveable {
        mutableIntStateOf(0)
    }
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                DrawerHeader()
                items.forEachIndexed{ index, item ->
                    NavigationDrawerItem(
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.contentDesc
                            )
                        },
                        label = {
                            Text(text = item.label)
                        },
                        selected = index == selectedItemIndx,
                        onClick = {
                            selectedItemIndx = index
                            coroutineScope.launch {
                                drawerState.close()
                            }
                        })
                }
            }
        },
        drawerState = drawerState) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    ),
                    title = {
                        Text(
                            "Expense Tracker",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Localized description"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            onLogOutClick()
                        }) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.icon_logout),
                                contentDescription = "Localized description"
                            )
                        }
                    },
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        navController.navigate(Screen.AddEntryScreen.route)
                    },
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
            }
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Column(
                    modifier = Modifier.padding(all = 16.dp)
                ) {
                    Row {
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surface,
                            ),
                            modifier = Modifier.weight(1f)
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = "Income",
                                    textAlign = TextAlign.Center,
                                )
                                Text(
                                    text = "+20",
                                    color = Color.Blue,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center,
                                )
                            }
                        }
                        Spacer(
                            modifier = Modifier
                                .height(intrinsicSize = IntrinsicSize.Max)
                                .width(16.dp)
                        )
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surface,
                            ),
                            modifier = Modifier.weight(1f)
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = "Expense",
                                    textAlign = TextAlign.Center,
                                )
                                Text(
                                    text = "+20",
                                    color = Color.Red,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center,
                                )
                            }
                        }
                    }
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(16.dp)
                    )
                    LazyColumn {
                        items(list.size) {
                            listItemView(spendModel = list[it])
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun listItemView(spendModel : SpendModel){
    val offset: Int = TimeZone.getDefault().rawOffset + TimeZone.getDefault().dstSavings
    val calendar = Calendar.getInstance()
    calendar.time = Date(spendModel.timestamp - offset)
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
        ){
            Row(
                modifier = Modifier.padding(bottom = 2.dp)
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = spendModel.name,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                )
                Text(
                    text = if(spendModel.type == Type.EXPENSE.value) "- ${spendModel.amount}" else "+ ${spendModel.amount}",
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = if(spendModel.type == Type.EXPENSE.value) Color.Red else Color.Blue
                )
            }
            Text(
                text = calendar.get(Calendar.DAY_OF_MONTH).toString() + "/" +
                        calendar.get(Calendar.MONTH).toString() + "/" +
                        calendar.get(Calendar.YEAR).toString(),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
            )
        }
    }
}
@Preview
@Composable
fun homePagePreview() {
//    homePage()
}