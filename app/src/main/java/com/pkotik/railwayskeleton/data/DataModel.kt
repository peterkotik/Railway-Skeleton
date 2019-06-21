package com.pkotik.railwayskeleton.data

sealed class DataModel<out T : Any> {
    data class Data<T : Any>(val data: T): DataModel<T>()
    data class Error(val error: Throwable): DataModel<Nothing>()

    fun <R : Any> map(mapper: (T) -> R): DataModel<R> {
        return when (this) {
            is Data -> Data(
                mapper(
                    data
                )
            )
            is Error -> this
        }
    }
}

fun <T : Any> DataModel<T>.toUIModel(): UIModel<T> {
    return when (this) {
        is DataModel.Data -> UIModel.Data(data)
        is DataModel.Error -> UIModel.Error(error)
    }
}