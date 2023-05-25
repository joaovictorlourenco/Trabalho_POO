/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Consulta;
import Model.FinanceiroMedico;
import Model.Procedimento;
import java.util.Arrays;
/**
 *
 * @author yn719471
 */
public class FinanceiroMedicoController implements Runnable {
    private static FinanceiroMedico[] financasMedico = new FinanceiroMedico[1000];
    private static int count = 0;
    
    @Override
    public void run() {
        for(Consulta c: ConsultaController.consultas){
            if(c != null){
                cadastraFinanceiroMedico(c.getIdMedico(), c.getUnidade(), (c.getValor()*0.7), 1);
            }
        }
        for(Procedimento p: ProcedimentoController.procedimentos){
            if(p != null){
                cadastraFinanceiroMedico(p.getIdMedico(), p.getIdUnidade(), (p.getValor()/2), 1);
            }
        }
        for(FinanceiroMedico fm: financasMedico){
            if(fm != null){
                fm = null;
            }
        }
    }

    
    
    public static void cadastraFinanceiroMedico(long idMedico, long idFranquia, double valor, int estado) {
        boolean registrado = false;
        for(FinanceiroMedico fm: financasMedico){
            if(fm != null){
                if(idMedico == fm.getIdMedico()){
                    fm.setValor(valor);
                    registrado = true;
                }
            }
        }
        if(registrado == false){
            FinanceiroMedico fm = new FinanceiroMedico(idMedico, idFranquia, valor, estado);
            boolean res = salvaFinanceiroMedico(fm);
            if (res == true) {
                count++;
            }
        }
    }

    public static FinanceiroMedico[] listarFinanceiroMedico() {
        return Arrays.copyOf(financasMedico, count);
    }

    public static boolean salvaFinanceiroMedico(FinanceiroMedico fm) {
        int prox = proximaPosicaoLivre();
        if (prox != -1) {
            financasMedico[prox] = fm;
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
}