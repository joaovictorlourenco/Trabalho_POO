/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author yn719471
 */
public class FinanceiroMedico {
    private static  long serial;
    private long id;
    private long idMedico;
    private long idFranquia;
    private double valor;
    private int estado;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;
    /*
    ----- estado -----
    1 - Agendado
    2 - Pago
    */
    
    public FinanceiroMedico(long idMedico, long idFranquia, double valor, int estado) {
        this.setId();
        this.setDataCriacao(LocalDate.now());
        this.setIdMedico(idMedico);
        this.setIdFranquia(idFranquia);
        this.setValor(valor);
        this.setEstado(estado);
    }   

    public long getId() {
        return id;
    }

    public void setId() {
        serial++;
        this.id = serial;
    }

    public long getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(long idMedico) {
        this.idMedico = idMedico;
    }

    public long getIdFranquia() {
        return idFranquia;
    }

    public void setIdFranquia(long idFranquia) {
        this.idFranquia = idFranquia;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor += valor;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDate getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(LocalDate dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    @Override
    public String toString() {
        String state = "";
        if(estado == 1) state = "Agendado";
        if(estado == 2) state = "Pago";
        return "FinanceiroMedico{" + "id=" + id + ", idMedico=" + idMedico + ", idFranquia=" + idFranquia + ", valor=" + valor + ", estado=" + state + ", dataCriacao=" + dataCriacao + ", dataModificacao=" + dataModificacao + '}';
    }
    
}
