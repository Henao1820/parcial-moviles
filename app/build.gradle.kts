plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.googleServices)
}

android {
    namespace = "com.myapp11"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.myapp11"
        minSdk = 28
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
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("com.google.firebase:firebase-auth:22.1.1")
    implementation ("com.google.firebase:firebase-database:20.2.2")
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}