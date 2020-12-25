package com.example.youtube.ui.main

import com.example.youtube.ui.LiveCycle

interface MainContract {

    interface View{
        fun showMessage(text:String)
    }

    interface Presenter : LiveCycle<View>{
        fun loadVideos()
    }
}