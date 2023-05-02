package com.tistory.eclipse4j.core.config.db

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository


@Repository
abstract class JpaSecondaryQueryDslRepositorySupport(domainClass: Class<*>)
    : QuerydslRepositorySupport(domainClass) {

    @Override
    @PersistenceContext(unitName = "secondary-pgsqldb")
    override fun setEntityManager(entityManager: EntityManager) {
        super.setEntityManager(entityManager)
    }
}