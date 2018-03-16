package com.example.xpdayone.controller;


import com.example.xpdayone.businesslogic.StatsCalculator;
import com.example.xpdayone.model.Database;
import com.example.xpdayone.model.Login;
import com.example.xpdayone.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {

    private Database database = new Database();
    private StatsCalculator statsCalculator = new StatsCalculator(database);

    @ModelAttribute
    public void addUsers(Model model){
        model.addAttribute("userlist", database.getAllUsers());
        model.addAttribute("average", statsCalculator.getAverageAmount());

    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model, HttpSession session){
        //session.setAttribute("status", "1"); // leger at vi har logget på
        System.out.println("Session id:" + session.getId());
        return "index";
    }



    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(User user, Model model){
        System.out.println("tilføjet user: " + user.getName());
        addNewUser(user);
        model.addAttribute("name", user.getName());
//        model.addAttribute("userlist", database.getAllUsers());
//        model.addAttribute("average", statsCalculator.getAverageAmount());
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Login login, Model model, HttpSession session){
        if(login.getName().equals("anna") && login.getPassword().equals("123456")){
            session.setAttribute("status", "1");
            System.out.println("Logget på");
        }else {
            session.setAttribute("status", "0");
            System.out.println("Ikke logget på");
        }
//        try {
//            response.sendRedirect("/");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return "index";
    }

    private void addNewUser(User user){
        database.insert(user);
    }

}
