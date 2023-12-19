package com.yoga.yoga.Controller;

import java.security.Principal;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yoga.yoga.Entities.User;
import com.yoga.yoga.dao.UserRepository;
import com.yoga.yoga.helper.Mssg;

import jakarta.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/")
    public String home() {
        // System.out.println("Hii, I am called");
        Calendar calendar = Calendar.getInstance();
        if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
            List<User> users = this.userRepository.findAll();
            for(User user : users) {
                user.setPaid(false);
                this.userRepository.save(user);
            }
        }
        return "home";
    }

    @RequestMapping("/signup")
    public String signUp(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/do_register")
    public String register(@Valid @ModelAttribute("user")User user,BindingResult bindingResult,@RequestParam(value="agreement",defaultValue = "false")boolean agreement,Model model) {
        try {
            // System.out.println(user);
            // System.out.println(agreement);
            if(!agreement) {
                throw new Exception("You have not agreed the terms and conditions");
            }

            // System.out.println("Binding Result error printing "+bindingResult.hasErrors());
            if(bindingResult.hasErrors()) {
                System.out.println(bindingResult.toString());
                model.addAttribute("user", user);
                return "signup";
            }

            model.addAttribute("user",user);
            // System.out.println("I am Printing "+this.userRepository.findByEmail(user.getEmail()));
            if(this.userRepository.findByEmail(user.getEmail()).size()>0) {
                throw new Exception("You have already registered try different Email");
            }

            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

            // this.userRepository.save(user);
            // System.out.println(resultUser);
            // System.out.println(user);
            this.userRepository.save(user);

            model.addAttribute("user",new User());
            model.addAttribute("message", new Mssg("You Successfully Registered !!","Thanks","alert-success"));
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("user", user);
            model.addAttribute("message", new Mssg("Something Went Wrong !!"+e.getMessage(),"Sorry","alert-danger"));
        }
        return "signup";
    }

    @GetMapping("/signin")
    public String customLogin(Model model) {
        return "signin";
    }

    @GetMapping("/afterlogin")
    public String doLogin(Principal principal,Model model) {
        User user = this.userRepository.getUserByUserName(principal.getName());
        model.addAttribute("user", user);
        // System.out.println(principal.toString());
        // System.out.println("Hii");
        // System.out.println(principal.getName());
        return "afterLogin";
    }

    @RequestMapping("/completePayment")
    public String completePayment(Principal principal,Model model) {
        User user = this.userRepository.getUserByUserName(principal.getName());
        user.setPaid(true);
        this.userRepository.save(user);
        model.addAttribute("user", user);
        return "afterLogin";
    }

    @RequestMapping("/changeSlot")
    public String changeSlot(Principal principal,Model model) {
        User user = this.userRepository.getUserByUserName(principal.getName());
        model.addAttribute("user", user);
        return "changeSlot";
    }


    @GetMapping("/updateSlot")
    public String updateSlot(@ModelAttribute("user")User user, Model model,Principal principal) {
        System.out.println(user.getBatch());
        // this.userRepository.save(user);
        User user1 = this.userRepository.getUserByUserName(principal.getName());
        user1.setBatch(user.getBatch());
        this.userRepository.save(user1);
        model.addAttribute("user", user1);
        // System.out.println(principal.toString());
        // System.out.println("Hii");
        // System.out.println(principal.getName());
        return "afterLogin";
    }
}