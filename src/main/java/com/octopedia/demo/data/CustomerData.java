package com.octopedia.demo.data;

import com.octopedia.demo.domain.Customer;
import com.octopedia.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/data")
public class CustomerData {
    private CustomerService customerService;

    @Autowired
    public CustomerData(CustomerService customerService){
        this.customerService = customerService;
    }
    @GetMapping("/email/{email}")
    public Customer getCustomerByemail(@PathVariable("email") String email){
        return this.customerService.getCustomerByEmail(email);
    }

}
