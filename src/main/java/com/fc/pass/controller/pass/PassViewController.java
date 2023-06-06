package com.fc.pass.controller.pass;

import com.fc.pass.service.pass.Pass;
import com.fc.pass.service.pass.PassService;
import com.fc.pass.service.user.User;
import com.fc.pass.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/passes")
public class PassViewController {

    private final UserService userService;
    private final PassService passService;

    public PassViewController(UserService userService, PassService passService) {
        this.userService = userService;
        this.passService = passService;
    }

    @GetMapping
    public ModelAndView getPasses(@RequestParam("userId") String userId, ModelAndView modelAndView) {
        final List<Pass> passes = passService.getPasses(userId);
        final User user = userService.getUser(userId);

        modelAndView.addObject("passes", passes);
        System.out.println(passes);
        modelAndView.addObject("user", user);
        modelAndView.setViewName("pass/index");

        return modelAndView;
    }

}
