package com.service.becatalog.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.Executor;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.service.becatalog.dto.LogRequest;
import com.service.becatalog.entity.TblTransaksi;
import com.service.becatalog.helper.CSVHelper;
import com.service.becatalog.repository.TransaksiRepository;

@Service
public class FileService {
    
    @Autowired
    private Executor executor;
    @Autowired
    private LogServiceClient logServiceClient;
    @Autowired
    private TransaksiRepository transaksiRepository;

    public void save(MultipartFile file){
        executor.execute((() -> process(file)));
    }

    private void process(MultipartFile file){
        try {
            Iterable<CSVRecord> csvRecords = CSVHelper.csvRecords(file.getInputStream());
            Integer success = 0;
            Integer fail = 0;
            Integer total = 0;
            String id_noters = "";

            for (CSVRecord csvRecord : csvRecords) {
                try {
                    long employee_id = Long.valueOf(csvRecord.get("employee_id"));
                    BigDecimal amount = new BigDecimal(Double.valueOf(csvRecord.get("amount")));
                    String tgl_transaksi = csvRecord.get("tgl_transaksi");
                    TblTransaksi transaksi = TblTransaksi.builder().employee_id(employee_id).amount(amount).tgl_transaksi(tgl_transaksi).build();
    
                    TblTransaksi t = transaksiRepository.save(transaksi);
                    success++;
                } catch (Exception e) {
                    // TODO: handle exception
                    fail++;
                    id_noters += ","+ csvRecord.get("employee_id");
                }
                total++;
            }
            LogRequest lrq = new LogRequest();
            lrq.setCsv_filename(file.getOriginalFilename());
            lrq.setTotal_record(total);
            lrq.setTotal_record_success(success);
            lrq.setTotal_record_faild(fail);
            lrq.setFaild_id_notes(id_noters);

            logServiceClient.createLog(lrq);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

    public List<TblTransaksi> listTransaksi(){
        return transaksiRepository.findAll();
    }
}
