package controller;

import Controller.MedicoController;
import Model.Pessoa;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PessoaController {
    public static List<Pessoa> pessoas = new ArrayList();
//    public static int count = 0;

//    Scanner scan = new Scanner(System.in);
    
    public static Pessoa login(String login, String senha) {
        Pessoa p = PessoaController.verifyLogin(login, senha);
        return p;
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
        return cadastrado;
    }
    
    public static void preCadastroPessoa(int indice) {
        Pessoa pessoa = new Pessoa("nome"+(indice+1), "endereco"+(indice+1), "CPF"+(indice+1), "Telefone"+(indice+1), "login"+(indice+1),
                        "senha"+(indice+1), indice+1);
        
        boolean res = salvaPessoas(pessoa);
        
        if(res == true){
            System.out.println("Cadastrado com sucesso");
        } else {
            System.out.println("Ocorreu um erro");
        }
    }
    
    public static Pessoa cadastraPessoa() {
        int i = 0;
        Pessoa pessoa = new Pessoa(i);
        boolean cadastrado = cadastrado(pessoa);
        
        while(cadastrado == true){
            pessoa.novoLogin(pessoa);
            cadastrado = cadastrado(pessoa);
        }

        boolean res = salvaPessoas(pessoa);
        
        if(res == true){
            System.out.println("Cadastrado com sucesso");
        } else {
            System.out.println("Ocorreu um erro");
        }
        return(pessoa);
    }

    public static boolean salvaPessoas(Pessoa pessoa) {
            pessoas.add(pessoa);
            return true;
    }

    public static boolean removePessoas(int id) {
        Iterator<Pessoa> it = pessoas.iterator();
        
        while(it.hasNext()){
            Pessoa p = it.next();
            if(p.getId() == id){
                if(p.getTipoUsuario()[1] == 2){
                    //logica de deletar na classe médico
                    MedicoController.removeMedicos(id);
                }
                it.remove();
                return true;
            }
        }
        
//        for (int i = 0; i < PessoaController.count; i++) {
//            if (PessoaController.pessoas[i].getId() == id) {
//                int[] tipoUsuario = PessoaController.pessoas[i].getTipoUsuario();
//                if(tipoUsuario[1] == 2){
//                    boolean res = MedicoController.removeMedicos(id);
//                }
//                
//                PessoaController.pessoas[i] = null;
//                for (int j = i; j < PessoaController.count - 1; j++) {
//                    PessoaController.pessoas[j] = PessoaController.pessoas[j + 1];
//                }
//                PessoaController.count--;
//               
//                return true;
//            }
//        }
        return false;
    }

    public static Pessoa buscarPorId(int id) {
        for(Pessoa p: pessoas){
            if(p.getId() == id){
                return p;
            }
        }
//        for (int i = 0; i < PessoaController.count; i++) {;
//            if (PessoaController.pessoas[i].getId() == id) {
//                return PessoaController.pessoas[i];
//            }
//        }
        return null;
    }

    public static boolean editarPessoas(Pessoa pessoa) {
        for(Pessoa p: pessoas){
            if(p.getId() == pessoa.getId()){
                p = pessoa;
                return true;
            }
        }
//        for (int i = 0; i < PessoaController.count; i++) {;
//            if (PessoaController.pessoas[i].getId() == pessoa.getId()) {
//                PessoaController.pessoas[i] = pessoa;
//                return true;
//            }
//        }
        return false;
    }

    public static List<Pessoa> listarPessoas() {
        return pessoas;
//        return Arrays.copyOf(PessoaController.pessoas, PessoaController.count);
    }

    public static Pessoa buscarPessoaPorCpf(String cpf) {
        for(Pessoa p: pessoas){
            if(p.getCpf().equals(cpf)){
                return p;
            }
        }
//        for(int i = 0; i < count; i++) {
//            if (pessoas[i].getCpf().equals(cpf)) {
//                return pessoas[i];
//            }
//        }
        return null;
    }
    
//    public static int proximaPosicaoLivre() {
//        for (int i = 0; i < PessoaController.pessoas.length; i++) {
//            if (pessoas[i] == null) {
//                return i;
//            }
//        }
//        return -1;
//    }
        
//    public static boolean verifyLogin(String login, String senha){
    public static Pessoa verifyLogin(String login, String senha){
        for(Pessoa pessoa: pessoas){
            if(pessoa != null){
                if(pessoa.getLogin().equals(login)) {
                     if(pessoa.getSenha().equals(senha)){
                         return pessoa;
                     }
                }
            }
        }
        return null;
    }

}
