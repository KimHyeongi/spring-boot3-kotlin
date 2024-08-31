plugins {
    id("java-library")
    id("java-test-fixtures")
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.Embeddable")
    annotation("jakarta.persistence.MappedSuperclass")
}

noArg {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.Embeddable")
    annotation("jakarta.persistence.MappedSuperclass")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    api("org.springframework.boot:spring-boot-starter-data-jpa")
    api("org.springframework.boot:spring-boot-starter-cache")
    api("org.springframework.boot:spring-boot-starter-data-redis")

    implementation("org.postgresql:postgresql")
    implementation("com.h2database:h2")
    implementation("com.querydsl:querydsl-jpa:${Versions.querydsl_version}:jakarta")
    implementation("com.querydsl:querydsl-core:${Versions.querydsl_version}")
    implementation("io.hypersistence:hypersistence-utils-hibernate-60:3.8.2")

    implementation("com.fasterxml.jackson.module:jackson-module-jakarta-xmlbind-annotations:2.17.0")

    kapt("org.springframework.boot:spring-boot-configuration-processor")
    kapt("com.querydsl:querydsl-apt:${Versions.querydsl_version}:jakarta")
    kapt("jakarta.persistence:jakarta.persistence-api")
    kapt("jakarta.annotation:jakarta.annotation-api")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

idea {
    module {
        val kaptMain = file("build/generated/source/kapt/main")
        sourceDirs.add(kaptMain)
        generatedSourceDirs.add(kaptMain)
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    jvmArgs(
        "-Xshare:off",
    )
    maxParallelForks = 1
}

tasks.register("prepareKotlinBuildScriptModel"){}

kotlin.sourceSets.main{
    setBuildDir("$buildDir/generated/source/kapt/main")
}
