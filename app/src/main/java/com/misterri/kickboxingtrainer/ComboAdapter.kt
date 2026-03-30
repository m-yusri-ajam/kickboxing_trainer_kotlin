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

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView // For ViewHolder
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.misterri.kickboxingtrainer.R

class ComboAdapter(private val onClick: (Combo) -> Unit) : ListAdapter<Combo, ComboAdapter.ComboViewHolder>(ComboComparator()) {

    // 1. ViewHolder: Holds the UI elements from list_item_combo.xml
    class ComboViewHolder(itemView: View, val onClick: (Combo) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val nameText: TextView = itemView.findViewById(R.id.text_combo_name_item)
        private val detailsText: TextView = itemView.findViewById(R.id.text_combo_details_item)
        private var currentCombo: Combo? = null

        init {
            // Set listener to handle taps for EDITING
            itemView.setOnClickListener {
                currentCombo?.let(onClick)
            }
        }

        fun bind(combo: Combo) {
            currentCombo = combo
            nameText.text = combo.name
            detailsText.text = "Intensity ${combo.intensity} | ${combo.type}"
        }

        companion object {
            fun create(parent: ViewGroup, onClick: (Combo) -> Unit): ComboViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item_combo, parent, false)
                return ComboViewHolder(view, onClick)
            }
        }
    }

    // 2. Lifecycle methods
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComboViewHolder {
        return ComboViewHolder.create(parent, onClick)
    }

    override fun onBindViewHolder(holder: ComboViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    // 3. DiffUtil
    class ComboComparator : DiffUtil.ItemCallback<Combo>() {
        override fun areItemsTheSame(oldItem: Combo, newItem: Combo): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: Combo, newItem: Combo): Boolean {
            return oldItem == newItem
        }
    }

    // Helper function to get the item for Swipe-to-Delete logic
    fun getComboAt(position: Int): Combo {
        return getItem(position)
    }
}