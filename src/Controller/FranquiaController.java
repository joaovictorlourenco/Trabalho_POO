/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Franquia;
import Model.FranquiaUnidade;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


/**
 *
 * @author jd719829
 */
public class FranquiaController {
    
    public static List<Franquia> Franquias = new ArrayList();
    
    //public static List<Medico> medicos = new ArrayList();
    
  public static void preCadastraFranquia(String nome, long idResponsavel) {
      
        Franquia Franquia = new Franquia(nome, idResponsavel);

        boolean res = SalvaFranquia(Franquia);
        if(res == true){
            System.out.println("Cadastrado com sucesso");
        } else {
            System.out.println("Ocorreu um erro");
        }
    }
    
  public static Franquia cadastraFranquia() {
      
        Franquia Franquia = new Franquia();

        boolean res = SalvaFranquia(Franquia);
        if(res == true){
            System.out.println("Cadastrado com sucesso");
        } else {
            System.out.println("Ocorreu um erro");
        }
        
        return (Franquia);
    }
  
    public static boolean SalvaFranquia(Franquia franquia) {
        Franquias.add(franquia);
        return true;
    }
    
    
    public static boolean removeFranquias(int id) {

        Iterator<Franquia> iterator = Franquias.iterator();
        while(iterator.hasNext()){
            Franquia f = iterator.next();
            if(f.getId() == id){
                iterator.remove();
                return true;
            }
            
        }
        return false;
    }
    
    public static List<Franquia> listarFranquias() {
        
        return Franquias;
        
    }
    
    public static Franquia buscaPorId(int id) {
        for(Franquia f: Franquias){
            if(f.getId() == id){
                return f;
            }
        }        
        return null;
    }
    
}
