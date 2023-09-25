package com.kaspersky.kaspresso.tutorial.differenttypes.list

import androidx.recyclerview.widget.RecyclerView
import com.kaspersky.kaspresso.tutorial.databinding.NoteItemBinding
import com.kaspersky.kaspresso.tutorial.lists.Note
import com.kaspersky.kaspresso.tutorial.lists.Priority

internal class NoteViewHolder(
    private val binding: NoteItemBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: Note) {
        val bgColor = when (model.priority) {
            Priority.LOW -> android.R.color.holo_green_light
            Priority.MEDIUM -> android.R.color.holo_orange_light
            Priority.HIGH -> android.R.color.holo_red_light
        }

        with(binding) {
            tvNoteId.text = model.id.toString()
            tvNoteText.text = model.text
            noteContainer.setBackgroundResource(bgColor)
        }
    }
}
