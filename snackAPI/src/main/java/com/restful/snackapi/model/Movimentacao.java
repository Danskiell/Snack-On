package com.restful.snackapi.model;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Movimentacao")
public class Movimentacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Mov;

    @Column(name = "data_Mov", nullable = false)
    private LocalDate data_Mov;

    @ManyToOne
    @JoinColumn(name = "fk_Cliente_id_Cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "fk_Fun_id_Fun", nullable = false)
    private Funcionario funcionario;

    public Long getId_Mov() {
        return id_Mov;
    }

    public void setId_Mov(Long id_Mov) {
        this.id_Mov = id_Mov;
    }

    public LocalDate getData_Mov() {
        return data_Mov;
    }

    public void setData_Mov(LocalDate data_Mov) {
        this.data_Mov = data_Mov;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}
