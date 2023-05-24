package com.example.springbootidstar.service;

import com.example.springbootidstar.model.Karyawan;
import com.example.springbootidstar.model.KaryawanDetail;
import com.example.springbootidstar.repository.KaryawanDetailRepository;
import com.example.springbootidstar.repository.KaryawanRepository;
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
 * @project springboot-idstar, 13/04/2023
 */
@Service
@Slf4j
public class KaryawanService {

    @Autowired
    KaryawanRepository karyawanRepository;
    @Autowired
    KaryawanDetailRepository karyawanDetailRepository;
    @Autowired
    GenericRs genericRs;

    public Map insert(Karyawan karyawan) {
        try {
            KaryawanDetail karyawanDetailData = new KaryawanDetail();
            karyawanDetailData.setCreatedDate(new Date());
            karyawanDetailData.setUpdateDate(new Date());
            karyawanDetailData.setNik(karyawan.getKaryawanDetail().getNik());
            karyawanDetailData.setNpwp(karyawan.getKaryawanDetail().getNpwp());

            Karyawan karyawanData = new Karyawan();
            karyawanData.setName(karyawan.getName());
            karyawanData.setDob(karyawan.getDob());
            karyawanData.setAddress(karyawan.getAddress());
            karyawanData.setStatus(karyawan.getStatus());
            karyawanData.setCreatedDate(new Date());
            karyawanData.setUpdateDate(new Date());
            karyawanData.setKaryawanDetail(karyawanDetailData);

            karyawanRepository.save(karyawanData);
            karyawanDetailRepository.save(karyawanDetailData);
            Karyawan updatedKaryawan = karyawanRepository.getbyID(karyawanData.getId());
            log.info("{}", "Success Menambahkan Karyawan");
            return genericRs.templateSuccess(updatedKaryawan);
        } catch (Exception e) {
            log.error("{}", "Error Menambahkan Karyawan : " + e);
            return genericRs.templateError(e.getMessage(), "400");
        }
    }

    public Map update(Karyawan karyawan) {
        try {
            Karyawan checkIdKaryawan = karyawanRepository.getbyID(karyawan.getId());
            if (checkIdKaryawan == null) {
                return genericRs.notFound("Id Tidak Ditemukan");
            }

            KaryawanDetail karyawanDetailData = new KaryawanDetail();
            karyawanDetailData.setId(checkIdKaryawan.getKaryawanDetail().getId());
            karyawanDetailData.setCreatedDate(checkIdKaryawan.getKaryawanDetail().getCreatedDate());
            karyawanDetailData.setUpdateDate(new Date());
            karyawanDetailData.setNik(karyawan.getKaryawanDetail().getNik());
            karyawanDetailData.setNpwp(karyawan.getKaryawanDetail().getNpwp());

            Karyawan karyawanData = new Karyawan();
            karyawanData.setId(karyawan.getId());
            karyawanData.setName(karyawan.getName());
            karyawanData.setDob(karyawan.getDob());
            karyawanData.setAddress(karyawan.getAddress());
            karyawanData.setStatus(karyawan.getStatus());
            karyawanData.setCreatedDate(checkIdKaryawan.getCreatedDate());
            karyawanData.setUpdateDate(new Date());
            karyawanData.setKaryawanDetail(karyawanDetailData);


            karyawanRepository.save(karyawanData);
            karyawanDetailRepository.save(karyawanDetailData);
            Karyawan updatedKaryawan = karyawanRepository.getbyID(karyawanData.getId());
            log.info("{}", "Success Update Karyawan");
            return genericRs.templateSuccess(updatedKaryawan);

        } catch (Exception e) {
            log.error("{}", "Error Update Karyawan : " + e);
            return genericRs.templateError(e.getMessage(), "400");
        }
    }

    public Map delete(Long id) {
        try {
            Karyawan checkIdKaryawan = karyawanRepository.getbyID(id);
            if (checkIdKaryawan == null) {
                return genericRs.notFound("Id Tidak Ditemukan");
            }
            checkIdKaryawan.setDeleteDate(new Date());
            karyawanRepository.save(checkIdKaryawan);

            log.info("{}", "Success Delete Karyawan ID : " + id);
            return genericRs.templateSuccess("Suskes Delete Karyawan ID : " + checkIdKaryawan.getId());
        } catch (Exception e) {
            log.error("{}", "Error Delete Karyawan : " + e);
            return genericRs.templateError(e.getMessage(), "400");
        }
    }

    public Map getAll(int size, int page) {
        try {
            Pageable show_data = PageRequest.of(page, size);
            Page<Karyawan> list = karyawanRepository.getAllData(show_data);
            log.info("{}", "Success getAll Karyawan ");
            return genericRs.templateSuccess(list);
        } catch (Exception e) {
            log.error("{}", "Error getAll Karyawan : " + e);
            return genericRs.templateError(e.getMessage(), "400");
        }
    }

    public Map getById(Long id) {
        try {
            Karyawan checkIdKaryawan = karyawanRepository.getbyID(id);
            if (checkIdKaryawan == null) {
                return genericRs.notFound("Id Tidak Ditemukan");
            }
            log.info("{}", "Success getById Karyawan ");
            return genericRs.templateSuccess(checkIdKaryawan);
        } catch (Exception e) {
            log.error("{}", "Error getById Karyawan : " + e);
            return genericRs.templateError(e.getMessage(), "400");
        }
    }
}
