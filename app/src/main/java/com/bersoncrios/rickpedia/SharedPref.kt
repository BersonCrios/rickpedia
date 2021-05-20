package com.bersoncrios.rickpedia

import android.content.Context
import android.content.SharedPreferences

object SharedPref{
    const val CHAR_NAME = "CHAR_NAME"
    private const val NAME = "Rickpedia"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    fun saveCharacter(charName: String) {
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putString(CHAR_NAME, charName)
        editor.apply()
    }
}