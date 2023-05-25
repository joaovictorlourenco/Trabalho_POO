/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 *
 * @author yn719471
 */
public class Procedimento {
    private static long serial;
    private long idProcedimento;
    private long idUnidade;
    private long idMedico;
    private String nome;
    private LocalDate dataProcedimento;
    private LocalTime horario;
    private int estado;
    private String laudo;
    private double valor;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;
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

    public LocalDate getdataProcedimento() {
        return dataProcedimento;
    }

    public void setdataProcedimento(LocalDate dataConsulta) {
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

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(LocalDateTime dataModificacao) {
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

    public long getIdMedico() {
        return this.idMedico;
    }
    public long getIdUnidade() {
        return this.idUnidade;
    }
    public void setIdMedico(long id) {
        this.idMedico = id;
    }
    public void setIdUnidade(long id) {
        this.idUnidade = id;
    }
}
