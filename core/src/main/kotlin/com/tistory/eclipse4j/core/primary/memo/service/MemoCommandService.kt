package com.tistory.eclipse4j.core.primary.memo.service

import com.tistory.eclipse4j.core.primary.memo.entity.MemoEntity
import com.tistory.eclipse4j.core.primary.memo.repository.MemoRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = false)
class MemoCommandService(
    private val memoRepository: MemoRepository
) {
    fun save(entity: MemoEntity): MemoEntity{
        return memoRepository.save(entity)
    }
}