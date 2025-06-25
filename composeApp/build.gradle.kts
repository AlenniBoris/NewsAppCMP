import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
    kotlin("plugin.serialization") version "2.2.0"
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }

    jvm("desktop")

    sourceSets {
        val desktopMain by getting

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)

            // Koin
            implementation("io.insert-koin:koin-android")
            implementation("io.insert-koin:koin-androidx-compose")

            //Ktor
            implementation("io.ktor:ktor-client-okhttp:3.2.0")
        }
        android {
            buildFeatures {
                buildConfig = true
            }
            defaultConfig {
                val apikeys = Properties().apply {
                    val file = rootProject.file("apikeys.properties")
                    if (file.exists()) {
                        load(file.inputStream())
                    }
                }

                val apiKey: String = apikeys.getProperty("NEWS_API_KEY") ?: ""
                buildConfigField("String", "NEWS_API_KEY", apiKey)
            }
        }

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            // Koin
            implementation(project.dependencies.platform("io.insert-koin:koin-bom:4.1.0"))
            implementation("io.insert-koin:koin-core")

            // Ktor
            implementation("io.ktor:ktor-client-core:3.2.0")
            implementation("io.ktor:ktor-client-content-negotiation:3.2.0")
            implementation("io.ktor:ktor-serialization-kotlinx-json:3.2.0")
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.1")
            implementation("io.ktor:ktor-client-logging:3.2.0")

            //Napier
            implementation("io.github.aakira:napier:2.7.1")

            // Time parse
            implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.7.0")
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutinesSwing)

            // Ktor
            implementation("io.ktor:ktor-client-apache:3.2.0")
            implementation("io.ktor:ktor-client-logging-jvm:3.2.0")

            implementation("androidx.collection:collection:1.5.0")

            implementation("org.slf4j:slf4j-simple:2.0.17")

            implementation("io.insert-koin:koin-core-jvm")
        }
    }
}

android {
    namespace = "com.alenniboris.newsappcmp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.alenniboris.newsappcmp"
        minSdk = 35
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

compose.desktop {
    application {
        mainClass = "com.alenniboris.newsappcmp.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.alenniboris.newsappcmp"
            packageVersion = "1.0.0"
        }
    }
}
