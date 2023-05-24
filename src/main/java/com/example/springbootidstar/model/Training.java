package com.example.springbootidstar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @author asief
 * @project springboot-idstar, 24/05/2023
 */
@Data
@Entity
@Table(name = "training")
@Where(clause = "deleted_date is null")
public class Training extends AbstractDate implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "tema", nullable = false, length = 100)
    private String tema;
    @Column(name = "pengajar", nullable = false, length = 100)
    private String pengajar;
    @JsonIgnore
    @OneToMany(mappedBy = "training")
    private Set<KaryawanTraining> karyawanTraining;
}
