buildscript {
    repositories {
        gradlePluginPortal()
        maven("https://repo.slne.dev/repository/maven-public/") { name = "maven-public" }
    }
    dependencies {
        classpath("dev.slne.surf:surf-api-gradle-plugin:26.1+")
    }
}

allprojects {
    group = "dev.slne.surf.bitmap"
    version = findProperty("version") as String
}