/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package View;

//import Controller.PessoaController;
import java.util.Scanner;
import Model.Pessoa;
import controller.PessoaController;

/**
 *
 * @author yn719471
 */
public class View {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean res = false;
        int opcLog;
        Scanner scan = new Scanner(System.in);
        System.out.println("Bem vindo ao sistema de gerenciamento de clinicas !!");

        
        do{
            System.out.println("Digite 1 para logar, 2 para cadastrar ou 0 para sair");
            opcLog = Integer.parseInt(scan.nextLine());
            
            while(opcLog != 1 && opcLog != 2 && opcLog != 0){
                System.out.println("Digite uma opção válida");
                opcLog = Integer.parseInt(scan.nextLine());
            }
            switch (opcLog){
                case 1:
                    System.out.println("Digite seu login:");
                    String login = scan.nextLine();
                    System.out.println("Digite sua senha:");
                    String senha = scan.nextLine();
                    res = PessoaController.login(login, senha);
                    if(res == true){
                        logado();
                    } else {
                        System.out.println("login ou senha incorreta");
                    }
                    break;
                    
                case 2:
                    Pessoa pessoa = (Pessoa) PessoaController.cadastraPessoa();
//                    Pessoa pessoa = new Pessoa();;
//                    res = PessoaController.cadastraPessoas(pessoa);
//                    System.out.println("Cadastrado com sucesso
//                    System.out.println(pessoa.getLogin());;
//                    System.out.println(pessoa.getSenha());
                    break;
                    
                case 0:
                    System.out.println("Finalizando......");
                    break;
            }
        }while(opcLog != 0);
    }

    private static void logado() {
        System.out.println("Logado com sucesso");
        inSystem();
    }

    private static void inSystem() {
        System.out.println("Escolha o que deseja fazer:");
        System.out.println("Escolha o que deseja fazer:");
        System.out.println("Escolha o que deseja fazer:");
        System.out.println("Escolha o que deseja fazer:");
    }
}
