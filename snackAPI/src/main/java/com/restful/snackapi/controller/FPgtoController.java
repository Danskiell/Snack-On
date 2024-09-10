package com.restful.snackapi.controller;
import com.restful.snackapi.model.F_Pgto;
import com.restful.snackapi.service.FPgtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/f_pgto")
public class FPgtoController {
    @Autowired
    private FPgtoService fPgtoService;

    @GetMapping
    public List<F_Pgto> getAllFPgtos(){
        return fPgtoService.getAllF_Pgtos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<F_Pgto> getF_PgtoById(@PathVariable Long id) {
        F_Pgto fPgto = fPgtoService.getF_PgtoById(id);
        if (fPgto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(fPgto);
    }

    @PostMapping
    public F_Pgto createF_Pgto(@RequestBody F_Pgto fPgto) {
        return fPgtoService.saveF_Pgto(fPgto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<F_Pgto> updateF_Pgto(@PathVariable Long id, @RequestBody F_Pgto fPgto) {
        F_Pgto existingF_Pgto = fPgtoService.getF_PgtoById(id);
        if (existingF_Pgto == null) {
            return ResponseEntity.notFound().build();
        }
        fPgto.setId_Pgto(id);
        return ResponseEntity.ok(fPgtoService.saveF_Pgto(fPgto));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteF_Pgto(@PathVariable Long id) {
        F_Pgto existingF_Pgto = fPgtoService.getF_PgtoById(id);
        if (existingF_Pgto == null) {
            return ResponseEntity.notFound().build();
        }
        fPgtoService.deleteF_PgtoById(id);
        return ResponseEntity.noContent().build();
    }

}