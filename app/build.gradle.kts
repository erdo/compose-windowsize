plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("org.jetbrains.kotlin.plugin.serialization")
}

val appId = "foo.bar.compose"
val composeVersion = rootProject.extra["composeVersion"] as String
val material3Version = rootProject.extra["material3Version"] as String

android {

    compileSdk = 33

    defaultConfig {
        applicationId = appId
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
          kotlinCompilerExtensionVersion = composeVersion
    }
}

dependencies {

    // reactivity
    implementation("co.early.fore:fore-kt-android:1.5.19")
    implementation("co.early.fore:fore-kt-android-compose:$composeVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")

    // persistence
    implementation("co.early.persista:persista:1.1.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")

    // design
    implementation("androidx.compose.material3:material3:$material3Version")
    implementation("androidx.window:window:1.0.0")

    // compose
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    implementation("androidx.activity:activity-compose:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-tooling:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$composeVersion")

    //testing
    testImplementation("junit:junit:4.13.2")
    testImplementation("io.mockk:mockk:1.11.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeVersion")
}
