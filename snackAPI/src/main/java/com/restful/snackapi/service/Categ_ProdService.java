package com.restful.snackapi.service;
import com.restful.snackapi.model.Categ_Prod;
import com.restful.snackapi.repository.Categ_ProdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class Categ_ProdService {
    @Autowired
    private Categ_ProdRepository categ_ProdRepository;

    public List<Categ_Prod> getAllCateg_Prods() {
        return categ_ProdRepository.findAll();
    }

    public Categ_Prod getCateg_ProdById(Long id) {
        return categ_ProdRepository.findById(id).orElse(null);
    }

    public Categ_Prod saveCateg_Prod(Categ_Prod categ_Prod) {
        return categ_ProdRepository.save(categ_Prod);
    }

    public void deleteCateg_Prod(Long id) {
        categ_ProdRepository.deleteById(id);
    }
}