package com.choi.coffee_kiosks.util.common

import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

fun ViewGroup.addFragment(current: Fragment, new: Fragment) {
    current.childFragmentManager.beginTransaction()
        .add(this.id, new)
        .addToBackStack(null)
        .commit()
}