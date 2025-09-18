import dev.slne.surf.surfapi.gradle.util.registerRequired
import net.minecrell.pluginyml.paper.PaperPluginDescription

plugins {
    id("dev.slne.surf.surfapi.gradle.paper-plugin")
}

surfPaperPluginApi {
    mainClass("dev.slne.surf.bitmap.paper.BitmapPaperPlugin")
    generateLibraryLoader(false)
    foliaSupported(true)
    authors.add("Ammo")

    serverDependencies {
        registerRequired(
            "MiniPlaceholders",
            joinClassPath = true,
            loadOrder = PaperPluginDescription.RelativeLoadOrder.BEFORE
        )
    }
}

dependencies {
    api(project(":surf-bitmap-provider-common"))
}