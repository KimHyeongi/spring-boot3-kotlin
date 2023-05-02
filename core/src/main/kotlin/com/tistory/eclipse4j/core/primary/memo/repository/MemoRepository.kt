package com.tistory.eclipse4j.core.primary.memo.repository

import com.tistory.eclipse4j.core.primary.memo.entity.MemoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.stereotype.Repository

@Repository
interface MemoRepository : JpaRepository<MemoEntity, Long>, QuerydslPredicateExecutor<MemoEntity>