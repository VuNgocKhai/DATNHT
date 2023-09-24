package com.example.demo.controller;

import com.example.demo.entity.DiaHinh;
import com.example.demo.entity.PageDTO;
import com.example.demo.repository.DiaHinhRepo;
import com.example.demo.repository.DiaHinhRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class DiaHinhController {
    @Autowired
    DiaHinhRepo diaHinhRepo;
    @RequestMapping("/admin/diahinh")
    public String DiaHinh(@ModelAttribute("diahinh") DiaHinh DiaHinh, @RequestParam("page") Optional<Integer> page,Model model) {
       PageDTO<DiaHinh> page1 = diaHinhRepo.getPageDiaHinh(page.orElse(0));
       model.addAttribute("i",0);
       model.addAttribute("listPDiaHinh",page1);
        return "product/dia_hinh";
    }

    @PostMapping("/admin/diahinh/create")
    public String createDiaHinh(@ModelAttribute("diahinh") DiaHinh DiaHinh) {
        diaHinhRepo.createDiaHinh(DiaHinh);
        return "redirect:/admin/diahinh";
    }
    @PostMapping("/admin/diahinh/update/{x}")
    public String updateDiaHinh(@PathVariable("x") String ma,@ModelAttribute("diahinh") DiaHinh DiaHinh) {
        diaHinhRepo.updateDiaHinh(ma,DiaHinh);
        return "redirect:/admin/diahinh";
    }
    @RequestMapping ("/admin/diahinh/delete/{x}")
    public String deleteDiaHinh(@PathVariable("x") String ma) {
        diaHinhRepo.delete(ma);
        return "redirect:/admin/diahinh";
    }

    @RequestMapping("/admin/diahinh/detail/{ma}")
    public String createDiaHinh(@PathVariable("ma") String ma,  @RequestParam("page") Optional<Integer> page,Model model) {
        PageDTO<DiaHinh> page1 = diaHinhRepo.getPageDiaHinh(page.orElse(0));
        model.addAttribute("listPDiaHinh",page1);
        model.addAttribute("diahinh", diaHinhRepo.getDiaHinhByMa(ma));
        return "product/dia_hinh";
    }

    @ModelAttribute("listDiaHinh")
    public List<DiaHinh> getListDiaHinh() {
        return diaHinhRepo.getListDiaHinh();
    }
}
