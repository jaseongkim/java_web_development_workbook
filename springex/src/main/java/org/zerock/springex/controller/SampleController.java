package org.zerock.springex.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Log4j2
@Controller
public class SampleController {

    @GetMapping("/hello")
    public void hello() {
        log.info("hello.......");
    }

    @GetMapping("/ex01")
    public void ex1(String name, int age) {
        log.info("ex1........");
        log.info("name : " + name);
        log.info("age : " + age);

    }

    @GetMapping("/ex02")
    public void ex02(@RequestParam(name = "name", defaultValue = "AAA") String name,
                     @RequestParam(name = "age", defaultValue = "20") int age) {
        log.info("ex2.......");
        log.info("name: " + name);
        log.info("age: " + age);
    }

    @GetMapping("/ex03")
    public void ex03(LocalDate dueDate) {
        log.info(dueDate);
    }

    @GetMapping("/ex04")
    public void ex04(Model model) {
        log.info("ex04.....");
        model.addAttribute("message", "Hello World");
    }

    @GetMapping("/ex07")
    public void ex07(String name, int age) {
        log.info("ex07.....");
        log.info("name : " + name);
        log.info("age : " + age);
    }


}
