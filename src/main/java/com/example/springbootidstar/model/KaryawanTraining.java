package com.example.springbootidstar.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.util.Date;

/**
 * @author asief
 * @project springboot-idstar, 24/05/2023
 */
@Data
@Entity
@Table(name = "karyawan_training")
@Where(clause = "deleted_date is null")
public class KaryawanTraining extends AbstractDate implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "training_date", nullable = false)
    private Date training_date;

    @ManyToOne()
    @JoinColumn(name = "id_karyawan", nullable = false)
    private Karyawan karyawan;

    @ManyToOne()
    @JoinColumn(name = "id_training", nullable = false)
    private Training training;
}
