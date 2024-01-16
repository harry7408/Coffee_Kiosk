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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import reactivecircus.flowbinding.android.view.clicks

/**
 * Set on avoid duplicate click with flow
 * FlowBinding 을  활용한 중복 클릭 방지
 * Throttle 의 인자로 0.25초의 Delay
 * @param action
 * @receiver
 */
fun View.setOnAvoidDuplicateClickWithFlow(action: () -> Unit) {
    this.clicks()
        .flowOn(Dispatchers.Main)
        .throttleFirst(INTERVAL_TIME)
        .flowOn(Dispatchers.IO)
        .onEach {
            action()
        }.launchIn(CoroutineScope(Dispatchers.Main))
}

/**
 * Throttle first
 * Throttle First가 Flow 에는 없어 구현 필요
 * @param T
 * @param interval
 * @return
 */
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

/**
 * Set window size
 * @ 메뉴 선택을 위한 Dialog 를 띄울 때 가로 세로를 인자로 받아 창의 크기 설정
 * @param horizontal
 * @param vertical
 */
fun DialogFragment.setWindowSize(horizontal: Double, vertical: Double) {
    dialog?.setContentView(R.layout.fragment_free_option)
    dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
    val window = dialog?.window
    val size = Point()
    val display = window?.windowManager?.defaultDisplay
    display?.getSize(size)

    val width = size.x * horizontal
    val height = size.y * vertical

    window?.setLayout(width.toInt(), height.toInt())
}

