package com.example.demo.controller;

import com.example.demo.entity.ThoiTietThichHop;
import com.example.demo.entity.PageDTO;
import com.example.demo.repository.ThoiTietThichHopRepo;
import com.example.demo.repository.ThoiTietThichHopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ThoiTietThichHopController {
    @Autowired
    ThoiTietThichHopRepo thoiTietThichHopRepo;
    @RequestMapping("/admin/thoitietthichhop")
    public String ThoiTietThichHop(@ModelAttribute("thoitietthichhop") ThoiTietThichHop ThoiTietThichHop, @RequestParam("page") Optional<Integer> page,Model model) {
       PageDTO<ThoiTietThichHop> page1 = thoiTietThichHopRepo.getPageThoiTietThichHop(page.orElse(0));
       model.addAttribute("i",0);
       model.addAttribute("listPThoiTietThichHop",page1);
        return "product/thoi_tiet_thich_hop";
    }

    @PostMapping("/admin/thoitietthichhop/create")
    public String createThoiTietThichHop(@ModelAttribute("thoitietthichhop") ThoiTietThichHop ThoiTietThichHop) {
        thoiTietThichHopRepo.createThoiTietThichHop(ThoiTietThichHop);
        return "redirect:/admin/thoitietthichhop";
    }
    @PostMapping("/admin/thoitietthichhop/update/{x}")
    public String updateThoiTietThichHop(@PathVariable("x") String ma,@ModelAttribute("thoitietthichhop") ThoiTietThichHop ThoiTietThichHop) {
        thoiTietThichHopRepo.updateThoiTietThichHop(ma,ThoiTietThichHop);
        return "redirect:/admin/thoitietthichhop";
    }
    @RequestMapping ("/admin/thoitietthichhop/delete/{x}")
    public String deleteThoiTietThichHop(@PathVariable("x") String ma) {
        thoiTietThichHopRepo.delete(ma);
        return "redirect:/admin/thoitietthichhop";
    }

    @RequestMapping("/admin/thoitietthichhop/detail/{ma}")
    public String createThoiTietThichHop(@PathVariable("ma") String ma,  @RequestParam("page") Optional<Integer> page,Model model) {
        PageDTO<ThoiTietThichHop> page1 = thoiTietThichHopRepo.getPageThoiTietThichHop(page.orElse(0));
        model.addAttribute("listPThoiTietThichHop",page1);
        model.addAttribute("thoitietthichhop", thoiTietThichHopRepo.getThoiTietThichHopByMa(ma));
        return "product/thoi_tiet_thich_hop";
    }

    @ModelAttribute("listThoiTietThichHop")
    public List<ThoiTietThichHop> getListThoiTietThichHop() {
        return thoiTietThichHopRepo.getListThoiTietThichHop();
    }
}
