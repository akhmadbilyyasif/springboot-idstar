package com.example.springbootidstar.model;

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
@Table(name = "rekening")
@Where(clause = "deleted_date is null")
public class Rekening extends AbstractDate implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "jenis", nullable = false, length = 100)
    private String jenis;
    @Column(name = "nama", nullable = false, length = 100)
    private String nama;
    @Column(name = "norek", nullable = false, length = 100)
    private String norek;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "karyawan",referencedColumnName = "id", nullable = false)
    private Karyawan karyawan;
}
