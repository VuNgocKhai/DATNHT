package com.example.demo.RestController;

import com.example.demo.entity.CamGiac;
import com.example.demo.entity.CamGiac;
import com.example.demo.entity.PageDTO;
import com.example.demo.repository.CamGiacDAO;
import com.example.demo.repository.CamGiacDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/camgiac")
public class CamGiacRestController {
    @Autowired
    CamGiacDAO camGiacDAO;

    @GetMapping()
    public List<CamGiac> getListCamGiac() {
        return camGiacDAO.findAll();
    }
    @GetMapping("/phantrang")
    public PageDTO<CamGiac> getPageCamGiac(@RequestParam("page") Optional<Integer> page) {
        Pageable pageable= PageRequest.of(page.orElse(0),5);
        return new PageDTO<>(camGiacDAO.findAll(pageable));
    }
    @GetMapping("/{ma}")
    public ResponseEntity<?> getCamGiacByMa(@PathVariable("ma") String ma) {
        if (ma.equals("null")){
            return new ResponseEntity<>("Loi", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(camGiacDAO.findCamGiacByMa(ma), HttpStatus.OK);
    }

    @PostMapping()
    public CamGiac createCamGiac(@RequestBody CamGiac CamGiac) {
        return camGiacDAO.save(CamGiac);
    }

    @PutMapping("/{ma}")
    public CamGiac updateCamGiac(@PathVariable("ma") String ma, @RequestBody CamGiac CamGiac) {
        return camGiacDAO.save(CamGiac);
    }

    @DeleteMapping("/{ma}")
    public void delete(@PathVariable("ma") String ma) {
        camGiacDAO.deleteCamGiacByMa(ma);
    }
}
