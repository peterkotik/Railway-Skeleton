package com.pkotik.railwayskeleton.screens.main

import androidx.lifecycle.ViewModel
import com.pkotik.railwayskeleton.data.MockRepository
import com.pkotik.railwayskeleton.data.toUIModel
import com.pkotik.railwayskeleton.utils.startByLoading

class MainViewModel(
    repository: MockRepository
) : ViewModel() {
    val data =
        repository.getMockData("id")
            .map { it.toUIModel() }
            .startByLoading()
}