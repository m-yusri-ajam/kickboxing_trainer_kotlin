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
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import java.util.Locale
import com.misterri.kickboxingtrainer.database.KickboxingDatabase
import com.misterri.kickboxingtrainer.database.ComboDao // Updated Import

class ComboManagerActivity : AppCompatActivity() {

    private lateinit var comboDao: ComboDao
    private lateinit var comboAdapter: ComboAdapter

    // UI elements for the input form
    private lateinit var inputComboName: EditText
    private lateinit var inputComboIntensity: EditText
    private lateinit var inputComboType: EditText
    private lateinit var btnAddCombo: Button

    // State for tracking if we are editing an existing combo
    private var comboToEdit: Combo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_combo_manager)

        // 1. Initialize Database (DAO)
        val database = KickboxingDatabase.getDatabase(this)
        comboDao = database.comboDao()

        // 2. Initialize UI Elements
        inputComboName = findViewById(R.id.input_combo_name)
        inputComboIntensity = findViewById(R.id.input_combo_intensity)
        inputComboType = findViewById(R.id.input_combo_type)
        btnAddCombo = findViewById(R.id.btn_add_combo)

        // 3. Setup RecyclerView for viewing existing combos
        val recyclerView = findViewById<RecyclerView>(R.id.combo_list_recycler_view)

        // Setup adapter with a click listener for editing
        comboAdapter = ComboAdapter { combo ->
            populateFormForEdit(combo)
        }

        recyclerView.adapter = comboAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 4. Observe the Combo List (updates UI automatically)
        lifecycleScope.launch {
            comboDao.getAllCombos().collect { combos ->
                comboAdapter.submitList(combos)
            }
        }

        // 5. Set up the Add/Update Button listener
        btnAddCombo.setOnClickListener {
            handleComboSubmission()
        }

        // 6. Implement Swipe-to-Delete
        setupSwipeToDelete(recyclerView)
    }

    private fun handleComboSubmission() {
        // Validation and creation/update logic
        val nameInput = inputComboName.text.toString().trim()
        val intensityInput = inputComboIntensity.text.toString()
        val typeInput = inputComboType.text.toString().trim()

        // --- Data Validation ---
        if (nameInput.isEmpty() || intensityInput.isEmpty() || typeInput.isEmpty()) {
            Toast.makeText(this, "All fields are required.", Toast.LENGTH_SHORT).show()
            return
        }

        val intensity = intensityInput.toIntOrNull()
        if (intensity == null || intensity !in 1..3) {
            Toast.makeText(this, "Intensity must be 1, 2, or 3.", Toast.LENGTH_SHORT).show()
            return
        }
        // --- End Validation ---

        lifecycleScope.launch {
            if (comboToEdit != null) {
                // UPDATE existing combo
                val updatedCombo = comboToEdit!!.copy(
                    name = nameInput.uppercase(Locale.ROOT),
                    intensity = intensity,
                    type = typeInput
                )
                comboDao.update(updatedCombo)
                Toast.makeText(this@ComboManagerActivity, "Combo '${updatedCombo.name}' Updated!", Toast.LENGTH_SHORT).show()
            } else {
                // ADD new combo
                val newCombo = Combo(
                    name = nameInput.uppercase(Locale.ROOT),
                    intensity = intensity,
                    type = typeInput
                )
                comboDao.insert(newCombo)
                Toast.makeText(this@ComboManagerActivity, "Combo ${newCombo.name} Added!", Toast.LENGTH_SHORT).show()
            }

            // Clear state and UI after save/update
            resetForm()
        }
    }

    private fun populateFormForEdit(combo: Combo) {
        // Set the state
        comboToEdit = combo

        // Populate the UI fields with the combo's current data
        inputComboName.setText(combo.name)
        inputComboIntensity.setText(combo.intensity.toString())
        inputComboType.setText(combo.type)

        // Change button text to reflect editing mode
        btnAddCombo.text = "UPDATE COMBO"
    }

    private fun resetForm() {
        // Clear all fields
        inputComboName.text.clear()
        inputComboIntensity.text.clear()
        inputComboType.text.clear()

        // Reset the state and button text
        comboToEdit = null
        btnAddCombo.text = "ADD NEW COMBO"
    }

    private fun setupSwipeToDelete(recyclerView: RecyclerView) {
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false // Not supporting drag-and-drop
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                // Use the adapter helper function to get the correct item
                val comboToDelete = comboAdapter.getComboAt(position)

                // Ensure we clear the edit form if the deleted combo was the one being edited
                if (comboToDelete == comboToEdit) {
                    resetForm()
                }

                // Execute the deletion in a coroutine
                lifecycleScope.launch {
                    comboDao.delete(comboToDelete)
                    Toast.makeText(this@ComboManagerActivity, "Combo '${comboToDelete.name}' Deleted", Toast.LENGTH_SHORT).show()
                }
            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
}