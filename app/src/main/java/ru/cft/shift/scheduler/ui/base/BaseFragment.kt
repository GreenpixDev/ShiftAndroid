package ru.cft.shift.scheduler.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment

abstract class BaseFragment<P : MvpPresenter> : Fragment(), MvpView {

    private lateinit var _presenter: P

    val presenter: P
    get() = _presenter

    protected abstract fun createPresenter(): P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _presenter = createPresenter()
        _presenter.attachView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _presenter.detachView()
    }
}