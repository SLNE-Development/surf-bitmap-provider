import dev.slne.surf.surfapi.gradle.util.slneReleases

plugins {
    id("dev.slne.surf.surfapi.gradle.core")
}

dependencies {
    compileOnlyApi(libs.miniplaceholders.api)
}

publishing {
    repositories {
        slneReleases()
    }
}