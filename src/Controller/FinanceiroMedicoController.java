/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.FinanceiroMedico;
import java.util.Arrays;

/**
 *
 * @author yn719471
 */
public class FinanceiroMedicoController {
    private static FinanceiroMedico[] financasMedico = new FinanceiroMedico[100];
    private static int count = 0;
    
    
    public static void cadastraFinanceiroMedico(long idMedico, long idConsulta, String descricao) {

        FinanceiroMedico info = new FinanceiroMedico();

        boolean res = salvaFinanceiroMedico(info);
        if (res == true) {
            count++;
            System.out.println("SUCESSO");
        } else {
            System.out.println("Ocorreu um ERRO");
        }

    }

    public static FinanceiroMedico[] listarFinanceiroMedico() {
        return Arrays.copyOf(financasMedico, count);
    }

    public static boolean salvaFinanceiroMedico(FinanceiroMedico info) {
        int prox = proximaPosicaoLivre();
        if (prox != -1) {
            financasMedico[prox] = info;
            return true;
        } else {
            return false;
        }
    }

    public static int proximaPosicaoLivre() {
        for (int i = 0; i < financasMedico.length; i++) {
            if (financasMedico[i] == null) {
                return i;
            }
        }
        return -1;
    }

    public static boolean removeFinanceiroMedico(int id) {
        for (int i = 0; i < FinanceiroMedicoController.count; i++) {

            if (FinanceiroMedicoController.financasMedico[i].getId()== id) {

                FinanceiroMedicoController.financasMedico[i] = null;

                for (int j = i; j < FinanceiroMedicoController.count - 1; j++) {
                    FinanceiroMedicoController.financasMedico[j] = FinanceiroMedicoController.financasMedico[j + 1];
                }

                FinanceiroMedicoController.count--;

                return true;
            }
        }
        return false;
    }

    public static FinanceiroMedico buscarPorId(int id) {

        for (int i = 0; i < FinanceiroMedicoController.count; i++) {

            if (FinanceiroMedicoController.financasMedico[i].getId() == id) {

                return FinanceiroMedicoController.financasMedico[i];

            }

        }

        return null;
    }

    public static boolean financeiroMedicoExiste(int id) {
        for (FinanceiroMedico ic : financasMedico) {
            if (ic.getId() == id) {
                return true;
            }
        }
        return false;
    }

//    public static void alteraFinanceiroMedico(String descricao) {
//        
//    }
}