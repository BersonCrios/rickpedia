package com.bersoncrios.rickpedia.ui.listener

import com.bersoncrios.rickpedia.model.Episode
import com.bersoncrios.rickpedia.model.Result

interface OnClickListener {
    fun onItemClickListener(result: Result)
    fun onItemClickListener(Episode: Episode)
}