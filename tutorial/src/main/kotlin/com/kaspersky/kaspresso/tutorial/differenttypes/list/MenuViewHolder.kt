package com.kaspersky.kaspresso.tutorial.differenttypes.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.kaspersky.kaspresso.tutorial.databinding.MenuItemBinding
import com.kaspersky.kaspresso.tutorial.differenttypes.view.SwipeLayout
import com.kaspersky.kaspresso.tutorial.lists.Note
import com.kaspersky.kaspresso.tutorial.lists.Priority

internal class MenuViewHolder(
    private val binding: MenuItemBinding,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.addListener(object : SwipeLayout.Listener {
            override fun onSwipeStateChanged(menuView: View, newState: Int) {
                if (newState == SwipeLayout.STATE_IDLE) {
                    binding.root.swipeFlags = SwipeLayout.LEFT or SwipeLayout.RIGHT
                }
            }
        })
    }

    fun bind(
        model: Note,
        onRemoveClicked: () -> Unit,
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

            ivbDelete.setOnClickListener { onRemoveClicked() }
            ivbClose.setOnClickListener { binding.root.closeMenu() }
        }
    }
}
