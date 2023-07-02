/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package View;

import Controller.ConsultaController;
import static Controller.ConsultaController.consultaExiste;
import Controller.FinanceiroAdmController;
import static Controller.FinanceiroAdmController.listarFinanceiroADM;
import Controller.FinanceiroMedicoController;
import Controller.FranquiaController;
import Controller.FranquiaUnidadeController;
import static Controller.FranquiaUnidadeController.unidadeExiste;
import Controller.InfoConsultaController;
import Controller.MedicoController;
import Controller.ProcedimentoController;
import Model.CalendarioAno;
import Model.Consulta;
import Model.FinanceiroAdm;
import Model.Franquia;
import Model.FranquiaUnidade;
import Model.InfoConsulta;
import Model.Medico;
import java.util.Scanner;
import Model.Pessoa;
import Model.Procedimento;
import controller.PessoaController;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.sql.SQLException;
/**
 *
 * @author yn719471
 */

// mysql -u root
public class View {
    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
//        ScheduledExecutorService timer = Executors.newScheduledThreadPool(1);
        
        CalendarioAno calendario = new CalendarioAno();
        
        boolean res = false;
        int opcLog;
        String toConvert = "";
//////////////////////////////////////////////////////////////////////////////////////        
///////////////////////////////////////////////////////////////////////////////////////
        
        Scanner scan = new Scanner(System.in);
        
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//        calendar.set(Calendar.DAY_OF_MONTH, 1);
//        calendar.add(Calendar.MONTH, 1);
//        calendar.set(Calendar.HOUR_OF_DAY, 0);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        long initialDelay = calendar.getTimeInMillis() - System.currentTimeMillis();
//        timer.scheduleAtFixedRate((Runnable) new FinanceiroMedicoController(), initialDelay, 1, TimeUnit.MILLISECONDS );


//        LocalDate data = LocalDate.now();
//        
//        data = acrescentaCalendario(data);
        
        System.out.println("\n######## Bem vindo ao sistema de gerenciamento de clinicas ########");
        System.out.println(" ");
        calendario.acrescentaCalendario();
        do{
            do{
                System.out.println("Digite:\n1 para fazer login\n2 para cadastrar\n3 para acrescentar calendario\n0 para sair");
                if((calendario.getData()).getDayOfMonth() == 1){
                    FinanceiroMedicoController fmc = new FinanceiroMedicoController(calendario.getData());
                    fmc.varreduraFinMed();
                    fmc.pagandoAdministradora();
                }
                toConvert = scan.nextLine();
                if(!("".equals(toConvert))){
                    res = isInt(toConvert);
                }
            }while(res != true);
            
            opcLog = Integer.parseInt(toConvert);
            while(opcLog != 1 && opcLog != 2 && opcLog != 0 && opcLog != 3){
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
                    System.out.println("digite seu nome:");
                    String nome = scan.nextLine();

                    System.out.println("digite seu endereço:");
                    String end = scan.nextLine();

                    System.out.println("digite seu CPF:");
                    String cpf = scan.nextLine();

                    System.out.println("digite seu telefone:");
                    String tel = scan.nextLine();

                    System.out.println("digite seu login:");
                    String log = scan.nextLine();
                    
                    System.out.println("digite seu sua senha:");
                    String passw = scan.nextLine();

                    PessoaController.cadastraPessoa(nome, end, cpf, tel, log, passw);
//                    Pessoa pessoa = (Pessoa) PessoaController.cadastraPessoa(nome, end, cpf, tel, log, passw);
                    listaPessoas();
                    break;
                    
                case 0:
                    System.out.println("Finalizando......");
                    break;
                case 3:
                    calendario.acrescentaCalendario();
                    break;
                default: 
                    System.out.println("Não existe essa opção");
                    
                    
            }
        }while(opcLog != 0);
    }

    ///// Função para o chamar o MENU para o usuario CONECTADO
    private static void logado(Pessoa pessoa) throws SQLException {
        System.out.println("=============== Success ==============");
        inSystem(pessoa);
    }

    ///////// MENU DO SISTEMA ///////////////////
    private static void inSystem(Pessoa pessoa) throws SQLException {
        
        Scanner scan = new Scanner(System.in);
        System.out.println("Logado no sistema: \n" + pessoa.toString());
        String toConvert;
        int opc;
        boolean res;

        do{
            System.out.println("Escolha o que deseja fazer");
            System.out.println("0 - Deslogar");
            System.out.println("1 - Cadastrar novo medico");
            if(pessoa.getDono_unidade() == 1 || pessoa.getDono_franquia() == 1){
                System.out.println("2 - Atribuir/Remover papel para usuario cadastrado");
                System.out.println("3 - Deletar Pessoa");
            }
            if(pessoa.getDono_franquia() == 1){
                System.out.println("4 - Criar uma Franquia");
            }
            System.out.println("5 - Menu Consultas");
            if(pessoa.getMedico() == 1){
                System.out.println("6 - Menu Informações de Consulta");
            }
            System.out.println("7 - Menu Procedimentos");
            if(pessoa.getDono_franquia() == 1 || pessoa.getDono_unidade() == 1){
                System.out.println("8 - Menu Financeiro ADM");
            
            }

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
                    if(pessoa.getDono_franquia() == 1 || pessoa.getDono_unidade()== 1){
                        criandoMedico();
                    }else{
                        System.out.println("Sem permissão");
                    }
                    break;
                case 2:
                    if(pessoa.getDono_franquia() == 1 || pessoa.getDono_unidade()== 1){
                        alteraTipoUser();
                    }
                    break;
                case 3:
                    if(pessoa.getDono_franquia() == 1 || pessoa.getDono_unidade()== 1){
                        deletaPessoa(pessoa);
                        break;
                    }
                case 4:
                    if(pessoa.getDono_franquia() == 1){
                        menuFranquia();
                        break;
                    }
                    break;
                case 5:
                    menuConsulta(pessoa);
                    break;
                case 6:
                    menuInfoConsulta(pessoa);
                    break;
                case 7:
                    menuProcedimento(pessoa);
                    break;
                case 8:
                    if(pessoa.getDono_franquia() == 1){
                        menuFinanceiro();
                     }
                    break;
                default:
                    System.out.println("Não existe essa opção");
                    opc = -1;
                }
            }while(opc != 0);
    }
    
    //menu financeiro 
     private static void menuFinanceiro(){
         
        Scanner scan = new Scanner(System.in);
        String toConvert;
        int opc;
        boolean res;
         System.out.println("\n============ Menu Financeiro Administrativo  ================");
         System.out.println("1 - Cadastrar Movimento");
                 
            do{
                toConvert = scan.nextLine();
                res = isInt(toConvert);
            }while(res != true);
            opc = Integer.parseInt(toConvert);
            
            switch(opc){
                case 1:
                    CadastrarConta();
                    break;
                case 2:
                    DeletandoConta();
                default:
                    System.out.println("Não existe essa opção");
                    opc = -1;
            }
     }
     
     private static void CadastrarConta(){
         
        FinanceiroAdmController.cadastraFinanceiroADM();
         
     }
     
    private static boolean RemovendoConta(FinanceiroAdm unidadeDeletar){

       boolean res;

       res = FinanceiroAdmController.removeFinanceiroAdm((int) unidadeDeletar.getId());

       return res;
        
    }
     
     private static void DeletandoConta(){
        int opcDe = -1;
        int opc = -1;
        boolean res;
        String toConvert;
        Scanner scan = new Scanner(System.in);
        
        do{
        
            System.out.println("Insira o id de uma das CONTAS presentes na lista abaixo que deseja excluir:");
            listarFinanceiroADM();
        
        do{
            toConvert = scan.nextLine();
            res = isInt(toConvert);
        }while(res != true);
        
            opc = Integer.parseInt(toConvert);
        
        
        do{
            
            FinanceiroAdm unidadeDeletar = FinanceiroAdmController.buscarPorId(opc);
            System.out.println("Essa é a conta que deseja EXCLUIR?");
            System.out.println("1 - sim");
            System.out.println("2 - não");
            System.out.println("------------------------------------------");
            System.out.println(unidadeDeletar);

            opcDe = Integer.parseInt(scan.nextLine());
     
            switch(opcDe){
            
                case 1: 
                    boolean resDeletado;
                    resDeletado = RemovendoConta(unidadeDeletar);
                    if(resDeletado == true){
                        System.out.println("Deletado com sucesso");
                    }else{
                        
                        System.out.println("Ocorreu algum erro");
                        
                    }
                    opcDe = 0;
                    opc = 0;
                    break;
                case 2:
                    opcDe = 0;
                    opc = 0;
                    break;
                default:
                    System.out.println("Não existe essa opção");
                    opc = -1;
            
            }
        
            
        }while(opcDe != 0);
        
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
                    DeletaFranquia();
                    break;
                case 3:
                    EditarFranquia();
                    break;
                case 4:
                    CriandoFranquiaUnidade();
                    break;
                default:
                    System.out.println("Não existe essa opção");
                    opc = -1;
                    
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
            opc = -1;
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
                    listarFranquias();
                    System.out.println("Qual será sua Franquia(digite o id)");
                    do{
                        toConvert = scan.nextLine();
                        res = isInt(toConvert);
                    }while(res != true);
                    int id_franquia = Integer.parseInt(toConvert);
//                    this.setId_franquia(Integer.parseInt(scan.nextLine()));

                    System.out.println("Cidade da Unidade:");
                    String cidade = scan.nextLine();
//                    this.setCidade(scan.nextLine());


                    System.out.println("Endereco da Unidade:");
                    String endereco = scan.nextLine();

                    listaPessoas();

                    System.out.println("\nDentre os seguintes usuarios quem será o responsavel(digite o id)");
                    do{
                        toConvert = scan.nextLine();
                        res = isInt(toConvert);
                    }while(res != true);
                    int id_responsavel = Integer.parseInt(toConvert);
//                    this.setId_responsavel(Integer.parseInt(scan.nextLine()));
//                    this.setDataCriacao(new Date());
                    FranquiaUnidadeController.cadastraFranquiaUnidades(id_franquia, cidade, endereco, id_responsavel);
//                    FranquiaUnidade NewFranquiaUnidade = FranquiaUnidadeController.cadastraFranquiaUnidades();
                    break;
                case 2:
                    DeletandoUnidadeFranquia();
                    break;
                case 3: 
                    EditarFranquiaUnidade();
                    break;
                 default:
                    System.out.println("Não existe essa opção");
                    opc = -1;
                }
            
        }while(opc != 0);
       
    
    }
    
    //       chamando metodo de criar
    
    private static void CriandoFranquia() {
        boolean res;
        int idResponsavel;
        String toConvert;
        Scanner scan = new Scanner(System.in);
        System.out.println("digite o nome da Franquia:");
        String nome = scan.nextLine();
        
        System.out.println("digite a cidade da matriz");
        String cidade = (scan.nextLine());
       
        listaPessoas();
        
        System.out.println("Dentre os seguintes usuarios quem será o responsavel(digite o id)");
        do{
            toConvert = scan.nextLine();
            res = isInt(toConvert);
        }while(res != true);
        idResponsavel = Integer.parseInt(toConvert);
        
//        this.setId_responsavel(Integer.parseInt(scan.nextLine()));
        
        System.out.println("digite o Endereco");
        String endereco = (scan.nextLine());
        
        System.out.println("digite o Cnpj");
        String cnpj = (scan.nextLine());
        
        FranquiaController.cadastraFranquia(nome, cnpj, cidade, endereco, idResponsavel);
        
//        Franquia NewFranquia = FranquiaController.cadastraFranquia();
        
    }
    
        ////listar Franquias
    
    private static void listarFranquias(){
    
        List<Franquia> Franquias = FranquiaController.listarFranquias();
        
        for(Franquia Franquia: Franquias){
            
            if(Franquia != null){
                System.out.println("-------------------------------------------------------");
                System.out.println(Franquia);
                System.out.println("-------------------------------------------------------");
            }
            
        }
    
    }
    private static boolean DeletandoFranquia(Franquia FranquiaDeletar){
    
        boolean res;
        
        res = FranquiaController.deleteFranquias((int) FranquiaDeletar.getId());
        
        return res;
        
    }
    
    private static void DeletaFranquia(){
    
        int opcDe = -1;
        int opc = -1;
        boolean res;
        String toConvert;
        Scanner scan = new Scanner(System.in);
        
        do{
        
            System.out.println("Insira o id de uma da FRANQUIAS presentes na lista abaixo que deseja excluir:");
            listarFranquias();
        
        do{
            toConvert = scan.nextLine();
            res = isInt(toConvert);
        }while(res != true);
        
            opc = Integer.parseInt(toConvert);
        
        
        do{
            
            Franquia unidadeDeletar = FranquiaController.buscaPorId(opc);
            System.out.println("Essa é a unidade que deseja EXCLUIR?");
            System.out.println("1 - sim");
            System.out.println("2 - não");
            System.out.println("------------------------------------------");
            System.out.println(unidadeDeletar);

            opcDe = Integer.parseInt(scan.nextLine());
     
            switch(opcDe){
            
                case 1: 
                    boolean resDeletado;
                    resDeletado = DeletandoFranquia(unidadeDeletar);
                    if(resDeletado == true){
                        System.out.println("Deletado com sucesso");
                    }else{
                        
                        System.out.println("Ocorreu algum erro");
                        
                    }
                    opcDe = 0;
                    opc = 0;
                    break;
                case 2:
                    opcDe = 0;
                    opc = 0;
                    break;
                default:
                    System.out.println("Não existe essa opção");
                    opc = -1;
            
            }
        
            
        }while(opcDe != 0);
        
    }while(opc != 0);
        
    
    }
    
    //listando unidades de franquia
    private static void listarUnidadesFranquia(){
        
        List<FranquiaUnidade> FranquiasUnidades = FranquiaUnidadeController.listarUnidadesFranquia();
       
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
                case 2:
                    opcDe = 0;
                    opc = 0;
                    break;
                default:
                    System.out.println("Não existe essa opção");
                    opc = -1;
            
            }
        
            
        }while(opcDe != 0);
        
    }while(opc != 0);
       
    }
    private static boolean EditarFranquiaUnidade(){

        int opc = -1;
        boolean res;
        String toConvert;
        Scanner scan = new Scanner(System.in);
        
        listarUnidadesFranquia();
        System.out.println("---------------------------------------");
        System.out.println("\nDigite o id que deseja editar");
        
        do{
           toConvert = scan.nextLine();
           res = isInt(toConvert);
       }while(res != true);
        
        int id = Integer.parseInt(toConvert);
        
        FranquiaUnidade Editar = FranquiaUnidadeController.buscarPorId(id);
        
        do { 
            opc = -1;
            System.out.println("------------------------------------------");
            System.out.println("\n" + Editar);
            System.out.println("\n Qual Campo Gostaria de alterar?");
            System.out.println("0 - Voltar");
            System.out.println("1 - Franquia Atribuida ");
            System.out.println("2 - Cidade ");
            System.out.println("3 - Endereço ");
            System.out.println("4 - Responsavel ");
            
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
                    listarFranquias();
                    System.out.println("Franquia atual(digite id): " + Editar.getFranquia());
                    Editar.setId_franquia(scan.nextInt());
//                    Editar.setDataModificacao(new Date());
                    opc=0;
                    break;
                case 2:
                    System.out.println("Cidade atual: " + Editar.getCidade());
                    Editar.setEndereço(scan.nextLine());
//                    Editar.setDataModificacao(new Date());
                    opc=0;
                    break;
                case 3:
                    System.out.println("Endereco atual: " + Editar.getEndereço());
                    Editar.setEndereço(scan.nextLine());
//                    Editar.setDataModificacao(new Date());
                    opc=0;
                    break;
                case 4:
                    listaPessoas();
                    System.out.println("Responsavel atual(digite id): " + Editar.getId_responsavel());
                    Editar.setId_responsavel(scan.nextLong());
//                    Editar.setDataModificacao(new Date());
                    opc=0;
                    break;
                    
                default:
                    System.out.println("Não existe essa opção");
                    opc = -1;
            }
        } while (opc != 0);
        
        return false;
        
    }
    
    private static boolean DeletaFranquiaUnidade(FranquiaUnidade unidadeDeletar){
                
        FranquiaUnidadeController.deleteFranquiaUnidade((int) unidadeDeletar.getId());
        
//        return res;
        return true;
    }
    
    //       chamando metodo de editar
    private static boolean EditarFranquia(){
        
        int opc = -1;
        boolean res;
        String toConvert;
        Scanner scan = new Scanner(System.in);
        
        listarFranquias();
        System.out.println("---------------------------------------");
        System.out.println("\nDigite o id que deseja editar");
        
        do{
           toConvert = scan.nextLine();
           res = isInt(toConvert);
       }while(res != true);
        
        int id = Integer.parseInt(toConvert);
        
        Franquia Editar = FranquiaController.buscaPorId(id);
        
        do { 
            opc = -1;
            System.out.println("------------------------------------------");
            System.out.println("\n" + Editar);
            System.out.println("\n Qual Campo Gostaria de alterar?");
            System.out.println("0 - Voltar");
            System.out.println("1 - Nome");
            System.out.println("2 - Cnpj");
            System.out.println("3 - Cidade");
            System.out.println("4 - Endereço ");
            System.out.println("5 - Responsavel ");
            
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
                    
                    System.out.println("Nome Atual: "+ Editar.getNome());
                    Editar.setNome(scan.nextLine());
//                    Editar.setDataModificacao(new Date());
                    opc=0;
                    break;
                case 2:
                    System.out.println("Cnpj atual: " + Editar.getCnpj());
                    Editar.setCnpj(scan.nextLine());
//                    Editar.setDataModificacao(new Date());
                    opc=0;
                    break;
                case 3:
                    System.out.println("Cidade atual: " + Editar.getCidade());
                    Editar.setCidade(scan.nextLine());
//                    Editar.setDataModificacao(new Date());
                    opc=0;
                    break;
                case 4:
                    listaPessoas();
                    System.out.println("Responsavel atual:(digite id) " + Editar.getId_responsavel());
                    Editar.setId_responsavel(scan.nextLong());
//                    Editar.setDataModificacao(new Date());
                    opc=0;
                    break;
                default:
                    System.out.println("Não existe essa opção");
                    opc = -1;
                    
            }
            
            
        } while (opc != 0);
        
        return false;
        
    }
    
    /////////////////////////////////////
    ////////////////////////////////////
    private static void criandoMedico() throws SQLException {
        Scanner scan = new Scanner(System.in);
        System.out.println("digite seu nome:");
        String nome = scan.nextLine();

        System.out.println("digite seu endereço:");
        String end = scan.nextLine();

        System.out.println("digite seu CPF:");
        String cpf = scan.nextLine();

        System.out.println("digite seu telefone:");
        String tel = scan.nextLine();

        System.out.println("digite seu login:");
        String log = scan.nextLine();

        System.out.println("digite seu sua senha:");
        String passw = scan.nextLine();
        
        System.out.println("digite o CRM");;
        String crm = scan.nextLine();

        System.out.println("digite a especialidade");
        String espec = scan.nextLine();
        
        listarFranquias();
        System.out.println("digite o ID da franquia");
        int franq = Integer.parseInt(scan.nextLine());

        listarUnidadesFranquia();
        System.out.println("digite o ID da unidade");
        int unit = Integer.parseInt(scan.nextLine());

        PessoaController.cadastraMed(nome, end, cpf, tel, log, passw, franq, unit, crm, espec);

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
            System.out.println("1 - Papel de medico");
            System.out.println("2 - Papel de dono de unidade de franquia");
            System.out.println("3 - Papel de dono de franquia");
            do{
                toConvert = scan.nextLine();
                res = isInt(toConvert);
            }while(res != true);
            papel = Integer.parseInt(toConvert);
            while(papel != 1 && papel != 2 && papel != 3){
                System.out.println("Digite uma opção válida");
                do{
                    toConvert = scan.nextLine();
                    res = isInt(toConvert);
                }while(res != true);
                papel = Integer.parseInt(toConvert);
            }
            PessoaController.alteraTipoUsuario(papel, (int) p.getId(), i);
        }
    }

    private static void listaPessoas() {
        List<Pessoa> pessoas = PessoaController.listarPessoas();
        for(Pessoa p: pessoas){
            if(p != null){
                System.out.println("-------------------------------------------------------");
                System.out.println(p);
                System.out.println("-------------------------------------------------------");
            }
        }
    }

    private static void listaMedicos() {
        List<Medico> medicos = MedicoController.listarMedicos();
        for(Medico m: medicos){
            if(m != null){
                System.out.println("-------------------------------------------------------");
                System.out.println(m);
                System.out.println("-------------------------------------------------------");
            }
        }
    }
    
    private static void listaConsultas() {
        List<Consulta> consultas = ConsultaController.listarConsultas();
        for(Consulta c: consultas){
            if(c != null){
                System.out.println("-------------------------------------------------------");
                System.out.println(c);
                System.out.println("-------------------------------------------------------");
            }
        }        
    }

    private static void listaProcedimentos() {
        List<Procedimento> procedimentos = ProcedimentoController.listarProcedimentos();
        for(Procedimento p: procedimentos){
            if(p != null){
                System.out.println("-------------------------------------------------------");
                System.out.println(p);
                System.out.println("-------------------------------------------------------");                
            }
        }
    }

    private static void deletaPessoa(Pessoa p) {
        Scanner scan = new Scanner(System.in);
        boolean res; int i; String toConvert;
        System.out.println("########################################################################");
        listaPessoas();
        listaMedicos();
        System.out.println("Insira o id de uma das pessoas presentes na lista abaixo que deseja excluir:");
        
        do{
            toConvert = scan.nextLine();
            res = isInt(toConvert);
        }while(res != true);
        i = Integer.parseInt(toConvert);

        if(p.getId() != i){
            Pessoa pessoa = PessoaController.buscarPorId(i);
            while(pessoa == null && (i != 0)){
                System.out.println("Erro ao buscar Pessoa");
                
                System.out.println("########################################################################");
                listaPessoas();
                System.out.println("Insira o id de uma das pessoas presentes na lista abaixo que deseja excluir ou 0 para sair:");
                
                do{
                    toConvert = scan.nextLine();
                    res = isInt(toConvert);
                }while(res != true);
                i = Integer.parseInt(toConvert);
            }
              
            if(p.getId() == i){
                System.out.println("não é possivel deletar");
            }else{
                boolean isMedic = false;
                if(pessoa.getMedico() == 1){
                    isMedic = true;
                    PessoaController.deletePessoa(i, isMedic);
                }else {
                    PessoaController.deletePessoa(i, isMedic);
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
        LocalDate convertData;
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
                    
                    LocalDate convertDate = LocalDate.of(ano, mes, dia);
//                    convertData = Date.from(dtConsulta.atStartOfDay(ZoneId.systemDefault()).toInstant());
//                    convertData = Date.from(dtConsulta);
                    
                    if(convertDate.isAfter(dtAtual) == false){
                        System.out.println("DATA INVÁLIDA (DATA PRECISA SER POSTERIOR A DATA ATUAL)");
                        break;
                    }
                    
                    System.out.println("Insira o HORARIO da sua consulta entre 8H - 19H");
                    System.out.println("No seguinte formato: HH:mm");
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
                    LocalTime hora = LocalTime.of(Integer.parseInt(horaConsulta[0]),Integer.parseInt(horaConsulta[1]),0);
                    
                    ConsultaController.cadastraConsulta(idMed, (int)p.getId(), convertDate, hora, idUnidFranq);
                    
                    listaConsultas();
                    break;
                    
                case 2:
                    if(p.getDono_franquia() == 1 || p.getDono_unidade() == 1){
                        listaConsultas();
//                        listaConsultas(permissoes);
                    }else if(p.getMedico() == 1){
                        //trazer lista especifica apenas para as consultas do usuário(médico)
                        listaConsultas();
                    }else{
                        //trazer lista especifica apenas para as consultas do usuário----
                        listaConsultas();
                    }
                    System.out.println("########################################################################");
                    System.out.println("Insira o id da consulta que deseja alterar");
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
                        System.out.println("Insira a opcao da informacao que deseja alterar");
                        System.out.println("0 - Sair");
                        System.out.println("1 - Alterar Unidade de Franquia");
                        System.out.println("2 - Alterar Data e hora da Consulta");
                        System.out.println("3 - Alterar Medico");
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
                                listarUnidadesFranquia();
                                System.out.println("Informe a unidade de franquia que deseja: ");
                                do{
                                    toConvert = scan.nextLine();
                                    res = isInt(toConvert);
                                }while(res != true);
                                idUnidFranq = Integer.parseInt(toConvert);
                                
                                ConsultaController.alteraConsulta(idConsulta, idUnidFranq, 0, null, null);
                                break;
                            case 2:
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

                                convertDate = LocalDate.of(ano, mes, dia);
            //                    convertData = Date.from(dtConsulta.atStartOfDay(ZoneId.systemDefault()).toInstant());
            //                    convertData = Date.from(dtConsulta);

                                if(convertDate.isAfter(dtAtual) == false){
                                    System.out.println("DATA INVÁLIDA (DATA PRECISA SER POSTERIOR A DATA ATUAL)");
                                    break;
                                }

                                System.out.println("Insira o HORARIO da sua consulta entre 8H - 19H");
                                System.out.println("No seguinte formato: HH:mm");
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
                                hora = LocalTime.of(Integer.parseInt(horaConsulta[0]),Integer.parseInt(horaConsulta[1]),0);
                                ConsultaController.alteraConsulta(idConsulta, 0, 0, convertDate, hora);
                                break;
                            case 3:
                                listaMedicos();
                                System.out.println("insira o ID do medico de desejo:");
                                do{
                                    toConvert = scan.nextLine();
                                    res = isInt(toConvert);
                                }while(res != true);
                                idMed = Integer.parseInt(toConvert);
                                ConsultaController.alteraConsulta(idConsulta, 0, idMed, null, null);
                                break;
                        }
                    }
                    break;
                case 3:
                    System.out.println("########################################################################");
//                    System.out.println("Insira o id da consulta que deseja cancelar");
                    if(p.getDono_franquia() == 1 || p.getDono_unidade() == 1){
                        listaConsultas();
                    }else if(p.getMedico() == 1){
                        listaConsultas();
                    }else{
                        listaConsultas();
                    }
                    System.out.println("Insira o id da consulta que deseja cancelar");
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
                        res = ConsultaController.deleteConsultas(idConsulta);
                    }
                    if(res == false){
                        System.out.println("Erro ao deletar consulta");
                    }else {
                        System.out.println("Consulta deletada com sucesso");
                    }
                    break;
                case 4:
                    System.out.println("########################################################################");
                    listaConsultas();
                    break;
            }
        }while(opc != 0);
    }

    private static void menuInfoConsulta(Pessoa pessoa) {
        InfoConsultaController.setInfoConsultas();
        Scanner scan = new Scanner(System.in);
        String toConvert;
        List<Consulta> consultas = ConsultaController.listarConsultas();
        boolean res;  
        int opc = 0;
        int idConsulta;
        do{
            System.out.println("####### Informacoes de Consulta #######");
            System.out.println("Informe a opcao que deseja");
            System.out.println("0 - Sair do menu Informacoes de Consulta");
            System.out.println("1 - Criar nova Informacao de Consulta");
            System.out.println("2 - Alterar Informacao de Consulta");
            System.out.println("3 - Deletar Informacao de Consulta");
            System.out.println("4 - Listar Informacoes de Consulta");
            do{
                toConvert = scan.nextLine();
                res = isInt(toConvert);
            }while(res != true);
            opc = Integer.parseInt(toConvert);
            switch (opc){
                case 1:
                    if(pessoa.getMedico() == 1){
                        for(Consulta c: consultas){
                            if(c != null){
                                if(c.getIdMedico() == pessoa.getId()){
                                    System.out.println("-------------------------------------------------------");
                                    System.out.println(c.toString());
                                    System.out.println("-------------------------------------------------------");
                                }
                            }
                        }       
                        System.out.println("Informe o id da Consulta");
                        do{
                            toConvert = scan.nextLine();
                            res = isInt(toConvert);
                        }while(res != true);
                        idConsulta = Integer.parseInt(toConvert);
//                        do{
//                            toConvert = scan.nextLine();
//                            res = isInt(toConvert);
//                        }while(res != true);
                        System.out.println("Descreva as informações referentes a consulta:");
                        String descricao = scan.nextLine();
                        for(Consulta c: consultas){
                            if(c != null){
                                if(c.getIdMedico() == pessoa.getId()){
                                    if(idConsulta == c.getId()){
                                       InfoConsultaController.cadastraInfoConsulta(pessoa.getId(), idConsulta, descricao);
                                       break;
                                    }
                                }
                            }
                        }
                    }else{
                        System.out.println("Você não possui permissão");
                    }  
                    break;
                case 2:
                    listaInfoConsultas(pessoa);
                    System.out.println("Insira o id da consulta que deseja alterar");
//                    for(Consulta c: consultas){
//                        if(c != null){
//                            if(c.getIdMedico() == pessoa.getId()){
//                                System.out.println("-------------------------------------------------------");
//                                System.out.println(c.toString());
//                                System.out.println("-------------------------------------------------------");
//                            }
//                        }
//                    }
                    do{
                        toConvert = scan.nextLine();
                        res = isInt(toConvert);
                    }while(res != true);
                    idConsulta = Integer.parseInt(toConvert);
                    for(Consulta c: consultas){
                        if(c != null){
                            if(c.getIdMedico() == pessoa.getId()){
                                if(idConsulta == c.getId()){
                                    System.out.println("Insira a nova descricao");
                                    String descricao = scan.nextLine();
                                    res = InfoConsultaController.alteraInfoConsulta(descricao, idConsulta); 
                                    if(res == true){
                                        System.out.println("SUCESSO em alterar a Informacao da Consulta");
                                        listaInfoConsultas(pessoa);
                                    }else{
                                        System.out.println("ERRO em alterar a Informacao da Consulta....");
                                    }
                                    break;
                                }else{
                                    System.out.println("ERROR");
                                }
                            }else{
                                System.out.println("ERROR");
                            }
                        }
                    }
                    System.out.println("id da consulta informado não corresponde ao medico da sessão");
                    break;
                case 3:
                    System.out.println("Insira o id da Informacao de Consulta que deseja deletar:");
                    listaInfoConsultas(pessoa);
                    do{
                        toConvert = scan.nextLine();
                        res = isInt(toConvert);
                    }while(res != true);
                    idConsulta = Integer.parseInt(toConvert);
                    InfoConsultaController.deleteInfoConsultas(idConsulta);
                    break;
                case 4:
                    listaInfoConsultas(pessoa);
                    break;
            }
        }while(opc != 0);
    }

    private static void listaInfoConsultas(Pessoa p) {
        List<InfoConsulta> infos = InfoConsultaController.listarInfoConsultas();
        for (InfoConsulta info: infos){
            if(info != null){
                if(info.getIdMedico() == p.getId()){
                    System.out.println("-------------------------------------------------------");
                    System.out.println(info.toString());
                    System.out.println("-------------------------------------------------------");
                }
            }
        }
    }

    private static void menuProcedimento(Pessoa pessoa) {
        Scanner scan = new Scanner(System.in);
        String toConvert; 
        String nome; 
        String laudo; 
        LocalDate dtAtual = LocalDate.now();
        LocalDate convertData;
        String[] dataConsulta;
        String[] horaConsulta;
        int dia;
        int mes;
        int ano;
        int i;
        boolean res;  
        int opc = 0;
        do{
            System.out.println("####### PROCEDIMENTOS #######");
            System.out.println("Informe a opcao que deseja");
            System.out.println("0 - Sair do menu de Procedimentos");
            if(pessoa.getMedico() == 1 || pessoa.getDono_unidade() == 1 || pessoa.getDono_franquia() == 1){
                System.out.println("1 - Criar novo Procedimento");
                System.out.println("2 - Criar Procedimento a partir de uma consulta");
                System.out.println("3 - Alterar Procedimento");
                System.out.println("4 - Deletar Procedimento");
            }
            System.out.println("5 - Listar Procedimento");
            
            do{
                toConvert = scan.nextLine();
                res = isInt(toConvert);
            }while(res != true);
            opc = Integer.parseInt(toConvert);
            switch(opc){
                case 0:
                    System.out.println("Saindo do menu de Procedimento");
                    break;
                case 1:    
                    if(pessoa.getMedico() == 1 || pessoa.getDono_unidade() == 1 || pessoa.getDono_franquia() == 1){
                        System.out.println("Insira o nome do Procedimento");
                        nome = scan.nextLine();

                        System.out.println("Insira o laudo do Procedimento");
                        laudo = scan.nextLine();
                        
                        System.out.println("Insira o id da unidade que irá consultar");
                        listarUnidadesFranquia();
                        do{
                            toConvert = scan.nextLine();
                            res = isInt(toConvert);
                        }while(res != true);

                        int idUnidFranq = Integer.parseInt(toConvert);
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

                        int idMed = Integer.parseInt(toConvert);
                        res = MedicoController.medicoExiste(idMed);
                        if(res == false){
                            System.out.println("id inválido");
                            break;
                        }

                        System.out.println("Insira o DIA, MES e ANO do seu procedimento no seguinte formato");
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
//                        convertData = Date.from(dtConsulta.atStartOfDay(ZoneId.systemDefault()).toInstant());

                        if(dtConsulta.isAfter(dtAtual) == false){
                            System.out.println("DATA INVÁLIDA (DATA PRECISA SER POSTERIOR A DATA ATUAL)");
                            break;
                        }

                        System.out.println("Insira o HORARIO do Procedimento entre 8H - 19H");
                        System.out.println("No seguinte formato: HH:mm");
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
                        LocalTime hora = LocalTime.of(Integer.parseInt(horaConsulta[0]),Integer.parseInt(horaConsulta[1]),0);
                        ProcedimentoController.cadastraProcedimento(nome, dtConsulta, hora, laudo, idUnidFranq, idMed);
                        listaProcedimentos();
                    }
                    break;
                case 2:
                    if(pessoa.getMedico() == 1 || pessoa.getDono_unidade() == 1 || pessoa.getDono_franquia() == 1){
                        listaProcedimentos();
                        System.out.println("Insira o id da consulta que irá gerar um procedimento");
                        do{
                            toConvert = scan.nextLine();
                            res = isInt(toConvert);
                        }while(res != true);
                        i = Integer.parseInt(toConvert);
                        Consulta c = ConsultaController.buscarPorId(i);
                        if(c == null){
                            System.out.println("Consulta nao existe");
                            break;
                        }
                        
                        System.out.println("Insira o nome do Procedimento");
                        nome = scan.nextLine();
                        
                        System.out.println("Insira o laudo do Procedimento");
                        laudo = scan.nextLine();
                        
//                        System.out.println("Insira o id da unidade que irá consultar");
//                        listarUnidadesFranquia();
//                        do{
//                            toConvert = scan.nextLine();
//                            res = isInt(toConvert);
//                        }while(res != true);
//
//                        int idUnidFranq = Integer.parseInt(toConvert);
//                        res = unidadeExiste(idUnidFranq);
//                        if(res == false){
//                            System.out.println("id inválido");
//                            break;
//                        }
//
//                        System.out.println("Insira a o id do medico na lista abaixo com quem deseja consultar");
//                        listaMedicos();
//                        do{
//                            toConvert = scan.nextLine();
//                            res = isInt(toConvert);
//                        }while(res != true);
//
//                        int idMed = Integer.parseInt(toConvert);
//                        res = MedicoController.medicoExiste(idMed);
//                        if(res == false){
//                            System.out.println("id inválido");
//                            break;
//                        }

                        System.out.println("Insira o DIA, MES e ANO do seu procedimento no seguinte formato");
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
//                        convertData = Date.from(dtConsulta.atStartOfDay(ZoneId.systemDefault()).toInstant());

                        if(dtConsulta.isAfter(dtAtual) == false){
                            System.out.println("DATA INVÁLIDA (DATA PRECISA SER POSTERIOR A DATA ATUAL)");
                            break;
                        }

                        System.out.println("Insira o HORARIO do Procedimento entre 8H - 19H");
                        System.out.println("No seguinte formato: HH:mm");
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
                        LocalTime hora = LocalTime.of(Integer.parseInt(horaConsulta[0]),Integer.parseInt(horaConsulta[1]),0);
                        ProcedimentoController.cadastraProcedimento(nome, dtConsulta, hora, laudo, c.getUnidade(), c.getIdMedico());
                        listaProcedimentos();
                    }
                    break;
                case 3:
                    if(pessoa.getMedico() == 1 || pessoa.getDono_unidade() == 1 || pessoa.getDono_franquia() == 1){
                        System.out.println("Informe o ID da consulta que deseja ALTERAR");
                        listaProcedimentos();
                        do{
                            toConvert = scan.nextLine();
                            res = isInt(toConvert);
                        }while(res != true);
                        i = Integer.parseInt(toConvert);
                        Procedimento p = ProcedimentoController.procedimentoExiste(i);
                        if(p != null){
                            System.out.println("Informe o que deseja ALTERAR");
                            System.out.println("1 - ALTERAR O NOME");
                            System.out.println("2 - ALTERAR O LAUDO");
                            System.out.println("3 - ALTERAR DATA E HORARIO");
                            do{
                                toConvert = scan.nextLine();
                                res = isInt(toConvert);
                            }while(res != true);
                            opc = Integer.parseInt(toConvert);
                            switch(opc){
                                case 1:
                                    System.out.println("Informe o novo NOME");
                                    nome = scan.nextLine();
                                    p.setNome(nome);
                                    break;
                                case 2:
                                    System.out.println("Informe o novo LAUDO");
                                    laudo = scan.nextLine();
                                    p.setLaudo(laudo);
                                    break;
                                case 3:
                                    System.out.println("Insira o DIA, MES e ANO do seu procedimento no seguinte formato");
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
//                                    convertData = Date.from(dtConsulta.atStartOfDay(ZoneId.systemDefault()).toInstant());

                                    if(dtConsulta.isAfter(dtAtual) == false){
                                        System.out.println("DATA INVÁLIDA (DATA PRECISA SER POSTERIOR A DATA ATUAL)");
                                        break;
                                    }

                                    System.out.println("Insira o HORARIO do Procedimento entre 8H - 19H");
                                    System.out.println("No seguinte formato: HH:mm");
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
                                    LocalTime hora = LocalTime.of(Integer.parseInt(horaConsulta[0]),Integer.parseInt(horaConsulta[1]),0);
                                    p.setdataProcedimento(dtConsulta);
                                    p.setHorario(hora);
                                    break;
                            }
                            
                        }else{
                            System.out.println("Ocorreu um ERRO!!");
                        }
                    }
                    break;
                case 4:
                    if(pessoa.getMedico() == 1 || pessoa.getDono_unidade() == 1 || pessoa.getDono_franquia() == 1){
                        System.out.println("Informe o ID da consulta que deseja CANCELAR");
                        listaProcedimentos();
                        
                        do{
                            toConvert = scan.nextLine();
                            res = isInt(toConvert);
                        }while(res != true);
                        i = Integer.parseInt(toConvert);
                        
                        res = ProcedimentoController.deleteProcedimentos(i);
                        if(res == true){
                            System.out.println("Cancelado com sucesso");
                        }else{
                            System.out.println("Ocorreu um ERRO!!");
                        }
                    }
                    break;
                case 5:
                    listaProcedimentos();
                    break;
            }
        }while(opc != 0);
    }
    
//    private static LocalDate acrescentaCalendario(LocalDate dataAtual) {
//        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        String dataFormatada = dataAtual.format(formatoData);
//        System.out.println("Data atual: " + dataFormatada);
//
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Informe o número de dias a serem adicionados: ");
//        int numeroDias = scanner.nextInt();
//
//        LocalDate novaData = dataAtual.plusDays(numeroDias);
//
//        String novaDataFormatada = novaData.format(formatoData);
//        System.out.println("Nova data: " + novaDataFormatada);
//        
//        return novaData;
//    }
}