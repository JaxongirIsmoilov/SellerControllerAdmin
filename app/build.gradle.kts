plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("kotlin-kapt")
    id ("com.google.dagger.hilt.android")
}

android {
    namespace = "uz.gita.jaxongir.sellmanageradmin"
    compileSdk = 34

    defaultConfig {
        applicationId = "uz.gita.jaxongir.sellmanageradmin"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.compose.material:material:1.5.3")
    implementation("com.google.firebase:firebase-messaging-ktx:23.3.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.navigation:navigation-compose:2.7.4")
    implementation("androidx.compose.ui:ui-util:1.6.0-alpha07")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.30.1")

    implementation ("androidx.compose.material3:material3:1.2.0-alpha10")
    //FireBase
    implementation(platform("com.google.firebase:firebase-bom:32.6.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-database")
    implementation("com.google.firebase:firebase-firestore")
    implementation("com.google.firebase:firebase-storage")


    /*
    *   voyager
    * */
    val voyagerVersion = "1.0.0-rc06"
    // Navigator
    implementation("cafe.adriel.voyager:voyager-navigator:$voyagerVersion")

    // TabNavigator
    implementation("cafe.adriel.voyager:voyager-tab-navigator:$voyagerVersion")

    // Transitions
    implementation("cafe.adriel.voyager:voyager-transitions:$voyagerVersion")

    // Android

    // Android ViewModel integration
    implementation("cafe.adriel.voyager:voyager-androidx:$voyagerVersion")

    // Koin integration
    implementation("cafe.adriel.voyager:voyager-koin:$voyagerVersion")

    // Hilt integration
    implementation("cafe.adriel.voyager:voyager-hilt:$voyagerVersion")

    // LiveData integration
    implementation("cafe.adriel.voyager:voyager-livedata:$voyagerVersion")

    /*
    *   Dagger hilt
    * */
    implementation("com.google.dagger:hilt-android:2.44.2")
    kapt("com.google.dagger:hilt-android-compiler:2.44.2")

    //Json
    implementation ("com.google.code.gson:gson:2.10.1")

    // ROOM
    val room_version = "2.6.0"
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    kapt("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")

    //Coil
    implementation("io.coil-kt:coil-compose:2.5.0")

    //Timber
    implementation ("com.jakewharton.timber:timber:5.0.1")


    //UiController
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.30.1")

    //Glide
    implementation ("com.github.bumptech.glide:compose:1.0.0-beta01")

    implementation ("com.karumi:dexter:6.2.3")

    implementation("com.google.accompanist:accompanist-permissions:0.30.1")
    // When using Kotlin.
    kapt("androidx.hilt:hilt-compiler:1.0.0")
    implementation ("androidx.hilt:hilt-work:1.0.0")
    implementation ("androidx.work:work-runtime-ktx:2.7.1")


}