plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.project"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.project"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        viewBinding = true
    }
}

//buildscript {
//    repositories {
//        google()
//        mavenCentral() // Gantikan jcenter() dengan mavenCentral()
//        maven("https://jitpack.io") // Tambahkan repositori JitPack untuk dependensi eksternal
//    }
//    dependencies {
//        classpath(libs.gradle) // Gunakan versi terbaru Gradle Plugin
//    }
//}

//allprojects {
//    repositories {
//        google()
//        mavenCentral() // Gantikan jcenter() dengan mavenCentral()
//        maven("https://jitpack.io") // Tambahkan repositori JitPack
//    }
//}

dependencies {

    implementation(libs.mpandroidchart)

    implementation(libs.room.runtime)
    implementation(libs.navigation.runtime)
    implementation(libs.navigation.ui)
    implementation(libs.navigation.fragment)
    annotationProcessor(libs.room.compiler)

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}