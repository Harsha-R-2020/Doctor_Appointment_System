package com.example.composecamp.Screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.composecamp.R

@Composable
fun DocLogin(navController: NavController, context: Context) {
    var User_id by remember {
        mutableStateOf("")
    }
    var Passwd by remember {
        mutableStateOf("")
    }
    val transparentGradientBrush = Brush.linearGradient(
        colors = listOf(
            Color(0x66FFFFFF),
            Color(0x1AFFFFFF)
        )
    )
    Image(
        painter = painterResource(id = R.drawable.loginbanner),
        contentDescription = "RIT",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
    Box(modifier = Modifier.background(transparentGradientBrush)) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = " Login",
                fontFamily = FontFamily.Cursive,
                textAlign = TextAlign.Center,

                fontSize = 65.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
            )

            OutlinedTextField(
                value = User_id, onValueChange = { User_id = it },
                label = {
                    Text(
                        "Enter you User Id", color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Green,
                    unfocusedBorderColor = Color.White
                ),
                leadingIcon = {
                    Icon(Icons.Default.Person, contentDescription = "person")
                },
                modifier = Modifier.background(color = Color.Transparent).padding(40.dp),

                )
            OutlinedTextField(
                value = Passwd, onValueChange = { Passwd = it },
                label = {
                    Text(
                        "Enter you Password", color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Green,
                    unfocusedBorderColor = Color.White
                ),
                leadingIcon = {
                    Icon(Icons.Default.Info, contentDescription = "Password")
                },
                modifier = Modifier.padding(40.dp),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            Row(horizontalArrangement = Arrangement.Center) {
                ExtendedFloatingActionButton(
                    onClick = { /* ... */
                        val db = DBHelper1(context, null);
                        Toast.makeText(context, "Database Connected", Toast.LENGTH_LONG).show()


                        val rslt = db.checkUser(User_id, Passwd);

                        // at last, clearing edit texts
                        if (rslt) {
                            Toast.makeText(context, "Login SuccessFull ", Toast.LENGTH_LONG).show();
                            navController.navigate("DocDashboard")
                        } else {
                            Toast.makeText(context, "Login Failed ", Toast.LENGTH_LONG).show();
                        }

                    },
                    modifier = Modifier.padding(30.dp),
                    icon = {
                        Icon(
                            Icons.Filled.Done,
                            contentDescription = "Login"
                        )
                    },
                    text = { Text("Login", textAlign = TextAlign.Center) }

                )
//                ExtendedFloatingActionButton(
//                    onClick = { /* ... */
//                        navController.navigate("DocCrtUsr")
//                    },
//                    modifier = Modifier.padding(
//                        30.dp
//                    ),
//                    icon = {
//                        Icon(
//                            Icons.Filled.Person,
//                            contentDescription = "person"
//                        )
//                    },
//                    text = { Text("Sign Up", textAlign = TextAlign.Center) }
//                )
            }
        }
    }
}