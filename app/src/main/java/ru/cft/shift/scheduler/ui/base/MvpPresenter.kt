package ru.cft.shift.scheduler.ui.base

interface MvpPresenter {

    fun attachView(mvpView: MvpView)

    fun detachView()

}