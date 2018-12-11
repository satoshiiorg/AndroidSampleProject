package com.example.user.myfirstapplication

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class MyFirstApplication() : Application() {
    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
        // FIXME DEBUG
        val config = RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build()
        Realm.setDefaultConfiguration(config)
    }

//    override fun onTerminate() {
//        super.onTerminate()
//        // Activityでcloseするもの？close されても getDefaultInstance()使えるの？
//    ⇒ Realmインスタンスはリファレンスカウント方式で管理されています。
// もし同じスレッドで２回getInstance()を呼んだ場合は、close()も同じように２回呼ばなければなりません。
//        Realm.getDefaultInstance().close()
//    }
}
