package com.example.demo.controller;

import com.example.demo.entity.DanhMuc;
import com.example.demo.entity.PageDTO;
import com.example.demo.repository.DanhMucRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class DanhMucController {
    @Autowired
    DanhMucRepo danhMucRepo;
    @RequestMapping("/admin/danhmuc")
    public String danhMuc(@ModelAttribute("danhmuc") DanhMuc danhMuc, @RequestParam("page") Optional<Integer> page,Model model) {
       PageDTO<DanhMuc> page1 = danhMucRepo.getPageDanhMuc(page.orElse(0));
       model.addAttribute("i",0);
       model.addAttribute("listPDanhMuc",page1);
        return "product/danh_muc";
    }

    @PostMapping("/admin/danhmuc/create")
    public String createDanhMuc(@ModelAttribute("danhmuc") DanhMuc danhMuc) {
        danhMucRepo.createDanhMuc(danhMuc);
        return "redirect:/admin/danhmuc";
    }
    @PostMapping("/admin/danhmuc/update/{x}")
    public String updateDanhMuc(@PathVariable("x") String ma,@ModelAttribute("danhmuc") DanhMuc danhMuc) {
        danhMucRepo.updateDanhMuc(ma,danhMuc);
        return "redirect:/admin/danhmuc";
    }
    @RequestMapping ("/admin/danhmuc/delete/{x}")
    public String deleteDanhMuc(@PathVariable("x") String ma) {
        danhMucRepo.delete(ma);
        return "redirect:/admin/danhmuc";
    }

    @RequestMapping("/admin/danhmuc/detail/{ma}")
    public String createDanhMuc(@PathVariable("ma") String ma,  @RequestParam("page") Optional<Integer> page,Model model) {
        PageDTO<DanhMuc> page1 = danhMucRepo.getPageDanhMuc(page.orElse(0));
        model.addAttribute("listPDanhMuc",page1);
        model.addAttribute("danhmuc", danhMucRepo.getDanhMucByMa(ma));
        return "product/danh_muc";
    }

    @ModelAttribute("listDanhMuc")
    public List<DanhMuc> getListDanhMuc() {
        return danhMucRepo.getListDanhMuc();
    }
}
