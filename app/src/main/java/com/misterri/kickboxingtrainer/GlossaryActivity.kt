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

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.misterri.kickboxingtrainer.databinding.ActivityGlossaryBinding

class GlossaryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGlossaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGlossaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the Toolbar/ActionBar if desired
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Technique Glossary"
    }

    // Handle the back button press on the action bar
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}