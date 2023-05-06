/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package View;

//import Controller.PessoaController;
import Controller.MedicoController;
import Model.Medico;
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
                    Pessoa logPessoa = PessoaController.login(login, senha);
                    if(logPessoa != null){
                        logado(logPessoa);
                    } else {
                        System.out.println("login ou senha incorreta");
                    }
                    break;
                    
                case 2:
                    Pessoa pessoa = (Pessoa) PessoaController.cadastraPessoa();
                    
                    Pessoa[] pessoas = PessoaController.listarPessoas();
                    
                    for(Pessoa p: pessoas){
                        if(p != null){
                            System.out.println(p.toString()+"\n");
                        }
                    }
                    break;
                    
                case 0:
                    System.out.println("Finalizando......");
                    break;
            }
        }while(opcLog != 0);
    }

    private static void logado(Pessoa pessoa) {
        System.out.println("Logado com sucesso");
        inSystem(pessoa);
    }

    private static void inSystem(Pessoa pessoa) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Logado no sistema: \n ===>" + pessoa.toString());
        int opc = -1;
        
        while(opc != 0){
            System.out.println("Escolha o que deseja fazer:");
            System.out.println("0- Para deslogar:");
            System.out.println("1- Cadastrar novo médico:");
            System.out.println("2- Atribuir nova função para usuario cadastrado:");

            opc = Integer.parseInt(scan.nextLine());
        
            switch(opc){
                case 1:
                    Pessoa med = (Pessoa) PessoaController.cadastraPessoa();
                    Medico medico = new Medico(med);
                    
                    medico = MedicoController.cadastraMedico(medico);
                    
                    
                    Medico[] medicos = MedicoController.listarMedicos();
                    
                    for(Medico m: medicos){
                        if(m != null){
                            System.out.println(m.toString()+"\n");
                        }
                    }
                    break;
            }            
        }
    }
}
