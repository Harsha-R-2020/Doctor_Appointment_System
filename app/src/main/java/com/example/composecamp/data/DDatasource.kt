package com.example.composecamp.data

import com.example.composecamp.R
import com.example.composecamp.model.Affirmation

class DDatasource() {
    fun loadAffirmations(): List<Affirmation> {
        return listOf<Affirmation>(
            Affirmation(R.string.Announcement1, R.drawable.png_20220828_221102_0000),
            Affirmation(R.string.Announcement2, R.drawable.compose),
            Affirmation(R.string.Announcement3,R.drawable.de170a461d83983ecccc93fa9d5aac0b)
        )
    }
}