/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDateTime;

/**
 *
 * @author yn719471
 */
public class InfoConsulta {
    private static long serial;
    private long idInfoConsulta;
    private long idConsulta;
    private long idMedico;
    private String descricao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;
    

    public long getIdInfoConsulta() {
        return idInfoConsulta;
    }

    public void setIdInfoConsulta(long idInfoConsulta) {
        this.idInfoConsulta = idInfoConsulta;
    }

    public long getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(long idConsulta) {
        this.idConsulta = idConsulta;
    }

    public long getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(long idMedico) {
        this.idMedico = idMedico;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
}
