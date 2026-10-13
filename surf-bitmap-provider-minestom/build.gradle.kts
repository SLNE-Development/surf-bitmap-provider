import dev.slne.surf.api.gradle.util.slneReleases

plugins {
    id("dev.slne.surf.api.gradle.minestom")
}

minestomPluginFile {
    main = "dev.slne.surf.bitmap.minestom.BitmapMinestomPlugin"
    authors = listOf("Ammo")
}

dependencies {
    api(projects.surfBitmapProviderCommon)
}

publishing {
    repositories {
        slneReleases()
    }
}
