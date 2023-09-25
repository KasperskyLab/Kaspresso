package com.kaspersky.kaspresso.tutorial.differenttypes.list

import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewTreeObserver
import androidx.recyclerview.widget.RecyclerView
import com.kaspersky.kaspresso.tutorial.databinding.ActionItemBinding
import com.kaspersky.kaspresso.tutorial.lists.Note
import com.kaspersky.kaspresso.tutorial.lists.Priority

internal class ActionViewHolder(
    private val binding: ActionItemBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        model: Note,
        isOpen: Boolean,
    ) {
        val bgColor = when (model.priority) {
            Priority.LOW -> android.R.color.holo_green_light
            Priority.MEDIUM -> android.R.color.holo_orange_light
            Priority.HIGH -> android.R.color.holo_red_light
        }

        with(binding) {
            tvNoteId.text = model.id.toString()
            tvNoteText.text = model.text
            noteContainer.setBackgroundResource(bgColor)
            // binding.root.setCardBackgroundColor(bgColor)

            //if (isOpen) {
            if (isOpen) {
                // binding.noteContainer.visibility = GONE
                binding.noteContainer2.visibility = VISIBLE
                // binding.cardView.translationX = -binding.tvNoteText222.x
                // binding.noteContainer.marginLeft= binding.noteContainer

//                binding.tvNoteText222.doOnPreDraw {
//                    binding.cardView.translationX = -it.width.toFloat()
//                }
                binding.tvNoteTextExpanded.viewTreeObserver.addOnGlobalLayoutListener(
                    object : ViewTreeObserver.OnGlobalLayoutListener {
                        override fun onGlobalLayout() {
                            binding.tvNoteTextExpanded.viewTreeObserver.removeOnGlobalLayoutListener(this)
                            binding.cardView.translationX = -binding.tvNoteTextExpanded.width.toFloat()
                        }
                    }
                )
            } else {
                // binding.noteContainer.visibility = VISIBLE
                binding.noteContainer2.visibility = GONE
                binding.cardView.translationX = 0F
            }
        }
    }
}
