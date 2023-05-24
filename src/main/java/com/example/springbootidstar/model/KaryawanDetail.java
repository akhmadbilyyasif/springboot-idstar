package com.example.springbootidstar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Where;

import java.io.Serializable;

/**
 * @author asief
 * @project springboot-idstar, 24/05/2023
 */
@Data
@Entity
@Table(name = "karyawan_detail")
@Where(clause = "deleted_date is null")
public class KaryawanDetail extends AbstractDate implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nik", nullable = false, length = 100)
    private String nik;
    @Column(name = "npwp", nullable = false, length = 100)
    private String npwp;
    @JsonIgnore
    @OneToOne(mappedBy = "karyawanDetail", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Karyawan karyawan;
}
