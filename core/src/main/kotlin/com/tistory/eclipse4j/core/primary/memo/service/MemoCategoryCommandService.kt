package com.tistory.eclipse4j.core.primary.memo.service

import com.tistory.eclipse4j.core.primary.memo.entity.MemoCategoryEntity
import com.tistory.eclipse4j.core.primary.memo.repository.MemoCategoryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = false)
class MemoCategoryCommandService(
    private val memoCategoryRepository: MemoCategoryRepository
) {

    fun save(entity: MemoCategoryEntity): MemoCategoryEntity {
        return memoCategoryRepository.save(entity)
    }

}