package com.pkotik.railwayskeleton.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.pkotik.railwayskeleton.R
import com.pkotik.railwayskeleton.data.UIModel
import java.util.concurrent.atomic.AtomicInteger

class LoadingDecorator(root: ViewGroup) {
    private val loadingCount = AtomicInteger(0)
    private val loadingSpinnerView: View =
        LayoutInflater.from(root.context).inflate(R.layout.loading_spinner, root, false)
            .also { root.addView(it) }

    fun observeLoading() {
        if (loadingCount.incrementAndGet() > 0) loadingSpinnerView.visibility = View.VISIBLE
    }

    fun observeResult() {
        if (loadingCount.decrementAndGet() == 0) loadingSpinnerView.visibility = View.INVISIBLE
    }

}

fun <T : Any> LiveData<UIModel<T>>.monitor(
    loadingDecorator: LoadingDecorator,
    lifecycleOwner: LifecycleOwner
): LiveData<UIModel<T>> {
    observe(lifecycleOwner, Observer {
        when (it) {
            is UIModel.Loading -> {
                loadingDecorator.observeLoading()
            }
            else -> {
                loadingDecorator.observeResult()
            }
        }
    })
    return this
}