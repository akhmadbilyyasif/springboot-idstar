package com.example.springbootidstar.service;

import com.example.springbootidstar.model.Training;
import com.example.springbootidstar.repository.KaryawanRepository;
import com.example.springbootidstar.repository.TrainingRepository;
import com.example.springbootidstar.utility.GenericRs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @author asief
 * @project springboot-idstar, 24/05/2023
 */
@Service
@Slf4j
public class TrainingService {

    @Autowired
    TrainingRepository trainingRepository;
    @Autowired
    KaryawanRepository karyawanRepository;

    @Autowired
    GenericRs genericRs;

    public Map insert(Training training) {
        try {
            training.setCreatedDate(new Date());
            training.setUpdateDate(new Date());

            Training trainingObj = trainingRepository.save(training);
            log.info("{}", "Success Menambahkan Training");
            return genericRs.templateSuccess(trainingObj);
        } catch (Exception e) {
            log.error("{}", "Error Menambahkan Training : " + e);
            return genericRs.templateError(e.getMessage(), "400");
        }
    }

    public Map update(Training training) {
        try {
            Training checkTraining = trainingRepository.getbyID(training.getId());
            if (checkTraining == null) {
                return genericRs.notFound("Training Id " + training.getId() + " Tidak Ditemukan");
            }
            training.setCreatedDate(checkTraining.getCreatedDate());
            training.setUpdateDate(new Date());
            training.setTema(training.getTema());
            training.setPengajar(training.getPengajar());

            Training trainingObj = trainingRepository.save(training);
            log.info("{}", "Success Update Training");
            return genericRs.templateSuccess(trainingObj);
        } catch (Exception e) {
            log.error("{}", "Error Update Training : " + e);
            return genericRs.templateError(e.getMessage(), "400");
        }
    }

    public Map delete(Long id) {
        try {
            Training checkTraining = trainingRepository.getbyID(id);
            if (checkTraining == null) {
                return genericRs.notFound("Id Tidak Ditemukan");
            }
            //  karyawanRepository.deleteById(checkTraining.getKaryawanTraining()); // hard delete (delete permanent)
            checkTraining.setDeleteDate(new Date()); // soft delete
            trainingRepository.save(checkTraining);

            log.info("{}", "Success Delete Training ID : " + id);
            return genericRs.templateSuccess("Success Delete Training ID : " + checkTraining.getId());
        } catch (Exception e) {
            log.error("{}", "Error Delete Training : " + e);
            return genericRs.templateError(e.getMessage(), "400");
        }
    }

    public Map getAll(int size, int page) {
        try {
            Pageable show_data = PageRequest.of(page, size);
            Page<Training> list = trainingRepository.getAllData(show_data);
            log.info("{}", "Success getAll Training ");
            return genericRs.templateSuccess(list);
        } catch (Exception e) {
            log.error("{}", "Error getAll Training : " + e);
            return genericRs.templateError(e.getMessage(), "400");
        }
    }

    public Map getById(Long id) {
        try {
            Training checkTraining = trainingRepository.getbyID(id);
            if (checkTraining == null) {
                return genericRs.notFound("Training Id " + id + " Tidak Ditemukan");
            }
            log.info("{}", "Success getById Training ");
            return genericRs.templateSuccess(checkTraining);
        } catch (Exception e) {
            log.error("{}", "Error getById Training : " + e);
            return genericRs.templateError(e.getMessage(), "400");
        }
    }
}
