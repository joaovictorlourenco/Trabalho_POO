/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import static Controller.FranquiaController.Franquias;
import Model.Franquia;
import Model.FranquiaUnidade;
import java.util.Arrays;

/**
 *
 * @author jv232
 */
public class FranquiaUnidadeController{
    
    public static FranquiaUnidade[] Unidades = new FranquiaUnidade[100];
    public static int count = 0;
    
    public static FranquiaUnidade preCadastraFranquiaUnidades(long idFranquia, long idResponsavel) {;

      FranquiaUnidade FranquiaUnidade = new FranquiaUnidade(idFranquia, idResponsavel);

      boolean res = SalvaFranquiaUnidade(FranquiaUnidade);
      
      Franquia f = FranquiaController.buscaPorId((int)idFranquia);

      if(res == true){
          count++;
          if(f != null) {
            System.out.println("Cadastrado com sucesso");
          }
      } else {
          System.out.println("Ocorreu um erro");
      }

      return (FranquiaUnidade);
    }
    
    
    public static FranquiaUnidade cadastraFranquiaUnidades() {;;

      FranquiaUnidade FranquiaUnidade = new FranquiaUnidade();
  
      boolean res = SalvaFranquiaUnidade(FranquiaUnidade);
      
      Franquia f = FranquiaController.buscaPorId((int) FranquiaUnidade.getFranquia());

      if(res == true){
          count++;
          System.out.println("Cadastrado com sucesso");
      } else {
          System.out.println("Ocorreu um erro");
      }

      return (FranquiaUnidade);
    };
    
    public static FranquiaUnidade[] listarUnidadesFranquia() {
    
        return Arrays.copyOf(FranquiaUnidadeController.Unidades, FranquiaUnidadeController.count);
    }

        
    public static boolean SalvaFranquiaUnidade(FranquiaUnidade FranquiaUnidade) {
     int prox = proximaPosicaoLivre();
         if (prox != -1) {
             Unidades[prox] = FranquiaUnidade;
             return true;
         } else {
             return false;
         }
    }
       
    public static int proximaPosicaoLivre() {
        for (int i = 0; i < FranquiaController.Franquias.length; i++) {
            if (Unidades[i] == null) {
                return i;
            }
        }
        return -1;
    }
    
      public static boolean removeFranquiaUnidade(int id) {
        for (int i = 0; i < FranquiaUnidadeController.count; i++) {
            
            if (FranquiaUnidadeController.Unidades[i].getId() == id) {
                
                FranquiaUnidadeController.Unidades[i] = null;
                
                for (int j = i; j < FranquiaUnidadeController.count - 1; j++) {
                    FranquiaUnidadeController.Unidades[j] = FranquiaUnidadeController.Unidades[j + 1];
                }
                
                FranquiaUnidadeController.count--;
               
                return true;
            }
        }
        return false;
    }
     
    public static FranquiaUnidade buscarPorId(int id) {

        for (int i = 0; i < FranquiaUnidadeController.count; i++) {

            if (FranquiaUnidadeController.Unidades[i].getId() == id) {

                return FranquiaUnidadeController.Unidades[i];

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
