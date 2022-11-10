package com.example.composecamp.Screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
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
fun AdminCreateusr(navController: NavController, context: Context) {

    var usr_name by remember {
        mutableStateOf("")
    }
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
    Box(modifier = Modifier.background(transparentGradientBrush)){
        Image(
            painter = painterResource(id = R.drawable.login_img),
            contentDescription = "RIT",
            modifier = Modifier.fillMaxHeight().fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.fillMaxSize(),

            verticalArrangement = Arrangement.Center) {

            Text(
                text = "Create User",
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
                value = usr_name, onValueChange = { usr_name = it },
                label = {
                    Text(
                        "Enter you User Name", color = MaterialTheme.colors.onSurface,
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
                modifier = Modifier.padding(30.dp),

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
                modifier = Modifier.padding(30.dp)
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
                modifier = Modifier.padding(30.dp),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            Row(horizontalArrangement = Arrangement.Center) {
                ExtendedFloatingActionButton(
                    onClick = { /* ... */
                        val db = DBHelper2(context, null);
                        Toast.makeText(context, "Database Connected", Toast.LENGTH_LONG).show()
                        db.addUser(User_id, usr_name, Passwd);

                        // at last, clearing edit texts
                        usr_name = ""
                        User_id = ""
                        Passwd = ""
                        Toast.makeText(context, usr_name + " added to database", Toast.LENGTH_LONG)
                            .show();

                    },
                    modifier = Modifier.padding(20.dp),
                    icon = {
                        Icon(
                            Icons.Filled.Person,
                            contentDescription = "person"
                        )
                    },
                    text = { Text("Create User", textAlign = TextAlign.Center) }
                )
                ExtendedFloatingActionButton(
                    onClick = { /* ... */
                        navController.navigate("AdminLogin")
                    },
                    modifier = Modifier.padding( 20.dp),
                    icon = {
                        Icon(
                            Icons.Filled.Person,
                            contentDescription = "person"
                        )
                    },
                    text = { Text("Login", textAlign = TextAlign.Center) }
                )


            }
        }
    }


}

