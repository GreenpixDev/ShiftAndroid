package ru.cft.shift.scheduler.ui.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import okhttp3.Cookie
import ru.cft.shift.scheduler.di.component.AppComponent
import ru.cft.shift.scheduler.di.component.DaggerAppComponent
import ru.cft.shift.scheduler.di.module.RetrofitModule

abstract class BaseActivity<P : MvpPresenter> : AppCompatActivity(), MvpView {

    companion object {
        const val MESSAGE_JWT_TOKEN = "jwt_token"
        const val COOKIE_JWT_TOKEN_NAME = "bezkoder"
        const val COOKIE_JWT_TOKEN_PATH = "/api"
    }

    lateinit var component: AppComponent
    private set

    abstract val presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component = DaggerAppComponent.create()

        if (intent.hasExtra(MESSAGE_JWT_TOKEN)) {
            component.cookieJar.addCookie(Cookie.Builder()
                .domain(RetrofitModule.DOMAIN)
                .name(COOKIE_JWT_TOKEN_NAME)
                .value(intent.getStringExtra(MESSAGE_JWT_TOKEN)!!)
                .path(COOKIE_JWT_TOKEN_PATH)
                .httpOnly()
                .build()
            )
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        presenter.attachView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun startActivity(intent: Intent?) {
        if (this.intent.hasExtra(MESSAGE_JWT_TOKEN)) {
            intent?.putExtra(
                MESSAGE_JWT_TOKEN,
                this.intent.getStringExtra(MESSAGE_JWT_TOKEN)
            )
        }
        super.startActivity(intent)
    }
}