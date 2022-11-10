package com.example.composecamp.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composecamp.R
import com.example.composecamp.data.DDatasource
import com.example.composecamp.model.Affirmation
import com.example.composecamp.ui.theme.ComposecampTheme


@Composable
fun AffirmationAdDashboard(navController: NavController) {
    ComposecampTheme {
        Scaffold(
            topBar = {
                TopAppBar {
                    Text(
                        "Admin Dashboard",
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colors.onSurface
                    )
                }
            }
        ) {
            AffirmationADocList(navController, affirmationList = DDatasource().loadAffirmations())
        }
    }

}

@Composable
fun AffirmationADocList(navController: NavController, affirmationList: List<Affirmation>, modifier: Modifier = Modifier) {
//    LazyColumn {
//        items(affirmationList) { affirmation ->
//            AffirmationDCard(affirmation)
//        }
//    }
    val openDialog = remember { mutableStateOf(false) }
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = "Remainder Sent")
            },
            text = {
                Text(
                    "The Appointments has been Successfully Reminded."
                )
            },
            buttons = {
                Row(
                    modifier = Modifier.padding(all = 8.dp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
//                    Button(
//                        onClick = {  }
//                    ) {
//                        Text("Dismiss")
//                    }
                    ExtendedFloatingActionButton(
                        onClick = { /* ... */
                            openDialog.value = false
                        },
                        modifier = Modifier.padding( 20.dp),
                        icon = {
                            Icon(
                                Icons.Filled.Done,
                                contentDescription = "Done"
                            )
                        },
                        text = { Text("Dismiss", textAlign = TextAlign.Center) }
                    )
                }
            }
        )
        Box(){
            CircularProgressIndicator(
                modifier = Modifier.size(size = 64.dp),
                color = MaterialTheme.colors.primary,
                strokeWidth = 6.dp
            )
        }

    }

    LazyColumn{
        item { AffirmationADocCard(affirmation= Affirmation(R.string.card3, R.drawable.appointment))
            ExtendedFloatingActionButton(
                onClick = { /* ... */
                    navController.navigate("AdminCreateUser")
                },
                modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
                icon = {
                    Icon(
                        Icons.Filled.KeyboardArrowRight,
                        contentDescription = "Arrow"
                    )
                },
                text = { Text("Create User", textAlign = TextAlign.Center) }
            )
        }
        item { AffirmationADocCard(affirmation = Affirmation(R.string.card1, R.drawable.header_cancellationreschedule_8c2905cd62b7d598b67aa031435b1994b6d27c79ffe82ec71e67c2df46d12b4b ) )
            ExtendedFloatingActionButton(
                onClick = { /* ... */
                    navController.navigate("ViewAppointmnt")
                },
                modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
                icon = {
                    Icon(
                        Icons.Filled.KeyboardArrowRight,
                        contentDescription = "Arrow"
                    )
                },
                text = { Text("View Appointments", textAlign = TextAlign.Center) }
            )
        }
        item { AffirmationADocCard(affirmation= Affirmation(R.string.card2, R.drawable._2feb_appointment))
            ExtendedFloatingActionButton(
                onClick = { /* ... */
//                    navController.navigate("CSE")
                    openDialog.value = true
                },
                modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
                icon = {
                    Icon(
                        Icons.Filled.KeyboardArrowRight,
                        contentDescription = "Arrow"
                    )
                },
                text = { Text("Send Remainder", textAlign = TextAlign.Center) }
            )
        }



    }

}

@Composable
fun AffirmationADocCard(affirmation: Affirmation, modifier: Modifier = Modifier) {
    Card(modifier = Modifier.padding(8.dp), elevation = 4.dp) {
        Column {
            Image(
                painter = painterResource(affirmation.imageResourceId),
                contentDescription = stringResource(affirmation.stringResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
}



