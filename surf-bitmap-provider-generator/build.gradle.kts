import dev.slne.surf.surfapi.gradle.util.registerRequired
import dev.slne.surf.surfapi.gradle.util.withSurfApiBukkit

plugins {
    id("dev.slne.surf.surfapi.gradle.paper-plugin")
}

surfPaperPluginApi {
    mainClass("dev.slne.surf.bitmap.generator.BitmapProviderPlugin")
    authors.add("Ammo")

    generateLibraryLoader(false)

    serverDependencies {
        registerRequired("Nexo")
    }

    runServer {
        withSurfApiBukkit()
    }
}

dependencies {
    api(project(":surf-bitmap-provider-bitmaps"))

    compileOnly(libs.nexo)
}