package com.misterri.kickboxingtrainer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.misterri.kickboxingtrainer.databinding.ListItemSessionLogBinding // Assumes correct package
import com.misterri.kickboxingtrainer.viewmodel.DisplaySessionLog

class SessionLogAdapter : ListAdapter<DisplaySessionLog, SessionLogAdapter.SessionLogViewHolder>(SessionLogDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SessionLogViewHolder {
        val binding = ListItemSessionLogBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SessionLogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SessionLogViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SessionLogViewHolder(private val binding: ListItemSessionLogBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(session: DisplaySessionLog) {
            binding.textMode.text = session.mode
            binding.textDate.text = session.date
            binding.textDuration.text = session.duration
            binding.textDetails.text = session.details
        }
    }
}

private class SessionLogDiffCallback : DiffUtil.ItemCallback<DisplaySessionLog>() {
    override fun areItemsTheSame(oldItem: DisplaySessionLog, newItem: DisplaySessionLog): Boolean {
        // Items are the same if their unique ID (from the database) matches
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DisplaySessionLog, newItem: DisplaySessionLog): Boolean {
        // Contents are the same if all displayed properties match
        return oldItem == newItem
    }
}