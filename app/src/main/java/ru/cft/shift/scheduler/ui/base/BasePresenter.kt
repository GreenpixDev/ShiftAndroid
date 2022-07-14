package ru.cft.shift.scheduler.ui.base

open class BasePresenter<V : MvpView> : MvpPresenter {

    var view: V? = null
    private set

    override fun attachView(mvpView: MvpView) {
        this.view = mvpView as V
    }

    override fun detachView() {
        view = null
    }

    open fun onAttachView(mvpView: V) {}

    open fun onDetachView() {}

}