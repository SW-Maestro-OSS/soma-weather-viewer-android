package org.soma.weatherviewer.home.detail

class MyMutableLiveData<T> : MyLiveData<T> {

    constructor(value: T) : super(value)

    constructor() : super()

    override fun postValue(value: T) {
        super.postValue(value)
    }

    override fun setValue(value: T) {
        super.setValue(value)
    }
}