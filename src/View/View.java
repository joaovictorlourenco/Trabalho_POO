/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package View;

//import Controller.PessoaController;
import Controller.ConsultaController;
import static Controller.ConsultaController.consultaExiste;
import Controller.FranquiaController;
import Controller.FranquiaUnidadeController;
import static Controller.FranquiaUnidadeController.unidadeExiste;
import Controller.MedicoController;
import Model.Consulta;
import Model.Franquia;
import Model.FranquiaUnidade;
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
        
        /// cast de franquias para testes e demonstrações........
        for(int indice = 1; indice <= 4; indice++){
            FranquiaController.preCadastraFranquia("Franquia"+indice, 4);
        }
        
        
        /// cast de unidades de franquias para testes e demonstrações........
        for(int indice = 1; indice <= 4; indice++){
            FranquiaUnidadeController.preCadastraFranquiaUnidades(indice, 3);
        }
        
///////////////////////////////////////////////////////////////////////////////////////
        
        Scanner scan = new Scanner(System.in);
        System.out.println("\n\n######## Bem vindo ao sistema de gerenciamento de clinicas ########\n\n");
        
        listaPessoas();
        System.out.println(" ");
        listaMedicos();
        System.out.println(" ");
        listarFranquias();
        System.out.println(" ");
        listarUnidadesFranquia();
        
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
            
            System.out.println("\n\n==============Franquia==============");
            System.out.println("0 - Voltar");
            System.out.println("1 - Cadastrar nova Franquia");
            System.out.println("2 - Deletar Franquia");
            System.out.println("3 - Editar Franquia");
            System.out.println("4 - Menu De Unidade de Franquia");
        
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
                    break;
                case 4:
                    CriandoFranquiaUnidade();
                    break;
                    
            }
            
        }while(opc != 0);
        
    }
    
    //          unidade de franquia
    private static void CriandoFranquiaUnidade() {
         Scanner scan = new Scanner(System.in);
        int opc = -1;
        boolean res;
        String toConvert;
        
        do{
            System.out.println("\n\n============Unidade de franquia===============");
            System.out.println("0 - Voltar");
            System.out.println("1 - Cadastrar nova Unidade de Franquia");
            System.out.println("2 - Deletar Unidade de Franquia");
            System.out.println("3 - Editar Unidade de Franquia");
            listarUnidadesFranquia();

            do{
                toConvert = scan.nextLine();
                res = isInt(toConvert);
            }while(res != true);
                opc = Integer.parseInt(toConvert);
            
        
            switch(opc){
                
                case 0:
                    opc = 0;
                    break;
                case 1: 
                    FranquiaUnidade NewFranquiaUnidade = FranquiaUnidadeController.cadastraFranquiaUnidades();
                    break;
                case 2:
                    DeletandoUnidadeFranquia();
                    break;
                case 3: 
                    System.out.println("Falta implementar");
                }
            
        }while(opc != 0);
       
    
    }
    
    //       chamando metodo de criar
    
    private static void CriandoFranquia() {
        
        Franquia NewFranquia = FranquiaController.cadastraFranquia();
        
    }
    
        ////listar Franquias
    
    private static void listarFranquias(){
    
        Franquia[] Franquias = FranquiaController.listarFranquias();
        
        for(Franquia Franquia: Franquias){
            
            if(Franquia != null){
                System.out.println("-------------------------------------------------------");
                System.out.println(Franquia);
                System.out.println("-------------------------------------------------------");
            }
            
        }
    
    }
    private static void DeletandoFranquia(){
    
        
        
    }
    
    //listando unidades de franquia
    private static void listarUnidadesFranquia(){
        
        FranquiaUnidade[] FranquiasUnidades = FranquiaUnidadeController.listarUnidadesFranquia();
       
        for(FranquiaUnidade Unidade: FranquiasUnidades){
            
            if(Unidade != null){
                System.out.println("-------------------------------------------------------");
                System.out.println(Unidade);
                System.out.println("-------------------------------------------------------");
            }
            
        }
    
    }
    //       chamando metodo deletar
    private static void DeletandoUnidadeFranquia(){
        int opcDe = -1;
        int opc = -1;
        boolean res;
        String toConvert;
        Scanner scan = new Scanner(System.in);
        
        do{
        
            System.out.println("Insira o id de uma das UNIDADES presentes na lista abaixo que deseja excluir:");
            listarUnidadesFranquia();
        
        do{
            toConvert = scan.nextLine();
            res = isInt(toConvert);
        }while(res != true);
        
            opc = Integer.parseInt(toConvert);
        
        
        do{
            
            FranquiaUnidade unidadeDeletar = FranquiaUnidadeController.buscarPorId(opc);
            System.out.println("Essa é a unidade que deseja EXCLUIR?");
            System.out.println("1 - sim");
            System.out.println("2 - não");
            System.out.println("------------------------------------------");
            System.out.println(unidadeDeletar);

            opcDe = Integer.parseInt(scan.nextLine());
     
            switch(opcDe){
            
                case 1: 
                    boolean resDeletado;
                    resDeletado = DeletaFranquiaUnidade(unidadeDeletar);
                    if(resDeletado == true){
                        System.out.println("Deletado com sucesso");
                    }else{
                        
                        System.out.println("Ocorreu algum erro");
                        
                    }
                    opcDe = 0;
                    opc = 0;
                    break;
                case 0:
                    opcDe = 0;opc = 0;
                    opc = 0;
                    break;
            
            }
        
            
        }while(opcDe != 0);
        
    }while(opc != 0);
        
 
        
//        res = FranquiaUnidadeController.removeFranquiaUnidade(unidadeDeletar);
        
        
        
    }
    
    private static boolean DeletaFranquiaUnidade(FranquiaUnidade unidadeDeletar){
        
        boolean res;
        res = FranquiaUnidadeController.removeFranquiaUnidade((int) unidadeDeletar.getId());
        System.out.println(res);
        System.out.println((int) unidadeDeletar.getId());
        
        return res;
        
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

    //// Listar Pessoas
    private static void listaPessoas() {
        Pessoa[] pessoas = PessoaController.listarPessoas();
        for(Pessoa p: pessoas){
            if(p != null){
                System.out.println("-------------------------------------------------------");
                System.out.println(p);
                System.out.println("-------------------------------------------------------");
            }
        }
    }

    //// Listar Medicos
    private static void listaMedicos() {
        Medico[] medicos = MedicoController.listarMedicos();
        for(Medico m: medicos){
            if(m != null){
                System.out.println("-------------------------------------------------------");
                System.out.println(m);
                System.out.println("-------------------------------------------------------");
            }
        }
    }
    
    private static void listaConsultas() {
        Consulta[] consultas = ConsultaController.listarConsultas();
        for(Consulta c: consultas){
            if(c != null){
                System.out.println("-------------------------------------------------------");
                System.out.println(c.toString());
                System.out.println("-------------------------------------------------------");
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
        int[] permissoes;
        Date convertData;
        int opc = -1;
        int idConsulta;
        String[] dataConsulta;
        String[] horaConsulta;
        int dia;
        int mes;
        int ano;
        int idMed;
        int idUnidFranq;
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
                    System.out.println("Insira o id da unidade que irá consultar");
                    listarUnidadesFranquia();
                    do{
                        toConvert = scan.nextLine();
                        res = isInt(toConvert);
                    }while(res != true);
                    
                    idUnidFranq = Integer.parseInt(toConvert);
                    res = unidadeExiste(idUnidFranq);
                    if(res == false){
                        System.out.println("id inválido");
                        break;
                    }
                    
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
                    
                    System.out.println("Insira o DIA, MES e ANO de sua consulta no seguinte formato");
                    System.out.println("DD/MM/YYYY");
                    toConvert = scan.nextLine();
                    dataConsulta = toConvert.split("/");
                    res = isInt(dataConsulta[0]);
                    if(res == false){
                        System.out.println("DATA do dia Informada NAO VALIDA");
                        break;                        
                    }
                    dia = Integer.parseInt(dataConsulta[0]);
                    
                    res = isInt(dataConsulta[1]);
                    if(res == false){
                        System.out.println("DATA do mes Informada NAO VALIDA");
                        break;                        
                    }
                    mes = Integer.parseInt(dataConsulta[1]);
                    
                    res = isInt(dataConsulta[2]);
                    if(res == false){
                        System.out.println("DATA do ano Informada NAO VALIDA");
                        break;                        
                    }
                    ano = Integer.parseInt(dataConsulta[2]);
                    
                    LocalDate dtConsulta = LocalDate.of(ano, mes, dia);
                    convertData = Date.from(dtConsulta.atStartOfDay().toInstant(ZoneOffset.UTC));
//                    convertData = Date.from(dtConsulta);
                    
                    if(dtConsulta.isAfter(dtAtual) == false){
                        System.out.println("DATA INVÁLIDA (DATA PRECISA SER POSTERIOR A DATA ATUAL)");
                        break;
                    }
                    
                    System.out.println("Insira o HORARIO da sua consulta entre 8H - 19H");
                    System.out.println("No seguinte formato: hh:mm");
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
//                    res = isInt(horaConsulta[2]);
//                    if(res == false){
//                        System.out.println("HORA Informada NAO VALIDA");
//                        break;         
//                    }
                    LocalTime hora = LocalTime.of(Integer.parseInt(horaConsulta[0]),Integer.parseInt(horaConsulta[1]),0);
                    
                    ConsultaController.cadastraConsulta(idMed, (int)p.getId(), convertData, hora, idUnidFranq);
                    
                    listaConsultas();
                    break;
                    
                case 2:
                    permissoes = p.getTipoUsuario(); 
                    
                    System.out.println("########################################################################");
                    System.out.println("Insira o id da consulta que deseja cancelar");
                    if(permissoes[3] == 4 || permissoes[2] == 3){
                        listaConsultas();
                    }else if(permissoes[1] == 2){
                        listaConsultas();
                    }else{
                        listaConsultas();
                    }
                    do{
                        toConvert = scan.nextLine();
                        res = isInt(toConvert);
                    }while(res != true);
                    
                    idConsulta = Integer.parseInt(toConvert);
                    res = consultaExiste(idConsulta);
                    if(res == false){
                        System.out.println("id inválido");
                        break;
                    } else {
                        //inserir logica de alterar consulta aqui.......
                        //////
//                        res = ConsultaController.removeConsultas(idConsulta);
                    }
                    opc = 0;
                    break;
                case 3:
                    permissoes = p.getTipoUsuario(); 
                    
                    System.out.println("########################################################################");
                    System.out.println("Insira o id da consulta que deseja cancelar");
                    if(permissoes[3] == 4 || permissoes[2] == 3){
                        listaConsultas();
                    }else if(permissoes[1] == 2){
                        listaConsultas();
                    }else{
                        listaConsultas();
                    }
                    do{
                        toConvert = scan.nextLine();
                        res = isInt(toConvert);
                    }while(res != true);
                    
                    idConsulta = Integer.parseInt(toConvert);
                    res = consultaExiste(idConsulta);
                    if(res == false){
                        System.out.println("id inválido");
                        break;
                    } else {
                        res = ConsultaController.removeConsultas(idConsulta);
                    }
                    if(res == false){
                        System.out.println("Erro ao deletar consulta");
                    }else {
                        System.out.println("Consulta deletada com sucesso");
                    }
                    break;
                case 4:
                    listaConsultas();
                    break;
            }
        }while(opc != 0);
    }
}