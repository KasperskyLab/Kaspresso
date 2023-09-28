package com.kaspersky.kaspresso.tutorial.differenttypes.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.kaspersky.kaspresso.tutorial.databinding.NoteItemBinding
import com.kaspersky.kaspresso.tutorial.differenttypes.view.SwipeLayout
import com.kaspersky.kaspresso.tutorial.lists.Note
import com.kaspersky.kaspresso.tutorial.lists.Priority

internal class NoteViewHolder(
    private val binding: NoteItemBinding,
) : RecyclerView.ViewHolder(binding.root), RecycledViewHolder {

    private var listener: SwipeLayout.Listener? = null

    init {
        binding.root.addListener(
            object : SwipeLayout.Listener {
                override fun onSwipeStateChanged(menuView: View, newState: Int) {
                    if (newState == SwipeLayout.STATE_IDLE) {
                        binding.root.swipeFlags = SwipeLayout.LEFT or SwipeLayout.RIGHT
                    }
                }
            }
        )
    }

    fun bind(
        model: Note,
        onRemove: () -> Unit,
    ) {
        binding.root.addListener(
            object : SwipeLayout.Listener {
                override fun onSwipe(menuView: View, swipeOffset: Float) {
                    onRemove()
                }
            }.also { listener = it }
        )
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

    override fun onRecycle() {
        listener?.let { binding.root.removeListener(it) }
    }
}
