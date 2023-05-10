/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author jv232
 */
public class FranquiaUnidade extends Franquia{
    
    protected long id; 
    private static long serial;
    private long franquia; 
    private String cidade;
    private String endereço;
    private long id_responsavel;
    private Date dataCriacao; 
    private Date dataModificacao;

    public FranquiaUnidade(long id, long franquia, String cidade, String endereço, long id_responsavel, Date dataCriacao, Date dataModificacao) {
        this.id = id;
        this.franquia = franquia;
        this.cidade = cidade;
        this.endereço = endereço;
        this.id_responsavel = id_responsavel;
        this.dataCriacao = dataCriacao;
        this.dataModificacao = dataModificacao;
    }

    
    public long getId() {
        return id;
    }
 
    public long getFranquia() {
        return franquia;
    }

    public void setFranquia(long franquia) {
        this.franquia = franquia;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEndereço() {
        return endereço;
    }

    public void setEndereço(String endereço) {
        this.endereço = endereço;
    }

    public long getId_responsavel() {
        return id_responsavel;
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
 
    
    
    
    
}
