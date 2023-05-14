/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.InfoConsulta;
import java.util.Arrays;

/**
 *
 * @author yn719471
 */
public class InfoConsultaController {

    public static InfoConsulta[] infoConsultas = new InfoConsulta[100];
    public static int count = 0;

    public static void cadastraInfoConsulta(long idMedico, long idConsulta, String descricao) {

        InfoConsulta info = new InfoConsulta(idMedico, idConsulta, descricao);

        boolean res = salvaInfoConsultas(info);
        if (res == true) {
            count++;
            System.out.println("Informacao sobre a consulta salva com SUCESSO");
        } else {
            System.out.println("Ocorreu um ERRO");
        }

    }

    public static InfoConsulta[] listarInfoConsultas() {
        return Arrays.copyOf(infoConsultas, count);
    }

    public static boolean salvaInfoConsultas(InfoConsulta info) {
        int prox = proximaPosicaoLivre();
        if (prox != -1) {
            infoConsultas[prox] = info;
            return true;
        } else {
            return false;
        }
    }

    public static int proximaPosicaoLivre() {
        for (int i = 0; i < infoConsultas.length; i++) {
            if (infoConsultas[i] == null) {
                return i;
            }
        }
        return -1;
    }

    public static boolean removeInfoConsultas(int id) {
        for (int i = 0; i < InfoConsultaController.count; i++) {

            if (InfoConsultaController.infoConsultas[i].getIdInfoConsulta() == id) {

                InfoConsultaController.infoConsultas[i] = null;

                for (int j = i; j < InfoConsultaController.count - 1; j++) {
                    InfoConsultaController.infoConsultas[j] = InfoConsultaController.infoConsultas[j + 1];
                }

                FranquiaUnidadeController.count--;

                return true;
            }
        }
        return false;
    }

    public static InfoConsulta buscarPorId(int id) {

        for (int i = 0; i < InfoConsultaController.count; i++) {

            if (InfoConsultaController.infoConsultas[i].getIdInfoConsulta() == id) {

                return InfoConsultaController.infoConsultas[i];

            }

        }

        return null;
    }

    public static boolean unidadeExiste(int id) {
        for (InfoConsulta ic : infoConsultas) {
            if (ic.getIdInfoConsulta() == id) {
                return true;
            }
        }
        return false;
    }

    public static void alteraInfoConsulta(String descricao) {
        
    }
}
