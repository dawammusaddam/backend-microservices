package com.service.becatalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.becatalog.entity.TblTransaksi;

@Repository
public interface TransaksiRepository extends JpaRepository<TblTransaksi, String>{
    
}
