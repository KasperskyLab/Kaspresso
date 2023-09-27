package com.kaspersky.kaspresso.tutorial.differenttypes.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.kaspersky.kaspresso.tutorial.databinding.ActionItemBinding
import com.kaspersky.kaspresso.tutorial.databinding.FallbackItemBinding
import com.kaspersky.kaspresso.tutorial.databinding.NoteItemBinding
import com.kaspersky.kaspresso.tutorial.differenttypes.DifferentTypesListView.ListModel

internal class DifferentTypesAdapter : RecyclerView.Adapter<ViewHolder>() {
    private val items: MutableList<ListModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            NOTES_VIEW_TYPE -> NoteViewHolder(
                NoteItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                )
            )

            ACTION_VIEW_TYPE -> ActionViewHolder(
                ActionItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                )
            )

            else -> FallbackViewHolder(
                FallbackItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false,
                )
            )
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (val item = items.getOrNull(position)) {
            is ListModel.Notes -> (holder as? NoteViewHolder)?.bind(item.note)
            is ListModel.Action -> (holder as? ActionViewHolder)?.bind(item.note, item.isOpen)
            else -> {}
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items.getOrNull(position)) {
            is ListModel.Notes -> NOTES_VIEW_TYPE
            is ListModel.Action -> ACTION_VIEW_TYPE
            null -> FALL_BACK_VIEW_TYPE
        }
    }

    fun submitList(newItems: List<ListModel>) {
        val diffCallback = DifferentTypesDiffCallback(
            oldList = items,
            newList = newItems,
        )
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        items.clear()
        items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }

    fun getItemByIndexOrNull(index: Int): ListModel? {
        return items.getOrNull(index)
    }

    companion object {
        private const val FALL_BACK_VIEW_TYPE = 0
        private const val NOTES_VIEW_TYPE = 1
        private const val ACTION_VIEW_TYPE = 2
    }
}
