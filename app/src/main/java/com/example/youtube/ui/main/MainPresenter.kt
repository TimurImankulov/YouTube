package com.example.youtube.ui.main

import android.util.Log
import com.example.youtube.data.remote.RetrofitBuilder
import kotlinx.coroutines.*

class MainPresenter : MainContract.Presenter {

    private var view: MainContract.View? = null
    private val job = Job()
    private val scope = CoroutineScope(job)

    override fun loadVideos() {
        scope.launch(Dispatchers.IO) {
            runCatching {
                val result = RetrofitBuilder.getService()
                    ?.getPopularVideos("mostPopular")

                view?.showMessage("YEHOOOO")
            }.onFailure {
                Log.e("error121212", "${it?.localizedMessage}")
            }
        }
    }

    override fun bind(v: MainContract.View) {
        this.view = v
    }

    override fun unbind() {
        scope.cancel()
        view = null
    }
}
