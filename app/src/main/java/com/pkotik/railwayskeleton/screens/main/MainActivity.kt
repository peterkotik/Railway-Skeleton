package com.pkotik.railwayskeleton.screens.main

import android.os.Bundle
import android.widget.TextView
import com.pkotik.railwayskeleton.R
import com.pkotik.railwayskeleton.base.BaseActivity
import com.pkotik.railwayskeleton.data.MockRepositoryImpl
import com.pkotik.railwayskeleton.data.UIModel
import com.pkotik.railwayskeleton.utils.LoadingDecorator
import com.pkotik.railwayskeleton.utils.asLiveData
import com.pkotik.railwayskeleton.utils.monitor

class MainActivity : BaseActivity() {

    private val viewModel by lazy {
        MainViewModel(MockRepositoryImpl())
    }

    private val loadingDecorator by lazy {
        LoadingDecorator(findViewById(R.id.root))
    }

    private val loadedTextView by lazy {
        findViewById<TextView>(R.id.loaded_text)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.data
            .asLiveData()
            .monitor(loadingDecorator, this)
            .subscribe {
                when (it) {
                    is UIModel.Data -> {
                        loadedTextView.text = it.data.joinToString()
                    }
                    is UIModel.Error -> {
                        loadedTextView.text = "Failed to load from repository"
                    }
                }
            }
    }
}
