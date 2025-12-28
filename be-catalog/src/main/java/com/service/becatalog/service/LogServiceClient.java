package com.service.becatalog.service;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.service.becatalog.dto.LogRequest;

@FeignClient(name = "report")
public interface LogServiceClient {
    @PostMapping("/log/create")
    void createLog(@RequestBody LogRequest logRequest);
}