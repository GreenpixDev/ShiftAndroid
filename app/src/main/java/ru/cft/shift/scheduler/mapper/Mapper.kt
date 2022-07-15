package ru.cft.shift.scheduler.mapper

interface Mapper<FROM, TO> {

    fun map(obj: FROM): TO

}