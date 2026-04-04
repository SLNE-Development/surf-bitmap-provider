plugins {
    id("dev.slne.surf.api.gradle.velocity")
}

velocityPluginFile {
    main = "dev.slne.surf.bitmap.velocity.BitmapVelocityPlugin"
    authors = listOf("Ammo")

    pluginDependencies {
        register("miniplaceholders")
    }
}

dependencies {
    api(projects.surfBitmapProviderCommon)
}