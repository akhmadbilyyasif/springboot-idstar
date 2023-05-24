package com.example.springbootidstar.model;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

/**
 * @author asief
 * @project springboot-idstar, 27/04/2023
 */
@Data
public abstract class AbstractDate {
    @Column(name = "created_date")
    protected Date createdDate;

    @Column(name = "updated_date")
    protected Date updateDate;
    @Column(name = "deleted_date")
    protected Date deleteDate;
}
