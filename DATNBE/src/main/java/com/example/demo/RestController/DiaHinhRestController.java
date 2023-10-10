package com.example.demo.RestController;

import com.example.demo.entity.DiaHinh;
import com.example.demo.entity.PageDTO;
import com.example.demo.repository.DiaHinhDAO;
import com.example.demo.repository.DiaHinhDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/diahinh")
public class DiaHinhRestController {
    @Autowired
    DiaHinhDAO diaHinhDAO;

    @GetMapping()
    public List<DiaHinh> getListDiaHinh() {
        return diaHinhDAO.findAll();
    }
    @GetMapping("/phantrang")
    public PageDTO<DiaHinh> getPageDiaHinh(@RequestParam("page") Optional<Integer> page) {
        Pageable pageable= PageRequest.of(page.orElse(0),5);
        return new PageDTO<>(diaHinhDAO.findAll(pageable));
    }
    @GetMapping("/{ma}")
    public DiaHinh getDiaHinhByMa(@PathVariable("ma") String ma) {
        return diaHinhDAO.findDiaHinhByMa(ma);
    }

    @PostMapping()
    public DiaHinh createDiaHinh(@RequestBody DiaHinh DiaHinh) {
        return diaHinhDAO.save(DiaHinh);
    }

    @PutMapping("/{ma}")
    public DiaHinh updateDiaHinh(@PathVariable("ma") String ma, @RequestBody DiaHinh DiaHinh) {
        return diaHinhDAO.save(DiaHinh);
    }

    @DeleteMapping("/{ma}")
    public void delete(@PathVariable("ma") String ma) {
        diaHinhDAO.deleteDiaHinhByMa(ma);
    }
}
