package com.example.composecamp.Screens


import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.composecamp.ui.theme.ComposecampTheme


@Composable
fun VAAffirmationDashboard(navController: NavController,context: Context) {
    ComposecampTheme {
        val db = DBHelper(context, null);
        val list: List<AptmntModelClass> = db.viewAppointmnt()
        AppointmentList(list)
    }

}

@Composable
fun AppointmentList(students: List<AptmntModelClass>) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        students.forEach { student ->
            StudentRow(student)
        }
    }
}

@Composable
fun StudentRow(aptmt: AptmntModelClass) {
    Card(modifier = Modifier
        .padding(all = 10.dp)
        .fillMaxWidth()) {
        Column(modifier = Modifier.padding(all = 10.dp)) {
            Row(){
                Text("Appointment No :", fontSize = 20.sp, fontWeight = FontWeight.W700, modifier = Modifier.padding(10.dp))
                Text(aptmt.aptid, fontSize = 20.sp, fontWeight = FontWeight.W700, modifier = Modifier.padding(10.dp))
            }

            Row() {
                Text(text = "Patient Id : ", color = Color.Gray, modifier = Modifier.padding(10.dp))
                Text(aptmt.userId, color = Color.Gray, modifier = Modifier.padding(10.dp))
            }
            Row() {
                Text(text = "Doctor Name : ", color = Color.Gray, modifier = Modifier.padding(10.dp))
                Text(aptmt.Dname, color = Color.Gray, modifier = Modifier.padding(10.dp))
            }
            Row() {
                Text(
                    text = "Date : ",
                    color = Color.Gray,
                    modifier = Modifier.padding(10.dp)
                )
                Text(aptmt.Date, color = Color.Gray, modifier = Modifier.padding(10.dp))
            }
            Row() {
                Text(text = "Time Slot : ", color = Color.Gray, modifier = Modifier.padding(10.dp))
                Text(aptmt.slot, color = Color.Gray, modifier = Modifier.padding(10.dp))
            }
        }
    }
}


//
//@Composable
//fun VAAffirmationDList(navController: NavController, list: String, modifier: Modifier = Modifier) {
//
//
//        Text(text = list)
//
//
////    LazyColumn{
////        item { AffirmationDCard(affirmation=Affirmation(R.string.card3,R.drawable.appointment))
////            ExtendedFloatingActionButton(
////                onClick = { /* ... */
////
////
////                    navController.navigate("ScheduleAppointment/{$userId}")
////                },
////                modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
////                icon = {
////                    Icon(
////                        Icons.Filled.KeyboardArrowRight,
////                        contentDescription = "Arrow"
////                    )
////                },
////                text = { Text("Schedule Appointment", textAlign = TextAlign.Center) }
////            )}
////        item { AffirmationDCard(affirmation = Affirmation(R.string.card1, R.drawable.header_cancellationreschedule_8c2905cd62b7d598b67aa031435b1994b6d27c79ffe82ec71e67c2df46d12b4b ) )
////            ExtendedFloatingActionButton(
////                onClick = { /* ... */
////                    navController.navigate("ReSchedule")
////                },
////                modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
////                icon = {
////                    Icon(
////                        Icons.Filled.KeyboardArrowRight,
////                        contentDescription = "Arrow"
////                    )
////                },
////                text = { Text("Re-Schedule Appointment", textAlign = TextAlign.Center) }
////            )}
////        item { AffirmationDCard(affirmation=Affirmation(R.string.card2, R.drawable._2feb_appointment))
////            ExtendedFloatingActionButton(
////                onClick = { /* ... */
////                    navController.navigate("CSE")
////                },
////                modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
////                icon = {
////                    Icon(
////                        Icons.Filled.KeyboardArrowRight,
////                        contentDescription = "Arrow"
////                    )
////                },
////                text = { Text("View Availiable Appointments", textAlign = TextAlign.Center) }
////            )}
////
////
////
////    }
//
//}
//
//@Composable
//fun VAAffirmationDCard(affirmation: Affirmation, modifier: Modifier = Modifier) {
//    Card(modifier = Modifier.padding(8.dp), elevation = 4.dp) {
//        Column {
//            Image(
//                painter = painterResource(affirmation.imageResourceId),
//                contentDescription = stringResource(affirmation.stringResourceId),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(194.dp),
//                contentScale = ContentScale.Crop
//            )
//        }
//    }
//}
//
//
//
