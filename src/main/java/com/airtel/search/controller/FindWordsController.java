package com.airtel.search.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.airtel.search.dto.FindWordsRequest;
import com.airtel.search.service.impl.FindCommonWordsService;

@RestController
public class FindWordsController {
    
    @Autowired
    private FindCommonWordsService findCommonWordsService;
    
    @PostMapping("/v1/files/common-words")
    public ResponseEntity<Object> findCommonWords(@RequestBody FindWordsRequest reqDto){
        if(reqDto.getFiles()==null ||reqDto.getFiles().size()<3) {
            return new ResponseEntity<>("Minimum 3 files are required", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(findCommonWordsService.findCommonWords(reqDto.getFiles()),HttpStatus.OK);
    }
    

}
