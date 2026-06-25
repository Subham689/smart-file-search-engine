package com.subham.smartsearch.repository;

import com.subham.smartsearch.model.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository
        extends JpaRepository<DocumentEntity, Long> {
}