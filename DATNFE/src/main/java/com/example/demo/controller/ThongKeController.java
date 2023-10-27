package com.example.demo.controller;

import com.example.demo.repository.HoaDonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class ThongKeController {

    @Autowired
    HoaDonDAO hoaDonDAO;

    @RequestMapping("/admin/thong-ke")
    public String index(Model model){
        LocalDate ngayHienTai = LocalDate.now();
        Optional<BigDecimal> tongTien = Optional.ofNullable(hoaDonDAO.tongSoTienHomNay(ngayHienTai));
        model.addAttribute("tongTien", tongTien.orElse(BigDecimal.ZERO));

        Integer tongsohoadondangchoxanhan = hoaDonDAO.tongsohoadondangchoxanhan();
        model.addAttribute("tongsohoadondangchoxanhan", tongsohoadondangchoxanhan);

        return "thongke/thongke";
    }

}
