import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.0.6"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version Versions.kotlin
    kotlin("plugin.spring") version Versions.kotlin
    kotlin("plugin.jpa") version Versions.kotlin apply false
    kotlin("kapt") version Versions.kotlin
    idea
}

java.sourceCompatibility = JavaVersion.VERSION_17
java.targetCompatibility = JavaVersion.VERSION_17


allprojects {
    group = "com.tistory.eclipse4j"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
        google()
        maven {
            url = uri("https://repo.spring.io/release")
        }
        maven {
            url = uri("https://repository.jboss.org/maven2")
        }
    }
}

subprojects {

    apply(plugin = "java")
    apply(plugin = "idea")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")

    apply(plugin = "kotlin")
    apply(plugin = "kotlin-spring")
    apply(plugin = "kotlin-jpa")
    apply(plugin = "kotlin-kapt")

    dependencies {
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin_stdlib}")
        implementation("io.github.microutils:kotlin-logging:${Versions.kotlin_logging}")

        implementation("ch.qos.logback:logback-core")
        implementation("ch.qos.logback:logback-access")
        implementation("ch.qos.logback:logback-classic")
        implementation("net.logstash.logback:logstash-logback-encoder:${Versions.logstash_logback_encoder}")

        testImplementation("org.junit.jupiter:junit-jupiter:${Versions.junit_jupiter}")
        testImplementation("org.testcontainers:testcontainers:${Versions.testcontainers}")
        testImplementation("org.testcontainers:junit-jupiter:${Versions.testcontainers_junit_jupiter}")
        testImplementation(platform("io.kotest:kotest-bom:${Versions.kotest_bom}"))
        testImplementation("io.kotest:kotest-runner-junit5")
        testImplementation("io.kotest.extensions:kotest-extensions-spring:${Versions.kotest_extensions_spring}")
        testImplementation("io.mockk:mockk:${Versions.mock}")
        testImplementation("org.springframework.boot:spring-boot-starter-test")

        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
        kapt("org.springframework.boot:spring-boot-configuration-processor")
    }

    dependencyManagement {
        imports {
            mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
            mavenBom("org.springframework.boot:spring-boot-dependencies:${Versions.spring_boot}")
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:${Versions.spring_boot_cloud}")
        }

        dependencies {
            dependency("net.logstash.logback:logstash-logback-encoder:${Versions.logstash_logback_encoder}")
        }
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = JavaVersion.VERSION_17.toString()
        }
    }

    tasks.withType<Test> {
        maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).takeIf { it > 0 } ?: 1
        useJUnitPlatform()
        testLogging {
//            events = setOf(FAILED, PASSED, SKIPPED)
        }
    }

    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
    }

    // Integration - Test
    sourceSets {
        create("integrationTest") {
            compileClasspath += sourceSets.main.get().output + sourceSets.test.get().output
            runtimeClasspath += sourceSets.main.get().output + sourceSets.test.get().output
        }
    }

    val integrationTestImplementation by configurations.getting {
        extendsFrom(configurations.implementation.get())
    }

    configurations["integrationTestRuntimeOnly"].extendsFrom(configurations.runtimeOnly.get())
    configurations["integrationTestImplementation"].extendsFrom(configurations.testImplementation.get())
    configurations["integrationTestImplementation"].extendsFrom(configurations.implementation.get())

    val integrationTest = task<Test>("integrationTest") {
        description = "Runs integration tests."
        group = "verification"

        testClassesDirs = sourceSets["integrationTest"].output.classesDirs
        classpath = sourceSets["integrationTest"].runtimeClasspath
        useJUnitPlatform()
        shouldRunAfter("test")
    }

    tasks.check { dependsOn(integrationTest) }

}

tasks.jar {
    enabled = true
}

tasks.bootJar {
    enabled = false
}

project(":core") {
    val jar: Jar by tasks
    val bootJar: org.springframework.boot.gradle.tasks.bundling.BootJar by tasks
    bootJar.enabled = false
    jar.enabled = true
}
