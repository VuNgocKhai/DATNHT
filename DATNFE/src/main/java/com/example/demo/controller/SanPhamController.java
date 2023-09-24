package com.example.demo.controller;

import com.example.demo.entity.Anh;
import com.example.demo.entity.Giay;
import com.example.demo.entity.GiayChiTiet;
import com.example.demo.repository.AnhGiayDAO;
import com.example.demo.repository.GiayChiTietDAO;
import com.example.demo.repository.GiayDAO;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.UUID;

@Controller
public class SanPhamController {
    @Autowired
    AnhGiayDAO anhGiayDAO;
    @Autowired
    GiayDAO giayDAO;
    @Autowired
    GiayChiTietDAO giayChiTietDAO;
    @Data
    public static class SearchForm {
       String tensp="";

       BigDecimal giasp=BigDecimal.ZERO;
    }
    @RequestMapping("/admin/sanpham")
    public String product(Model model,@ModelAttribute("searchform") SearchForm searchForm){
        model.addAttribute("items",giayDAO.getSearch("%"+searchForm.tensp+"%",searchForm.giasp));
        return "product/hien_thi_sp";
    }
    @RequestMapping("/admin/sanpham/{x}")
    public String productDetail(Model model, @PathVariable("x") String x){
        model.addAttribute("items",giayDAO.findAll());
        return "product/index";
    }
    @GetMapping("/admin/sanpham/create")
    public String productCreate(Model model,@ModelAttribute("sanpham") Giay giay){
        model.addAttribute("items",giayDAO.findAll());
        return "product/create_sp";
    }
    @GetMapping("/admin/sanpham/update/{x}")
    public String productUpdate(Model model,@PathVariable("x") String x,@ModelAttribute("giaychitiet") GiayChiTiet giayChiTiet ){
        model.addAttribute("listgiaychitiet",giayChiTietDAO.getAllByMaGiay(x));
        model.addAttribute("anh",anhGiayDAO.getAnhByMaGiay(x));
        model.addAttribute("sanpham",giayDAO.getGiayByMa(x));
        return "product/update_sp";
    }
    @GetMapping(value = "/admin/sanpham/update/{x}",params = "spct")
    public String productUpdate1(Model model, @PathVariable("x") String x, @RequestParam("spct") UUID spct){
        model.addAttribute("itemsdetail",giayChiTietDAO.getAllByMaGiay(x));
        model.addAttribute("giaychitiet",giayChiTietDAO.findById(spct).get());
        model.addAttribute("item",giayDAO.getGiayByMa(x));
        return "product/update_sp";
    }
    @PostMapping("/admin/sanpham/update/{x}")
    public String productUpdate2(Model model,@ModelAttribute("item") Giay ao){
        giayDAO.save(ao);
        return "redirect:/admin/sanpham/update/"+ao.getMa();
    }
    @PostMapping("/admin/sanpham/create")
    public String productCreate2(Model model,@ModelAttribute("sanpham") Giay giay){
        String ma= giayDAO.save(giay).getMa();
        model.addAttribute("items",giayDAO.findAll());
        return "redirect:/admin/sanpham/update/"+ma;
    }
    @PostMapping("/admin/sanpham/createact")
    public String productCreateAct(Model model, @ModelAttribute("giaychitiet") GiayChiTiet giayChiTiet,@RequestParam("idsp")UUID idsp){
        giayChiTiet.setGiay(Giay.builder().id(idsp).build());
        giayChiTietDAO.save(giayChiTiet);
      String ma=  giayDAO.findById(giayChiTiet.getGiay().getId()).get().getMa();
        return "redirect:/admin/sanpham/update/"+ma;
    }
    @PostMapping("/admin/sanpham/createanh")
    public String createanh(Model model, HttpServletRequest request, @RequestParam("idsp")UUID idsp, @RequestPart("ten_url1") MultipartFile file) throws IOException, ServletException {
        Anh anh = new Anh();
        Path path = Paths.get("src/main/webapp/images/");
        try {
            InputStream inputStream = file.getInputStream();
            Files.copy(inputStream,path.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            e.printStackTrace();
        }
        anh.setGiay(Giay.builder().id(idsp).build());
        anh.setTen_url(file.getOriginalFilename());
        String ma=  giayDAO.findById(anh.getGiay().getId()).get().getMa();
        anhGiayDAO.save(anh);
        return "redirect:/admin/sanpham/update/"+ma;
    }
}
