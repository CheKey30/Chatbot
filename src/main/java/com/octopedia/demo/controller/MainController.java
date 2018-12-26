package com.octopedia.demo.controller;

import com.octopedia.demo.domain.Customer;
import com.octopedia.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class MainController {
    private CustomerService customerService;

    @Autowired
    public MainController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping("/")
    public String login(HttpServletRequest request){
        if(request.getSession().getAttribute("email") !=null){
            return "redirect:/index";
        }
        return "signIn";
    }

    @PostMapping("/login")
    public String login(Model model, @Valid Customer customer, HttpServletRequest request){
        String email = customer.getEmail();
        String password = customer.getPassword();
        try{
            Customer result = this.customerService.getCustomerByEmail(email);
            if(password.equals(result.getPassword())){
                HttpSession session = request.getSession();
                session.setAttribute("email",result.getEmail());
                return "redirect:/index";
            }else {
                model.addAttribute("invalid",true);
            }
        }catch (NullPointerException ex){
            model.addAttribute("invalid",true);
        }
        return "signIn";
    }

    @GetMapping("/index")
    public String toIndex(){
        return "index";
    }

    @GetMapping("/register")
    public String toRegister(){
        return "register";
    }
}
