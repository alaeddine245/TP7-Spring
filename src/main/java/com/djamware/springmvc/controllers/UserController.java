/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.djamware.springmvc.controllers;

import com.djamware.springmvc.models.User;
import com.djamware.springmvc.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/user")
    public String user(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "user";
    }

    @RequestMapping("/create")
    public String create(Model model) {
        return "create";
    }

    @RequestMapping("/save")
    public String save(@RequestParam String login, @RequestParam String pass, @RequestParam String ville, @RequestParam String email) {
        User user = new User();
        user.setLogin(login);
        user.setPass(pass);
        user.setVille(ville);
        user.setEmail(email);
        userRepository.save(user);

        return "redirect:/show/" + user.getId();
    }

    @RequestMapping("/show/{id}")
    public String show(@PathVariable Long id, Model model) {
        model.addAttribute("user", userRepository.findById(id).orElse(null));
        return "show";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam Long id) {
        User user = userRepository.findById(id).orElse(null);
        userRepository.delete(user);

        return "redirect:/user";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("user", userRepository.findById(id).orElse(null));
        return "edit";
    }

    @RequestMapping("/update")
    public String update(@RequestParam Long id, @RequestParam String login, @RequestParam String pass, @RequestParam String ville,@RequestParam String email) {
        User user = userRepository.findById(id).orElse(null);
        user.setLogin(login);
        user.setPass(pass);
        user.setVille(ville);
        user.setEmail(email);
        userRepository.save(user);

        return "redirect:/show/" + user.getId();
    }

}