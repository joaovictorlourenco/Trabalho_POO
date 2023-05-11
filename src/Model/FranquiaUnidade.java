/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Controller.FranquiaController;
import static Controller.FranquiaController.Franquias;
import static Controller.FranquiaController.listarFranquias;
import controller.PessoaController;
import static controller.PessoaController.listarPessoas;
import static controller.PessoaController.pessoas;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author jv232
 */
public class FranquiaUnidade {
    
    protected long id; 
    private static long serial;
    private long id_franquia; 
    private String cidade;
    private String endereço;
    private long id_responsavel;
    private Date dataCriacao; 
    private Date dataModificacao;

    public FranquiaUnidade() {
        
        Scanner scan = new Scanner(System.in);
        
        serial++;
        
        this.id = serial;
        
        
        for(Franquia Franquia: Franquias){
            
            if(Franquia != null){
                System.out.println(Franquia.toString());
            }
            
        }
        
        System.out.println("Qual será sua Franquia(digite o id)");
        this.setId_franquia(Integer.parseInt(scan.nextLine()));
        
        
        System.out.println("Cidade da Unidade:");
        this.setCidade(scan.nextLine());
        
        
        System.out.println("Endereco da Unidade:");
        this.setEndereço(scan.nextLine());
        

        for(Pessoa p: pessoas){
            if(p != null){
                System.out.println(p.toString());
            }
        }
      
        System.out.println("\nDentre os seguintes usuarios quem será o responsavel(digite o id)");
        this.setId_responsavel(Integer.parseInt(scan.nextLine()));
        
        
        this.setDataCriacao(new Date());
    }
    
    public long getId() {
        return id;
    }
 
    public long getFranquia() {
        return id_franquia;
    }

    public void setFranquia(long franquia) {
        this.id_franquia = id_franquia;
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

    public void setId_franquia(long id_franquia) {
        this.id_franquia = id_franquia;
    }

    public void setId_responsavel(long id_responsavel) {
        this.id_responsavel = id_responsavel;
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

    @Override
    public String toString() {
        return "FranquiaUnidade{" + "id=" + id + ", franquia=" + id_franquia + ", cidade=" + cidade + ", endereco=" + endereço + ", id_responsavel=" + id_responsavel + ", dataCriacao=" + dataCriacao + ", dataModificacao=" + dataModificacao + '}';
    }
 
    
    
    
    
}
