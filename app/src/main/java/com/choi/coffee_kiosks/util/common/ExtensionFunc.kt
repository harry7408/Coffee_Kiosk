package com.choi.coffee_kiosks.util.common

import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.choi.coffee_kiosks.R
import kotlinx.coroutines.CoroutineScope
import reactivecircus.flowbinding.android.view.clicks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

fun View.setOnAvoidDuplicateClickWithFlow(action: () -> Unit) {
    this.clicks()
        .flowOn(Dispatchers.Main)
        .throttleFirst(INTERVAL_TIME)
        .flowOn(Dispatchers.IO)
        .onEach {
            action()
        }.launchIn(CoroutineScope(Dispatchers.Main))
}

private fun <T> Flow<T>.throttleFirst(interval: Long): Flow<T> = flow {
    var throttleTime = 0L
    collect { upstream ->
        val currentTime = System.currentTimeMillis()
        if ((currentTime - throttleTime) > interval) {
            throttleTime = currentTime
            emit(upstream)
        }
    }
}

fun ViewGroup.changeFragment(current: Fragment, new: Fragment) {
    current.parentFragmentManager.beginTransaction()
        .add(this.id, new)
        .addToBackStack(null)
        .commit()
}
fun ViewGroup.changeMenu(current: Fragment, new: Fragment) {
    current.parentFragmentManager.beginTransaction()
        .replace(this.id, new)
        .commit()
}

fun DialogFragment.setWindowSize(horizontal: Double, vertical : Double) {
    dialog?.setContentView(R.layout.fragment_free_option)
    dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
    val window = dialog?.window
    val size = Point()
    val display = window?.windowManager?.defaultDisplay
    display?.getSize(size)

    val width = size.x * horizontal
    val height = size.y * vertical

    window?.setLayout(width.toInt(),height.toInt())
}