package com.example.composecamp.Screens


import android.content.Context
import android.os.Build
import android.telephony.SmsManager
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.composecamp.ui.theme.ComposecampTheme


@Composable
fun CAAffirmationDashboard(navController: NavController,context: Context) {
    ComposecampTheme {
        val db = DBHelper(context, null);
        val list: List<AptmntModelClass> = db.viewAppointmnt()
        CAAppointmentList(list)
    }

}

@Composable
fun CAAppointmentList(students: List<AptmntModelClass>) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        students.forEach { student ->
            CStudentRow(student)
        }
    }
}

@Composable
fun CStudentRow(aptmt: AptmntModelClass) {

    val mContext = LocalContext.current
    val db = DBHelper(mContext, null);
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
            ExtendedFloatingActionButton(
                onClick = { /* ... */
                    db.delete_usr(aptmt.userId)
//                    var aptid = id+mSelectedText1.replace("[^0-9]".toRegex(), "")
//                    db.AddAptmnt(aptid,id, mDate.value, mSelectedText1, mSelectedText)
//                    openDialog.value = true
//                    val phoneNumber = email
//                    var d = mDate.value
                    val email = db.getEmail(aptmt.userId)
                    val name = db.getName1(aptmt.userId)
                    val message = "Hi ${name} your Doctor Appointment with DR.${aptmt.Dname} has been CANCELED by the Doctor/Admin "
                    val phoneNumber = email
                    // on the below line we are creating a try and catch block
                    try {

                        // on below line we are initializing sms manager.
                        //as after android 10 the getDefault function no longer works
                        //so we have to check that if our android version is greater
                        //than or equal toandroid version 6.0 i.e SDK 23
                        val smsManager: SmsManager
                        if (Build.VERSION.SDK_INT>=23) {
                            //if SDK is greater that or equal to 23 then
                            //this is how we will initialize the SmsManager
                            smsManager = mContext.getSystemService(SmsManager::class.java)
                        }
                        else{
                            //if user's SDK is less than 23 then
                            //SmsManager will be initialized like this
                            smsManager = SmsManager.getDefault()
                        }

                        // on below line we are sending text message.
                        smsManager.sendTextMessage(phoneNumber, null, message, null, null)

                        // on below line we are displaying a toast message for message send,
                        Toast.makeText(mContext, "Message Sent", Toast.LENGTH_LONG).show()

                    } catch (e: Exception) {

                        // on catch block we are displaying toast message for error.
                        Toast.makeText(mContext, ""+e.message.toString(), Toast.LENGTH_LONG)
                            .show()
                    }
                },
                modifier = Modifier.padding(15.dp),
                icon = {
                    Icon(
                        Icons.Filled.DateRange,
                        contentDescription = "person"
                    )
                },
                text = { Text("Cancel", textAlign = TextAlign.Center) }
            )
        }
    }
}
