/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Medico;
import java.util.Arrays;

/**
 *
 * @author yn719471
 */
public class MedicoController {
    public static Medico[] medicos = new Medico[100];
    public static int count = 0;
    
    public static Medico cadastraMedico(Medico m) {
        boolean res = salvaMedicos(m);
        
        if(res == true){
            count++;
            System.out.println("Cadastrado com sucesso");
        } else {
            System.out.println("Ocorreu um erro");
        }
        return(m);
    }

    public static boolean salvaMedicos(Medico m) {
        int prox = proximaPosicaoLivre();
        if (prox != -1) {
            medicos[prox] = m;
            return true;
        } else {
            return false;
        }
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
    
    public static Medico buscarPorId(int id) {
        for (int i = 0; i < MedicoController.count; i++) {
            if (MedicoController.medicos[i].getId() == id) {
                return MedicoController.medicos[i];
            }
        }
        return null;
    }
/*=================== À Fazer ======================*/
//    public static boolean editarMedicos(Medic pessoa) {;
//        for (int i = 0; i < PessoaController.count; i++) {
//            if (PessoaController.pessoas[i].getId() == pessoa.getId()) {
//                PessoaController.pessoas[i] = pessoa;
//                return true;
//            }
//        }
//        return false;
//    }

    public static Medico[] listarMedicos() {
//        return pessoas;
        return Arrays.copyOf(MedicoController.medicos, MedicoController.count);
    }

//    public static Pessoa buscarPessoaPorCpf(String cpf) {
//        for(int i = 0; i < count; i++) {
//            if (pessoas[i].getCpf().equals(cpf)) {
//                return pessoas[i];
//            }
//        }
//        return null;
//    }
    
    public static int proximaPosicaoLivre() {
        for (int i = 0; i < MedicoController.medicos.length; i++) {
            if (medicos[i] == null) {
                return i;
            }
        }
        return -1;
    }
        
//    public static boolean verifyLogin(String login, String senha){
//        for(Pessoa pessoa: pessoas){
//           if(pessoa.getLogin().equals(login)) {
//                if(pessoa.getSenha().equals(senha)){
//                    return true;
//                } else {
//                    return false;
//                }
//           }
//        }
//        return false;
//    }

}
