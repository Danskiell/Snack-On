package com.restful.snackapi.service;
import com.restful.snackapi.model.Movimentacao;
import com.restful.snackapi.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MovimentacaoService {
    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    public List<Movimentacao> getAllMovimentacao() {
        return movimentacaoRepository.findAll();
    }

    public Movimentacao getMovimentacaoById(Long id) {
        return movimentacaoRepository.findById(id).orElse(null);
    }

    public Movimentacao saveMovimentacao(Movimentacao movimentacao){
        return movimentacaoRepository.save(movimentacao);
    }

    public void deleteMovimentacao(Long id) {
        movimentacaoRepository.deleteById(id);
    }





}