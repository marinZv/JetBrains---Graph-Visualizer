import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {
    implementation(compose.desktop.currentOs)
    implementation("net.sourceforge.plantuml:plantuml:1.2025.2")
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.3")
    testImplementation("io.kotest:kotest-assertions-core:5.7.0")
}

tasks.test {
    useJUnitPlatform()
}


compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "graph-visualizer"
            packageVersion = "1.0.0"
        }
    }
}
