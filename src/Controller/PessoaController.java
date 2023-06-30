package controller;

//import org.mindrot.jbcrypt.BCrypt;
import Controller.MedicoController;
import java.sql.ResultSet;
import Model.DBConnect;
import Model.Pessoa;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.sql.Timestamp;

public class PessoaController {
    public static List<Pessoa> pessoas = new ArrayList();

    private Connection connection;
    public PessoaController() {
        this.connection = new DBConnect().getConnection();
    }
    
//    public static int count = 0;

//    Scanner scan = new Scanner(System.in);
    
//    DBConnect con = (DBConnect) new DBConnect().getConnection();
//    string sql = "insert into tabela..." + (coluna, coluna, coluna) + "(?, ? ,?)";
//    PreparedStatement stmt = con.preparedStatement(sql);
//    stmt.setString(1, "asdasd")
//    stmt.setString(1, "asdasd")

    
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
    
//    public static void preCadastroPessoa(int indice) throws SQLException {
//        String sql = "insert into pessoa" + "(nome, endereco, cpf, telefone, login, senha, cliente, medico, dono_unidade, dono_franquia, data_criacao)" +
//                "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//        try (Connection connection = new DBConnect().getConnection(); 
//                PreparedStatement stmt = connection.prepareStatement(sql)){
////            stmt.setString(1, );
//            stmt.setString(1, "sdasd");
//            stmt.setString(2, "123123");
//            stmt.setString(3, "123123"+ indice);
//            stmt.setString(4, "0129312");
//            stmt.setString(5, "login"+ indice);
//            stmt.setString(6, "senha"+ indice);
//            stmt.setInt(7, 1);
//            stmt.setInt(8, 0);
//            stmt.setInt(9, 0);
//            stmt.setInt(10, 0);
////            stmt.setDate(5, java.sql.Date.valueOf(elemento.getDataNascimento()));
//            LocalDateTime now = LocalDateTime.now();
//            java.sql.Timestamp dateNow = java.sql.Timestamp.valueOf(now);
//            stmt.setTimestamp(11, dateNow);
////            stmt.setDate(11, dateNow);
//            
//            stmt.execute();
//        }catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//            
//            
////        Pessoa pessoa = new Pessoa("nome"+(indice+1), "endereco"+(indice+1), "CPF"+(indice+1), "Telefone"+(indice+1), "login"+(indice+1),;
////                        "senha"+(indice+1), indice+1);
////        
////        boolean res = salvaPessoas(pessoa);
//        
////        if(res == true){
////            System.out.println("Cadastrado com sucesso");
////        } else {
////            System.out.println("Ocorreu um erro");
////        }
//    }
    
    public static void cadastraPessoa(String nome, String end, String cpf, String tel, String login, String senha) {
        Pessoa pessoa = new Pessoa();
//        String hashedPassword = BCrypt.hashpw(senha, BCrypt.gensalt());
        String sql = "insert into pessoa" + "(nome, endereco, cpf, telefone, login, senha, cliente, medico, dono_unidade, dono_franquia, data_criacao)" +
        "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = new DBConnect().getConnection(); 
                PreparedStatement stmt = connection.prepareStatement(sql)){
//            stmt.setString(1, );
            stmt.setString(1, nome);
            stmt.setString(2, end);
            stmt.setString(3, cpf);
            stmt.setString(4, tel);
            stmt.setString(5, login);
            stmt.setString(6, senha);
            stmt.setInt(7, 1);
            stmt.setInt(8, 0);
            stmt.setInt(9, 0);
            stmt.setInt(10, 0);
//            stmt.setDate(5, java.sql.Date.valueOf(elemento.getDataNascimento()));
            LocalDateTime now = LocalDateTime.now();
            java.sql.Timestamp dateNow = java.sql.Timestamp.valueOf(now);
            stmt.setTimestamp(11, dateNow);
            
            stmt.execute();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try(Connection connection = new DBConnect().getConnection(); 
                PreparedStatement stmt = connection.prepareStatement("select * from pessoa where login = " +"'"+ login +"'")){
            ResultSet rs = stmt.executeQuery();
            // itera no ResultSet
            while (rs.next()) {
                pessoa.setId(rs.getInt("id"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setEndereco(rs.getString("endereco"));
                pessoa.setCpf(rs.getString("cpf"));
                pessoa.setTelefone(rs.getString("telefone"));
                pessoa.setLogin(rs.getString("login"));
                pessoa.setSenha(rs.getString("senha"));
                pessoa.setCliente(rs.getInt("cliente"));
                pessoa.setMedico(rs.getInt("medico"));
                pessoa.setDono_unidade(rs.getInt("dono_unidade"));
                pessoa.setDono_franquia(rs.getInt("dono_franquia"));
                
                java.sql.Timestamp timestamp = rs.getTimestamp("data_criacao");
                pessoa.setDataCriacao(timestamp.toLocalDateTime());
                
                java.sql.Timestamp dataMod = rs.getTimestamp("data_modificacao");
                if(dataMod != null)
                    pessoa.setDataModificacao(dataMod.toLocalDateTime());
//                Timestamp timestamp = new Timestamp(rs.getTimestamp("data_criacao"));
//                pessoa.setDataCriacao(rs.getTimestamp("data_criacao"));
            }
            
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println(pessoa);
        
//        Pessoa pessoa = new Pessoa();
//        boolean cadastrado = cadastrado(pessoa);
        
//        while(cadastrado == true){
//            pessoa.novoLogin(pessoa);
//            cadastrado = cadastrado(pessoa);
//        }


//        boolean res = salvaPessoas(pessoa);
//        
//        if(res == true){
//            System.out.println("Cadastrado com sucesso");
//        } else {
//            System.out.println("Ocorreu um erro");
//        }
//        return(pessoa);
    }
    public static void cadastraMed(String nome, String end, String cpf, String tel, String login, String senha, int franq, int unit, String crm, String espec) throws SQLException {
//        Pessoa pessoa = new Pessoa();
//        String hashedPassword = BCrypt.hashpw(senha, BCrypt.gensalt());
        String sql = "insert into pessoa" + "(nome, endereco, cpf, telefone, login, senha, cliente, medico, dono_unidade, dono_franquia, data_criacao)" +
        "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, current_timestamp())";
        try (Connection connection = new DBConnect().getConnection(); 
                PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, nome);
            stmt.setString(2, end);
            stmt.setString(3, cpf);
            stmt.setString(4, tel);
            stmt.setString(5, login);
            stmt.setString(6, senha);
            stmt.setInt(7, 1);
            stmt.setInt(8, 1);
            stmt.setInt(9, 0);
            stmt.setInt(10, 0);
//            LocalDateTime now = LocalDateTime.now();
//            java.sql.Timestamp dateNow = java.sql.Timestamp.valueOf(now);
//            stmt.setTimestamp(11, dateNow);
            
            stmt.execute();
           
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        setPessoas();
        int id = 0;
        for (Pessoa pessoa : pessoas) {
            if((pessoa.getLogin()).equals(login)){
                id = (int)pessoa.getId();
            }
        }
        if(id != 0){
            MedicoController.cadastraMedico(id, crm, espec, franq, unit);
        }else{
            System.out.println("ERRO ao cadastrar medico");
        }
        
//        try(Connection connection = new DBConnect().getConnection(); 
//            PreparedStatement stmt2 = connection.prepareStatement("select id from pessoa where medico = 1 and login = " + "'" + login + "'")){
//            ResultSet rs = stmt2.executeQuery();
//            System.out.println(rs.getInt("id"));
//            int id = rs.getInt("id");
//            MedicoController.cadastraMedico(id, crm, espec, franq, unit);
//        }catch (RuntimeException e) {
//            System.out.println("erro ao cadastrar o medico");
//        }

    }

    public static void salvaPessoas(Pessoa pessoa) {
            pessoas.add(pessoa);
//            return true;
    }
    
    public static void deletePessoa(int id, boolean isMedic){
        if (isMedic != true){
            String sql = "delete from pessoa where id = ?";

            try (Connection connection = new DBConnect().getConnection();
                    PreparedStatement stmt = connection.prepareStatement(sql)) {

                stmt.setInt(1, id);

                stmt.execute();

                System.out.println("Excluído com sucesso");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else {
            String sql = "delete from medico where id = ?";

            try (Connection connection = new DBConnect().getConnection();
                    PreparedStatement stmt = connection.prepareStatement(sql)) {

                stmt.setInt(1, id);

                stmt.execute();

                System.out.println("Excluído com sucesso");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            
            String sql2 = "delete from pessoa where id = ?";

            try (Connection connection = new DBConnect().getConnection();
                    PreparedStatement stmt = connection.prepareStatement(sql2)) {

                stmt.setInt(1, id);

                stmt.execute();

                System.out.println("Excluído com sucesso");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static boolean removePessoas(int id) {
        Iterator<Pessoa> it = pessoas.iterator();
        
        while(it.hasNext()){
            Pessoa p = it.next();
            if(p.getId() == id){
//                if(p.getTipoUsuario()[1] == 2){
//                    //logica de deletar na classe médico
//                    MedicoController.removeMedicos(id);
//                }
                it.remove();
                return true;
            }
        }
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
        listCleaner();
        try(Connection con = new DBConnect().getConnection(); 
                PreparedStatement stmt = con.prepareStatement("select * from pessoa")){
            ResultSet rs = stmt.executeQuery();
            // itera no ResultSet
            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(rs.getInt("id"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setEndereco(rs.getString("endereco"));
                pessoa.setCpf(rs.getString("cpf"));
                pessoa.setTelefone(rs.getString("telefone"));
                pessoa.setLogin(rs.getString("login"));
                pessoa.setSenha(rs.getString("senha"));
                pessoa.setCliente(rs.getInt("cliente"));
                pessoa.setMedico(rs.getInt("medico"));
                pessoa.setDono_unidade(rs.getInt("dono_unidade"));
                pessoa.setDono_franquia(rs.getInt("dono_franquia"));
                
                java.sql.Timestamp timestamp = rs.getTimestamp("data_criacao");
//                pessoa.setDataCriacao(timestamp.toLocalDateTime());;
//                java.sql.Timestamp dataMod = rs.getTimestamp("data_modificacao");
//                pessoa.setDataModificacao(dataMod.toLocalDateTime());
////                Timestamp timestamp = new Timestamp(rs.getTimestamp("data_criacao"));
//                pessoa.setDataCriacao(rs.getTimestamp("data_criacao"));
                salvaPessoas(pessoa);

            }
            
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pessoas;
//        return Arrays.copyOf(PessoaController.pessoas, PessoaController.count);
    }

//    public static Pessoa buscarPessoaPorCpf(String cpf) {
//        for(Pessoa p: pessoas){
//            if(p.getCpf().equals(cpf)){
//                return p;
//            }
//        }
//        return null;
//    }
    
    public static void alteraTipoUsuario(int papel, int id, int val) {
        if(papel == 1){
            try(Connection con = new DBConnect().getConnection(); 
                    PreparedStatement stmt = con.prepareStatement("update pessoa set medico = ?, data_modificacao = ? where id= ?")){
                LocalDateTime now = LocalDateTime.now();
                java.sql.Timestamp dateNow = java.sql.Timestamp.valueOf(now);
                
                stmt.setInt(1, val);
                stmt.setTimestamp(2, dateNow);
                stmt.setInt(3, id);
                
                
                stmt.execute();
                
                System.out.println("Papel alterado com sucesso");

            }catch (SQLException e) {
                throw new RuntimeException(e);
            }
            
        }else if(papel == 2){
            try(Connection con = new DBConnect().getConnection(); 
                    PreparedStatement stmt = con.prepareStatement("update pessoa set dono_unidade = ? where id= ?")){
                LocalDateTime now = LocalDateTime.now();
                java.sql.Timestamp dateNow = java.sql.Timestamp.valueOf(now);
                
                stmt.setInt(1, val);
                stmt.setTimestamp(2, dateNow);
                stmt.setInt(3, id);
                
                
                stmt.execute();
                
                System.out.println("Papel alterado com sucesso");

            }catch (SQLException e) {
                throw new RuntimeException(e);
            }
            
        }else{
            try(Connection con = new DBConnect().getConnection(); 
                    PreparedStatement stmt = con.prepareStatement("update pessoa set dono_franquia = ? where id= ?")){
                LocalDateTime now = LocalDateTime.now();
                java.sql.Timestamp dateNow = java.sql.Timestamp.valueOf(now);
                
                stmt.setInt(1, val);
                stmt.setTimestamp(2, dateNow);
                stmt.setInt(3, id);
                
                
                stmt.execute();
                
                System.out.println("Papel alterado com sucesso");

            }catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    
    public static void listCleaner(){
        Iterator<Pessoa> it = pessoas.iterator();
        while(it.hasNext()){
            it.next();
            it.remove();
        }
    }
    
    public static Pessoa verifyLogin(String login, String senha){
//        listCleaner();
//        try(Connection con = new DBConnect().getConnection(); 
//                PreparedStatement stmt = con.prepareStatement("select * from pessoa")){
//            ResultSet rs = stmt.executeQuery();
//            // itera no ResultSet
//            while (rs.next()) {
//                Pessoa pessoa = new Pessoa();
//                pessoa.setId(rs.getInt("id"));
//                pessoa.setNome(rs.getString("nome"));
//                pessoa.setEndereco(rs.getString("endereco"));
//                pessoa.setCpf(rs.getString("cpf"));
//                pessoa.setTelefone(rs.getString("telefone"));
//                pessoa.setLogin(rs.getString("login"));
//                pessoa.setSenha(rs.getString("senha"));
//                pessoa.setCliente(rs.getInt("cliente"));
//                pessoa.setMedico(rs.getInt("medico"));
//                pessoa.setDono_unidade(rs.getInt("dono_unidade"));
//                pessoa.setDono_franquia(rs.getInt("dono_franquia"));
//                
//                java.sql.Timestamp timestamp = rs.getTimestamp("data_criacao");
//                pessoa.setDataCriacao(timestamp.toLocalDateTime());
////                Timestamp timestamp = new Timestamp(rs.getTimestamp("data_criacao"));
////                pessoa.setDataCriacao(rs.getTimestamp("data_criacao"));
//                salvaPessoas(pessoa);
//
//            }
//            
//        }catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        setPessoas();
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
    
    public static void setPessoas(){
        listCleaner();
        try(Connection con = new DBConnect().getConnection(); 
                PreparedStatement stmt = con.prepareStatement("select * from pessoa")){
            ResultSet rs = stmt.executeQuery();
            // itera no ResultSet
            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setId(rs.getInt("id"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setEndereco(rs.getString("endereco"));
                pessoa.setCpf(rs.getString("cpf"));
                pessoa.setTelefone(rs.getString("telefone"));
                pessoa.setLogin(rs.getString("login"));
                pessoa.setSenha(rs.getString("senha"));
                pessoa.setCliente(rs.getInt("cliente"));
                pessoa.setMedico(rs.getInt("medico"));
                pessoa.setDono_unidade(rs.getInt("dono_unidade"));
                pessoa.setDono_franquia(rs.getInt("dono_franquia"));
                
                java.sql.Timestamp timestamp = rs.getTimestamp("data_criacao");
                pessoa.setDataCriacao(timestamp.toLocalDateTime());
                java.sql.Timestamp dataMod = rs.getTimestamp("data_modificacao");
                if(dataMod != null)
                    pessoa.setDataModificacao(dataMod.toLocalDateTime());
//                Timestamp timestamp = new Timestamp(rs.getTimestamp("data_criacao"));
//                pessoa.setDataCriacao(rs.getTimestamp("data_criacao"));
                salvaPessoas(pessoa);

            }
            
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
