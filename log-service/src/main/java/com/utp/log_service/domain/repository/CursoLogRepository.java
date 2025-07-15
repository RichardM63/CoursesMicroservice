package com.utp.log_service.domain.repository;

import com.utp.log_service.domain.model.CursoLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoLogRepository extends JpaRepository<CursoLog, Long> {
}
