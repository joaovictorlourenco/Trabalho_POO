/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import controller.PessoaController;
import static controller.PessoaController.listarPessoas;
import static controller.PessoaController.pessoas;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;
import javax.swing.text.html.HTMLEditorKit;

/**
 *
 * @author jd719829
 */
public class Franquia {
    
    protected long id;
    private static long serial;
    private String nome; 
    private String cnpj; 
    private String cidade; 
    private String endereço; 
    private long id_responsavel; 
    private LocalDateTime dataCriacao; 
    private LocalDateTime dataModificacao;
    
//    Scanner scan = new Scanner(System.in);
//    public Franquia(String nome, long idResponsavel) {
//        serial++;
//        this.id = serial;
//        this.setNome(nome);
//        this.setCidade("cidade");
//        this.setEndereço("endereco");
//        this.setCnpj("CNPJ");
//        this.setDataCriacao(new Date());
//        this.setId_responsavel(idResponsavel);
//
//    }
    
//    public Franquia(){
//        
//        serial++;
//        
//        this.id = serial;
//        
//        System.out.println("digite o nome da Franquia:");
//        this.setNome(scan.nextLine());
//        
//        System.out.println("digite a cidade da matriz");
//        this.setCidade(scan.nextLine());
//       
//        for(Pessoa p: pessoas){
//            if(p != null){
//                System.out.println(p.toString());
//            }
//        }
//        
//        System.out.println("Dentre os seguintes usuarios quem será o responsavel(digite o id)");
//        this.setId_responsavel(Integer.parseInt(scan.nextLine()));
//        
//        System.out.println("digite o Endereco");
//        this.setEndereço(scan.nextLine());
//        
//        System.out.println("digite o Cnpj");
//        this.setCnpj(scan.nextLine());
//        
//        this.setDataCriacao(new Date());
//        
//    } 
    
    public long getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = (long) id;
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
        return "Franquia{" + "id=" + id + ", nome=" + nome + ", cnpj=" + cnpj + ", cidade=" + cidade + ", endereco=" + endereço + ", id_responsavel=" + id_responsavel + ", dataCriacao=" + dataCriacao + ", dataModificacao=" + dataModificacao + '}';
    }
    
 
}
