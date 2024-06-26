plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id("com.google.gms.google-services")
    id ("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id ("dagger.hilt.android.plugin")
}

android {
    namespace = "com.choi.coffee_kiosks"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.choi.coffee_kiosks"
        minSdk = 23
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.fragment:fragment-ktx:1.6.2")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Splash Screen
    implementation("androidx.core:core-splashscreen:1.1.0-alpha02")

    // RxBinding
    implementation("io.reactivex.rxjava3:rxandroid:3.0.2")
    implementation("io.reactivex.rxjava3:rxjava:3.0.3")

    implementation ("com.jakewharton.rxbinding4:rxbinding:4.0.0")

    // Fancy Toast
    implementation("io.github.shashank02051997:FancyToast:2.0.2")

    // google map
    implementation ("com.google.android.gms:play-services-maps:18.2.0")
    implementation ("com.google.android.gms:play-services-location:21.0.1")

    // retrofit2
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.10.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Hilt
    implementation ("com.google.dagger:hilt-android:2.48.1")
    kapt ("com.google.dagger:hilt-compiler:2.48.1")

    // FlowBinding
    implementation("io.github.reactivecircus.flowbinding:flowbinding-android:1.2.0")
    implementation("io.github.reactivecircus.flowbinding:flowbinding-material:1.2.0")
    implementation("io.github.reactivecircus.flowbinding:flowbinding-activity:1.2.0")
    implementation("io.github.reactivecircus.flowbinding:flowbinding-appcompat:1.2.0")
    implementation("io.github.reactivecircus.flowbinding:flowbinding-core:1.2.0")
    implementation("io.github.reactivecircus.flowbinding:flowbinding-lifecycle:1.2.0")
    implementation("io.github.reactivecircus.flowbinding:flowbinding-navigation:1.2.0")
    implementation("io.github.reactivecircus.flowbinding:flowbinding-preference:1.2.0")
    implementation("io.github.reactivecircus.flowbinding:flowbinding-recyclerview:1.2.0")
    implementation("io.github.reactivecircus.flowbinding:flowbinding-viewpager2:1.2.0")

    // RxJava
    implementation ("io.reactivex.rxjava3:rxjava:3.0.3")
    implementation ("io.reactivex.rxjava3:rxandroid:3.0.0")

    // RxBinding
    implementation ("com.jakewharton.rxbinding4:rxbinding:4.0.0")
    implementation ("com.jakewharton.rxbinding4:rxbinding-material:4.0.0")
    implementation("com.jakewharton.rxbinding4:rxbinding-core:4.0.0")
    implementation ("com.jakewharton.rxbinding4:rxbinding-appcompat:4.0.0")
    implementation ("com.jakewharton.rxbinding4:rxbinding-drawerlayout:4.0.0")
    implementation ("com.jakewharton.rxbinding4:rxbinding-leanback:4.0.0")
    implementation ("com.jakewharton.rxbinding4:rxbinding-recyclerview:4.0.0")
    implementation ("com.jakewharton.rxbinding4:rxbinding-slidingpanelayout:4.0.0")
    implementation ("com.jakewharton.rxbinding4:rxbinding-swiperefreshlayout:4.0.0")
    implementation ("com.jakewharton.rxbinding4:rxbinding-viewpager:4.0.0")
    implementation ("com.jakewharton.rxbinding4:rxbinding-viewpager2:4.0.0")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.5.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-database-ktx:20.3.0")

    // ViewPager2
    implementation("androidx.viewpager2:viewpager2:1.0.0")

    // Lottie
    implementation ("com.airbnb.android:lottie:6.2.0")
}
//hilt debugging
kapt {
    correctErrorTypes; true
}