package com.example.springbootidstar.repository;

import com.example.springbootidstar.model.KaryawanTraining;
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
public interface KaryawanTrainingRepository extends CrudRepository<KaryawanTraining,Long> {

    @Query("select c from KaryawanTraining c")// nama class
    public Page<KaryawanTraining> getAllData(Pageable pageable);

    @Query("select c from KaryawanTraining c WHERE c.id = :karyawanTrainingId")
    public KaryawanTraining getbyID(@Param("karyawanTrainingId") Long idKaryawanTraining);
}
