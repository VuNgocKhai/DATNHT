package com.example.demo.controller;

import com.example.demo.entity.ChucVu;
import com.example.demo.entity.HoaDon;
import com.example.demo.entity.NhanVien;
import com.example.demo.entity.PageDTO;
import com.example.demo.repository.ChucVuRepository;
import com.example.demo.repository.HoaDonDAO;
import com.example.demo.repository.NhanVienDAO;
import com.example.demo.repository.NhanVienRepository;
import com.example.demo.dto.NhanVienDto;
import com.example.demo.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("/admin/nhan-vien")
@Controller
public class NhanVienController {

    @Autowired
    private NhanVienService nhanVienService;
    @Autowired
    private NhanVienDAO nhanVienDAO;
    @Autowired
    private HoaDonDAO hoaDonDAO;

    @Autowired
    private NhanVienRepository nhanVienRepository;
    @Autowired
    private ChucVuRepository chucVuRepository;

    PageDTO<NhanVien> page;

    @GetMapping("")
    public String page(@ModelAttribute NhanVien nhanVien,
                       @RequestParam(defaultValue = "0") String number,
                       Model model) {
//        page = nhanVienRepository.getPage(number);
        page=new PageDTO<>(nhanVienService.getAllByTrangThai(1,Integer.valueOf(number)));
        model.addAttribute("page", page);
        return "nhan_vien/nhan_vien/nhan_vien";
    }

    @GetMapping("/find")
    public String find(@RequestParam Optional<String> ma,
                       @RequestParam Optional<String> data,
                       @RequestParam Optional<String> idCv,
                       @ModelAttribute NhanVien nhanVien,
                       Model model,
                       Optional<String> number) {
        page = new PageDTO<>(nhanVienService.findNhanVien(ma,data,idCv, Integer.valueOf(number.orElse("0"))));
        model.addAttribute("page", page);
        model.addAttribute("data", data.orElse(null));
        model.addAttribute("idCv", idCv.orElse(null));
        model.addAttribute("ma", ma.orElse(null));
        return "nhan_vien/nhan_vien/nhan_vien_tim_kiem";
    }

    @GetMapping("/findHoaDon/{maNv}")
    public String findHoaDon(@PathVariable String maNv, @RequestParam(defaultValue = "0") String number, Model model) {
        Pageable pageable = PageRequest.of(Integer.valueOf(number), 5);
        PageDTO<HoaDon> pageDTO = new PageDTO<>(hoaDonDAO.findHdByMaNv(maNv, pageable));
        model.addAttribute("pageHdNv", pageDTO);
        model.addAttribute("nv", nhanVienDAO.findNvByMaNv(maNv));
        return "nhan_vien/nhan_vien/nhan_vien_hoa_don";
    }

    @GetMapping("/view-create")
    public String viewCreate(@ModelAttribute NhanVien nhanVien) {
        return "nhan_vien/nhan_vien/nhan_vien_view_create";
    }

    @GetMapping("/detail/{ma}")
    public String detail(@PathVariable String ma, Model model) {
        if (page == null) {
            page = nhanVienRepository.getPage("0");
        }
        model.addAttribute("nhanVien", nhanVienRepository.getByMa(ma));
        return "nhan_vien/nhan_vien/nhan_vien_view_update";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute NhanVien nhanVien) {
        nhanVienRepository.create(nhanVien);
        return "redirect:/admin/nhan-vien";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute NhanVien nhanVien) {
        nhanVienRepository.update(nhanVien);
        return "redirect:/admin/nhan-vien";
    }

    @GetMapping("/delete/{ma}")
    public String delete(@PathVariable String ma) {
        nhanVienRepository.delete(ma);
        return "redirect:/admin/nhan-vien";
    }

    @ModelAttribute("listNhanVien")
    public List<NhanVien> getListNhanVien() {
        return nhanVienRepository.getList();
    }

    @ModelAttribute("nhanVienDto")
    public NhanVienDto getNhanVienView() {
        return nhanVienDAO.getNhanVienDto();
    }

    @ModelAttribute("listChucVu")
    public List<ChucVu> getListChucVu() {
        return chucVuRepository.getList();
    }
}
