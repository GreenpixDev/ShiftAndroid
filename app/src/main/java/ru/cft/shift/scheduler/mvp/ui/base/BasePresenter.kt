package ru.cft.shift.scheduler.mvp.ui.base

open class BasePresenter<V : MvpView> : MvpPresenter {

    public var view: V? = null
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