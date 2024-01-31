// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript{
    dependencies{
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.49")
       // classpath ("org.jetbrains.kotlin:kotlin-serialization:1.9.0")
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.3")
    }
}
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.safeArgs) apply false
}
true // Needed to make the Suppress annotation work for the plugins block