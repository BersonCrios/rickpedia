package com.bersoncrios.rickpedia.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bersoncrios.rickpedia.R
import com.bersoncrios.rickpedia.base.BaseActivity
import com.bersoncrios.rickpedia.model.Result
import com.bersoncrios.rickpedia.ui.adapter.CharAdapter
import com.bersoncrios.rickpedia.ui.listener.OnClickListener
import com.bersoncrios.rickpedia.viewmodel.CharacterViewModel
import kotlinx.android.synthetic.main.layout_characters_activity.*

class CharactersActivity : BaseActivity(), OnClickListener {

    private lateinit var viewModel: CharacterViewModel
    private val charAdapter = CharAdapter(arrayListOf(), this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_characters_activity)

        viewModel = ViewModelProvider(this)
            .get(CharacterViewModel::class.java)

        PersonagensRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = charAdapter
        }

        viewModel.fetchChars()

        viewModel.items.observe(this, Observer{
            charAdapter.update(it.results)
        })

    }

    override fun onItemClickListener(result: Result) {
        val intent = Intent(applicationContext, CharDetailsActivity::class.java)
        intent.putExtra("id", result.id)
        startActivity(intent)
    }
}