package com.restful.snackapi.service;
import com.restful.snackapi.model.Categ_Prod;
import com.restful.snackapi.repository.CategProdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class Categ_ProdService {
    @Autowired
    private CategProdRepository categProdRepository;

    public List<Categ_Prod> getAllCateg_Prods() {
        return categProdRepository.findAll();
    }

    public Categ_Prod getCateg_ProdById(Long id) {
        return categProdRepository.findById(id).orElse(null);
    }

    public Categ_Prod saveCateg_Prod(Categ_Prod categ_Prod) {
        return categProdRepository.save(categ_Prod);
    }

    public void deleteCateg_Prod(Long id) {
        categProdRepository.deleteById(id);
    }
}