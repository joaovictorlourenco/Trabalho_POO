/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

//import static Controller.ConsultaController.consultas;
//import static Controller.ConsultaController.count;
import Model.Consulta;
import Model.Procedimento;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author yn719471
 */
public class ProcedimentoController {
    public static Procedimento[] procedimentos = new Procedimento[100];
    public static int count = 0;

    public static void cadastraProcedimento(String nome, Date data, LocalTime hora, String laudo) {
        Procedimento procedimento = new Procedimento();
        procedimento.setdataProcedimento(data);
        procedimento.setHorario(hora);
        procedimento.setIdProcedimento();
        procedimento.setNome(nome);
        procedimento.setLaudo(laudo);
        procedimento.setValor(500);
        procedimento.setDataCriacao(new Date());
        boolean res = salvaProcedimentos(procedimento);
        if(res == true){
            count++;
            procedimento.setEstado(2);
        } else{
            System.out.println("Erro ao cadastrar a procedimento");
        }                
    }

    public static boolean salvaProcedimentos(Procedimento p) {
        int prox = proximaPosicaoLivre();
        if (prox != -1) {
            procedimentos[prox] = p;
            return true;
        } else {
            return false;
        }
    }

    public static boolean removeProcedimentos(int id) {
        for (int i = 0; i < ProcedimentoController.count; i++) {
            if (ProcedimentoController.procedimentos[i].getIdProcedimento()== id) {
                ProcedimentoController.procedimentos[i] = null;
                for (int j = i; j < ProcedimentoController.count - 1; j++) {
                    ProcedimentoController.procedimentos[j] = ProcedimentoController.procedimentos[j + 1];
                }
                ProcedimentoController.count--;
                return true;
            }
        }
        return false;
    }
    
    public static Procedimento buscarPorId(int id) {
        for (int i = 0; i < ProcedimentoController.count; i++) {
            if (ProcedimentoController.procedimentos[i].getIdProcedimento()== id) {
                return ProcedimentoController.procedimentos[i];
            }
        }
        return null;
    }

    public static Procedimento[] listarProcedimentos() {
        return Arrays.copyOf(ProcedimentoController.procedimentos, ProcedimentoController.count);
    }

    
    public static int proximaPosicaoLivre() {
        for (int i = 0; i < ProcedimentoController.procedimentos.length; i++) {
            if (procedimentos[i] == null) {
                return i;
            }
        }
        return -1;
    }
    
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
