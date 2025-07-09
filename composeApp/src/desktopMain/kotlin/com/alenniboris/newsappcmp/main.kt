package com.alenniboris.newsappcmp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.alenniboris.newsappcmp.di.commonModules
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.core.context.startKoin
import java.util.Properties

fun main() = application {

    println("Приложение запускается...")

    val apikeys = Properties().apply {
        val stream = Thread.currentThread().contextClassLoader
            .getResourceAsStream("desktop.apikeys.properties")
        requireNotNull(stream) { "desktop.apikeys.properties not found in resources" }
        load(stream)
    }
    System.setProperty("NEWS_API_KEY", apikeys.getProperty("NEWS_API_KEY") ?: "")

    startKoin {
        modules(commonModules)
    }
    Napier.base(DebugAntilog())

    Window(
        onCloseRequest = ::exitApplication,
        title = "NewsAppCMP",
    ) {
        App()
    }
}