package com.example.composecamp.data

import com.example.composecamp.R
import com.example.composecamp.model.Affirmation


class Depdata() {
    fun loadAffirmations1(): List<Affirmation> {
        return listOf<Affirmation>(
            Affirmation(R.string.card4, R.drawable.cse),
            Affirmation(R.string.card5, R.drawable.dece),
            Affirmation(R.string.card6,R.drawable.eee))
    }
}