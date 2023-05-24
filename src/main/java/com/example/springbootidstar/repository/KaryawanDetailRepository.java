package com.example.springbootidstar.repository;

import com.example.springbootidstar.model.Karyawan;
import com.example.springbootidstar.model.KaryawanDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author asief
 * @project springboot-idstar, 24/05/2023
 */
@Repository
public interface KaryawanDetailRepository extends CrudRepository<KaryawanDetail, Long> {
    @Query("select c from KaryawanDetail c WHERE c.id = :karyawanDetailId")
    public Karyawan getbyID(@Param("karyawanDetailId") Long idKaryawanDetail);
}
