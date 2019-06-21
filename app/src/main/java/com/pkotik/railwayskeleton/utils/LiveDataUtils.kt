package com.pkotik.railwayskeleton.utils

import androidx.lifecycle.LiveDataReactiveStreams
import org.reactivestreams.Publisher

fun <T> Publisher<T>.asLiveData() = LiveDataReactiveStreams.fromPublisher(this)