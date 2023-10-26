package com.example.demo.controller;

import com.example.demo.entity.DoCaoGiay;
import com.example.demo.entity.PageDTO;
import com.example.demo.repository.DoCaoGiayRepo;
import com.example.demo.repository.DoCaoGiayRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class DoCaoGiayController {
    @Autowired
    DoCaoGiayRepo doCaoGiayRepo;
    @RequestMapping("/admin/docaogiay")
    public String DoCaoGiay(@ModelAttribute("docaogiay") DoCaoGiay DoCaoGiay, @RequestParam("page") Optional<Integer> page,Model model) {
       PageDTO<DoCaoGiay> page1 = doCaoGiayRepo.getPageDoCaoGiay(page.orElse(0));
       model.addAttribute("i",0);
       model.addAttribute("listPDoCaoGiay",page1);
        return "product/do_cao_giay";
    }

    @PostMapping("/admin/docaogiay/create")
    public String createDoCaoGiay(@ModelAttribute("docaogiay") DoCaoGiay DoCaoGiay) {
        doCaoGiayRepo.createDoCaoGiay(DoCaoGiay);
        return "redirect:/admin/docaogiay";
    }
    @PostMapping("/admin/docaogiay/update/{x}")
    public String updateDoCaoGiay(@PathVariable("x") String ma,@ModelAttribute("docaogiay") DoCaoGiay DoCaoGiay) {
        doCaoGiayRepo.updateDoCaoGiay(ma,DoCaoGiay);
        return "redirect:/admin/docaogiay";
    }
    @RequestMapping ("/admin/docaogiay/delete/{x}")
    public String deleteDoCaoGiay(@PathVariable("x") String ma) {
        doCaoGiayRepo.delete(ma);
        return "redirect:/admin/docaogiay";
    }

    @RequestMapping("/admin/docaogiay/detail/{ma}")
    public String createDoCaoGiay(@PathVariable("ma") String ma,  @RequestParam("page") Optional<Integer> page,Model model) {
        PageDTO<DoCaoGiay> page1 = doCaoGiayRepo.getPageDoCaoGiay(page.orElse(0));
        model.addAttribute("listPDoCaoGiay",page1);
        model.addAttribute("docaogiay", doCaoGiayRepo.getDoCaoGiayByMa(ma));
        return "product/do_cao_giay";
    }

    @ModelAttribute("listDoCaoGiay")
    public List<DoCaoGiay> getListDoCaoGiay() {
        return doCaoGiayRepo.getListDoCaoGiay();
    }
}
