package com.foreignexchange.repository;

import com.foreignexchange.entity.Conversion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface ConversionRepository extends JpaRepository<Conversion, UUID>, JpaSpecificationExecutor<Conversion> {
}
