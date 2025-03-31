plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "ie.setu.cloudbalance_00"
    compileSdk = 35

    defaultConfig {
        applicationId = "ie.setu.cloudbalance_00"
        minSdk = 30
        targetSdk = 35
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

    // ✅ Add this to enable Jetpack Compose
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10"
    }

    packaging {
        resources {
            excludes += setOf(
                "META-INF/DEPENDENCIES",
                "META-INF/LICENSE",
                "META-INF/LICENSE.txt",
                "META-INF/license.txt",
                "META-INF/NOTICE",
                "META-INF/NOTICE.txt",
                "META-INF/INDEX.LIST",
                "META-INF/io.netty.versions.properties" // 👈 add this line
            )
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
//    implementation(libs.androidx.security.crypto.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // ✅ Updated Jetpack Compose Dependencies
    implementation("androidx.compose.ui:ui:1.5.3")
    implementation("androidx.compose.material:material:1.5.3")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.3")
    implementation("androidx.activity:activity-compose:1.8.1")

    // ✅ ViewModel for Jetpack Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

    // ✅ Required for @Preview annotation
    debugImplementation("androidx.compose.ui:ui-tooling:1.5.3")

    // ✅ Retrofit (API Client)
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // ✅ Coroutines (For Asynchronous Operations)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    // Navigation
    implementation ("androidx.navigation:navigation-compose:2.7.5")
// Material 3
    implementation ("androidx.compose.material3:material3:1.1.2")

    implementation ("androidx.navigation:navigation-compose:2.7.6")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation ("androidx.activity:activity-compose:1.8.1")
    implementation ("androidx.compose.material:material:1.5.4")

// Cognito
    implementation("aws.sdk.kotlin:cognitoidentityprovider:0.25.0-beta") // check for latest version
    implementation(libs.aws.cognito.idp)
    implementation(libs.aws.config)

//    implementation("androidx.security:security-crypto:1.1.0-alpha03")
    implementation(libs.androidx.security.crypto)

}
