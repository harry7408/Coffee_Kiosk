package com.choi.coffee_kiosks.base

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.choi.coffee_kiosks.R
import com.choi.coffee_kiosks.entity.Document
import com.choi.coffee_kiosks.util.common.CLICK_TAG
import com.choi.coffee_kiosks.util.common.INTERVAL_TIME
import com.jakewharton.rxbinding4.view.clicks
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

typealias FragmentInflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB : ViewBinding>(
    private val inflate: FragmentInflate<VB>
) : Fragment() {

    private var compositeDisposable = CompositeDisposable()

    var _binding: VB? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        compositeDisposable.dispose()
    }

    /**
     * On avoid duplicate click
     * Rx 를 활용한 이벤트 처리를 위해 만든 확장 함수
     * @param doOnMainThread : 작업할 내용
     */
    fun View.onAvoidDuplicateClick(doOnMainThread: () -> Unit) {
        compositeDisposable.add(
            this.clicks().observeOn(
                Schedulers.io()
            )
                .throttleFirst(INTERVAL_TIME, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    doOnMainThread()
                }, {
                    Log.e(CLICK_TAG, it.toString())
                })
        )
    }

    /**
     * Show mission : 난이도 별 미션 보기 Diapog
     */

    val showMission = { document: Document ->
        AlertDialog.Builder(requireContext())
            .run {
                setMessage(document.fields.missionDetail.stringValue)
                    .setPositiveButton(R.string.dialog_ok_text, null)
                    .setNegativeButton(R.string.cancel_text, null)
                    .create().show()
            }

    }

    fun getBundle(key: String, value: String): Bundle {
        val bundle = Bundle()
        bundle.putString(key, value)
        return bundle
    }

}