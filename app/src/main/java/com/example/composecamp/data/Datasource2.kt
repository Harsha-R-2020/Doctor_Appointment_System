package com.example.composecamp.data


import com.example.composecamp.R
import com.example.composecamp.model.Affirmation

class Datasource2 {
    fun loadAffirmations(): List<Affirmation> {
        return listOf<Affirmation>(
            Affirmation(R.string.affirmation7, R.drawable.ecea5),
            Affirmation(R.string.affirmation8, R.drawable.ecea7),
            Affirmation(R.string.affirmation9, R.drawable.eceb5),
            Affirmation(R.string.affirmation10, R.drawable.eceb7)

        )
    }
}