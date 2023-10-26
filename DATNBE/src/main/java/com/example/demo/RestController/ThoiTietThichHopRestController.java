package com.example.demo.RestController;

import com.example.demo.entity.ThoiTietThichHop;
import com.example.demo.entity.PageDTO;
import com.example.demo.repository.ThoiTietThichHopDAO;
import com.example.demo.repository.ThoiTietThichHopDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/thoitietthichhop")
public class ThoiTietThichHopRestController {
    @Autowired
    ThoiTietThichHopDAO thoiTietThichHopDAO;

    @GetMapping()
    public List<ThoiTietThichHop> getListThoiTietThichHop() {
        return thoiTietThichHopDAO.findAll();
    }
    @GetMapping("/phantrang")
    public PageDTO<ThoiTietThichHop> getPageThoiTietThichHop(@RequestParam("page") Optional<Integer> page) {
        Pageable pageable= PageRequest.of(page.orElse(0),5);
        return new PageDTO<>(thoiTietThichHopDAO.findAll(pageable));
    }
    @GetMapping("/{ma}")
    public ThoiTietThichHop getThoiTietThichHopByMa(@PathVariable("ma") String ma) {
        return thoiTietThichHopDAO.findThoiTietThichHopByMa(ma);
    }

    @PostMapping()
    public ThoiTietThichHop createThoiTietThichHop(@RequestBody ThoiTietThichHop ThoiTietThichHop) {
        return thoiTietThichHopDAO.save(ThoiTietThichHop);
    }

    @PutMapping("/{ma}")
    public ThoiTietThichHop updateThoiTietThichHop(@PathVariable("ma") String ma, @RequestBody ThoiTietThichHop ThoiTietThichHop) {
        return thoiTietThichHopDAO.save(ThoiTietThichHop);
    }

    @DeleteMapping("/{ma}")
    public void delete(@PathVariable("ma") String ma) {
        thoiTietThichHopDAO.deleteThoiTietThichHopByMa(ma);
    }
}
