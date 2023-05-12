/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Consulta;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author yn719471
 */
public class ConsultaController {
    public static Consulta[] consultas = new Consulta[100];
    public static int count = 0;
    
    
    
    public static void cadastraConsulta(int idMed, int idPes, int idUnit, Date data, Date hora) {
        Consulta consulta = new Consulta();
        consulta.setDataConsulta(data);
        consulta.setHorario(hora);
//        consulta.setHorario(dataConsulta);
    }

    public static boolean salvaConsultas(Consulta c) {
        int prox = proximaPosicaoLivre();
        if (prox != -1) {
            consultas[prox] = c;
            return true;
        } else {
            return false;
        }
    }

    public static boolean removeConsultas(int id) {
        for (int i = 0; i < ConsultaController.count; i++) {
            if (ConsultaController.consultas[i].getId()== id) {
                ConsultaController.consultas[i] = null;
                for (int j = i; j < MedicoController.count - 1; j++) {
                    ConsultaController.consultas[j] = ConsultaController.consultas[j + 1];
                }
                ConsultaController.count--;
                return true;
            }
        }
        return false;
    }
    
    public static Consulta buscarPorId(int id) {
        for (int i = 0; i < ConsultaController.count; i++) {
            if (ConsultaController.consultas[i].getId() == id) {
                return ConsultaController.consultas[i];
            }
        }
        return null;
    }

    public static Consulta[] listarConsultas() {
        return Arrays.copyOf(ConsultaController.consultas, ConsultaController.count);
    }

    
    public static int proximaPosicaoLivre() {
        for (int i = 0; i < ConsultaController.consultas.length; i++) {
            if (consultas[i] == null) {
                return i;
            }
        }
        return -1;
    }
}
