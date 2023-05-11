/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package View;

//import Controller.PessoaController;
import Controller.ConsultaController;
import Controller.FranquiaController;
import Controller.MedicoController;
import Model.Franquia;
import Model.Medico;
import java.util.Scanner;
import Model.Pessoa;
import controller.PessoaController;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Date;

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
        String toConvert = "";
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
            do{
                System.out.println("Digite 1 para fazer login, 2 para cadastrar ou 0 para sair");
                toConvert = scan.nextLine();
                res = isInt(toConvert);
            }while(res != true);
            
            opcLog = Integer.parseInt(toConvert);
            while(opcLog != 1 && opcLog != 2 && opcLog != 0){
                do{
                    System.out.println("Digite uma opção válida");
                    toConvert = scan.nextLine();
                    res = isInt(toConvert);
                }while(res != true);
                opcLog = Integer.parseInt(toConvert);
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
                default: 
                    System.out.println("Não existe essa opção");
                    
                    
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
        boolean res;
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
            System.out.println("5 - Menu Consultas");


            do{
                toConvert = scan.nextLine();
                res = isInt(toConvert);
            }while(res != true);
            opc = Integer.parseInt(toConvert);
            
            switch(opc){
                case 0:
                    System.out.println("Deslogando....");
                    opc = 0;
                    break;
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
                        break;
                    }
                case 4:
                    if(permissao[3] == 4){
                        menuFranquia();
                        break;
                    }
                    break;
                case 5:
                    System.out.println("falta implementar");
                    menuConsulta(pessoa);
                default:
                    System.out.println("Não existe essa opção");
                    opc = -1;
                }
            }while(opc != 0);
    }
    //franquia
   
    private static void menuFranquia(){
        Scanner scan = new Scanner(System.in);
        int opc = -1;
        boolean res;
        String toConvert;
        
        do{
            
            System.out.println("==============Franquia==============");
            System.out.println("0 - Voltar");
            System.out.println("1 - Cadastrar nova Franquia");
            System.out.println("2 - Deletar Franquia");
            System.out.println("3 - Editar Franquia");
            System.out.println("4 - Atribuir unidade para Franquia");
        
            listarFranquias();
            do{
                toConvert = scan.nextLine();
                res = isInt(toConvert);
            }while(res != true);
            opc = Integer.parseInt(toConvert);
               
//            opc = Integer.parseInt( scan.nextLine());
            
            switch(opc){
                
                case 0:
                    opc = 0;
                    break;
                case 1:
                    CriandoFranquia();
                    break;
                case 2:
                    DeletandoFranquia();
                    break;
                case 3:
                    EditarFranquia();
                    
            }
            
        }while(opc != 0);
        
    }
    
    //       chamando metodo de criar
    
    private static void CriandoFranquia() {
        
        Franquia NewFranquia = FranquiaController.cadastraFranquia();
        
    }
    //       chamando metodo deletar
    private static void DeletandoFranquia(){
        
        
        
    }
    //       chamando metodo de editar
    private static void EditarFranquia(){
        
        
        
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
        boolean res;
        int i;
        int papel;
        System.out.println("A seguir escolha um usuario cadastrado da lista abaixo e insira seu respectivo id para alteração:");
        listaPessoas();

        do{
            toConvert = scan.nextLine();
            res = isInt(toConvert);
        }while(res != true);
        i = Integer.parseInt(toConvert);
        
        Pessoa p = PessoaController.buscarPorId(i);
        
        if(p == null) {
            System.out.println("id inválido");
        }else {
            System.out.println("1 - para adicionar papel");
            System.out.println("0 - para remover papel");
            do{
                toConvert = scan.nextLine();
                res = isInt(toConvert);
            }while(res != true);
            i = Integer.parseInt(toConvert);
//            i = Integer.parseInt(scan.nextLine());
            while(i != 0 && i != 1){
                System.out.println("Digite uma opção válida");
                do{
                    toConvert = scan.nextLine();
                    res = isInt(toConvert);
                }while(res != true);
                i = Integer.parseInt(toConvert);
            }
            System.out.println("Insira o tipo de papel a remover/alterar:");
            System.out.println("2 - Papel de medico");
            System.out.println("3 - Papel de dono de unidade de franquia");
            System.out.println("4 - Papel de dono de franquia");
            do{
                toConvert = scan.nextLine();
                res = isInt(toConvert);
            }while(res != true);
            papel = Integer.parseInt(toConvert);
            while(papel != 2 && papel != 3 && papel != 4){
                System.out.println("Digite uma opção válida");
                do{
                    toConvert = scan.nextLine();
                    res = isInt(toConvert);
                }while(res != true);
                papel = Integer.parseInt(toConvert);
            }
            
            p.alteraTipoUsuario(papel, i);
        }
    }
    ////listar Franquias
    
    private static void listarFranquias(){
    
        Franquia[] Franquias = FranquiaController.listarFranquias();
        
        for(Franquia Franquia: Franquias){
            
            if(Franquia != null){
                System.out.println(Franquia.toString());
            }
            
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
        boolean res; int i; String toConvert;
        System.out.println("Insira o id de uma das pessoas presentes na lista abaixo que deseja excluir:");
        listaPessoas();
        listaMedicos();
        
        do{
            toConvert = scan.nextLine();
            res = isInt(toConvert);
        }while(res != true);
        i = Integer.parseInt(toConvert);

        if(p.getId() != i){
            res = PessoaController.removePessoas(i);
            while(res == false && (i != 0)){
                System.out.println("Erro ao buscar Pessoa");
                System.out.println("Insira o id de uma das pessoas presentes na lista abaixo que deseja excluir ou 0 para sair:");
                listaPessoas();
                
                do{
                    toConvert = scan.nextLine();
                    res = isInt(toConvert);
                }while(res != true);
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
    }
    
    
    private static boolean isInt(String toConvert) {
        return toConvert != null && toConvert.matches("[0-9]*");
    }
    
//////////*******Consulta*******///////////
    /////////////////////////////////
    private static void menuConsulta(Pessoa p) {
        Scanner scan = new Scanner(System.in);
        LocalDate dtAtual = LocalDate.now();
        Date convertData;
        int opc = -1;
        String[] dataConsulta;
        String[] horaConsulta;
        int dia;
        int mes;
        int ano;
        int idMed;
        boolean res;
        String toConvert;
        do{
            System.out.println("####### Consultas #######");
            System.out.println("0 - Sair do menu de consulta");
            System.out.println("1 - Marcar Consulta");
            System.out.println("2 - Alterar Consulta");
            System.out.println("3 - Cancelar Consulta");
            System.out.println("4 - Listar Consultas");
            do{
                toConvert = scan.nextLine();
                res = isInt(toConvert);
            }while(res != true);
            opc = Integer.parseInt(toConvert);
            
            switch (opc){
                case 0:
                    opc = 0;
                    break;
                    
                case 1:
                    System.out.println("Insira a o id do medico na lista abaixo com quem deseja consultar");
                    listaMedicos();
                    do{
                        toConvert = scan.nextLine();
                        res = isInt(toConvert);
                    }while(res != true);
                    
                    idMed = Integer.parseInt(toConvert);
                    res = MedicoController.medicoExiste(idMed);
                    if(res == false){
                        System.out.println("id inválido");
                        break;
                    }
                    
                    System.out.println("Insira o DIA e MES de sua consulta no seguinte formato");
                    System.out.println("DIA/MES/ANO");
                    toConvert = scan.nextLine();
                    dataConsulta = toConvert.split("/");
                    res = isInt(dataConsulta[0]);
                    if(res == false){
                        System.out.println("Data Informada NAO VALIDA");
                        break;                        
                    }
                    dia = Integer.parseInt(dataConsulta[0]);
                    
                    res = isInt(dataConsulta[1]);
                    if(res == false){
                        System.out.println("DATA Informada NAO VALIDA");
                        break;                        
                    }
                    mes = Integer.parseInt(dataConsulta[1]);
                    
                    res = isInt(dataConsulta[2]);
                    if(res == false){
                        System.out.println("DATA Informada NAO VALIDA");
                        break;                        
                    }
                    ano = Integer.parseInt(dataConsulta[1]);
                    
                    LocalDate dtConsulta = LocalDate.of(ano, mes, dia);
                    convertData = Date.from(dtConsulta.atStartOfDay().toInstant(ZoneOffset.UTC));
//                    convertData = Date.from(dtConsulta);
                    
                    if(!dtConsulta.isAfter(dtAtual)){
                        System.out.println("DATA INVÁLIDA (HORARIO PRECISA SER POSTERIOR A DATA ATUAL)");
                    }
                    
                    System.out.println("Insira o HORARIO da sua consulta entre 8H - 19H");
                    System.out.println("No seguinte formato: hh:mm:ss");
                    toConvert = scan.nextLine();
                    horaConsulta = toConvert.split(":");
                    
                    res = isInt(horaConsulta[0]);
                    if(res == false){
                        System.out.println("HORA Informada NAO VALIDA");
                        break;         
                    }
                    res = isInt(horaConsulta[1]);
                    if(res == false){
                        System.out.println("HORA Informada NAO VALIDA");
                        break;         
                    }
                    res = isInt(horaConsulta[2]);
                    if(res == false){
                        System.out.println("HORA Informada NAO VALIDA");
                        break;         
                    }
                    
                    LocalTime hora = LocalTime.of(Integer.parseInt(horaConsulta[0]),Integer.parseInt(horaConsulta[1]),Integer.parseInt(horaConsulta[2]));
                    break;
                    
                case 2:
                    opc = 0;
                    break;
                case 3:
                    opc = 0;
                    break;
                case 4:
                    opc = 0;
                    break;
            }
            
        }while(opc != 0);
    }
}