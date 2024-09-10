package com.restful.snackapi.controller;
import com.restful.snackapi.model.Produto;
import com.restful.snackapi.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<Produto> getAllProdutos() {
        return produtoService.getAllProdutos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable Long id) {
        Produto produto = produtoService.getProdutoById(id);
        if (produto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(produto);
    }

    @PostMapping
    public Produto createProduto(@RequestBody Produto produto) {
        return produtoService.saveProduto(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> updateProduto(@PathVariable Long id, @RequestBody Produto produto) {
        Produto existingProduto = produtoService.getProdutoById(id);
        if (existingProduto == null) {
            return ResponseEntity.notFound().build();
        }
        produto.setId_Produto(id);
        return ResponseEntity.ok(produtoService.saveProduto(produto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
        Produto existingProduto = produtoService.getProdutoById(id);
        if (existingProduto == null) {
            return ResponseEntity.notFound().build();
        }
        produtoService.deleteProduto(id);
        return ResponseEntity.noContent().build();
    }
}


