plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.plugin.serialization")
    kotlin("kapt")
}

android {
    namespace = "com.brainrot.android"
    compileSdk = 34

    // Use Java 17 toolchain (auto-download if not present)
    kotlin {
        jvmToolchain(17)
    }

    defaultConfig {
        applicationId = "com.brainrot.android"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        multiDexEnabled = true
    }

    signingConfigs {
        create("release") {
            if (project.hasProperty("storeFile")) {
                storeFile = file(project.property("storeFile") as String)
                storePassword = project.property("storePassword") as String
                keyAlias = project.property("keyAlias") as String
                keyPassword = project.property("keyPassword") as String
            }
        }
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
            signingConfig = signingConfigs.getByName("debug")
            matchingFallbacks += listOf("release")
        }
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
            buildConfigField("boolean", "IS_DEBUG", "false")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs += listOf(
            "-Xopt-in=kotlin.RequiresOptIn",
            "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-Xopt-in=androidx.compose.material3.ExperimentalMaterial3Api",
            "-Xopt-in=androidx.glance.ExperimentalGlanceApi"
        )
    }

    buildFeatures {
        compose = true
        viewBinding = true
        dataBinding = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.11"
    }

    // packagingOptions is deprecated, use packaging
    packaging {
        resources {
            excludes += listOf(
                "META-INF/AL2.0",
                "META-INF/LICENSE",
                "META-INF/LICENSE.txt",
                "META-INF/license.txt",
                "META-INF/NOTICE",
                "META-INF/NOTICE.txt",
                "META-INF/notice.txt",
                "META-INF/DEPENDENCIES",
                "META-INF/kotlinx_coroutines_core.version",
                "META-INF/kotlinx-coroutines-core.kotlin_module"
            )
        }
        jniLibs {
            pickFirsts += listOf(
                "lib/arm64-v8a/libc++_shared.so",
                "lib/armeabi-v7a/libc++_shared.so",
                "lib/x86/libc++_shared.so",
                "lib/x86_64/libc++_shared.so"
            )
        }
    }

    namespace = "com.brainrot.android"
}

dependencies {
    // Core Android
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    implementation("androidx.navigation:navigation-compose:2.7.6")
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")

    // Compose
    val composeBom = platform("androidx.compose:compose-bom:2024.02.00")
    implementation(composeBom)
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material3:material3-window-size-class")
    implementation("androidx.compose.runtime:runtime-livedata")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.animation:animation")
    implementation("androidx.compose.animation:animation-graphics")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    // Glance (Widgets)
    implementation("androidx.glance:glance:1.0.0")
    implementation("androidx.glance:glance-appwidget:1.0.0")
    implementation("androidx.glance:glance-wear-tiles:1.0.0")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.50")
    kapt("com.google.dagger:hilt-compiler:2.50")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")
    kapt("androidx.hilt:hilt-compiler:1.1.0")

    // Coroutines & Flow
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")

    // WorkManager (for background tasks)
    implementation("androidx.work:work-runtime-ktx:2.9.0")

    // DataStore (for preferences)
    implementation("androidx.datastore:datastore-preferences:1.1.1")

    // Biometric (optional, for app lock)
    implementation("androidx.biometric:biometric:1.1.0")

    // Testing
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito:mockito-core:5.11.0")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.1.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    androidTestImplementation("androidx.compose.ui:ui-test-manifest")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-tooling-data")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        freeCompilerArgs += listOf(
            "-Xopt-in=kotlin.RequiresOptIn",
            "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-Xopt-in=androidx.compose.material3.ExperimentalMaterial3Api",
            "-Xopt-in=androidx.glance.ExperimentalGlanceApi"
        )
    }
}