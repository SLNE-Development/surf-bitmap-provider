plugins {
    id("dev.slne.surf.surfapi.gradle.paper-plugin")
}

surfPaperPluginApi {
    mainClass("dev.slne.surf.bitmap.paper.BitmapPaperPlugin")
    generateLibraryLoader(false)
    foliaSupported(true)
    authors.add("Ammo")
}

dependencies {
    api(project(":surf-bitmap-provider-common"))
}