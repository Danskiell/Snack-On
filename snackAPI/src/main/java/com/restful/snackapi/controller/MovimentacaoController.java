package com.restful.snackapi.controller;
import com.restful.snackapi.model.Movimentacao;
import com.restful.snackapi.service.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoController {
    @Autowired
    private MovimentacaoService movimentacaoService;

    @GetMapping
    public List<Movimentacao> getAllMovimentacoes() {
        return movimentacaoService.getAllMovimentacao();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movimentacao> getMovimentacaoById(@PathVariable Long id) {
        Movimentacao movimentacao = movimentacaoService.getMovimentacaoById(id);
        if(movimentacao == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movimentacao);
    }

    @PostMapping
    public Movimentacao createMovimentacao(@RequestBody Movimentacao movimentacao) {
        return movimentacaoService.saveMovimentacao(movimentacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movimentacao> updateMovimentacao(@PathVariable Long id, @RequestBody Movimentacao movimentacao) {
        Movimentacao existingMovimentacao = movimentacaoService.getMovimentacaoById(id);
        if(existingMovimentacao == null){
            return ResponseEntity.notFound().build();
        }
        movimentacao.setId_Mov(id);
        return ResponseEntity.ok(movimentacaoService.saveMovimentacao(movimentacao));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovimentacao(@PathVariable Long id) {
        Movimentacao existingMovimentacao = movimentacaoService.getMovimentacaoById(id);
        if(existingMovimentacao == null){
            return ResponseEntity.notFound().build();
        }
        movimentacaoService.deleteMovimentacao(id);
        return ResponseEntity.noContent().build();  // 204 No Content status code
    }


}