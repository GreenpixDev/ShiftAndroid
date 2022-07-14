package ru.cft.shift.scheduler.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.cft.shift.scheduler.di.component.ApplicationComponent
import ru.cft.shift.scheduler.di.component.DaggerApplicationComponent

abstract class BaseActivity<P : MvpPresenter> : AppCompatActivity(), MvpView {

    private lateinit var _presenter: P

    private lateinit var _component: ApplicationComponent

    val presenter: P
    get() = _presenter

    val component: ApplicationComponent
    get() = _component

    protected abstract fun createPresenter(): P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _presenter = createPresenter()
        _presenter.attachView(this)
        _component = DaggerApplicationComponent.create()
    }

    override fun onDestroy() {
        super.onDestroy()
        _presenter.detachView()
    }
}