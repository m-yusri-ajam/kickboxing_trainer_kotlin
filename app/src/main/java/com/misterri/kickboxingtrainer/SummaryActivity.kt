/**
 * -----------------------------------------------------------------------------
 * Copyright (c) 2026 Mogamad Yusri Ajam. All rights reserved.
 * * This source code is the proprietary property of Mogamad Yusri Ajam.
 * Unauthorized copying, modification, or distribution of this file,
 * via any medium, is strictly prohibited.
 *
 * Project: KickboxingTrainer (HIIT Performance)
 * Contact: mogamadyusriajam@gmail.com
 * -----------------------------------------------------------------------------
 */
package com.misterri.kickboxingtrainer

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SummaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)

        val duration = intent.getLongExtra("EXTRA_DURATION", 0L)
        val rounds = intent.getIntExtra("EXTRA_ROUNDS", 0)
        val intensity = intent.getIntExtra("EXTRA_INTENSITY", 1)
        val type = intent.getStringExtra("EXTRA_TYPE")

        val durationInMinutes = duration / 1000 / 60
        val summaryText = "Completed $rounds rounds, $durationInMinutes minutes total, focusing on $type (Intensity $intensity)."
        // Assuming R.id.summary_text exists
        // findViewById<TextView>(R.id.summary_text).text = summaryText

        // FIX: The Unresolved reference (btn_log_data) is an XML ID.
        // We assume it exists in activity_summary.xml now.
        findViewById<Button>(R.id.btn_log_data).setOnClickListener {
            val logIntent = Intent(this, DataInputActivity::class.java).apply {
                putExtras(intent.extras ?: Bundle())
            }
            startActivity(logIntent)
            finish()
        }

        findViewById<Button>(R.id.btn_return_home).setOnClickListener {
            val homeIntent = Intent(this, MainActivity::class.java)
            startActivity(homeIntent)
            finish()
        }
    }
}