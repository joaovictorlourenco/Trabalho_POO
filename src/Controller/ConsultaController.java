/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Consulta;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author yn719471
 */
public class ConsultaController {
    public static List<Consulta> consultas = new ArrayList();
//    public static Consulta[] consultas = new Consulta[100];
//    public static int count = 0;
    
    
    
    public static void cadastraConsulta(int idMed, int idPes, LocalDate data, LocalTime hora, int idUnidade) {
        Consulta consulta = new Consulta();
        consulta.setDataConsulta(data);
        consulta.setHorario(hora);
        consulta.setId();
        consulta.setIdPessoa(idPes);
        consulta.setIdMedico(idMed);
        consulta.setUnidade(idUnidade);
        consulta.setValor(200);
        consulta.setDataCriacao(LocalDate.now());
        boolean res = salvaConsultas(consulta);
        if(res == true){
//            count++;
            consulta.setEstado(2);
        } else{
            System.out.println("Erro ao cadastrar a consulta");
        }                
    }

    public static boolean salvaConsultas(Consulta c) {
        consultas.add(c);
        return true;
//        int prox = proximaPosicaoLivre();
//        if (prox != -1) {
//            consultas[prox] = c;
//            return true;
//        } else {
//            return false;
//        }
    }

    public static boolean removeConsultas(int id) {
        Iterator<Consulta> it = consultas.iterator();
        while(it.hasNext()){
            Consulta c = it.next();
            if(c.getId() == id){
                it.remove();
                return true;
            }
        }
//        for (int i = 0; i < ConsultaController.count; i++) {
//            if (ConsultaController.consultas[i].getId() == id) {
//                ConsultaController.consultas[i] = null;
//                for (int j = i; j < ConsultaController.count - 1; j++) {
//                    ConsultaController.consultas[j] = ConsultaController.consultas[j + 1];
//                }
//                ConsultaController.count--;
//                return true;
//            }
//        }
        return false;
    }
    
    public static Consulta buscarPorId(int id) {
        for(Consulta c : consultas){
            if(c.getId() == id){
                return c;
            }
        }
//        for (int i = 0; i < ConsultaController.count; i++) {
//            if (ConsultaController.consultas[i].getId() == id) {
//                return ConsultaController.consultas[i];
//            }
//        }
        return null;
    }

    public static List<Consulta> listarConsultas() {
        return consultas;
//        return Arrays.copyOf(ConsultaController.consultas, ConsultaController.count);
    }

    
//    public static int proximaPosicaoLivre() {
//        for (int i = 0; i < ConsultaController.consultas.length; i++) {
//            if (consultas[i] == null) {
//                return i;
//            }
//        }
//        return -1;
//    }
    
    public static boolean consultaExiste(int id) {
       for(Consulta c: consultas){
           if(c.getId() == id){
               return true;
           }
       }
       return false;
    }
}
