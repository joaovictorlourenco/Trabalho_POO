/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalTime;
import java.util.Date;

/**
 *
 * @author yn719471
 */
public class Procedimento {
    private static long serial;
    private long idProcedimento;
    private String nome;
    private Date dataProcedimento;
    private LocalTime horario;
    private int estado;
    private String laudo;
    private double valor;
    private Date dataCriacao;
    private Date dataModificacao;
    /* 
    ***
    ******
    *********
    ---- Estado do Procedimento ----
        1 - Vazio
        2 - Agendada
        3 - Cancelada
        4 - Realizada
    *********
    ******
    ***
    */
//    public Procedimento(String nome, Date data, LocalTime hora){
//        
//    }

    public long getIdProcedimento() {
        return idProcedimento;
    }

    public void setIdProcedimento() {
        this.idProcedimento = serial;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getdataProcedimento() {
        return dataProcedimento;
    }

    public void setdataProcedimento(Date dataConsulta) {
        this.dataProcedimento = dataConsulta;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getLaudo() {
        return laudo;
    }

    public void setLaudo(String laudo) {
        this.laudo = laudo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
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
        return "Procedimento{" + "idProcedimento=" + idProcedimento + ", nome=" + nome + ", dataConsulta=" + dataProcedimento + ", horario=" + horario + ", estado=" + state + ", laudo=" + laudo + ", valor=" + valor + ", dataCriacao=" + dataCriacao + '}';
    }
}
