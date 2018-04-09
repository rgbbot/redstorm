package com.maxsopilkov.redstorm.repositories;

import com.maxsopilkov.redstorm.entities.DBCalculate;
import com.maxsopilkov.redstorm.entities.Result;
import org.springframework.data.repository.CrudRepository;

public interface CalculationRepository extends CrudRepository<Result, Long> {

}
