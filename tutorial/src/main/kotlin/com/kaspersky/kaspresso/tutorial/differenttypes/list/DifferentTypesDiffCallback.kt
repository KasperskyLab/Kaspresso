package com.kaspersky.kaspresso.tutorial.differenttypes.list

import androidx.recyclerview.widget.DiffUtil
import com.kaspersky.kaspresso.tutorial.differenttypes.DifferentTypesListView.ListModel

internal class DifferentTypesDiffCallback(
    private val oldList: List<ListModel>,
    private val newList: List<ListModel>,
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList.getOrNull(oldItemPosition)
        val newItem = newList.getOrNull(newItemPosition)

        return when {
            oldItem is ListModel.Action
                    && newItem is ListModel.Action -> oldItem.note.id == newItem.note.id

            oldItem is ListModel.Notes
                    && newItem is ListModel.Notes -> oldItem.note.id == newItem.note.id

            else -> false
        }
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList.getOrNull(oldItemPosition)
        val newItem = newList.getOrNull(newItemPosition)
        return oldItem != null && oldItem == newItem
    }
}
