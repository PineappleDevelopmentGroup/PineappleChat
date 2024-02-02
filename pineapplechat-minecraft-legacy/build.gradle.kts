plugins {
    id("me.champeau.jmh") version "0.7.2"
}

dependencies {
    api(project(":pineapplechat-core"))

    jmh("org.openjdk.jmh:jmh-core:0.9")
    jmh("org.openjdk.jmh:jmh-generator-annprocess:0.9")
}

configurations {
    testImplementation.get().extendsFrom(compileOnly.get())
    jmhImplementation.get().extendsFrom(compileOnly.get())
}

