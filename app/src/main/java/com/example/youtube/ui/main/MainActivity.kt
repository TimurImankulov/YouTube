package com.example.youtube.ui.main

import android.os.Bundle
import android.widget.Toast
import com.example.youtube.R
import com.example.youtube.data.remote.RetrofitBuilder
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : YouTubeBaseActivity(), MainContract.View {

    var presenter : MainPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter()
        presenter?.bind(this)

        presenter?.loadVideos()

        val listener = object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                youTubePlayer: YouTubePlayer?,
                p2: Boolean
            ) {
                youTubePlayer?.loadVideo("1TB1x67Do5U")
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {

            }
        }

          player.initialize("AIzaSyC5xOKtR05SHjyWVvBXC6850AMbO4_JkLo", listener)
    }

    override fun onDestroy() {
        presenter?.unbind()
        super.onDestroy()
    }

    override fun showMessage(text: String) {
        runOnUiThread {
            Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
        }
    }
}
