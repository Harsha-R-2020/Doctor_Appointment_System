package com.example.composecamp.Screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composecamp.R
import com.example.composecamp.model.Affirmation
import com.example.composecamp.ui.theme.ComposecampTheme


@Composable
fun AffirmationDashboard(navController: NavController, usrId: String) {
    ComposecampTheme {
        Scaffold(
            topBar = {
                TopAppBar { Text("Doctor Appointment System",fontWeight = FontWeight.SemiBold,color = MaterialTheme.colors.onSurface) }
            }
        ) {
            AffirmationDList(navController, usrId)
        }
    }

}

@Composable
fun AffirmationDList(navController: NavController, userId: String, modifier: Modifier = Modifier) {
//    LazyColumn {
//        items(affirmationList) { affirmation ->
//            AffirmationDCard(affirmation)
//        }
//    }

    LazyColumn{
        item { AffirmationDCard(affirmation=Affirmation(R.string.card3,R.drawable.appointment))
            ExtendedFloatingActionButton(
                onClick = { /* ... */


                    navController.navigate("ScheduleAppointment/{$userId}")
                },
                modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
                icon = {
                    Icon(
                        Icons.Filled.KeyboardArrowRight,
                        contentDescription = "Arrow"
                    )
                },
                text = { Text("Schedule Appointment", textAlign = TextAlign.Center) }
            )}
        item { AffirmationDCard(affirmation = Affirmation(R.string.card1, R.drawable.header_cancellationreschedule_8c2905cd62b7d598b67aa031435b1994b6d27c79ffe82ec71e67c2df46d12b4b ) )
            ExtendedFloatingActionButton(
                onClick = { /* ... */

                    navController.navigate("ReSchedule/{$userId}")
                },
                modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
                icon = {
                    Icon(
                        Icons.Filled.KeyboardArrowRight,
                        contentDescription = "Arrow"
                    )
                },
                text = { Text("Re-Schedule Appointment", textAlign = TextAlign.Center) }
            )}
        item { AffirmationDCard(affirmation=Affirmation(R.string.card2, R.drawable._2feb_appointment))
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
                text = { Text("View Availiable Appointments", textAlign = TextAlign.Center) }
            )}



    }

}

@Composable
fun AffirmationDCard(affirmation: Affirmation, modifier: Modifier = Modifier) {
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



