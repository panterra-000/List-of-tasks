package uz.rdo.projects.listoftasks.utils.extensions

import android.view.View

/**
 * Created by Davronbek Raximjanov on 17-Feb-21
 **/

fun View.showV(){
    this.visibility = View.VISIBLE
}

fun View.hideV(){
    this.visibility = View.GONE
}

fun View.inVisible(){
    this.visibility = View.INVISIBLE
}