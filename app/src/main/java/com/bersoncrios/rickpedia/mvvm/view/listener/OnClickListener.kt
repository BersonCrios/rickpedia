package com.bersoncrios.rickpedia.mvvm.view.listener

import com.bersoncrios.rickpedia.main.ListMenuUi
import com.bersoncrios.rickpedia.model.Episode
import com.bersoncrios.rickpedia.model.Result

interface OnClickListener {
    fun onItemClickListener(result: Result)
    fun onItemClickListener(Episode: Episode)
    fun onItemClickListener(menu: ListMenuUi)
}