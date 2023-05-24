package com.example.springbootidstar.service;

import com.example.springbootidstar.model.Karyawan;
import com.example.springbootidstar.model.Rekening;
import com.example.springbootidstar.repository.KaryawanRepository;
import com.example.springbootidstar.repository.RekeningRepository;
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
public class RekeningService {

    @Autowired
    KaryawanRepository karyawanRepository;
    @Autowired
    RekeningRepository rekeningRepository;
    @Autowired
    GenericRs genericRs;

    public Map insert(Rekening rekening) {
        try {
            Karyawan karyawan = karyawanRepository.getbyID(rekening.getKaryawan().getId());
            if (karyawan == null) {
                return genericRs.notFound("Karyawan Id " + rekening.getKaryawan().getId() + " Tidak Ditemukan");
            }
            Rekening newRekeningData = new Rekening();
            newRekeningData.setNama(rekening.getNama());
            newRekeningData.setJenis(rekening.getJenis());
            newRekeningData.setNorek(rekening.getNorek());
            newRekeningData.setCreatedDate(new Date());
            newRekeningData.setUpdateDate(new Date());
            newRekeningData.setKaryawan(karyawan);
            rekeningRepository.save(newRekeningData);
            log.info("{}", "Success Update Rekening");
            return genericRs.templateSuccess(newRekeningData);
        } catch (Exception e) {
            log.error("{}", "Error Update Rekening : " + e);
            return genericRs.templateError(e.getMessage(), "400");
        }
    }

    public Map update(Rekening rekening) {
        try {
            Rekening checkRekening = rekeningRepository.getbyID(rekening.getId());
            if (checkRekening == null) {
                return genericRs.notFound("Rekening Id " + rekening.getId() + " Tidak Ditemukan");
            }
            Karyawan karyawan = karyawanRepository.getbyID(rekening.getKaryawan().getId());

            if (karyawan == null) {
                return genericRs.notFound("Karyawan Id " + rekening.getKaryawan().getId() + " Tidak Ditemukan");
            }
            Rekening newRekeningData = new Rekening();
            newRekeningData.setId(rekening.getId());
            newRekeningData.setNama(rekening.getNama());
            newRekeningData.setJenis(rekening.getJenis());
            newRekeningData.setNorek(rekening.getNorek());
            newRekeningData.setCreatedDate(checkRekening.getCreatedDate());
            newRekeningData.setUpdateDate(new Date());
            newRekeningData.setDeleteDate(null);
            newRekeningData.setKaryawan(karyawan);
            rekeningRepository.save(newRekeningData);
            Rekening updatedRekening = rekeningRepository.getbyID(newRekeningData.getId());
            log.info("{}", "Success Update Rekening");
            return genericRs.templateSuccess(updatedRekening);
        } catch (Exception e) {
            log.error("{}", "Error Update Rekening : " + e);
            return genericRs.templateError(e.getMessage(), "400");
        }
    }

    public Map delete(Long id) {
        try {
            Rekening checkRekening = rekeningRepository.getbyID(id);
            if (checkRekening == null) {
                return genericRs.notFound("Id Tidak Ditemukan");
            }
            karyawanRepository.deleteById(checkRekening.getKaryawan().getId()); // hard delete (delete permanent)
            checkRekening.setDeleteDate(new Date()); // soft delete
            rekeningRepository.save(checkRekening);

            log.info("{}", "Success Delete Rekening ID : " + id);
            return genericRs.templateSuccess("Suskes Delete Rekening ID : " + checkRekening.getId());
        } catch (Exception e) {
            log.error("{}", "Error Delete Rekening : " + e);
            return genericRs.templateError(e.getMessage(), "400");
        }
    }

    public Map getAll(int size, int page) {
        try {
            Pageable show_data = PageRequest.of(page, size);
            Page<Rekening> list = rekeningRepository.getAllData(show_data);
            log.info("{}", "Success getAll Rekening ");
            return genericRs.templateSuccess(list);
        } catch (Exception e) {
            log.error("{}", "Error getById Rekening : " + e);
            return genericRs.templateError(e.getMessage(), "400");
        }
    }

    public Map getById(Long id) {
        try {
            Rekening checkRekening = rekeningRepository.getbyID(id);
            if (checkRekening == null) {
                return genericRs.notFound("Id Tidak Ditemukan");
            }
            log.info("{}", "Success getById Rekening ");
            return genericRs.templateSuccess(checkRekening);
        } catch (Exception e) {
            log.error("{}", "Error getById Rekening : " + e);
            return genericRs.templateError(e.getMessage(), "400");
        }
    }
}
