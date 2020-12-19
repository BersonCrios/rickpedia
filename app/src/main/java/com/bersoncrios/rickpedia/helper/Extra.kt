package com.bersoncrios.rickpedia.helper

import android.content.Intent
import android.os.Bundle

class Extra {
    companion object {
        fun extractExtras(i:Intent): Int {
            val b: Bundle? = i.extras
            var id: Int = 0

            if (b != null) {
                id = b.getInt("id")
            }
            return id
        }
    }
}