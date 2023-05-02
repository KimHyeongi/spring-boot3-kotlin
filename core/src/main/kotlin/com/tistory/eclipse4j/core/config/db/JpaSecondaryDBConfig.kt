package com.tistory.eclipse4j.core.config.db

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
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource


@EnableTransactionManagement
@EnableJpaRepositories(basePackages = ["com.tistory.eclipse4j.core.secondary"],
    transactionManagerRef = "secondaryTransactionManager",
    entityManagerFactoryRef = "secondaryEntityManagerFactory")
@EntityScan(
    basePackages = ["com.tistory.eclipse4j.core.secondary"]
)
@Configuration
class JpaSecondaryDBConfig {

    @Value("\${spring.datasource.hikari.ddl-auto}")
    lateinit var ddlAuto: String

    @Bean(name = ["secondaryDataSource"])
    @ConfigurationProperties(prefix = "spring.datasource.hikari.secondary-pgsqldb")
    fun secondaryDataSource(): DataSource {
        return HikariDataSource()
    }

    @PersistenceContext(unitName = "secondary-pgsqldb")
    @Bean(name = ["secondaryEntityManagerFactory"])
    fun secondaryEntityManagerFactory(
        builder: EntityManagerFactoryBuilder,
        @Qualifier("secondaryDataSource") secondaryDataSource: DataSource
    ): LocalContainerEntityManagerFactoryBean {
        val properties = HashMap<String, Any?>()
        properties["hibernate.hbm2ddl.auto"] = ddlAuto
        properties["hibernate.dialect"] = "org.hibernate.dialect.PostgreSQLDialect"
        return builder
            .dataSource(secondaryDataSource)
            .packages("com.tistory.eclipse4j.core.secondary")
            .persistenceUnit("secondary-pgsqldb")
            .properties(properties)
            .build()
    }

    @Bean(name = ["secondaryTransactionManager"])
    fun secondaryTransactionManager(
        @Qualifier("secondaryEntityManagerFactory") secondaryEntityManagerFactory: EntityManagerFactory
    ): PlatformTransactionManager {
        return JpaTransactionManager(secondaryEntityManagerFactory)
    }
}