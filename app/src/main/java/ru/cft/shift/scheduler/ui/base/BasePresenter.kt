package ru.cft.shift.scheduler.ui.base

import retrofit2.Call
import retrofit2.Callback

open class BasePresenter<V : MvpView> : MvpPresenter {

    private val calls = mutableListOf<Call<*>>()

    var view: V? = null
    private set

    override fun attachView(mvpView: MvpView) {
        this.view = mvpView as V
    }

    override fun detachView() {
        calls.forEach { it.cancel() }
        calls.clear()
        view = null
    }

    protected fun <T> Call<T>.enqueueSafe(callback: Callback<T>) {
        calls.add(this)
        this.enqueue(callback)
    }

    open fun onAttachView(mvpView: V) {}

    open fun onDetachView() {}

}