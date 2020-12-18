package com.bersoncrios.rickpedia.ui

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bersoncrios.rickpedia.R
import com.bersoncrios.rickpedia.base.BaseActivity
import com.bersoncrios.rickpedia.viewmodel.CharacterViewModel


class CharDetailsActivity : BaseActivity() {

    private lateinit var viewModel: CharacterViewModel
    private val extras: Bundle? = intent.extras
    private val id = extras!!.getInt("id")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_char_details_activity)

        viewModel = ViewModelProvider(this)
            .get(CharacterViewModel::class.java)

        viewModel.fetchDetailsOfChars(id)

        viewModel.items.observe(this, Observer {
            Log.e("Char", it.toString())
        })
    }
}