@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    id("kotlin-android")
    id("kotlin-kapt")

}

android {
    namespace = "by.bsuir.ief172303.data"
    compileSdk = 33

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {

    implementation(project(":domain"))

    implementation("androidx.room:room-runtime:2.6.0")
    kapt("androidx.room:room-compiler:2.6.0") // Используем kapt для Room
    implementation("androidx.room:room-ktx:2.6.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.10")

    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
    implementation ("com.google.code.gson:gson:2.8.8")

    implementation("io.ktor:ktor-client-features:1.6.4")
    implementation ("io.ktor:ktor-client-serialization:2.3.4")
    implementation ("io.ktor:ktor-client-cio:2.3.4")
    implementation("io.ktor:ktor-client-okhttp:1.6.4")
    implementation("io.ktor:ktor-client-json:1.6.4")
    implementation("io.ktor:ktor-client-logging:1.6.4")
    implementation("io.ktor:ktor-client-core:1.6.4")
    implementation("io.ktor:ktor-client-json-jvm:1.6.4")

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}