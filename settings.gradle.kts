pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://aws.oss.sonatype.org/content/repositories/snapshots")
        }
    }
}
rootProject.name = "Cloud Balance"
include(":app")
