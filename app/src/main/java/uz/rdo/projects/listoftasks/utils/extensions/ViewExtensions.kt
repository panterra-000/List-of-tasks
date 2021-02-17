package uz.rdo.projects.listoftasks.utils.extensions

import android.view.View

/**
 * Created by Davronbek Raximjanov on 17-Feb-21
 **/

fun View.visible(){
    this.visibility = View.VISIBLE
}

fun View.gone(){
    this.visibility = View.GONE
}