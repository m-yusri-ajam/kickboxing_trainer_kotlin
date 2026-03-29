package com.misterri.kickboxingtrainer

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Loads the layout you provided (activity_main.xml)
        setContentView(R.layout.activity_main)

        // 1. START TRAINING (Launches SetupActivity)
        findViewById<Button>(R.id.btn_start_training).setOnClickListener {
            val intent = Intent(this, SetupActivity::class.java)
            startActivity(intent)
        }

        // 2. SESSION HISTORY (Launches SessionLogActivity)
        findViewById<Button>(R.id.btn_view_history).setOnClickListener {
            // 🔑 MODIFIED: Ensure this targets the new SessionLogActivity
            val intent = Intent(this, SessionLogActivity::class.java)
            startActivity(intent)
        }

        // 3. COMBO MANAGER (Launches ComboManagerActivity)
        findViewById<Button>(R.id.btn_manage_combos).setOnClickListener {
            val intent = Intent(this, ComboManagerActivity::class.java)
            startActivity(intent)
        }

        // 4. TECHNIQUE GLOSSARY (Launches GlossaryActivity)
        findViewById<Button>(R.id.btn_glossary).setOnClickListener {
            val intent = Intent(this, GlossaryActivity::class.java)
            startActivity(intent)
        }
    }
}