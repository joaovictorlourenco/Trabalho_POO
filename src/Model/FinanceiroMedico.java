/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

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
    private Date dataCriacao;
    private Date dataModificacao;
    /*
    ----- estado -----
    1 - Agendado
    2 - Pago
    */

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
        this.valor = valor;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(Date dataModificacao) {
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
