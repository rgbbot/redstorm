package com.maxsopilkov.redstorm.repositories;

import com.maxsopilkov.redstorm.bayess.BayessProbability;
import com.maxsopilkov.redstorm.entities.CalculationResult;
import org.springframework.data.repository.CrudRepository;

public interface CalculationResultRepository extends CrudRepository<CalculationResult, Long>{
}
