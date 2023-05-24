package com.example.springbootidstar.service;

import com.example.springbootidstar.model.Karyawan;
import com.example.springbootidstar.model.KaryawanTraining;
import com.example.springbootidstar.model.Training;
import com.example.springbootidstar.repository.KaryawanRepository;
import com.example.springbootidstar.repository.KaryawanTrainingRepository;
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
public class KaryawanTrainingService {
    @Autowired
    KaryawanTrainingRepository karyawanTrainingRepository;
    @Autowired
    KaryawanRepository karyawanRepository;
    @Autowired
    TrainingRepository trainingRepository;
    @Autowired
    GenericRs genericRs;

    public Map insert(KaryawanTraining karyawanTraining) {
        try {
            Karyawan checkIdKaryawan = karyawanRepository.getbyID(karyawanTraining.getKaryawan().getId());
            if (checkIdKaryawan == null) {
                return genericRs.notFound("Id Karyawan Tidak ada di database");
            }

            Training checkIdTraining = trainingRepository.getbyID(karyawanTraining.getTraining().getId());
            if (checkIdTraining == null) {
                return genericRs.notFound("Id Training Tidak ada di database");
            }

            //insert
            KaryawanTraining karyawanTrainingData = new KaryawanTraining();
            karyawanTrainingData.setKaryawan(checkIdKaryawan);
            karyawanTrainingData.setTraining(checkIdTraining);
            karyawanTrainingData.setTraining_date(karyawanTraining.getTraining_date());
            karyawanTrainingData.setCreatedDate(new Date());
            karyawanTrainingData.setUpdateDate(new Date());

            KaryawanTraining karyawanTrainingObj = karyawanTrainingRepository.save(karyawanTrainingData);
            log.info("{}", "Success Menambahkan Karyawan Training");
            return genericRs.templateSuccess(karyawanTrainingObj);
        } catch (Exception e) {
            log.error("{}", "Error Menambahkan Karyawan Training : " + e);
            return genericRs.templateError(e.getMessage(), "400");
        }
    }

    public Map update(KaryawanTraining karyawanTraining) {
        try {
            Karyawan checkIdKaryawan = karyawanRepository.getbyID(karyawanTraining.getKaryawan().getId());
            if (checkIdKaryawan == null) {
                return genericRs.notFound("Id Karyawan Tidak ada di database");
            }
            Training checkIdTraining = trainingRepository.getbyID(karyawanTraining.getTraining().getId());
            if (checkIdTraining == null) {
                return genericRs.notFound("Id Training Tidak ada di database");
            }
            KaryawanTraining checkIdKaryawanTraining = karyawanTrainingRepository.getbyID(karyawanTraining.getId());
            if (checkIdKaryawanTraining == null) {
                return genericRs.notFound("Id Training Tidak ada di database");
            }

            //insert
            KaryawanTraining karyawanTrainingData = new KaryawanTraining();
            karyawanTrainingData.setId(checkIdKaryawanTraining.getId());
            karyawanTrainingData.setKaryawan(checkIdKaryawan);
            karyawanTrainingData.setTraining(checkIdTraining);
            karyawanTrainingData.setTraining_date(karyawanTraining.getTraining_date());
            karyawanTrainingData.setCreatedDate(checkIdKaryawanTraining.getCreatedDate());
            karyawanTrainingData.setUpdateDate(new Date());

            KaryawanTraining karyawanTrainingObj = karyawanTrainingRepository.save(karyawanTrainingData);
            log.info("{}", "Success Update Karyawan Training");
            return genericRs.templateSuccess(karyawanTrainingObj);
        } catch (Exception e) {
            log.error("{}", "Error Update Karyawan Training : " + e);
            return genericRs.templateError(e.getMessage(), "400");
        }
    }

    public Map delete(Long id) {
        try {
            KaryawanTraining checkIdKaryawanTraining = karyawanTrainingRepository.getbyID(id);
            if (checkIdKaryawanTraining == null) {
                return genericRs.notFound("Id Training Tidak ada di database");
            }
            checkIdKaryawanTraining.setDeleteDate(new Date()); // soft delete
            KaryawanTraining karyawanTrainingObj = karyawanTrainingRepository.save(checkIdKaryawanTraining);
            log.info("{}", "Success Delete Karyawan Training ID : " + id);
            return genericRs.templateSuccess("Success Delete Karyawan Training ID : " + checkIdKaryawanTraining.getId());
        } catch (Exception e) {
            log.error("{}", "Error Delete Karyawan Training : " + e);
            return genericRs.templateError(e.getMessage(), "400");
        }
    }

    public Map getAll(int size, int page) {
        try {
            Pageable show_data = PageRequest.of(page, size);
            Page<KaryawanTraining> list = karyawanTrainingRepository.getAllData(show_data);
            log.info("{}", "Success getAll Karyawan Training ");
            return genericRs.templateSuccess(list);
        } catch (Exception e) {
            log.error("{}", "Error getAll Karyawan Training : " + e);
            return genericRs.templateError(e.getMessage(), "400");
        }
    }

    public Map getById(Long id) {
        try {
            KaryawanTraining karyawanTraining = karyawanTrainingRepository.getbyID(id);
            if (karyawanTraining == null) {
                return genericRs.notFound("Karyawan Training Id " + id + " Tidak Ditemukan");
            }
            log.info("{}", "Success getById Karyawan Training ");
            return genericRs.templateSuccess(karyawanTraining);
        } catch (Exception e) {
            log.error("{}", "Error getById Karyawan Training : " + e);
            return genericRs.templateError(e.getMessage(), "400");
        }
    }
}
