package com.service.bereport.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LogRequest {
    
    private String csv_filename;
    private Integer total_record;
    private Integer total_record_faild;
    private Integer total_record_success;
    private String faild_id_notes;
    
}
