rootProject.name = "PineappleChat"

gradle.rootProject {
    this.version = "1.0.0-SNAPSHOT"
    this.group = "sh.miles"
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        maven { url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/") }
        maven { url = uri("https://oss.sonatype.org/content/repositories/central") }
    }
}

include("pineapplechat-core")
include("pineapplechat-bungee")
include("pineapplechat-minecraft-legacy")

