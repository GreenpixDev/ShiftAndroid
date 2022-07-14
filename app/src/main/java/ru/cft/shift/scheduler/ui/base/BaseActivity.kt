package ru.cft.shift.scheduler.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.cft.shift.scheduler.di.component.AppComponent
import ru.cft.shift.scheduler.di.component.DaggerAppComponent

abstract class BaseActivity<P : MvpPresenter> : AppCompatActivity(), MvpView {

    lateinit var component: AppComponent
    private set

    abstract val presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component = DaggerAppComponent.create()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        presenter.attachView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}