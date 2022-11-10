package com.example.composecamp.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composecamp.R
import com.example.composecamp.data.DDatasource
import com.example.composecamp.model.Affirmation
import com.example.composecamp.ui.theme.ComposecampTheme

@Composable
fun AffirmationAdCreate(navController: NavController) {
    ComposecampTheme {
        AffirmationCADocList(navController,affirmationList = DDatasource().loadAffirmations())
    }

}

@Composable
fun AffirmationCADocList(navController: NavController, affirmationList: List<Affirmation>, modifier: Modifier = Modifier) {
//    LazyColumn {
//        items(affirmationList) { affirmation ->
//            AffirmationDCard(affirmation)
//        }
//    }

    LazyColumn{
        item { AffirmationCADocCard(affirmation= Affirmation(R.string.card3, R.drawable.appointment))
            ExtendedFloatingActionButton(
                onClick = { /* ... */
                    navController.navigate("signup")
                },
                modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
                icon = {
                    Icon(
                        Icons.Filled.KeyboardArrowRight,
                        contentDescription = "Arrow"
                    )
                },
                text = { Text("Create Patient User", textAlign = TextAlign.Center) }
            )
        }
        item { AffirmationCADocCard(affirmation = Affirmation(R.string.card1, R.drawable.header_cancellationreschedule_8c2905cd62b7d598b67aa031435b1994b6d27c79ffe82ec71e67c2df46d12b4b ) )
            ExtendedFloatingActionButton(
                onClick = { /* ... */
                    navController.navigate("DocCrtUsr")
                },
                modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
                icon = {
                    Icon(
                        Icons.Filled.KeyboardArrowRight,
                        contentDescription = "Arrow"
                    )
                },
                text = { Text("Create Doctor User", textAlign = TextAlign.Center) }
            )
        }
//        item { AffirmationCADocCard(affirmation= Affirmation(R.string.card2, R.drawable._2feb_appointment))
//            ExtendedFloatingActionButton(
//                onClick = { /* ... */
////                    navController.navigate("CSE")
//                },
//                modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
//                icon = {
//                    Icon(
//                        Icons.Filled.KeyboardArrowRight,
//                        contentDescription = "Arrow"
//                    )
//                },
//                text = { Text("Send Remainder", textAlign = TextAlign.Center) }
//            )
//        }



    }

}

@Composable
fun AffirmationCADocCard(affirmation: Affirmation, modifier: Modifier = Modifier) {
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



