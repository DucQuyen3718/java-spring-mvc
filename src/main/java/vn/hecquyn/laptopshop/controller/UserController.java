package vn.hecquyn.laptopshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.hecquyn.laptopshop.service.UserService;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String getHomePage() {
        String test = this.userService.handleHello();
        return "hello";
    }
}

// @RestController
// public class UserController {
//     private UserService userService;
//     public UserController(UserService userService) {
//         this.userService = userService;
//     }
//     @GetMapping("/")
//     public String getHomePage() {
//         return this.userService.handleHello();
//     }
// }
