package com.pkotik.railwayskeleton.data

sealed class UIModel<out T : Any> {
    data class Data<T : Any>(val data: T): UIModel<T>()
    data class Error(val error: Throwable): UIModel<Nothing>()
    object Loading: UIModel<Nothing>()

    fun <R : Any> map(mapper: (T) -> R): UIModel<R> {
        return when (this) {
            is Data -> Data(
                mapper(
                    data
                )
            )
            is Error -> this
            is Loading -> this
        }
    }
}
