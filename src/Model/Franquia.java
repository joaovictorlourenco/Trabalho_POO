/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author jd719829
 */
public class Franquia {
    
    private long id;
    private static long serial;
    private String nome; 
    private String cnpj; 
    private String cidade; 
    private String endereço; 
    private long id_responsavel; 
    private Date dataCriacao; 
    private Date dataModificacao;
    
    Scanner scan = new Scanner(System.in);
    public Franquia(String nome, String cnpj, String cidade, String endereço) {
        serial++;
        this.id = serial;
        this.setNome(nome);
        this.setCidade(cidade);
        this.setEndereço(endereço);
        this.setCnpj(cnpj);
        this.setDataCriacao(new Date());
        
    }
    
    public Franquia(){
        
        serial++;
        
        this.id = serial;
        
        System.out.println("digite o nome da Franquia:");
        this.setNome(scan.nextLine());
        
        
        
        
        
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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

    public void setId_responsavel(long id_responsavel) {
        this.id_responsavel = id_responsavel;
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
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Franquia other = (Franquia) obj;
        return Objects.equals(this.cnpj, other.cnpj);
    }

    @Override
    public String toString() {
        return "Franquia{" + "id=" + id + ", nome=" + nome + ", cnpj=" + cnpj + ", cidade=" + cidade + ", endereco=" + endereço + ", id_responsavel=" + id_responsavel + ", dataCriacao=" + dataCriacao + ", dataModificacao=" + dataModificacao + ", scan=" + scan + '}';
    }
    
 
}
