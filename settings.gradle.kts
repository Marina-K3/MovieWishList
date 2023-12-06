pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()

    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {

        google()
        mavenCentral()

    }
}

rootProject.name = "MovieWishList"
include(":data")
include(":domain")
include(":app")

