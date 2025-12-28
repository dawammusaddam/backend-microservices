package com.service.becatalog.controller;

import com.service.becatalog.dto.ProductRequest;
import com.service.becatalog.dto.RequestPage;
import com.service.becatalog.entity.TblTransaksi;
import com.service.becatalog.service.FileService;
import com.service.becatalog.service.ProductService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private FileService fileService;

    @PostMapping(value = "/create")
    public ResponseEntity<Object> create(@Valid @RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok(productService.create(productRequest));
    }

    @PostMapping(value = "/getProductPerPage")
    public ResponseEntity<Object> getProductPerPage(@Valid @RequestBody RequestPage requestPage) {
        return ResponseEntity.ok(productService.getProductPerPage(requestPage));
    }

    @GetMapping(value = "/getById")
    public ResponseEntity<Object> getById(@RequestParam(value = "id", defaultValue = "")String id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @Valid @RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok(productService.update(id, productRequest));
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<Object> delete(@RequestParam(value = "id", defaultValue = "")String id,
                                         @RequestParam(value = "username", defaultValue = "")String username) {
        return ResponseEntity.ok(productService.delete(id, username));
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> upload(@RequestParam("file") MultipartFile file){
        fileService.save(file);
        return ResponseEntity.ok("File berhasil diupload!");
    }


    @PostMapping(value = "/transaksi/list", consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> transaksiList(){
        List<TblTransaksi> ls = fileService.listTransaksi();
        return ResponseEntity.ok(ls);
    }

}
