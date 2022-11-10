package com.example.composecamp.data

import com.example.composecamp.R
import com.example.composecamp.model.Affirmation


class Datasource3 {
    fun loadAffirmations(): List<Affirmation> {
        return listOf<Affirmation>(
            Affirmation(R.string.affirmation11, R.drawable.acadamic1),
            Affirmation(R.string.affirmation12, R.drawable.acadamic2)

        )
    }
}