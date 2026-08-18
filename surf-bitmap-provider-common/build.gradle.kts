import dev.slne.surf.api.gradle.util.slneReleases

plugins {
    id("dev.slne.surf.api.gradle.core")
}

dependencies {
    compileOnlyApi(libs.miniplaceholders.api)
}

sourceSets.test {
    compileClasspath += sourceSets.main.get().compileClasspath
    runtimeClasspath += sourceSets.main.get().compileClasspath
}

publishing {
    repositories {
        slneReleases()
    }
}
