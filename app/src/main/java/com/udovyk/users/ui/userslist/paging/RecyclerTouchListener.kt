package com.udovyk.users.ui.userslist.paging

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView

class RecyclerTouchListener (context: Context) : GestureDetector.SimpleOnGestureListener(),
    RecyclerView.OnItemTouchListener {

    private val gestureDetector = GestureDetector(context, this)
    var listener: ClickListener? = null
    private val rv: RecyclerView? = null

    override fun onSingleTapUp(e: MotionEvent): Boolean {
        return true
    }

    override fun onLongPress(e: MotionEvent) {
        val child = rv!!.findChildViewUnder(e.x, e.y)
        if (child != null && listener != null && rv != null) {
            listener!!.onLongPress(rv!!.getChildAdapterPosition(child), child)
        }
    }

    override
    fun onInterceptTouchEvent(@NonNull recyclerView: RecyclerView, @NonNull motionEvent: MotionEvent): Boolean {
        val child = recyclerView.findChildViewUnder(motionEvent.x, motionEvent.y)
        if (child != null && listener != null && gestureDetector.onTouchEvent(motionEvent)) {
            listener!!.onPress(recyclerView.getChildAdapterPosition(child), child)
        }

        return false
    }

    override
    fun onTouchEvent(@NonNull recyclerView: RecyclerView, @NonNull motionEvent: MotionEvent) {

    }

    override
    fun onRequestDisallowInterceptTouchEvent(b: Boolean) {

    }

    interface ClickListener {
        fun onPress(position: Int, view: View)

        fun onLongPress(position: Int, view: View)
    }
}