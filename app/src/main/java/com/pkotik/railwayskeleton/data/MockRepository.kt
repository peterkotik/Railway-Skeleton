package com.pkotik.railwayskeleton.data

import io.reactivex.Single
import java.util.concurrent.TimeUnit

interface MockRepository {
    fun getMockData(id: String): Single<DataModel<List<String>>>

}

class MockRepositoryImpl : MockRepository {
    override fun getMockData(id: String): Single<DataModel<List<String>>> {
        return Single.just(DataModel.Data(listOf(1, 2, 3)))
            .delay(2000, TimeUnit.MILLISECONDS)
            .map {
                it.map {
                    it.map {
                        it.toString()
                    }
                }
            }
    }
}