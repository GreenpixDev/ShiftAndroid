package ru.cft.shift.scheduler.service

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

class SessionCookieJar : CookieJar {

    private val cookies = hashMapOf<String, Cookie>()

    override fun saveFromResponse(url: HttpUrl, cookies: MutableList<Cookie>) {
        this.cookies.putAll(cookies.associateBy { it.name() })
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        return cookies.values.toList()
    }

    fun addCookie(cookie: Cookie) {
        cookies[cookie.name()] = cookie
    }

    operator fun get(name: String): Cookie? {
        return cookies[name]
    }
}