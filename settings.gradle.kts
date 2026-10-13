rootProject.name = "surf-bitmap-provider"

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://reposilite.slne.dev/public/") { name = "public" }
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
    id("dev.slne.surf.api.gradle.settings") version "+"
}

include("surf-bitmap-provider-common")
include("surf-bitmap-provider-paper")
include("surf-bitmap-provider-velocity")
include("surf-bitmap-provider-minestom")

include("surf-bitmap-provider-placeholders")
