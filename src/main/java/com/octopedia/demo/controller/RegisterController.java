package com.octopedia.demo.controller;

import com.octopedia.demo.domain.Customer;
import com.octopedia.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/register")
public class RegisterController {
    private CustomerService customerService;

    @Autowired
    public RegisterController(CustomerService customerService){
        this.customerService = customerService;
    }
    @PostMapping
    public String toRegister(@ModelAttribute("Customer") Customer customer){
        String email = customer.getEmail();
        if(this.customerService.getCustomerByEmail(email) !=null){
            return "error";
        }else{
            return "signIn";
        }

    }
}
