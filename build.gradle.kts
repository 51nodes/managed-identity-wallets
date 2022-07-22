val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val kompendium_version: String by project
val exposed_version: String by project
val version: String by project

plugins {
    application
    kotlin("jvm") version "1.6.10"
                id("org.jetbrains.kotlin.plugin.serialization") version "1.6.10"
}

group = "org.eclipse.tractusx"
application {
    mainClass.set("org.eclipse.tractusx.managedidentitywallets.ApplicationKt")
}

repositories {
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap") }
    maven { url = uri("https://repo.danubetech.com/repository/maven-public/") }
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation("io.ktor:ktor-server-core:$ktor_version")

    // for 1.6.7
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-client-apache:$ktor_version")
    implementation("io.ktor:ktor-client-serialization:$ktor_version")

    implementation("io.ktor:ktor-serialization:$ktor_version")
    implementation("io.ktor:ktor-webjars:$ktor_version")
    implementation("io.ktor:ktor-websockets:$ktor_version")
    implementation("io.ktor:ktor-auth:$ktor_version")
    implementation("io.ktor:ktor-auth-jwt:$ktor_version")

    implementation("io.ktor:ktor-client-jackson:$ktor_version")

    // for 2.0.0-beta
    // implementation("io.ktor:ktor-server-websockets:$ktor_version")
    // implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
    // implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")

    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-client-logging:$ktor_version")

    implementation("io.bkbn:kompendium-core:$kompendium_version")
    implementation("io.bkbn:kompendium-auth:$kompendium_version")
    // for now: using kotlinx.serialization
    // implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.9.7")
    // for now: using redocs instead of swagger ui
    // implementation("io.bkbn:kompendium-swagger-ui:$kompendium_version")

    implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-dao:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-java-time:$exposed_version")

    implementation("decentralized-identity:did-common-java:1.0.0")

    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
    testImplementation(kotlin("test"))

    runtimeOnly("org.xerial:sqlite-jdbc:3.36.0.3")
    runtimeOnly("org.postgresql:postgresql:42.2.25")

}
