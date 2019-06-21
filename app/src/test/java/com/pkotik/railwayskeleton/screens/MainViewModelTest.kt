package com.pkotik.railwayskeleton.screens

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doAnswer
import com.nhaarman.mockito_kotlin.mock
import com.pkotik.railwayskeleton.data.DataModel
import com.pkotik.railwayskeleton.data.MockRepository
import com.pkotik.railwayskeleton.data.UIModel
import com.pkotik.railwayskeleton.screens.main.MainViewModel
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class MainViewModelTest {

    private lateinit var viewModel: MainViewModel
    private val repository: MockRepository = mock {
        on { getMockData(any()) } doAnswer { Single.just(DataModel.Data(listOf("a"))) }
    }

    @Before
    fun setup() {
        viewModel = MainViewModel(repository)
    }

    @Test
    fun `Data emits loading state before results`() {
        viewModel.data.test().assertValueSequence(listOf(UIModel.Loading, UIModel.Data(listOf("a"))))
    }
}