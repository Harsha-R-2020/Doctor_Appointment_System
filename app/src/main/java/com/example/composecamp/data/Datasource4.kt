package com.example.composecamp.data


import com.example.composecamp.R
import com.example.composecamp.model.Affirmation

class Datasource4 {
    fun loadAffirmations(): List<Affirmation> {
        return listOf<Affirmation>(
            Affirmation(R.string.affirmation13, R.drawable.cs),
            Affirmation(R.string.affirmation14, R.drawable.ece),
            Affirmation(R.string.affirmation15, R.drawable.civil)


        )
    }
}