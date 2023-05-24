package com.example.springbootidstar.repository;

import com.example.springbootidstar.model.Karyawan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author asief
 * @project springboot-idstar, 13/04/2023
 */
@Repository
public interface KaryawanRepository extends CrudRepository<Karyawan,Long> {

    @Query("select c from Karyawan c WHERE c.id = :karyawanId")
    public Karyawan getbyID(@Param("karyawanId") Long idKaryawan);

    @Query("select c from Karyawan c")// nama class
    public Page<Karyawan> getAllData(Pageable pageable);
}
