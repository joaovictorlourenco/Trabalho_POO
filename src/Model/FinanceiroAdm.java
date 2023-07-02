/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import static Controller.FranquiaController.buscaPorId;
import static Controller.FranquiaController.listarFranquias;
import static Controller.FranquiaUnidadeController.Unidades;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.Date;

/**
 *
 * @author jv232
 */
public class FinanceiroAdm {
    
    private static long serial;
    private long id;
    private int tipoMovimento;
    private long idFranquia;
    private long idUnidade; 
    private String descritivoMovimento;
    private double valor;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    /*
        ----- Movimento -----
        1 - Entrada
        2 - Saida
    */
    
//    Scanner scan = new Scanner(System.in);
//    public FinanceiroAdm() {
//          
//        
//        serial++;
//        
//        this.id = serial;
//        
//        
//        System.out.println("1 - Entrada");
//        System.out.println("2 - Saida");
//        this.setEstado(scan.nextInt());
//             
//        
//        listarFranquias();
//        System.out.println("Atribuir a qual Franquia (digite o id):");
//        int opc = scan.nextInt();
//        this.setIdFranquia(opc);
//        
//        Franquia franquia = buscaPorId(opc);
//        
//        if(franquia == null){
//            
//            System.out.println("Não tem Franquia");
//        
//        }else{
//            
//            System.out.println("Unidades dessa Franquia");
//                
//            for(FranquiaUnidade f : Unidades){
//
//                if(f.getFranquia() == franquia.getId()){
//                    System.out.println("-----------------------------");
//                    System.out.println(f.toString());
//                    System.out.println("-----------------------------");
//                }
//
//            }
//            
//        }
//        System.out.println("Digite a unidade que deseja atribuir:");
//
//        this.setIdUnidade(scan.nextInt());
//        
//        System.out.println("Descrição exemplos: Contas de agua, pagamento, energia:");
//
//        this.setDescritivoMovimento(scan.nextLine());
//        
//        System.out.println("Valor:");
//        
//        this.setValor(scan.nextDouble());
//                
//        
//        this.setDataCriacao(new Date());
//        
//    
//
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
      this.id = (long) id;
    }

    public int getTipoMovimento() {
        return tipoMovimento;
    }

    public void setTipoMovimento(int tipoMovimento) {
        this.tipoMovimento = tipoMovimento;
    }

    public long getIdFranquia() {
        return idFranquia;
    }

    public void setIdFranquia(long idFranquia) {
        this.idFranquia = idFranquia;
    }

    public long getIdUnidade() {
        return idUnidade;
    }

    public void setIdUnidade(long idUnidade) {
        this.idUnidade = idUnidade;
    }

    public String getDescritivoMovimento() {
        return descritivoMovimento;
    }

    public void setDescritivoMovimento(String descritivoMovimento) {
        this.descritivoMovimento = descritivoMovimento;
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

        String state = tipoMovimento == 1 ? "Entrada" : "Saida" ;
        
        return "FinanceiroAdm{" + "id=" + id + ", tipoMovimento=" + state + ", idFranquia=" + idFranquia + ", idUnidade=" + idUnidade + ", descritivoMovimento=" + descritivoMovimento + ", valor=" + valor + ", dataCriacao=" + dataCriacao + ", dataModificacao=" + dataModificacao + '}';
    }
    
}
