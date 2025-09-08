plugins {
    id("dev.slne.surf.surfapi.gradle.velocity")
}

velocityPluginFile {
    main = "dev.slne.surf.bitmap.velocity.BitmapVelocityPlugin"
    authors = listOf("Ammo")

    pluginDependencies {
        register("miniplaceholders")
    }
}

dependencies {
    api(project(":surf-bitmap-provider-common"))
    compileOnly(libs.miniplaceholders.api)
}