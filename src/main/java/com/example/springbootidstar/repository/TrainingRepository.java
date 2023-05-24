package com.example.springbootidstar.repository;

import com.example.springbootidstar.model.Training;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author asief
 * @project springboot-idstar, 24/05/2023
 */

@Repository
public interface TrainingRepository extends CrudRepository<Training, Long> {

    @Query("select c from Training c")// nama class
    public Page<Training> getAllData(Pageable pageable);

    @Query("select c from Training c WHERE c.id = :trainingId")
    public Training getbyID(@Param("trainingId") Long idTraining);
}
