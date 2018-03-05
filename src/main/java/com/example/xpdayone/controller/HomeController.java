package com.example.xpdayone.controller;


import com.example.xpdayone.businesslogic.StatsCalculator;
import com.example.xpdayone.model.Database;
import com.example.xpdayone.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class HomeController {

    private Database database = new Database();
    private List<User> users = database.getAllUsers();
    private StatsCalculator statsCalculator = new StatsCalculator(database);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("userlist", users);
        model.addAttribute("average", statsCalculator.getAverageAmount());
        return "index";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(User user, Model model){
        System.out.println("tilføjet user: " + user.getName());
        addNewUser(user);
        model.addAttribute("userlist", users);
        model.addAttribute("average", statsCalculator.getAverageAmount());
        return "index";
    }

    private void addNewUser(User user){
        database.insert(user);
    }

}
