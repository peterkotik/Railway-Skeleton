package com.pkotik.railwayskeleton.utils

import com.pkotik.railwayskeleton.data.UIModel
import io.reactivex.Single

fun <T : Any> Single<UIModel<T>>.startByLoading() =
    Single.just<UIModel<T>>(UIModel.Loading).concatWith(this)