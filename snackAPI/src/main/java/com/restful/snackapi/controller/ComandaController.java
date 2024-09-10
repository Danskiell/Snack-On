package com.restful.snackapi.controller;
import com.restful.snackapi.model.Comanda;
import com.restful.snackapi.service.ComandaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comandas")
public class ComandaController {
    @Autowired
    private ComandaService comandaService;

    @GetMapping
    public List<Comanda> getAllComandas(){
        return comandaService.getAllComandas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comanda> getComandaById(@PathVariable Long id){
       Comanda comanda = comandaService.getComandaById(id);
       if(comanda == null){
           return ResponseEntity.notFound().build();
       }
       return ResponseEntity.ok(comanda);
    }

    @PostMapping
    public Comanda createComanda(@RequestBody Comanda comanda){
        return comandaService.saveComanda(comanda);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comanda> updateComanda(@PathVariable Long id, @RequestBody Comanda comanda){
        Comanda existingComanda = comandaService.getComandaById(id);
        if(existingComanda == null){
            return ResponseEntity.notFound().build();
        }
        comanda.setId_Comanda(id);
        return ResponseEntity.ok(comandaService.saveComanda(comanda));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComanda(@PathVariable Long id){
        Comanda existingComanda = comandaService.getComandaById(id);
        if(existingComanda == null){
            return ResponseEntity.notFound().build();
        }
        comandaService.deleteComanda(id);
        return ResponseEntity.noContent().build();
    }







}