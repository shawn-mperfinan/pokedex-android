import com.google.firebase.crashlytics.buildtools.gradle.CrashlyticsExtension
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.devtools.ksp)
    alias(libs.plugins.android.hilt)
    alias(libs.plugins.google.services)
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.roboelectric.extension)
    alias(libs.plugins.kotlin.serialization)
}

// Load local.properties if it exists
val localProperties =
    File(rootDir, "local.properties").let { file ->
        Properties().apply {
            if (file.exists()) {
                file.inputStream().use { load(it) }
            } else {
                println("⚠️ No local.properties found, using defaults")
            }
        }
    }

// Calculates and get the versionCode based on commit counts
fun getVersionCode(): Int {
    // Run the Git command and get the commit count result as an integer
    val commitCount =
        providers.exec {
            commandLine("git", "rev-list", "--no-merges", "--count", "HEAD")
        }.standardOutput.asText.get().trim().toInt()

    // kindly refer to the current value of versionName based on its MAJOR, MINOR and PATCH value then append 000
    // current value --> versionName = 1.0.0
    val semanticVersionCombination = 100000

    return commitCount + semanticVersionCombination
}

android {
    namespace = "dev.mperfinan.pokedex"
    compileSdk = 36

    defaultConfig {
        applicationId = "dev.mperfinan.pokedex"
        minSdk = 33
        targetSdk = 36
        versionName = "1.0.0"
        versionCode = getVersionCode()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(JavaVersion.VERSION_21.toString())
        }
    }

    kotlin {
        compilerOptions {
            freeCompilerArgs.add("-Xcontext-parameters")
        }
    }

    ksp {
        /**
         * The schemas directory contains a schema file for each version of the Room database.
         * This is required to enable Room auto migrations.
         * See https://developer.android.com/reference/kotlin/androidx/room/AutoMigration.
         */
        arg("room.schemaLocation", "$projectDir/schemas")
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    signingConfigs {
        create("release") {
            val keyAlias = localProperties.getProperty("ANDROID_KEY_ALIAS")
            val storePassword = localProperties.getProperty("ANDROID_KEYSTORE_PASSWORD")
            val keyPassword = localProperties.getProperty("ANDROID_KEY_PASSWORD")
            val storeFilePath = "../release/secret/android-keystore.jks"

            if (keyAlias != null && storePassword != null && keyPassword != null) {
                storeFile = file(storeFilePath)
                this.keyAlias = keyAlias
                this.storePassword = storePassword
                this.keyPassword = keyPassword
            } else {
                println("⚠️ Skipping release signingConfig — no credentials found")
            }
        }
    }

    flavorDimensions += "type"
    productFlavors {
        create("dev") {
            dimension = "type"
            applicationId = "dev.mperfinan.pokedex.dev"
            versionNameSuffix = "-dev"

            configure<CrashlyticsExtension> {
                mappingFileUploadEnabled = false
            }
        }

        create("live") {
            dimension = "type"

            configure<CrashlyticsExtension> {
                mappingFileUploadEnabled = true
            }
        }
    }

    buildTypes {
        getByName("debug") {
            // Don’t upload mapping files for debug builds
            configure<CrashlyticsExtension> {
                mappingFileUploadEnabled = false
            }
        }

        getByName("release") {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = true
            isShrinkResources = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
    }
}

dependencies {
    // AndroidX
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.core.ktx)

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.crashlytics.ndk)

    // Hilt
    implementation(libs.google.dagger.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    ksp(libs.google.dagger.hilt.android.compiler)

    // Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.paging)
    ksp(libs.androidx.room.compiler)

    // Preferences Datastore
    implementation(libs.androidx.datastore.preferences)

    // Retrofit
    implementation(libs.squareup.retrofit2)
    implementation(libs.squareup.retrofit2.converter.gson)
    implementation(libs.google.gson)
    implementation(libs.squareup.okhttp3.logging.interceptor)

    // Coil Image Loading
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    // MockWebserver
    testImplementation(libs.squareup.okhttp3.mockwebserver)

    // Room test helpers
    testImplementation(libs.androidx.room.testing)

    // JUnit 4
    testImplementation(libs.junit4)

    // JUnit 5
    testImplementation(libs.junit5.api)
    testRuntimeOnly(libs.junit5.engine)

    // Robolectric
    testImplementation(libs.robolectric)

    // Coroutines
    testImplementation(libs.kotlinx.coroutines.test)

    // Truth & Turbine
    testImplementation(libs.cash.app.turbine)
    testImplementation(libs.google.truth)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.navigation.testing)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
