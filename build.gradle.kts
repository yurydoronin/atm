plugins {
    kotlin("jvm") version "2.2.10"
    kotlin("plugin.spring") version "2.2.10"
    id("org.springframework.boot") version "3.5.4"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "atm"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Spring
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")

    // Logging
    implementation("org.zalando:logbook-spring-boot-starter:3.12.2")

    testImplementation(kotlin("test"))
    testImplementation("io.mockk:mockk:1.14.5")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}

tasks.register("printSrc") {
    doLast {
        file("src/main/kotlin").walkTopDown()
            .filter { it.isFile }
            .forEach { println(it.relativeTo(file("src/main/kotlin"))) }
    }
}
