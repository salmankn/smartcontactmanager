package com.validate.smartcontactmanager.Controller;

import com.validate.smartcontactmanager.Entities.User;
import com.validate.smartcontactmanager.Helper.Message;
import com.validate.smartcontactmanager.Repository.UserRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    //Home handler
    @RequestMapping("/")
    public String home(Model model){
        model.addAttribute("title", "Home - Smart Contact Manager");
        return "home";
    }

    //about handler
    @RequestMapping(value = "/about")
    //@GetMapping("/about")
    public String about(Model model){
        model.addAttribute("title", "About - Smart Contact Manager");
        return "about";
    }

    //signUp handler
    @RequestMapping("/signup")
    public String signUp(Model model){
        model.addAttribute("title", "Signup - Smart Contact Manager");
        model.addAttribute("user", new User());
        return "signup";
    }

    //registering user handler
    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, @RequestParam(value = "agreement", defaultValue="false") boolean agreement, Model model, HttpSession session){

        try{
            //apart from User validation if user not applying agreement checkbox then hit false at console
            if (!agreement) {
                System.out.println("You have not agreed the terms and conditions");
                throw new Exception("You have not agreed the terms and conditions");
            }

            //triggering validation of User Entity
            if (bindingResult.hasErrors()) {
                System.out.println("ERROR" + bindingResult);
                model.addAttribute("user", user);
                return "signup";
            }

            //setting user default true
            user.setRole("ROLE_USER");
            //enabling email default true
            user.setEnabled(true);
            //you can set default png file
            user.setImageUrl("default.png");

            System.out.println("Agreement " + agreement);
            System.out.println("USER " + user);

            User newUser = userRepository.save(user);

            model.addAttribute("user", new User());

            model.addAttribute("title", "Signup - Smart Contact Manager");
            //set success message in session
            session.setAttribute("message", new Message("Successfully Register !!", "success"));

        }catch (Exception exception){
            exception.printStackTrace();
            model.addAttribute("user", user);
            //set error message in session
            session.setAttribute("message", new Message("Something went wrong !!" +exception.getMessage(), "danger"));
        }
        //add session message to model
        if(session.getAttribute("message") != null){
            model.addAttribute("message", session.getAttribute("message"));
            session.removeAttribute("message");
        }

        return "signup";
    }
}
