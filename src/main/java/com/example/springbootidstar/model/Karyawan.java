package com.example.springbootidstar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @author asief
 * @project springboot-idstar, 13/04/2023
 */
@Data
@Entity
@Table(name = "karyawan")
@Where(clause = "deleted_date is null")
public class Karyawan extends AbstractDate implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Not Null")
    @Size(min = 4, max = 100, message= "Minimal 4 karakter dan maksimal 100 karakter")
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @NotNull(message = "Not Null")
    @Column(name = "status", nullable = false, length = 100)
    private String status;

    @NotNull(message = "Not Null")
    @Column(name = "address", nullable = false)
    private String address;

    @NotNull(message = "Not Null")
    @Column(name = "dob", nullable = false)
    private Date dob;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "karyawanDetail",referencedColumnName = "id", nullable = false)
    private KaryawanDetail karyawanDetail;
    @JsonIgnore
    @OneToOne(mappedBy = "karyawan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Rekening rekening;
    @JsonIgnore
    @OneToMany(mappedBy = "karyawan")
    private Set<KaryawanTraining> karyawanTrainingSet;
}
