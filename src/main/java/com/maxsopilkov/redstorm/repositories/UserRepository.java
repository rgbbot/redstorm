package com.maxsopilkov.redstorm.repositories;

import com.maxsopilkov.redstorm.entities.DBCalculate;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<DBCalculate, Long> {

}
