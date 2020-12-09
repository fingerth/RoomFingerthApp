package com.fingerth.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.fingerth.room.room.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dao = (application as App).mAppDatabase?.userDao()

        tv1.setOnClickListener {
            dao?.let {
                val list: List<User> = it.loadAllUsersByFirstName("李")
                if (list.isNotEmpty()) tv1.text = list[0].name
            }
        }
        tv2.setOnClickListener {
            dao?.insertUsers(
                User(1, "张", "三", "张三"),
                User(16, "李", "斯", "李斯"),
                User(26, "王", "五", "王五")
            )
        }
    }
}
