/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import static Controller.FranquiaController.Franquias;
import Model.Franquia;
import Model.FranquiaUnidade;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author jv232
 */
public class FranquiaUnidadeController{
    
    public static List<FranquiaUnidade> Unidades = new ArrayList();
            
            
    public static FranquiaUnidade preCadastraFranquiaUnidades(long idFranquia, long idResponsavel) {

      FranquiaUnidade FranquiaUnidade = new FranquiaUnidade(idFranquia, idResponsavel);

      boolean res = SalvaFranquiaUnidade(FranquiaUnidade);
      
      Franquia f = FranquiaController.buscaPorId((int)idFranquia);

      if(res == true){
          if(f != null) {
            System.out.println("Cadastrado com sucesso");
          }
      } else {
          System.out.println("Ocorreu um erro");
      }

      return (FranquiaUnidade);
    }
    
    
    public static FranquiaUnidade cadastraFranquiaUnidades() {

      FranquiaUnidade FranquiaUnidade = new FranquiaUnidade();
  
      boolean res = SalvaFranquiaUnidade(FranquiaUnidade);
      
      Franquia f = FranquiaController.buscaPorId((int) FranquiaUnidade.getFranquia());

      if(res == true){
          System.out.println("Cadastrado com sucesso");
      } else {
          System.out.println("Ocorreu um erro");
      }

      return (FranquiaUnidade);
    };
    
    public static List<FranquiaUnidade> listarUnidadesFranquia() {
    
        return Unidades;
    }

        
    public static boolean SalvaFranquiaUnidade(FranquiaUnidade FranquiaUnidade) {

            Unidades.add(FranquiaUnidade);
            return true;
        
    }
       
    
      public static boolean removeFranquiaUnidade(int id) {
         Iterator<FranquiaUnidade> iterator = Unidades.iterator();
        while(iterator.hasNext()){
            FranquiaUnidade f = iterator.next();
            if(f.getId() == id){
                iterator.remove();
                return true;
            }

        }
        return false;
    }
     
    public static FranquiaUnidade buscarPorId(int id) {

          for(FranquiaUnidade fu: Unidades){
            if(fu.getId() == id){
                return fu;
            }
        }        

        return null;
    }
    
    public static boolean unidadeExiste(int id) {
       for(FranquiaUnidade fu: Unidades){
           if(fu.getId() == id){
               return true;
           }
       }
       return false;
    }
    
}
