package com.service.bereport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.bereport.dto.LogRequest;
import com.service.bereport.entity.LogTransaksi;
import com.service.bereport.repository.LogTransaksiRepository;

import jakarta.transaction.Transactional;

@Service
public class LogService {
    
    @Autowired
    private LogTransaksiRepository logTransaksiRepository;

    @Transactional
    public LogTransaksi create(LogRequest logRequest){
        LogTransaksi logTransaksi = LogTransaksi.builder()
        .csv_filename(logRequest.getCsv_filename())
        .total_record(logRequest.getTotal_record())
        .total_record_success(logRequest.getTotal_record_success())
        .total_record_faild(logRequest.getTotal_record_faild())
        .faild_id_notes(logRequest.getFaild_id_notes())
        .build();

        logTransaksi = logTransaksiRepository.save(logTransaksi);

        return logTransaksi;
    }
}
