package com.pkotik.railwayskeleton.base

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer


abstract class BaseActivity : AppCompatActivity() {
    protected fun <T> LiveData<T>.subscribe(observer: (T) -> Unit) =
        observe(this@BaseActivity, Observer { observer(it) })
}