package com.studio.yami.ajpfinal.idleresource

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource

internal object EspressoIdlingResource {

    private const val RESOURCE = "GLOBAL"
    private val espressoTestIdlingResource = CountingIdlingResource(RESOURCE)

    val getEspressoIdlingResource: IdlingResource
        get() = espressoTestIdlingResource

    fun increment() {
        espressoTestIdlingResource.increment()
    }

    fun decrement() {
        espressoTestIdlingResource.decrement()
    }
}