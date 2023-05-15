/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.FinanceiroAdm;
import java.util.Arrays;

/**
 *
 * @author jv232
 */
public class FinanceiroAdmController {
    private static FinanceiroAdm[] FinanceiroAdm = new FinanceiroAdm[100];
    private static int count = 0;
    
        
    
    public static void cadastraFinanceiroADM() {

        FinanceiroAdm mov = new FinanceiroAdm();

        boolean res = salvaFinanceiroADM(mov);
        if (res == true) {
            count++;
            System.out.println("Cadastrada com Sucesso");
        } else {
            System.out.println("Ocorreu um ERRO");
        }

    }
    
    public static boolean salvaFinanceiroADM(FinanceiroAdm mov){
          int prox = proximaPosicaoLivre();
        if (prox != -1) {
            FinanceiroAdm[prox] = mov;
            return true;
        } else {
            return false;
        }
        
        
    }
    
    public static boolean removeFinanceiroAdm(int id) {
        for (int i = 0; i < FinanceiroAdmController.count; i++) {

            if (FinanceiroAdmController.FinanceiroAdm[i].getId()== id) {

                FinanceiroAdmController.FinanceiroAdm[i] = null;

                for (int j = i; j < FinanceiroAdmController.count - 1; j++) {
                    FinanceiroAdmController.FinanceiroAdm[j] = FinanceiroAdmController.FinanceiroAdm[j + 1];
                }

                FinanceiroAdmController.count--;

                return true;
            }
        }
        return false;
    }

    public static int proximaPosicaoLivre() {
        for (int i = 0; i < FinanceiroAdm.length; i++) {
            if (FinanceiroAdm[i] == null) {
                return i;
            }
        }
        return -1;
    }
    
    public static FinanceiroAdm[] listarFinanceiroADM() {
        return Arrays.copyOf(FinanceiroAdm, count);
    }
    
    public static FinanceiroAdm buscarPorId(int id) {

    for (int i = 0; i < FinanceiroAdmController.count; i++) {

        if (FinanceiroAdmController.FinanceiroAdm[i].getId() == id) {

            return FinanceiroAdmController.FinanceiroAdm[i];

        }

    }

        return null;
    }
    
}
