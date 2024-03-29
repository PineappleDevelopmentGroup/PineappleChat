plugins {
    id("me.champeau.jmh") version "0.7.2"
}

dependencies {
    // https://hub.spigotmc.org/stash/projects/SPIGOT/repos/spigot/browse/Bukkit-Patches/0010-BungeeCord-Chat-API.patch#15-21
    compileOnly("net.md-5:bungeecord-chat:1.20-R0.1")
    api(project(":pineapplechat-core"))

    jmh("org.openjdk.jmh:jmh-core:0.9")
    jmh("org.openjdk.jmh:jmh-generator-annprocess:0.9")
}

configurations {
    testImplementation.get().extendsFrom(compileOnly.get())
    jmhImplementation.get().extendsFrom(compileOnly.get())
}
