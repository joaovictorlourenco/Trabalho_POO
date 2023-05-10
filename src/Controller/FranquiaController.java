/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Franquia;
import java.util.Arrays;


/**
 *
 * @author jd719829
 */
public class FranquiaController {
    
    public static Franquia[] Franquias = new Franquia[100];
    public static int count = 0;
    
  public static Franquia cadastraFranquia() {
      
        Franquia Franquia = new Franquia();

        boolean res = SalvaFranquia(Franquia);
        System.out.println(Arrays.toString(Franquias));
        if(res == true){
            count++;
            System.out.println("Cadastrado com sucesso");
        } else {
            System.out.println("Ocorreu um erro");
        }
        
        return (Franquia);
    }
  
    public static boolean SalvaFranquia(Franquia franquia) {
        int prox = proximaPosicaoLivre();
            if (prox != -1) {
                Franquias[prox] = franquia;
                return true;
            } else {
                return false;
            }
    }
    
    public static int proximaPosicaoLivre() {
        for (int i = 0; i < FranquiaController.Franquias.length; i++) {
            if (Franquias[i] == null) {
                return i;
            }
        }
        return -1;
    }
    
    public static boolean removeMedicos(int id) {
        for (int i = 0; i < MedicoController.count; i++) {
            if (MedicoController.medicos[i].getId_pessoa() == id) {
                MedicoController.medicos[i] = null;
                for (int j = i; j < MedicoController.count - 1; j++) {
                    MedicoController.medicos[j] = MedicoController.medicos[j + 1];
                }
                MedicoController.count--;
                return true;
            }
        }
        return false;
    }
    
    public static Franquia[] listarFranquias() {
//        return pessoas;
        return Arrays.copyOf(FranquiaController.Franquias, FranquiaController.count);
    }
    
}
