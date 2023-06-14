package com.tistory.eclipse4j.core.config.db

import com.tistory.eclipse4j.core.primary.base.entity.AuditorAwareImpl
import com.zaxxer.hikari.HikariDataSource
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.PersistenceContext
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource


@EnableJpaAuditing(auditorAwareRef="auditorProvider")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = ["com.tistory.eclipse4j.core.primary"],
    transactionManagerRef = "primaryTransactionManager",
    entityManagerFactoryRef = "primaryEntityManagerFactory")
@EntityScan(
    basePackages = ["com.tistory.eclipse4j.core.primary"]
)
@Configuration
class JpaPrimaryDBConfig {

    @Value("\${spring.datasource.hikari.ddl-auto}")
    lateinit var ddlAuto: String

    @Primary
    @Bean(name = ["primaryDataSource"])
    @ConfigurationProperties(prefix = "spring.datasource.hikari.primary-pgsqldb")
    fun primaryDataSource(): DataSource {
        return HikariDataSource()
    }
    @Primary
    @PersistenceContext(unitName = "primary-pgsqldb")
    @Bean(name = ["primaryEntityManagerFactory"])
    fun primaryEntityManagerFactory(
        builder: EntityManagerFactoryBuilder,
        @Qualifier("primaryDataSource") primaryDataSource: DataSource
    ): LocalContainerEntityManagerFactoryBean? {
        val properties = HashMap<String, Any?>()
        properties["hibernate.hbm2ddl.auto"] = ddlAuto
        properties["hibernate.dialect"] = "org.hibernate.dialect.PostgreSQLDialect"
        return builder
            .dataSource(primaryDataSource)
            .packages("com.tistory.eclipse4j.core.primary")
            .persistenceUnit("primary-pgsqldb")
            .properties(properties)
            .build()
    }
    @Primary
    @Bean(name = ["primaryTransactionManager"])
    fun primaryTransactionManager(
        @Qualifier("primaryEntityManagerFactory") primaryEntityManagerFactory: EntityManagerFactory
    ): PlatformTransactionManager {
        return JpaTransactionManager(primaryEntityManagerFactory)
    }

    @Bean(name = ["auditorProvider"])
    fun auditorProvider(): AuditorAware<String> {
        return AuditorAwareImpl()
    }
}