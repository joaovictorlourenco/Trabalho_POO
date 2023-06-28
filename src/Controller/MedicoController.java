/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import Model.DBConnect;
import Model.Medico;
import Model.Pessoa;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author yn719471
 */
public class MedicoController {
    public static List<Medico> medicos = new ArrayList();
    
    private Connection connection;
    public MedicoController() {
        this.connection = new DBConnect().getConnection();
    }
    
    public static void cadastraMedico(int id, String crm, String espec, int franq, int unit) {
//        String hashedPassword = BCrypt.hashpw(senha, BCrypt.gensalt());
        String sql = "insert into medico" + "(id, crm, especialidade, franquia, unidade_franquia, data_criacao)" +
        "values (?, ?, ?, ? ,?, current_timestamp())";
        try (Connection connection = new DBConnect().getConnection(); 
                PreparedStatement stmt = connection.prepareStatement(sql)){
//            stmt.setString(1, );
            stmt.setInt(1, id);
            stmt.setString(2, crm);
            stmt.setString(3, espec);
            stmt.setInt(4, franq);
            stmt.setInt(5, unit);
//            stmt.setDate(5, java.sql.Date.valueOf(elemento.getDataNascimento()));
//            LocalDateTime now = LocalDateTime.now();
//            java.sql.Timestamp dateNow = java.sql.Timestamp.valueOf(now);
//            stmt.setTimestamp(5, dateNow);
            
            stmt.execute();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        try(Connection con = new DBConnect().getConnection(); 
//                PreparedStatement stmt = con.prepareStatement("select * from pessoa where login = " +"'"+ m.getLogin() +"'")){
//            ResultSet rs = stmt.executeQuery();
//            // itera no ResultSet
//            while (rs.next()) {
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
//                
//                java.sql.Timestamp dataMod = rs.getTimestamp("data_modificacao");
//                pessoa.setDataModificacao(dataMod.toLocalDateTime());
////                Timestamp timestamp = new Timestamp(rs.getTimestamp("data_criacao"));
////                pessoa.setDataCriacao(rs.getTimestamp("data_criacao"));
//            }
//            
//        }catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        System.out.println(pessoa);
//        boolean res = salvaMedicos(m);
//        
//        if(res == true){
//            System.out.println("Cadastrado com sucesso");
//        } else {
//            System.out.println("Ocorreu um erro");
//        }
//        return(m);
    }

    public static boolean salvaMedicos(Medico m) {
        medicos.add(m);
        return true;
    }

    public static boolean removeMedicos(int id) {
        String sql = "delete from pessoa where id = ? and medico = ?";

        try (Connection connection = new DBConnect().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setLong(1, id);
            stmt.setLong(2, 1);
            
            stmt.execute();
            
            System.out.println("Excluído com sucesso");
            return true; 
       } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
//        Iterator<Medico> it = medicos.iterator();
//        while(it.hasNext()){
//            Medico m = it.next();
//            if(m.getId_pessoa() == id){
//                it.remove();
//                return true;
//            }
//        }
    }
    
    public static Medico buscarPorId(int id) {
        for(Medico m : medicos){
            if(m.getId_pessoa() == id){
                return m;
            }
        }
//        for (int i = 0; i < MedicoController.count; i++) {
//            if (MedicoController.medicos[i].getId() == id) {
//                return MedicoController.medicos[i];
//            }
//        }
        return null;
    }
/*=================== À Fazer ======================*/
//    public static boolean editarMedicos(Medic pessoa) {;
//        for (int i = 0; i < PessoaController.count; i++) {
//            if (PessoaController.pessoas[i].getId() == pessoa.getId()) {
//                PessoaController.pessoas[i] = pessoa;
//                return true;
//            }
//        }
//        return false;
//    }

    public static List<Medico> listarMedicos() {
        return medicos;
//        return Arrays.copyOf(MedicoController.medicos, MedicoController.count);
    }
    
    public static void setMedicos(){
        listCleaner();
        try(Connection con = new DBConnect().getConnection(); 
                PreparedStatement stmt = con.prepareStatement("select * from pessoa where medico = 1")){
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
                pessoa.setDataModificacao(dataMod.toLocalDateTime());

                try(Connection conn = new DBConnect().getConnection(); 
                    PreparedStatement stmt2 = conn.prepareStatement("select * from medico where id = " + pessoa.getId())){
                    ResultSet rs2 = stmt2.executeQuery();
                    // itera no ResultSet
                    while (rs2.next()) {
                        Medico medico = new Medico();
                        medico.setId_pessoa(rs2.getInt("id"));
                        medico.setCrm(rs2.getString("nome"));
                        medico.setEspecialidade(rs.getString("endereco"));
                        medico.setFranquia(rs2.getInt("franquia"));
                        medico.setUnidade(rs2.getInt("unidade_franquia"));

                        java.sql.Timestamp timestamp2 = rs.getTimestamp("data_criacao");
                        medico.setDataCriacao(timestamp2.toLocalDateTime());
                        java.sql.Timestamp dataMod2 = rs.getTimestamp("data_modificacao");
                        medico.setDataModificacao(dataMod2.toLocalDateTime());
                        
                        medico.setPessoa(pessoa);
                        boolean res = salvaMedicos(medico);
                        if(res == false){
                            throw new RuntimeException();
                        }
                    }
                }catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
           
    public static boolean medicoExiste(int id) {
       for(Medico m: medicos){
           if(m != null){
                if(m.getId_pessoa() == id){
                    return true;
                }
           }
       }
       return false;
    }
    
    public static void listCleaner(){
        Iterator<Medico> it = medicos.iterator();
        while(it.hasNext()){
            it.remove();
        }
    }
}
