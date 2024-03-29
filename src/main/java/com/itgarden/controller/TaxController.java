package com.itgarden.controller;

import com.itgarden.dto.TaxInfo;
import com.itgarden.messages.ResponseMessage;
import com.itgarden.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/*
 * Created by Suresh Stalin on 23 / Nov / 2020.
 */

@RestController
@RequestMapping("api/private/taxes")
public class TaxController {

    @Autowired
    private TaxService taxService;

    @PostMapping
    public ResponseEntity<ResponseMessage<?>> save(@Valid @RequestBody TaxInfo taxInfo,
                                                   @RequestHeader(value="Authorization") String accessToken) throws Exception {
        ResponseMessage responseMessage  = taxService.save(taxInfo);
        return new ResponseEntity<ResponseMessage<?>>(responseMessage, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage<?>> get(@PathVariable Long id,
                                                  @RequestHeader(value="Authorization") String accessToken) throws Exception {
        ResponseMessage responseMessage = taxService.findResourceById(id);
        return new ResponseEntity<ResponseMessage<?>>(responseMessage, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<ResponseMessage<?>> getAll() throws Exception {
        ResponseMessage responseMessage = taxService.findAll();
        return new ResponseEntity<ResponseMessage<?>>(responseMessage, HttpStatus.OK);
    }
}
