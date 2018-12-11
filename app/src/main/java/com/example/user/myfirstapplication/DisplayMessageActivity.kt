package com.example.user.myfirstapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

class DisplayMessageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_message)

        val message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE)
        Log.d(DisplayMessageActivity::class.toString(), "ここ")
        Log.d(DisplayMessageActivity::class.toString(), "message=$message")

        // Capture the layout's TextView and set the string as its text
//        val textView = findViewById<TextView>(R.id.textView)
//        textView.text = message

        // RxJava2サンプル
        // 時刻を表示するだけ
        time = findViewById<TextView>(R.id.textView)
        doRx()
    }

    private var time: TextView? = null
    private var disposable: Disposable? = null

    private fun doRx() {
        disposable = Flowable.interval(1000L, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { _ -> updateTimeView() }
    }

    private fun updateTimeView() {
        val current = LocalDateTime.now()
//        time?.text = "${current.hour}:${current.minute}:${current.second}"
        time?.text = "%02d:%02d:%02d".format(current.hour, current.minute, current.second)
    }
}
