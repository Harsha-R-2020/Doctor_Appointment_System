package com.example.composecamp.Screens

import android.app.DatePickerDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.telephony.SmsManager
import android.telephony.SubscriptionInfo
import android.telephony.SubscriptionManager
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.NavController
import com.example.composecamp.MainActivity

import com.example.composecamp.R
import java.util.*


fun createNotificationChannel(channelId: String, context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = "MyTestChannel"
        val descriptionText = "My important test channel"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelId, name, importance).apply {
            description = descriptionText
        }

        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}
fun showSimpleNotification(
    context: Context,
    channelId: String,
    notificationId: Int,
    textTitle: String,
    textContent: String,
    priority: Int = NotificationCompat.PRIORITY_DEFAULT
) {
    val builder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.notification_bell)
        .setContentTitle(textTitle)
        .setContentText(textContent)
        .setPriority(priority)

    with(NotificationManagerCompat.from(context)) {
        notify(notificationId, builder.build())
    }
}
@RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
@Composable
fun ScheduleApt(navController: NavController, usrid: String) {

    val mContext = LocalContext.current

// Declaring integer values
// for year, month and day
    val mYear: Int
    val mMonth: Int
    val mDay: Int

// Initializing a Calendar
    val mCalendar = Calendar.getInstance()

// Fetching current year, month and day
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

// Declaring a string value to
// store date in string format
    val mDate = remember { mutableStateOf("") }

// Declaring DatePickerDialog and setting
// initial values as current values (present year, month and day)
    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate.value = "$mDayOfMonth/${mMonth + 1}/$mYear"
        }, mYear, mMonth, mDay
    )
    var P_name by remember {
        mutableStateOf("")
    }
    var P_id by remember {
        mutableStateOf("")
    }
    var mExpanded by remember { mutableStateOf(false) }
    var mExpanded1 by remember { mutableStateOf(false) }
    var mExpanded2 by remember { mutableStateOf(false) }
    // Create a list of cities
    val mCities1 = listOf(
        "10.00AM to 10.30 AM",
        "11.00AM to 11.30AM",
        "1.00 PM to 1.30 PM",
        "3.00 PM to 3.30 PM"
    )

    val doctors = listOf("Andrew", "Smith", "Steve", "Robert")
    val specialization = listOf("Ornithologist", "ENT", "Neurologist", "Cardiologist")
    // Create a string value to store the selected city
    var mSelectedText by remember { mutableStateOf("") }

    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }

    var mSelectedText1 by remember { mutableStateOf("") }

    var mTextFieldSize1 by remember { mutableStateOf(Size.Zero) }
    var mSelectedText2 by remember { mutableStateOf("") }

    var mTextFieldSize2 by remember { mutableStateOf(Size.Zero) }

    // Up Icon when expanded and down icon when collapsed
    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown
    val icon1 = if (mExpanded1)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown
    val db = DBHelper(mContext, null);
    val id = usrid.replace("[^0-9]".toRegex(), "")
    val name = db.getName1(id)
    val email = db.getEmail(id)
    val openDialog = remember { mutableStateOf(false) }
    val context = LocalContext.current
    val channelId = "MyTestChannel"
    val notificationId = 0

    if (openDialog.value) {
        createNotificationChannel(channelId, context)
        showSimpleNotification(
            context,
            channelId,
            notificationId,
            "Appointment Scheduled",
            "Dear $name your Appointment has been successfully Scheduled"
        )
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = "Appointment Scheduled")
            },
            text = {
                Text(
                    "Your Appointment has been scheduled Successfully.Confirmation sent to $email mobile number."
                )
            },
            buttons = {
                Row(
                    modifier = Modifier
                        .padding(all = 8.dp)
                        .fillMaxWidth(),
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
                        modifier = Modifier.padding(20.dp),
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
        Box() {
            CircularProgressIndicator(
                modifier = Modifier.size(size = 64.dp),
                color = MaterialTheme.colors.primary,
                strokeWidth = 6.dp
            )
        }

    }
    Scaffold(
        topBar = {
            TopAppBar {
                Text(
                    "Schedule Appointment",
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colors.onSurface
                )
            }
        }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

//        OutlinedTextField(value = P_id, onValueChange ={P_id = it},
//            label = { Text("Enter you Patient Id",color = MaterialTheme.colors.onSurface,
//                fontWeight = FontWeight.SemiBold) },
//            colors = TextFieldDefaults.outlinedTextFieldColors(
//                focusedBorderColor = Color.Green,
//                unfocusedBorderColor = Color.White
//            ),
//            leadingIcon = {
//                Icon(Icons.Default.Person,contentDescription = "person")
//            },
//            modifier = Modifier.background(color = Color.Transparent).padding(20.dp),
//
//            )

            Column() {

                Text(
                    text = "Name : $name ",
//                fontFamily = FontFamily.Cursive,
                    textAlign = TextAlign.Center,

                    fontSize = 15.sp,

                    color = MaterialTheme.colors.onSurface,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp)
                )


            }

//        OutlinedTextField(value = P_name, onValueChange ={P_name = it},
//            label = { Text("Enter Patient Name",color = MaterialTheme.colors.onSurface,
//                fontWeight = FontWeight.SemiBold) },
//            colors = TextFieldDefaults.outlinedTextFieldColors(
//                focusedBorderColor = Color.Green,
//                unfocusedBorderColor = Color.White
//            ),
//            leadingIcon = {
//                Icon(Icons.Default.Info,contentDescription = "Password")
//            },
//            modifier = Modifier.padding( 20.dp)
//        )
            // Creating a button that on
            // click displays/shows the DatePickerDialog
            Row() {
                Text(text = "Choose Date", textAlign = TextAlign.Left)
                ExtendedFloatingActionButton(
                    onClick = {
                        mDatePickerDialog.show()
                    },
                    modifier = Modifier.padding(15.dp),
                    text = { Text(text = "Open Date Picker", color = Color.White) })
            }


            // Adding a space of 100dp height

            // Displaying the mDate value in the Text
            Text(
                text = "Selected Date: ${mDate.value}",
                fontSize = 5.sp,
                textAlign = TextAlign.Center
            )
            OutlinedTextField(
                value = mSelectedText2,
                onValueChange = { mSelectedText2 = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        // This value is used to assign to
                        // the DropDown the same width
                        mTextFieldSize2 = coordinates.size.toSize()
                    },
                label = { Text("Choose Specialization") },
                trailingIcon = {
                    Icon(icon1, "contentDescription",
                        Modifier.clickable { mExpanded2 = !mExpanded2 })
                }
            )
            DropdownMenu(
                expanded = mExpanded2,
                onDismissRequest = { mExpanded2 = false },
                modifier = Modifier
                    .width(with(LocalDensity.current) { mTextFieldSize2.width.toDp() })
            ) {
                specialization.forEach { label ->
                    DropdownMenuItem(onClick = {
                        mSelectedText2 = label
                        mExpanded2 = false
                    }) {
                        Text(text = label)
                    }
                }
            }
            OutlinedTextField(
                value = mSelectedText,
                onValueChange = { mSelectedText = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        // This value is used to assign to
                        // the DropDown the same width
                        mTextFieldSize = coordinates.size.toSize()
                    },
                label = { Text("Choose Doctor") },
                trailingIcon = {
                    Icon(icon1, "contentDescription",
                        Modifier.clickable { mExpanded1 = !mExpanded1 })
                }
            )

            // Create a drop-down menu with list of cities,
            // when clicked, set the Text Field text as the city selected
            DropdownMenu(
                expanded = mExpanded1,
                onDismissRequest = { mExpanded1 = false },
                modifier = Modifier
                    .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
            ) {
                doctors.forEach { label ->
                    DropdownMenuItem(onClick = {
                        mSelectedText = label
                        mExpanded1 = false
                    }) {
                        Text(text = label)
                    }
                }
            }
            OutlinedTextField(
                value = mSelectedText1,
                onValueChange = { mSelectedText1 = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        // This value is used to assign to
                        // the DropDown the same width
                        mTextFieldSize1 = coordinates.size.toSize()
                    },
                label = { Text("Choose Time Slot") },
                trailingIcon = {
                    Icon(icon, "contentDescription",
                        Modifier.clickable { mExpanded = !mExpanded })
                }
            )

            // Create a drop-down menu with list of cities,
            // when clicked, set the Text Field text as the city selected
            DropdownMenu(
                expanded = mExpanded,
                onDismissRequest = { mExpanded = false },
                modifier = Modifier
                    .width(with(LocalDensity.current) { mTextFieldSize1.width.toDp() })
            ) {
                mCities1.forEach { label ->
                    DropdownMenuItem(onClick = {
                        mSelectedText1 = label
                        mExpanded = false
                    }) {
                        Text(text = label)
                    }
                }
            }
            ExtendedFloatingActionButton(
                onClick = { /* ... */
                    var aptid = id+mSelectedText1.replace("[^0-9]".toRegex(), "")
                    db.AddAptmnt(aptid,id, mDate.value, mSelectedText1, mSelectedText)
                    openDialog.value = true
                    val phoneNumber = email
                    var d = mDate.value
                    val message = "Hi $name your Doctor Appointment with DR.$mSelectedText has been successfully scheduled on $d at time slot $mSelectedText1 "

                    // on the below line we are creating a try and catch block
                    try {

                        // on below line we are initializing sms manager.
                        //as after android 10 the getDefault function no longer works
                        //so we have to check that if our android version is greater
                        //than or equal toandroid version 6.0 i.e SDK 23
                        val smsManager:SmsManager
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
                modifier = Modifier.padding(20.dp),
                icon = {
                    Icon(
                        Icons.Filled.DateRange,
                        contentDescription = "person"
                    )
                },
                text = { Text("Schedule", textAlign = TextAlign.Center) }
            )
        }
    }
}