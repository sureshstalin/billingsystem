package com.itgarden.controller;


import com.itgarden.dto.OrganizationInfo;
import com.itgarden.messages.ResponseMessage;
import com.itgarden.service.OrganizationService;
import io.swagger.v3.oas.annotations.headers.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
 * Created by Suresh Stalin on 24 / Nov / 2020.
 */

@RestController
@RequestMapping("api/public/orgs")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<ResponseMessage<?>> saveOrg(
            @RequestBody OrganizationInfo organizationInfo) throws Exception{
        ResponseMessage responseMessage = organizationService.save(organizationInfo);
        return new ResponseEntity<ResponseMessage<?>>(responseMessage, HttpStatus.CREATED);
    }

    @GetMapping("/{orgId}")
    public ResponseEntity<ResponseMessage<?>> getOrgById(@RequestHeader(value="Authorization") String accessToken,
                                                         @PathVariable Long orgId) throws Exception {
        ResponseMessage responseMessage = organizationService.findResourceById(orgId);
        return  new ResponseEntity<ResponseMessage<?>>(responseMessage,HttpStatus.OK);
    }

    @GetMapping("/code/{orgCode}")
    public ResponseEntity<ResponseMessage<?>> getOrgByCode(@RequestHeader(value="Authorization") String accessToken,
                                                         @PathVariable String orgCode) throws Exception {
        ResponseMessage responseMessage = organizationService.findResourceByCode(orgCode);
        return  new ResponseEntity<ResponseMessage<?>>(responseMessage,HttpStatus.OK);
    }
}
