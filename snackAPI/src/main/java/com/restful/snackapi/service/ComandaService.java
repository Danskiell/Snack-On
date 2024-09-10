package com.restful.snackapi.service;
import com.restful.snackapi.model.Comanda;
import com.restful.snackapi.repository.ComandaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ComandaService{
    @Autowired
    private ComandaRepository comandaRepository;

    public List<Comanda> getAllComandas() {
        return comandaRepository.findAll();
    }

    public Comanda getComandaById(Long id) {
        return comandaRepository.findById(id).orElse(null);
    }

    public Comanda saveComanda(Comanda comanda) {
        return comandaRepository.save(comanda);
    }

    public void deleteComanda(Long id) {
        comandaRepository.deleteById(id);
    }
}