/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Procedimento;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author yn719471
 */
public class ProcedimentoController {
//    public static Procedimento[] procedimentos = new Procedimento[100];
//    public static int count = 0;
    public static List<Procedimento> procedimentos = new ArrayList();

    public static void cadastraProcedimento(String nome, LocalDate data, LocalTime hora, String laudo) {
        Procedimento procedimento = new Procedimento();
        procedimento.setdataProcedimento(data);
        procedimento.setHorario(hora);
        procedimento.setIdProcedimento();
        procedimento.setNome(nome);
        procedimento.setLaudo(laudo);
        procedimento.setValor(500);
        procedimento.setDataCriacao(LocalDateTime.now());
        boolean res = salvaProcedimentos(procedimento);
        if(res == true){
            procedimento.setEstado(2);
        } else{
            System.out.println("Erro ao cadastrar a procedimento");
        }                
    }

    public static boolean salvaProcedimentos(Procedimento p) {
        procedimentos.add(p);
        return true;
//        int prox = proximaPosicaoLivre();
//        if (prox != -1) {
//            procedimentos[prox] = p;
//            return true;
//        } else {
//            return false;
//        }
    }

    public static boolean removeProcedimentos(int id) {
        Iterator<Procedimento> it = procedimentos.iterator();
        while(it.hasNext()){
            Procedimento p = it.next();
            if(p.getIdProcedimento() == id){
                it.remove();
                return true;
            }
        }
//        for (int i = 0; i < ProcedimentoController.count; i++) {
//            if (ProcedimentoController.procedimentos[i].getIdProcedimento()== id) {
//                ProcedimentoController.procedimentos[i] = null;
//                for (int j = i; j < ProcedimentoController.count - 1; j++) {
//                    ProcedimentoController.procedimentos[j] = ProcedimentoController.procedimentos[j + 1];
//                }
//                ProcedimentoController.count--;
//                return true;
//            }
//        }
        return false;
    }
    
    public static Procedimento buscarPorId(int id) {
        for(Procedimento p : procedimentos){
            if(p.getIdProcedimento() == id){
                return p;
            }
        }
//        for (int i = 0; i < ProcedimentoController.count; i++) {
//            if (ProcedimentoController.procedimentos[i].getIdProcedimento()== id) {
//                return ProcedimentoController.procedimentos[i];
//            }
//        }
        return null;
    }

    public static List<Procedimento> listarProcedimentos() {
        return procedimentos;
//        return Arrays.copyOf(ProcedimentoController.procedimentos, ProcedimentoController.count);
    }

    
//    public static int proximaPosicaoLivre() {
//        for (int i = 0; i < ProcedimentoController.procedimentos.length; i++) {
//            if (procedimentos[i] == null) {
//                return i;
//            }
//        }
//        return -1;
//    }
    
    public static Procedimento procedimentoExiste(int id) {
       for(Procedimento p: procedimentos){
           if(p.getIdProcedimento()== id){
               return p;
//               return true;
           }
       }
       return null;
//       return false;
    }
}
