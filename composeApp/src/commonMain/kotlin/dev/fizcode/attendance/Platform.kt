package dev.fizcode.attendance

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform