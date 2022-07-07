plugins {
    kotlin("jvm") version "1.7.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.arrow-kt:arrow-core:1.1.2")
}

tasks {
    sourceSets {
        main {
            java.srcDirs("src")
        }
    }

    wrapper {
        gradleVersion = "7.3"
    }
}
