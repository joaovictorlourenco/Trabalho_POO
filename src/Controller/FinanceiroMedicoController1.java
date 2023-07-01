/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import Model.DBConnect;

import Model.Consulta;
import Model.FinanceiroMedico;
import Model.Procedimento;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 *
 * @author yn719471
 */
public class FinanceiroMedicoController1 implements Runnable {
    private static List<FinanceiroMedico> financasMedico = new ArrayList();
    
    private Connection connection;
    public FinanceiroMedicoController1() {
        this.connection = new DBConnect().getConnection();
    }
//    private static int count = 0;
    
    @Override
    public void run() {
        for(Consulta c: ConsultaController.consultas){
            if(c != null){
                if(c.getEstado() == 4){
                    cadastraFinanceiroMedico(c.getIdMedico(), c.getUnidade(), (c.getValor()*0.7), 1);
                }
            }
        }
        for(Procedimento p: ProcedimentoController.procedimentos){
            if(p != null){
                if(p.getEstado() == 4){
                    cadastraFinanceiroMedico(p.getIdMedico(), p.getIdUnidade(), (p.getValor()/2), 1);
                }
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
//            boolean res = salvaFinanceiroMedico(fm)
//            if (res == true) {
//                count++;
//            }
        }
    }

    public static List<FinanceiroMedico> listarFinanceiroMedico() {
        return financasMedico;
    }

//    public static boolean salvaFinanceiroMedico(FinanceiroMedico fm) {;
//    }

    public static boolean removeFinanceiroMedico(int id) {
        Iterator<FinanceiroMedico> it = financasMedico.iterator();
        while(it.hasNext()){
            if(it.next().getId() == id){
                it.remove();
                return true;
            }
        }
//        for (int i = 0; i < FinanceiroMedicoController.count; i++) {
//
//            if (FinanceiroMedicoController.financasMedico[i].getId()== id) {
//
//                FinanceiroMedicoController.financasMedico[i] = null;
//
//                for (int j = i; j < FinanceiroMedicoController.count - 1; j++) {
//                    FinanceiroMedicoController.financasMedico[j] = FinanceiroMedicoController.financasMedico[j + 1];
//                }
//
//                FinanceiroMedicoController.count--;
//
//                return true;
//            }
//        }
        return false;
    }

    public static FinanceiroMedico buscarPorId(int id) {
        for(FinanceiroMedico fa: financasMedico){
            if(fa.getId() == id){
                return fa;
            }
        }

//        for (int i = 0; i < FinanceiroMedicoController.count; i++) {
//
//            if (FinanceiroMedicoController.financasMedico[i].getId() == id) {
//
//                return FinanceiroMedicoController.financasMedico[i];
//
//            }
//
//        }

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