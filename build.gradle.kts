plugins {
    kotlin("jvm") version "2.2.0"
    kotlin("plugin.spring") version "2.2.0"
    id("org.springframework.boot") version "3.5.4"
    id("io.spring.dependency-management") version "1.1.3"
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
