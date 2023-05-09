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
import java.util.Arrays;

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
        String toConvert;
//////////////////////////////////////////////////////////////////////////////////////        
        /// cast dos primeiros usuarios para testes e demonstrações........
        for(int indice = 0; indice < 4; indice++){
            PessoaController.preCadastroPessoa(indice);
        }
        Pessoa p = PessoaController.buscarPorId(2);
        Medico m = new Medico(p, "CRM", "Especialidade");
        m = MedicoController.cadastraMedico(m);
///////////////////////////////////////////////////////////////////////////////////////
        
        Scanner scan = new Scanner(System.in);
        System.out.println("######## Bem vindo ao sistema de gerenciamento de clinicas ########\n\n");
        
        listaPessoas();
        System.out.println(" ");
        listaMedicos();
        
        do{
            System.out.println("Digite 1 para fazer login, 2 para cadastrar ou 0 para sair");
            toConvert = scan.nextLine();
            opcLog = Integer.parseInt(toConvert);
            
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
                    listaPessoas();
                    break;
                    
                case 0:
                    System.out.println("Finalizando......");
                    break;
            }
        }while(opcLog != 0);
    }

    ///// Função para o chamar o MENU para o usuario CONECTADO
    private static void logado(Pessoa pessoa) {
        System.out.println("=============== Success ==============");
        inSystem(pessoa);
    }

    ///////// MENU DO SISTEMA ///////////////////
    private static void inSystem(Pessoa pessoa) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Logado no sistema: \n ===>" + pessoa.toString());
        String toConvert;
        int opc;
        int[] permissao = pessoa.getTipoUsuario();
        
        System.out.println("============ Permissoes do usuario logado ================");
        System.out.println("=============>" + Arrays.toString(permissao));
        System.out.println("==========================================================");

        do{
            System.out.println("Escolha o que deseja fazer");
            System.out.println("0 - Deslogar");
            System.out.println("1 - Cadastrar novo medico");
//            System.out.println("x - Cadastrar novo medico");
//            System.out.println("xx - Cadastrar novo medico");
//            System.out.println("xxx - Cadastrar novo medico");
            if(permissao[3] == 4 || permissao[2] == 3){
                System.out.println("2 - Atribuir/Remover papel para usuario cadastrado");
                System.out.println("3 - Deletar Pessoa");
            }
            if(permissao[3] == 4){
                System.out.println("4 - Criar uma Franquia");
            }

            toConvert = scan.nextLine();
            opc = Integer.parseInt(toConvert);
            
            switch(opc){
                case 1:
                    if(permissao[3] == 4 || permissao[2] == 3){
                        criandoMedico();
                    }
                    break;
                case 2:
                    if(permissao[3] == 4 || permissao[2] == 3){
                        alteraTipoUser();
                    }
                    break;
                case 3:
                    if(permissao[3] == 4 || permissao[2] == 3){
                        deletaPessoa(pessoa);
                    }
                    break;
                case 4: 
                    if(permissao[3] == 4){
                        franquia();
                    }
                    break;
                case 5:
                    System.out.println("falta implementar");
                    break;
                default:
                    System.out.println("Não existe essa opção");
                    opc = -1;
                    break;
            }                   
        }while(opc != 0);
    }
    
    //franquia
    private static void franquia() {
        System.out.println("funçao franquia!!!!!!!");
        System.out.println("=============================");
    }
    
    /////////////////////////////////////
    ////////////////////////////////////
    private static void criandoMedico() {
        Pessoa med = (Pessoa) PessoaController.cadastraPessoa();
        Medico medico = new Medico(med);
        medico = MedicoController.cadastraMedico(medico);
        
        listaMedicos();
        listaPessoas();
    }
    

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Função que altera o papel de algum usuario(Somente donos de franquia e de unidades de franquia podem realizar alterações)
    public static void alteraTipoUser() {
        Scanner scan = new Scanner(System.in);
        String toConvert;
        int i;
        int papel;
        System.out.println("A seguir escolha um usuario cadastrado da lista abaixo e insira seu respectivo id para alteração:");
        listaPessoas();

        toConvert = scan.nextLine();
        i = Integer.parseInt(toConvert);
        
        Pessoa p = PessoaController.buscarPorId(i);
        
        if(p == null) {
            System.out.println("id inválido");
        }else {
            System.out.println("1 - para adicionar papel");
            System.out.println("0 - para remover papel");
            toConvert = scan.nextLine();
            i = Integer.parseInt(toConvert);
//            i = Integer.parseInt(scan.nextLine());
            while(i != 0 && i != 1){
                System.out.println("Digite uma opção válida");
                toConvert = scan.nextLine();
                i = Integer.parseInt(toConvert);
            }
            System.out.println("Insira o tipo de papel a remover/alterar:");
            System.out.println("2 - Papel de medico");
            System.out.println("3 - Papel de dono de unidade de franquia");
            System.out.println("4 - Papel de dono de franquia");
            toConvert = scan.nextLine();
            papel = Integer.parseInt(toConvert);
            while(papel != 2 && papel != 3 && papel != 4){
                System.out.println("Digite uma opção válida");
                toConvert = scan.nextLine();
                papel = Integer.parseInt(toConvert);
            }
            
            p.alteraTipoUsuario(papel, i);
        }
    }

    //// Listar Pessoas
    private static void listaPessoas() {
        Pessoa[] pessoas = PessoaController.listarPessoas();
        for(Pessoa p: pessoas){
            if(p != null){
                System.out.println(p.toString());
            }
        }
    }

    //// Listar Medicos
    private static void listaMedicos() {
        Medico[] medicos = MedicoController.listarMedicos();
        for(Medico m: medicos){
            if(m != null){
                System.out.println(m.toString());
            }
        }
    }

    private static void deletaPessoa(Pessoa p) {
        Scanner scan = new Scanner(System.in);
        boolean res; int i;
        System.out.println("Insira o id de uma das pessoas presentes na lista abaixo que deseja excluir:");
        listaPessoas();
        listaMedicos();
        String toConvert = scan.nextLine();
        i = Integer.parseInt(toConvert);
//        if(p.getId() == i){
//            System.out.println("nao eh possivel deletar");
//        }else 
        if(p.getId() != i){
            res = PessoaController.removePessoas(i);
            while(res == false && (i != 0)){
                System.out.println("Erro ao buscar Pessoa");
                System.out.println("Insira o id de uma das pessoas presentes na lista abaixo que deseja excluir ou 0 para sair:");
                listaPessoas();
                toConvert = scan.nextLine();
                i = Integer.parseInt(toConvert);
                if(p.getId() == i){
                    System.out.println("não é possivel deletar");
                }else{
                    res = PessoaController.removePessoas(i);
                }
            }
        }else {
            System.out.println("nao eh possivel deletar");
        }
        listaPessoas();
        listaMedicos();

        
//        Scanner scan = new Scanner(System.in);
//        System.out.println("Insira o id de uma das pessoas presentes na lista abaixo que deseja excluir:");
//        listaPessoas();
//        String toConvert = scan.nextLine();
//        int i = Integer.parseInt(toConvert);
//        Pessoa p = PessoaController.buscarPorId(i);
//        while(p == null){
//            System.out.println("Erro ao buscar Pessoa");
//            System.out.println("Insira o id de uma das pessoas presentes na lista abaixo que deseja excluir:");
//            listaPessoas();
//            toConvert = scan.nextLine();
//            i = Integer.parseInt(toConvert);
//            p = PessoaController.buscarPorId(i);
//        }
    }
}

/*
***
***
do{
    System.out.println("Escolha o que deseja fazer:");
    System.out.println("0 - Deslogar:");
    System.out.println("1 - Cadastrar novo medico:");
    System.out.println("2 - Atribuir nova funcao para usuario cadastrado:");

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

            Pessoa[] pessoas = PessoaController.listarPessoas();
            for(Pessoa p: pessoas){
                if(p != null){
                    System.out.println(p.toString()+"\n");
                }
            }
            break;
    }            
}while(opc != 0);
***
***
*/