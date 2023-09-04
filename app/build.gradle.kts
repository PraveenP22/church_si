plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.church_si"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.church_si"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isShrinkResources = true
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                "proguard-rules.pro"
            )
        }

        getByName("debug") {
            isShrinkResources = true
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
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
        dataBinding = true
        buildConfig = true

    }


    flavorDimensions += "default"
    productFlavors {
        create("tst") {
            applicationId =  "com.example.tstchurch_si"
            buildConfigField ("String", "BASE_URL", "\"${properties["tst_base_url"]}\"")
            buildConfigField ("String", "APP_VERSION", "\"${properties["tst_version"]}\"")
            resValue ( "string", "app_name","\"${properties["tst_app_name"]}\"" )
            versionCode = 1
            versionName  = "0.0.1"
        }
        create("uat") {
            applicationId = "com.izamedia.uatchurch_si"
            buildConfigField ("String", "BASE_URL", "\"${properties["uat_base_url"]}\"")
            buildConfigField ("String", "APP_VERSION", "\"${properties["uat_version"]}\"")
            resValue ( "string", "app_name","\"${properties["uat_app_name"]}\"" )
            versionCode = 1
            versionName  ="0.0.1"
        }
        create("prd") {
            applicationId = "com.example.church_si"
            buildConfigField ("String", "BASE_URL", "\"${properties["prd_base_url"]}\"")
            buildConfigField ("String", "APP_VERSION", "\"${properties["prd_version"]}\"")
            resValue ( "string", "app_name","\"${properties["prd_app_name"]}\"" )
            versionCode = 1
            versionName = "0.0.1"
        }
    }

}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation ("android.arch.lifecycle:extensions:1.1.1")


    //multidex
    implementation("androidx.multidex:multidex:2.0.1")
    //retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.google.code.gson:gson:2.10.1")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.4.1")

    //volley
    implementation ("com.android.volley:volley:1.2.1")
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")

    //preference Datastore
    implementation ("androidx.datastore:datastore-preferences:1.0.0")


// Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.6.4")

    // Coroutine Lifecycle Scopes
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")

    //Viewpager Implementation
    implementation ("androidx.viewpager2:viewpager2:1.0.0")

    //CircleIndicator
    implementation ("me.relex:circleindicator:2.1.6")

    //lottify
    implementation ("com.airbnb.android:lottie:4.1.0")
}