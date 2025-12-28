package com.service.bereport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.bereport.dto.LogRequest;
import com.service.bereport.service.LogService;

@RestController
@RequestMapping(value = "/log")
public class LogController {

    @Autowired
    private LogService logService;
    

    @PostMapping("/create")
    public ResponseEntity<Void> createLog(@RequestBody LogRequest logRequest) {
        System.out.println("CREATE cc " + logRequest.getCsv_filename() + " total " + 
        logRequest.getTotal_record() + " success " + logRequest.getTotal_record_success() + " fail " + 
        logRequest.getTotal_record_faild() + " note " + logRequest.getFaild_id_notes());

        logService.create(logRequest);

        return ResponseEntity.ok().build();
    }
}
