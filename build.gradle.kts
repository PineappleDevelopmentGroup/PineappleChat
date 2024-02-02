plugins {
    id("java-library")
    id("checkstyle")
    id("maven-publish")
    id("io.freefair.aggregate-javadoc-jar") version "8.4"
}

subprojects {
    apply(plugin = "java-library")
    apply(plugin = "checkstyle")
    apply(plugin = "io.freefair.aggregate-javadoc-jar")
    apply(plugin = "maven-publish")

    dependencies {
        testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.1")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.1")

        compileOnly("org.jetbrains:annotations-java5:24.0.1")
    }

    tasks.test {
        useJUnitPlatform()

        this.testLogging {
            events("passed", "skipped", "failed", "standard_out")
            showStandardStreams = true
        }
    }

    checkstyle {
        toolVersion = "10.12.5"
        configFile = rootProject.file("config/checkstyle/checkstyle.xml")
        this.isIgnoreFailures = false;
        this.isShowViolations = true;
        this.sourceSets = listOf(project.sourceSets.main.get())
    }
    
    tasks.aggregateJavadoc.get().options {
    }

    publishing {
        publications {
            create<MavenPublication>("Maven") {
                from(components["java"])
                this.artifact(tasks.aggregateJavadocJar)
                this.groupId = "sh.miles.pineapplechat"
                this.artifactId = project.name
                this.version = rootProject.version.toString()
            }
        }
        repositories {
            maven {
                this.url = uri("https://maven.miles.sh/libraries")
                credentials {
                    this.username = System.getenv("PINEAPPLE_REPOSILITE_USERNAME")
                    this.password = System.getenv("PINEAPPLE_REPOSILITE_PASSWORD")
                }
            }
        }
    }
}

