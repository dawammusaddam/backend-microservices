package com.service.bereport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.bereport.entity.LogTransaksi;

@Repository
public interface LogTransaksiRepository extends JpaRepository<LogTransaksi, String>{
    
}
