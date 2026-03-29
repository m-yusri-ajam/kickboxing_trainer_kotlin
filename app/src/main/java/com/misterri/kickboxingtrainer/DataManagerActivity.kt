// File: DataManagerActivity.kt

package com.misterri.kickboxingtrainer

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.misterri.kickboxingtrainer.SessionLogActivity
import com.misterri.kickboxingtrainer.R

class DataManagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_manager)

        val btnViewHistory: Button = findViewById(R.id.btn_view_session_history)

        btnViewHistory.setOnClickListener {
            val intent = Intent(this, SessionLogActivity::class.java)
            startActivity(intent)
        }

        val btnReturnHome: Button = findViewById(R.id.btn_return_home)
        btnReturnHome.setOnClickListener {
            finish()
        }
    }
}