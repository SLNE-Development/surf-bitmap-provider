rootProject.name = "surf-bitmap-provider"

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://repo.slne.dev/repository/maven-public/") { name = "maven-public" }
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
    id("dev.slne.surf.api.gradle.settings") version "26.1+"
}

include("surf-bitmap-provider-common")
include("surf-bitmap-provider-paper")
include("surf-bitmap-provider-velocity")

include("surf-bitmap-provider-placeholders")