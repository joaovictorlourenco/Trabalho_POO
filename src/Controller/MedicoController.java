/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Medico;
import Model.Pessoa;
import controller.PessoaController;
import static controller.PessoaController.cadastrado;
import static controller.PessoaController.count;
import static controller.PessoaController.pessoas;
import java.util.Arrays;

/**
 *
 * @author yn719471
 */
public class MedicoController {
    public static Medico[] medicos = new Medico[100];
    public static int count = 0;
    
    public static Medico cadastraMedico(Medico m) {
//        Pessoa pessoa = new Pessoa();
//        boolean cadastrado = cadastrado(medico);
//        
//        if(cadastrado == true) {
//            System.out.println("Médico já cadastrado");
//            System.out.println("Deseja fazer alguma alteração nesse registro?");
//        }
//        
//        while(cadastrado == true){
//            pessoa.novoLogin(pessoa);
//            cadastrado = cadastrado(pessoa);
//        }

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

    public static boolean removePessoas(int id) {
        for (int i = 0; i < PessoaController.count; i++) {
            if (PessoaController.pessoas[i].getId() == id) {
                PessoaController.pessoas[i] = null;
                for (int j = i; j < PessoaController.count - 1; j++) {
                    PessoaController.pessoas[j] = PessoaController.pessoas[j + 1];
                }
                PessoaController.count--;
                return true;
            }
        }
        return false;
    }

    public static Pessoa buscarPorId(int id) {
        for (int i = 0; i < PessoaController.count; i++) {
            if (PessoaController.pessoas[i].getId() == id) {
                return PessoaController.pessoas[i];
            }
        }
        return null;
    }

    public static boolean editarPessoas(Pessoa pessoa) {
        for (int i = 0; i < PessoaController.count; i++) {
            if (PessoaController.pessoas[i].getId() == pessoa.getId()) {
                PessoaController.pessoas[i] = pessoa;
                return true;
            }
        }
        return false;
    }

    public static Medico[] listarMedicos() {
//        return pessoas;
        return Arrays.copyOf(MedicoController.medicos, MedicoController.count);
    }

    public static Pessoa buscarPessoaPorCpf(String cpf) {
        for(int i = 0; i < count; i++) {
            if (pessoas[i].getCpf().equals(cpf)) {
                return pessoas[i];
            }
        }
        return null;
    }
    
    public static int proximaPosicaoLivre() {
        for (int i = 0; i < MedicoController.medicos.length; i++) {
            if (medicos[i] == null) {
                return i;
            }
        }
        return -1;
    }
        
    public static boolean verifyLogin(String login, String senha){
        for(Pessoa pessoa: pessoas){
           if(pessoa.getLogin().equals(login)) {
                if(pessoa.getSenha().equals(senha)){
                    return true;
                } else {
                    return false;
                }
           }
        }
        return false;
    }

}
