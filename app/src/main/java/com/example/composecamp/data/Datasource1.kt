package com.example.composecamp.data


import com.example.composecamp.R
import com.example.composecamp.model.Affirmation

class Datasource1 {
    fun loadAffirmations(): List<Affirmation> {
        return listOf<Affirmation>(
            Affirmation(R.string.affirmation4, R.drawable.civil3),
            Affirmation(R.string.affirmation5, R.drawable.civil5),
            Affirmation(R.string.affirmation6, R.drawable.civil7)

        )
    }
}