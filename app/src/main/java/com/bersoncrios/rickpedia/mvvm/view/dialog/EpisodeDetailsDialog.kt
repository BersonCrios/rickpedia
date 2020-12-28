package com.bersoncrios.rickpedia.mvvm.view.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import com.bersoncrios.rickpedia.R
import kotlinx.android.synthetic.main.layout_episode_details_dialog.*

class EpisodeDetailsDialog(context: Context, nome: String, data: String, ep: String) : Dialog(context) {

    private var _nome: String = nome
    private var _data: String = data
    private var _ep: String = ep

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.layout_episode_details_dialog)

        preencheNomes()
    }

    private fun preencheNomes() {
        EpNomeTextView.text = _nome
        epLancamentoTextView.text = _data
        epNumeroTextView.text = _ep
    }
}