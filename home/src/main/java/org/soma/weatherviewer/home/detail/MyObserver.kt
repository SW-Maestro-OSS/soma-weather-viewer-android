package org.soma.weatherviewer.home.detail

interface MyObserver<T> {
    fun update(item: T)
}