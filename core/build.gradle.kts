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

    // https://github.com/resilience4j/resilience4j-spring-boot3-demo 참고
    // implementation("org.springframework.cloud:spring-cloud-starter-circuitbreaker-reactor-resilience4j")
    // implementation("org.springframework.cloud:spring-cloud-starter-circuitbreaker-resilience4j")

    // implementation("mysql:mysql-connector-java")
    // implementation("org.mariadb.jdbc:mariadb-java-client")
    implementation("org.postgresql:postgresql")
    implementation("com.h2database:h2")
    implementation("com.querydsl:querydsl-jpa:${Versions.querydsl_version}:jakarta")
    implementation("com.querydsl:querydsl-core:${Versions.querydsl_version}")
    implementation("io.hypersistence:hypersistence-utils-hibernate-60:3.3.2")

    implementation("com.fasterxml.jackson.module:jackson-module-jakarta-xmlbind-annotations:2.15.0")


    kapt("org.springframework.boot:spring-boot-configuration-processor")
    // kapt("com.querydsl:querydsl-apt:${Versions.querydsl_version}:jpa")
    kapt("com.querydsl:querydsl-apt:${Versions.querydsl_version}:jakarta")
    kapt("jakarta.persistence:jakarta.persistence-api")
    kapt("jakarta.annotation:jakarta.annotation-api")
//    kapt("com.querydsl:querydsl-kotlin-codegen:5.0.0")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    //    implementation(project(":"))
}

idea {
    module {
        val kaptMain = file("build/generated/source/kapt/main")
        sourceDirs.add(kaptMain)
        generatedSourceDirs.add(kaptMain)
    }
}


tasks.register("prepareKotlinBuildScriptModel"){}

kotlin.sourceSets.main{
    setBuildDir("$buildDir/generated/source/kapt/main")
}
