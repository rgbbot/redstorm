package com.maxsopilkov.redstorm.repositories;

import com.maxsopilkov.redstorm.entities.CalculationResult;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CalculationResultRepository extends CrudRepository<CalculationResult, Long>{



//    @Query(RELEVANT_DATA)
//    public Iterable<CalculationResult> findAllRelevantDate();
}
