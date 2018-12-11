package com.example.user.myfirstapplication

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.EditText
import io.realm.Realm


//import io.reactivex.flowables.*
//import io.reactivex.observables.*
//import io.reactivex.observers.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // realmサンプル
        doRealm()

    }

    fun sendMessage(view: View) {
        val intent = Intent(this, DisplayMessageActivity::class.java)
        val editText = findViewById<EditText>(R.id.editText2)
        val message = editText.text.toString()
        intent.putExtra(EXTRA_MESSAGE, message)
        startActivity(intent)
    }

    // 画面が開かれるたびにレコードを挿入する
    private fun doRealm() {
        Realm.getDefaultInstance().use {
            // idの最大値を取得
            val prevBook: Book? = it.where(Book::class.java).findAll().maxBy(Book::id)
            val prevId    = prevBook?.id ?: 0
            val currentId = prevId + 1

            // 書き込み
            it.executeTransaction { realm ->
                val book = realm.createObject(Book::class.java, currentId).apply {
                    name = "book${currentId}"
                    price = 10000 + currentId
                }
            }

//            val id1 = it.where(Book::class.java).equalTo("id", 1 as Int).findFirst()
//            Log.d("MainActivity", id1.toString())

            // 全件取得してログに出すだけ
            val all = it.where(Book::class.java).findAll()
            Log.d("MainActivity", all.toString())
        }
    }

}
