import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.*

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.room)
    alias(libs.plugins.ksp)
}


val buildConfigGenerator by tasks.registering(Sync::class) {
    var baseUrl = ""
    var apiKey = ""
    var imageUrl = ""
    try {
        val properties = Properties().apply {
            file("local.properties").reader().use(::load)
        }
        baseUrl = properties["BASE_URL"].toString()
        apiKey = properties["API_KEY"].toString()
        imageUrl = properties["IMAGE_URL"].toString()
    } catch (ignore: Exception) {

    }

    from(
        resources.text.fromString(
            """
        |package com.pien.moviekmm
        |
        |object BuildConfig {
        |  const val BASE_URL = "$baseUrl"
        |  const val API_KEY = "$apiKey"
        |  const val IMAGE_URL = "$imageUrl"
        |}
        |
      """.trimMargin()
        )
    ) {
        rename { "BuildConfig.kt" }
        into("com/pien/moviekmm")
    }

    into(layout.buildDirectory.dir("generated-src/kotlin/"))
}
kotlin {
    androidTarget {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_1_8)
                }
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            kotlin.srcDir(
                buildConfigGenerator.map { it.destinationDir }
            )
            dependencies {
                // Networking - Ktor
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.auth)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.serialization.kotlinx.json)
                implementation(libs.ktor.client.logging)

                // Image Loading - Coil
                implementation(libs.coil.compose)
                implementation(libs.coil.network.ktor3)

                // Serialization & Coroutines
                implementation(libs.kotlinx.serialization.json)
                implementation(libs.kotlinx.coroutines.core)

                // Room
                implementation(libs.sqlite.bundled)
                api(libs.androidx.room.runtime)
            }
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.ktor.android)
                implementation(libs.accompanist.drawablepainter)
            }
        }
        val iosMain by creating {
            dependencies {
                implementation(libs.ktor.ios)
            }
        }
    }
}


room {
    schemaDirectory("$projectDir/schemas")
}

android {
    namespace = "com.pien.moviekmm"
    compileSdk = 35
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(libs.play.services.auth)
    ksp(libs.androidx.room.compiler)
}
