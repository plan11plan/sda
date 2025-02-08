package com.dnd.backend.domain.incident.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dnd.backend.domain.incident.entity.IncidentEntity;

@Repository
public interface JpaIncidentRepository extends JpaRepository<IncidentEntity, Long> {
	List<IncidentEntity> findAllByWriterId(Long writerId, Pageable pageable);

	List<IncidentEntity> findAllByWriterIdAndIdLessThan(Long writerId, Long id, Pageable pageable);

	List<IncidentEntity> findAll();

}
