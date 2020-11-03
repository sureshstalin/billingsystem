package com.itgarden.service.bo;

import com.itgarden.dto.CustomerDTO;
import com.itgarden.dto.EmployeeDTO;
import com.itgarden.dto.VendorDTO;
import com.itgarden.entity.Customer;
import com.itgarden.entity.Employee;
import com.itgarden.entity.Vendor;
import com.itgarden.exception.ResourceNotFoundException;
import com.itgarden.mapper.CustomerMapper;
import com.itgarden.mapper.EmployeeMapper;
import com.itgarden.mapper.VendorMapper;
import com.itgarden.messages.ResponseMessage;
import com.itgarden.repository.EmployeeRepository;
import com.itgarden.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class VendorService {

    private final VendorRepository repository;

    @Autowired
    VendorService(VendorRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ResponseMessage findVendor(String id) throws Exception {
        Vendor vendor = null;
        if(id.contains("VEN")) {
            vendor = repository.findByVendorCode(id);
        }
        else {
            vendor = repository.findById(Long.parseLong(id)).orElse(null);
        }
        ResponseMessage responseMessage = new ResponseMessage();
        if (vendor != null) {
            VendorDTO vendorDTO = VendorMapper.INSTANCE.vendorToDTO(vendor);
            responseMessage.setResponseClassType(vendorDTO);
        } else {
            throw new ResourceNotFoundException("Vendor not found");
        }
        return responseMessage;
    }


}
