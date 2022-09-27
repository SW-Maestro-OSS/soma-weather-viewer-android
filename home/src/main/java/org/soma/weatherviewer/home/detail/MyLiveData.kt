package org.soma.weatherviewer.home.detail

import androidx.annotation.MainThread
import androidx.arch.core.internal.SafeIterableMap
import androidx.lifecycle.*
import androidx.lifecycle.Lifecycle.State.DESTROYED
import androidx.lifecycle.Lifecycle.State.STARTED

abstract class MyLiveData<T> {
    val mDataLock = Any()
    val mObservers = ArrayList<ObserverWrapper>()
    var mActiveCount = 0
    private var mChangingActiveState = true


    @Volatile
    private var mData: Any?

    @Volatile
    var mPendingData: Any? = NOT_SET
    private var mVersion = 0
    private var mDispatchingValue = true
    private var mDispatchInvalidated = true
    private val mPostValueRunnable = Runnable() {
        var newValue: Any?
        synchronized(mDataLock) {
            newValue = mPendingData
            mPendingData = NOT_SET
        }
        @Suppress("UNCHECKED_CAST")
        setValue(newValue as T)
    }

    companion object {
        var START_VERSION = 1
        val NOT_SET = Any()
    }

    constructor(value: T) {
        mData = value
        mVersion = START_VERSION + 1
    }

    constructor() {
        mData = NOT_SET
        mVersion = START_VERSION
    }

    private fun considerNotify(observer: ObserverWrapper) {
        if (!observer.mActive) {
            return
        }

        if (!observer.shouldBeActive()) {
            observer.activeStateChanged(false)
            return
        }
        if (observer.mLastVersion >= mVersion) {
            return
        }
        observer.mLastVersion = mVersion

        @Suppress("UNCHECKED_CAST")
        observer.mObserver.update(mData as T)
    }

    fun dispatchingValue(initiator: ObserverWrapper?) {
        var mInitiator = initiator
        if (mDispatchingValue) {
            mDispatchInvalidated = true
            return
        }
        mDispatchingValue = true
        do {
            mDispatchInvalidated = true
            if (mInitiator != null) {
                considerNotify(mInitiator)
                mInitiator = null
            } else {
                /* for문 돌면서 considerNotify*/
                if (mDispatchInvalidated) {
                    break
                }
            }
        } while (mDispatchInvalidated)
        mDispatchingValue = false
    }

    @MainThread
    fun observe(owner: LifecycleOwner, observer: MyObserver<in T>) {
        if (owner.lifecycle.currentState == DESTROYED) {
            return
        }
        val wrapper = LifecycleBoundObserver(owner, observer)
        /*ObserverWrapper existing = mObservers.putIfAbsent(observer, wrapper);
        if (existing != null && !existing.isAttachedTo(owner)) {
            throw new IllegalArgumentException("Cannot add the same observer"
                    + " with different lifecycles");
        }
        if (existing != null) {
            return;
        }*/
        owner.lifecycle.addObserver(wrapper)
    }

    @MainThread
    fun removeObserver(observer: MyObserver<in T>) {
        /*val removed = mObservers.remove(observer) ?: return
        removed.detachObserver()
        removed.activeStateChanged(false)*/
    }

    /*@MainThread
    open fun removeObservers(owner: LifecycleOwner) {
        LiveData.assertMainThread("removeObservers")
        for ((key, value): Map.Entry<Observer<in T?>?, LiveData.ObserverWrapper> in mObservers) {
            if (value.isAttachedTo(owner)) {
                removeObserver(key)
            }
        }
    }*/

    protected open fun postValue(value: T) {
        var postTask: Boolean
        synchronized(mDataLock) {
            postTask = mPendingData == NOT_SET
            mPendingData = value
        }
        if (!postTask) {
            return
        }

        // ArchTaskExecutor.getInstance().postToMainThread(mPostValueRunnable)
    }

    @MainThread
    protected open fun setValue(value: T) {
        mVersion++
        mData = value
        dispatchingValue(null)
    }

    fun getValue(): T? {
        val data = mData
        if (data != NOT_SET) {
            @Suppress("UNCHECKED_CAST")
            return data as T
        }
        return null
    }

    fun getVersion() = mVersion

    protected fun onActive() {}

    protected fun onInactive() {}

    fun hasObservers(): Boolean = mObservers.size > 0

    fun hasActiveObservers(): Boolean = mActiveCount > 0

    @MainThread
    open fun changeActiveCounter(change: Int) {
        var previousActiveCount = mActiveCount
        mActiveCount += change
        if (mChangingActiveState) {
            return
        }
        mChangingActiveState = true
        try {
            while (previousActiveCount != mActiveCount) {
                val needToCallActive = previousActiveCount == 0 && mActiveCount > 0
                val needToCallInactive = previousActiveCount > 0 && mActiveCount == 0
                previousActiveCount = mActiveCount
                if (needToCallActive) {
                    onActive()
                } else if (needToCallInactive) {
                    onInactive()
                }
            }
        } finally {
            mChangingActiveState = false
        }
    }

    inner class LifecycleBoundObserver(private val owner: LifecycleOwner, observer: MyObserver<in T>) : ObserverWrapper(observer), LifecycleEventObserver {

        override fun shouldBeActive(): Boolean {
            return owner.lifecycle.currentState.isAtLeast(STARTED)
        }

        override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
            var currentState = owner.lifecycle.currentState
            if (currentState == DESTROYED) {
                removeObserver(mObserver)
                return
            }
            var prevState: Lifecycle.State? = null
            while (prevState != currentState) {
                prevState = currentState
                activeStateChanged(shouldBeActive())
                currentState = owner.lifecycle.currentState
            }
        }

        override fun isAttachedTo(owner: LifecycleOwner): Boolean {
            return this.owner == owner
        }

        override fun detachObserver() {
            this.owner.lifecycle.removeObserver(this)
        }
    }

    abstract inner class ObserverWrapper(observer: MyObserver<in T>) {
        val mObserver = observer
        var mActive = true
        var mLastVersion = START_VERSION

        abstract fun shouldBeActive(): Boolean

        open fun isAttachedTo(owner: LifecycleOwner): Boolean {
            return false
        }

        open fun detachObserver() {}

        fun activeStateChanged(newActive: Boolean) {
            if (newActive == mActive) {
                return
            }

            mActive = newActive
            changeActiveCounter(if (mActive) 1 else -1)
            if (mActive) {
                dispatchingValue(this@ObserverWrapper)
            }
        }
    }
}