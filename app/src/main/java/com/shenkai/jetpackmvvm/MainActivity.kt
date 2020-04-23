package com.shenkai.jetpackmvvm

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.shenkai.jetpackmvvm.api.NewsApiInterface
import com.shenkai.jetpackmvvm.api.NewsChannelsBean
import com.shenkai.network.TencentNetworkApi
import com.shenkai.network.observer.BaseObserver

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btn_request).setOnClickListener {
            TencentNetworkApi.getService(NewsApiInterface::class.java)
                .newsChannels
                .compose(TencentNetworkApi.getInstance().applySchedulers(object :BaseObserver<NewsChannelsBean>(){
                    override fun onSuccess(newsChannelsBean: NewsChannelsBean?) {
                        Log.e("MainActivity", Gson().toJson(newsChannelsBean))
                    }

                    override fun onFailure(e: Throwable?) {
                    }

                }))
        }
    }
}
