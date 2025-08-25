plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    alias(libs.plugins.kotlinAndroidKsp)
    alias(libs.plugins.serialization)
    alias(libs.plugins.google.gms.google.services)
    alias(libs.plugins.google.firebase.crashlytics)
    alias(libs.plugins.apollo)
}

android {
    namespace = "com.genxlead.lingeriePer_FitApp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.genxlead.lingeriePer_FitApp"
        minSdk = 24
        targetSdk = 36
        versionCode = 2
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }
}

apollo {
    service("service") {
        packageName.set("com.gxl.lingerieperfit")
        introspection {
            endpointUrl.set("https://mcstaging2.shyaway.com/graphql/")
            schemaFile.set(file("app/src/main/graphql/schema.graphqls"))
        }
    }
}

tasks.register("downloadSchema") {
    doLast {
        providers.exec {
            commandLine(
                "apollo",
                "schema:download",
                "--endpoint=https://mcstaging2.shyaway.com/graphql/",
                "--output=app/src/main/graphql/schema.graphqls"
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
    implementation(libs.firebase.crashlytics.buildtools)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.coil.compose)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.datastore.core)

    implementation(libs.firebase.analytics)
    implementation(libs.firebase.database)
    implementation(libs.firebase.crashlytics)

    // Core client for Android
    implementation(libs.ktor.client.android)

    // Content negotiation for JSON (uses kotlinx.serialization)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.client.content.negotiation)

    // Logging
    implementation(libs.ktor.client.logging)

    // Optional: Auth (e.g., Bearer token)
    implementation(libs.ktor.client.auth)

    //Koin
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.compose.viewmodel)
    implementation(libs.koin.compose.viewmodel.navigation)

    implementation(libs.androidx.ui.text.google.fonts)

    //Splash
    implementation (libs.androidx.core.splashscreen)

    //AppCompat
    implementation(libs.accompanist.systemuicontroller)

    //Apollo
    implementation(libs.apollo.runtime)

    // OkHttp
    implementation(libs.okhttp)

// OkHttp logging interceptor (needed for HttpLoggingInterceptor)
    implementation(libs.logging.interceptor)
    // Facebook
    implementation(libs.facebook.android.sdk)

    // App update
    implementation(libs.app.update)
    implementation(libs.app.update.ktx)


}