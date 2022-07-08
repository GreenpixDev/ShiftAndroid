package ru.cft.shift.scheduler.mvp.ui.base

interface MvpPresenter {

    fun attachView(mvpView: MvpView)

    fun detachView()

}