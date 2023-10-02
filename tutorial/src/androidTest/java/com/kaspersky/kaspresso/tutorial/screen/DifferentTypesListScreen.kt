package com.kaspersky.kaspresso.tutorial.screen

import android.view.View
import androidx.test.espresso.action.ViewActions
import com.kaspersky.kaspresso.screens.KScreen
import com.kaspersky.kaspresso.tutorial.R
import com.kaspersky.kaspresso.tutorial.differenttypes.DifferentTypesListActivity
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher

object DifferentTypesListScreen : KScreen<DifferentTypesListScreen>() {

    override val layoutId: Int = R.layout.activity_different_types_list
    override val viewClass: Class<*> = DifferentTypesListActivity::class.java

    val rvNotes = KRecyclerView(
        builder = { withId(R.id.rv_notes) },
        itemTypeBuilder = {
            itemType(::NoteItemScreen)
            itemType(::MenuItemScreen)
        }
    )

    class NoteItemScreen(
        matcher: Matcher<View>,
    ) : KRecyclerItem<NoteItemScreen>(matcher) {
        val noteContainer = KView(matcher) { withId(R.id.note_container) }
        val tvNoteId = KTextView(matcher) { withId(R.id.tv_note_id) }
        val tvNoteText = KTextView(matcher) { withId(R.id.tv_note_text) }

        fun swipeLeft() {
            view.perform(ViewActions.swipeLeft())
        }
    }

    class MenuItemScreen(
        matcher: Matcher<View>,
    ) : KRecyclerItem<MenuItemScreen>(matcher) {
        val noteContainer = KView(matcher) { withId(R.id.note_container) }
        val tvNoteId = KTextView(matcher) { withId(R.id.tv_note_id) }
        val tvNoteText = KTextView(matcher) { withId(R.id.tv_note_text) }
        val ivbDelete = KView(matcher) { withId(R.id.ivb_delete) }
        val ivbClose = KView(matcher) { withId(R.id.ivb_close) }

        fun swipeLeft() {
            view.perform(ViewActions.swipeLeft())
        }

        fun swipeRight() {
            view.perform(ViewActions.swipeRight())
        }
    }
}
