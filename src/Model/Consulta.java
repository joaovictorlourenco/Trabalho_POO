/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalTime;
import java.util.Date;
import java.util.Calendar;

/**
 *
 * @author yn719471
 */
public class Consulta {
    protected long id;
    private static long serial;
    private Date dataConsulta;
    private LocalTime horario;
    private int estado;
    private int idMedico;
    private int idPessoa;
    private double valor;
    private int unidade;
    private Date dataCriacao;
    private Date dataModificacao;
    /* 
    ***
    ******
    *********
    ---- Estado da consulta ----
        1 - Vazio
        2 - Agendada
        3 - Cancelada
        4 - Realizada
    *********
    ******
    ***
    */
    public long getId() {
        return id;
    }

    public void setId() {
        serial++;
        this.id = serial;
    }

    public Date getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(Date dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime hora) {
        this.horario = hora;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getUnidade() {
        return unidade;
    }

    public void setUnidade(int unidade) {
        this.unidade = unidade;
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
        if(estado == 1) state = "Vazio";
        if(estado == 2) state = "Agendado";
        if(estado == 3) state = "Cancelado";
        if(estado == 4) state = "Realizado";
//        return "Consulta{" + "id=" + id + ", dataConsulta=" + dataConsulta + ", horario=" + horario + ", estado=" + state +", idMedico=" + idMedico + ", idPessoa=" + idPessoa + ", valor=" + valor + ", unidade=" + unidade + ", dataCriacao=" + dataCriacao + '}';
        return "Consulta{" + "id=" + id + ", dataConsulta=" + dataConsulta.getDate() +"/"+ (dataConsulta.getMonth()+1) +"/"+ (dataConsulta.getYear()+1900)+ ", horario=" + horario + ", estado=" + state +", idMedico=" + idMedico + ", idPessoa=" + idPessoa + ", valor=" + valor + ", unidade=" + unidade + ", dataCriacao=" + dataCriacao + '}';
    } 
}  