plugins {
    //alias(libs.plugins.androidApplication)
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "filrouge.app"
    compileSdk = 34

    defaultConfig {
        applicationId = "filrouge.app"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
    // Import the Firebase BoM
    //noinspection UseTomlInstead
    implementation(platform("com.google.firebase:firebase-bom:32.7.4"))
    //noinspection UseTomlInstead
    implementation("com.google.firebase:firebase-analytics")
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Pour l'authentification
    implementation (libs.firebase.ui.auth)
    //noinspection UseTomlInstead
    // implementation ("com.firebaseui:firebase-ui-auth:7.2.0")

    implementation ("com.squareup.retrofit2:converter-jackson:2.7.2")
    implementation ("com.fasterxml.jackson.core:jackson-databind:2.10.3")
    implementation ("com.fasterxml.jackson.core:jackson-core:2.10.3")
    implementation ("com.fasterxml.jackson.core:jackson-annotations:2.10.3")

    implementation (libs.picasso)


}
