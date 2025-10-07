package com.developerstring.jetco_kmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform