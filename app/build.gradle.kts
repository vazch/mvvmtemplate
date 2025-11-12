plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.androidx.room)
}

android {
    namespace = "com.vazch.mvvmtemplate"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.vazch.mvvmtemplate"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", "\"https://drawsomething-59328-default-rtdb.europe-west1.firebasedatabase.app/\"")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"https://drawsomething-59328-default-rtdb.europe-west1.firebasedatabase.app/\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        buildConfig = true
        viewBinding = true
    }
    room {
        schemaDirectory("$projectDir/schemas")
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    // Retrofit
    implementation(libs.retrofit)
    // Retrofit with GSON Converter
    implementation(libs.retrofit.converter.gson)
    // OkHttp via BOM (keeps versions aligned)
    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp)
    // Interceptor keep it for debug only
    debugImplementation(libs.logging.interceptor)

    // Lifecycle for viewModelScope (and lifecycleScope)
    implementation(libs.bundles.lifecycle.core)

    // Hilt core
    implementation(libs.hilt.android)

    // KSP
    ksp(libs.hilt.compiler)

    //Room
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)

}