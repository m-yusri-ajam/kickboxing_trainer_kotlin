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
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.misterri.kickboxingtrainer.database.KickboxingDatabase // Assuming your DB is here
import com.misterri.kickboxingtrainer.database.SessionLogDao
import com.misterri.kickboxingtrainer.databinding.ActivitySessionLogBinding // Assumes you create this layout
import com.misterri.kickboxingtrainer.viewmodel.SessionLogViewModel
import com.misterri.kickboxingtrainer.viewmodel.SessionLogViewModelFactory
import kotlinx.coroutines.launch

class SessionLogActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySessionLogBinding
    private lateinit var adapter: SessionLogAdapter // You will need to create this adapter

    private val logDao: SessionLogDao by lazy {
        KickboxingDatabase.getDatabase(applicationContext).sessionLogDao()
    }
    private val viewModel: SessionLogViewModel by viewModels {
        SessionLogViewModelFactory(logDao)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySessionLogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeViewModel()
        setupListeners()
    }

    private fun setupRecyclerView() {
        adapter = SessionLogAdapter() // Initialize your RecyclerView Adapter
        binding.recyclerViewLog.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewLog.adapter = adapter
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.sessionLogs.collect { sessions ->
                // Update the RecyclerView with the latest list of sessions
                adapter.submitList(sessions)

                // Optional: Show/hide empty state message
                // binding.textEmptyState.visibility = if (sessions.isEmpty()) View.VISIBLE else View.GONE
            }
        }
    }

    private fun setupListeners() {
        // Optional: Button to delete all data for testing
        binding.buttonClearHistory.setOnClickListener {
            viewModel.deleteAllSessions()
        }

        // Ensure this activity is linked from your main screen or menu
    }
}