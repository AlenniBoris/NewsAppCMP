package com.alenniboris.newsappcmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform