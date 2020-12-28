package com.bersoncrios.rickpedia.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bersoncrios.rickpedia.R
import com.bersoncrios.rickpedia.model.Episode
import com.bersoncrios.rickpedia.model.Result
import com.bersoncrios.rickpedia.mvvm.view.CharactersActivity
import com.bersoncrios.rickpedia.mvvm.view.listener.OnClickListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnClickListener {

    private val menuAdapter = ListMenuAdapter(arrayListOf(), this)
    private var menuArray: MutableList<ListMenuUi> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configAdapter()

        val mvvm = ListMenuUi("MVVM")
        val mvi = ListMenuUi("MVI")
        val mvc = ListMenuUi("MVC")
        val mvp = ListMenuUi("MVP")
        val clean = ListMenuUi("CLEAN")

        menuArray.add(mvvm)
        menuArray.add(mvi)
        menuArray.add(mvc)
        menuArray.add(mvp)
        menuArray.add(clean)

        menuAdapter.update(menuArray)
    }

    private fun configAdapter() {
        menuRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = menuAdapter
        }
    }

    override fun onItemClickListener(result: Result) {}

    override fun onItemClickListener(Episode: Episode) {}

    override fun onItemClickListener(menu: ListMenuUi) {
        var i: Intent? = null
        when(menu.titulo){
            "MVVM" -> {
                i = Intent(this, CharactersActivity::class.java)
            }
             "MVI" -> {
                 Toast.makeText(this, menu.titulo , Toast.LENGTH_SHORT).show()
            }
             "MVC" -> {
                 Toast.makeText(this, menu.titulo , Toast.LENGTH_SHORT).show()
            }
             "MVP" -> {
                 Toast.makeText(this, menu.titulo , Toast.LENGTH_SHORT).show()
            }
             "CLEAN" -> {
                 Toast.makeText(this, menu.titulo , Toast.LENGTH_SHORT).show()
            }

        }
        if (i != null){
            startActivity(i)
        }
    }
}