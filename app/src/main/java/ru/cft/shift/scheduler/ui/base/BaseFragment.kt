package ru.cft.shift.scheduler.ui.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ru.cft.shift.scheduler.di.component.AppComponent
import ru.cft.shift.scheduler.di.component.DaggerAppComponent

abstract class BaseFragment<P : MvpPresenter> : Fragment(), MvpView {

    lateinit var component: AppComponent
    private set

    abstract val presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component = if (context is BaseActivity<*>) {
            (context as BaseActivity<*>).component
        } else {
            DaggerAppComponent.create()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.attachView(this)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun startActivity(intent: Intent?) {
        val context = context
        if (context is Activity && context.intent.hasExtra(BaseActivity.MESSAGE_JWT_TOKEN)) {
            intent?.putExtra(
                BaseActivity.MESSAGE_JWT_TOKEN,
                context.intent.getStringExtra(BaseActivity.MESSAGE_JWT_TOKEN)
            )
        }
        super.startActivity(intent)
    }
}