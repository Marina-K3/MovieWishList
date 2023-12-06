import org.gradle.kotlin.dsl.`kotlin-dsl`

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    namespace = "by.bsuir.ief172303.kotova_marina"
    compileSdk = Configs.compileSdk

    defaultConfig {
        applicationId = "by.bsuir.ief172303.kotova_marina"
        minSdk = Configs.minSdk
        targetSdk = Configs.targetSdk
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
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Configs.composeKotlinCompilerExtensionVersion
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }


}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation(Dependencies.coil)
    implementation(Dependencies.coilCompose)
    implementation(Dependencies.navigationCompose)
    implementation(Dependencies.appcompat)
    implementation(Dependencies.datastore)
    implementation(Dependencies.workRuntime)
    implementation(Dependencies.lifecycleViewModel)
    implementation(Dependencies.lifecycleRuntime)
    implementation(Dependencies.lifecycleModel)
    implementation(Dependencies.pagingRuntime)
    implementation(Dependencies.composeMaterial)
    implementation(Dependencies.composeUITooling)
    implementation(Dependencies.logbackClassic)
    implementation(Dependencies.kotlinxCoroutinesCore)
    implementation(Dependencies.kotlinxCoroutinesAndroid)
    implementation(Dependencies.kotlinStdlib)
    implementation(Dependencies.ktorClientFeatures)
    implementation(Dependencies.ktorClientSerialization)
    implementation(Dependencies.ktorClientCio)
    implementation(Dependencies.kotlinxSerializationJson)
    implementation(Dependencies.gson)
    implementation(Dependencies.koinAndroid)
    implementation(Dependencies.koinAndroidxCompose)
    implementation(Dependencies.roomRuntime)
    kapt(Dependencies.roomCompiler)
    implementation(Dependencies.roomKtx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
}