package controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

import Model.Pessoa;

public class PessoaController {
    public static Pessoa[] pessoas = new Pessoa[100];
    public static int count = 0;

    Scanner scan = new Scanner(System.in);
    
    public static boolean login(String login, String senha) {
        boolean res = PessoaController.verifyLogin(login, senha);
        return res;
    }
    
    public static boolean cadastrado(Pessoa pessoa){
        boolean cadastrado = false;
        for(Pessoa logPessoa: pessoas){
            if(logPessoa != null) {
                if(logPessoa.getLogin().equals(pessoa.getLogin())) {
                    cadastrado = true;
                }
            }
        }
//        if(cadastrado == true){;
//            System.out.println("Login em uso... insira outro login:");
//            pessoa.setLogin(scan.nextLine());
//        }
        return cadastrado;
    }
    
    public static Pessoa cadastraPessoa() {
        Pessoa pessoa = new Pessoa();
        boolean cadastrado = cadastrado(pessoa);
        
        while(cadastrado == true){
            pessoa.novoLogin(pessoa);
            cadastrado = cadastrado(pessoa);
        }

        boolean res = salvaPessoas(pessoa);
        
        if(res == true){
            count++;
            System.out.println("Cadastrado com sucesso");
        } else {
            System.out.println("Ocorreu um erro");
        }
        return(pessoa);
    }

    public static boolean salvaPessoas(Pessoa pessoa) {
        int prox = proximaPosicaoLivre();
        if (prox != -1) {
            pessoas[prox] = pessoa;
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

    public static Pessoa[] listarPessoas() {
        return Arrays.copyOf(PessoaController.pessoas, PessoaController.count);
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
        for (int i = 0; i < PessoaController.pessoas.length; i++) {
            if (pessoas[i] == null) {
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
