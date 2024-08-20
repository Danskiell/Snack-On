package com.restful.snackapi.service;
import com.restful.snackapi.model.F_Pgto;
import com.restful.snackapi.repository.FPgtoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FPgtoRepository {
    @Autowired
    private FPgtoRepository fpgtoRepository;

    public List<F_Pgto> getAllF_Pgtos() {
        return fpgtoRepository.findAll();
    }

    public F_Pgto getF_PgtoById(Long id) {
        return fpgtoRepository.findById(id).orElse(null);
    }

    public F_Pgto saveF_Pgto(F_Pgto ptgto) {
        return fpgtoRepository.save(ptgto);
    }

    public void deleteF_PgtoById(Long id) {
        fpgtoRepository.deleteById(id);
    }


}