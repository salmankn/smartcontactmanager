package com.validate.smartcontactmanager.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/index")
    public String dashboard(){

        return "normal/user_dashboard";
    }


//    @Autowired
//    private UserRepository userRepository;
//
//    @GetMapping("/test")
//    @ResponseBody
//    public String test(){
//        User user = new User();
//        user.setId(1);
//        user.setName("Salman Khan");
//        user.setEmail("salmankhan@gamil.com");
//
//        Contact contact = new Contact();
//        user.getContacts().add(contact);
//
//        userRepository.save(user);
//        return "working";
//    }
}
