package com.example.demo.RestController;

import com.example.demo.entity.DoCaoGiay;
import com.example.demo.entity.PageDTO;
import com.example.demo.repository.DoCaoGiayDAO;
import com.example.demo.repository.DoCaoGiayDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/docaogiay")
public class DoCaoGiayRestController {
    @Autowired
    DoCaoGiayDAO doCaoGiayDAO;

    @GetMapping()
    public List<DoCaoGiay> getListDoCaoGiay() {
        return doCaoGiayDAO.findAll();
    }
    @GetMapping("/phantrang")
    public PageDTO<DoCaoGiay> getPageDoCaoGiay(@RequestParam("page") Optional<Integer> page) {
        Pageable pageable= PageRequest.of(page.orElse(0),5);
        return new PageDTO<>(doCaoGiayDAO.findAll(pageable));
    }
    @GetMapping("/{ma}")
    public DoCaoGiay getDoCaoGiayByMa(@PathVariable("ma") String ma) {
        return doCaoGiayDAO.findDoCaoGiayByMa(ma);
    }

    @PostMapping()
    public DoCaoGiay createDoCaoGiay(@RequestBody DoCaoGiay DoCaoGiay) {
        return doCaoGiayDAO.save(DoCaoGiay);
    }

    @PutMapping("/{ma}")
    public DoCaoGiay updateDoCaoGiay(@PathVariable("ma") String ma, @RequestBody DoCaoGiay DoCaoGiay) {
        return doCaoGiayDAO.save(DoCaoGiay);
    }

    @DeleteMapping("/{ma}")
    public void delete(@PathVariable("ma") String ma) {
        doCaoGiayDAO.deleteDoCaoGiayByMa(ma);
    }
}
