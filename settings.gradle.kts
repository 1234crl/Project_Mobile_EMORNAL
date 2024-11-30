@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal() // Jika Anda menggunakan plugin Gradle
        maven("https://jitpack.io") // Repositori tambahan jika diperlukan
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS) // Atur preferensi untuk settings repositories
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

rootProject.name = "Project"
include(":app")
 