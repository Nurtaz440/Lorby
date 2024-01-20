@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.safeArgs)
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = "com.example.lorby"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.lorby"
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
    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)


    implementation(libs.viewmodel)
    implementation(libs.livedata)
    implementation(libs.runtime)
    implementation(libs.extensionsArg)
    implementation(libs.coroutineCore)
    implementation(libs.coroutineAndroid)
    implementation(libs.okhttp)
    implementation(libs.okhhtpInterceptor)
    implementation(libs.retrofite)
    implementation(libs.retrofiteAdapter)
    implementation(libs.retrofiteGson)
    implementation(libs.gson)
    implementation(libs.pinview)

//viewBinding
    implementation("com.github.kirich1409:viewbindingpropertydelegate:1.5.6")
    // Dagger - Hilt
    implementation("com.google.dagger:hilt-android:2.49")
    kapt("com.google.dagger:hilt-android-compiler:2.49")
    kapt("androidx.hilt:hilt-compiler:1.0.0")

}
kapt {
    correctErrorTypes = true
}
hilt {
    enableAggregatingTask = true
}