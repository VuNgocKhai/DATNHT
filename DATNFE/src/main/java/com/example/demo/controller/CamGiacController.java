package com.example.demo.controller;

import com.example.demo.entity.CamGiac;
import com.example.demo.entity.PageDTO;
import com.example.demo.repository.CamGiacRepo;
import com.example.demo.repository.CamGiacRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class CamGiacController {
    @Autowired
    CamGiacRepo camGiacRepo;
    @RequestMapping("")
    public String slidebar() {
        return "sildebar/sildebar";
    }
    @RequestMapping("/admin/camgiac")
    public String CamGiac(@ModelAttribute("camgiac") CamGiac CamGiac, @RequestParam("page") Optional<Integer> page,Model model) {
       PageDTO<CamGiac> page1 = camGiacRepo.getPageCamGiac(page.orElse(0));
       model.addAttribute("i",0);
       model.addAttribute("listPCamGiac",page1);
        return "product/cam_giac";
    }

    @PostMapping("/admin/camgiac/create")
    public String createCamGiac(@ModelAttribute("camgiac") CamGiac CamGiac) {
        camGiacRepo.createCamGiac(CamGiac);
        return "redirect:/admin/camgiac";
    }
    @PostMapping("/admin/camgiac/update/{x}")
    public String updateCamGiac(@PathVariable("x") String ma,@ModelAttribute("camgiac") CamGiac CamGiac) {
        camGiacRepo.updateCamGiac(ma,CamGiac);
        return "redirect:/admin/camgiac";
    }
    @RequestMapping ("/admin/camgiac/delete/{x}")
    public String deleteCamGiac(@PathVariable("x") String ma) {
        camGiacRepo.delete(ma);
        return "redirect:/admin/camgiac";
    }

    @RequestMapping("/admin/camgiac/detail/{ma}")
    public String createCamGiac(@PathVariable("ma") String ma,  @RequestParam("page") Optional<Integer> page,Model model) {
        PageDTO<CamGiac> page1 = camGiacRepo.getPageCamGiac(page.orElse(0));
        model.addAttribute("listPCamGiac",page1);
        model.addAttribute("camgiac", camGiacRepo.getCamGiacByMa(ma));
        return "product/cam_giac";
    }
   }
