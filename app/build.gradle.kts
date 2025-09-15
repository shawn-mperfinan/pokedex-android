import com.google.firebase.crashlytics.buildtools.gradle.CrashlyticsExtension
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.devtools.ksp)
    alias(libs.plugins.google.services)
    alias(libs.plugins.firebase.crashlytics)
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
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.crashlytics.ndk)

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
