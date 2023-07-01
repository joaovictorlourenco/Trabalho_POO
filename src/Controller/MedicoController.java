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
            
            stmt.execute();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean salvaMedicos(Medico m) {
        medicos.add(m);
        return true;
    }

//    public static boolean removeMedicos(int id) {
//        String sql = "delete from pessoa where id = ? and medico = ?";
//
//        try (Connection connection = new DBConnect().getConnection();
//                PreparedStatement stmt = connection.prepareStatement(sql)) {
//
//            stmt.setLong(1, id);
//            stmt.setLong(2, 1);
//            
//            stmt.execute();
//            
//            System.out.println("Excluído com sucesso");
//            return true; 
//       } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        
//        Iterator<Medico> it = medicos.iterator();
//        while(it.hasNext()){
//            Medico m = it.next();
//            if(m.getId_pessoa() == id){
//                it.remove();
//                return true;
//            }
//        }
//    }
    
    public static Medico buscarPorId(int id) {
        setMedicos();
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
        setMedicos();
        return medicos;
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
                if(dataMod != null)
                    pessoa.setDataModificacao(dataMod.toLocalDateTime());

                try(Connection conn = new DBConnect().getConnection(); 
                    PreparedStatement stmt2 = conn.prepareStatement("select * from medico where id = " + pessoa.getId())){
                    ResultSet rs2 = stmt2.executeQuery();
                    // itera no ResultSet
                    while (rs2.next()) {
                        Medico medico = new Medico();
                        medico.setId_pessoa(rs2.getInt("id"));
                        medico.setCrm(rs2.getString("crm"));
                        medico.setEspecialidade(rs2.getString("especialidade"));
                        medico.setFranquia(rs2.getInt("franquia"));
                        medico.setUnidade(rs2.getInt("unidade_franquia"));

                        java.sql.Timestamp timestamp2 = rs2.getTimestamp("data_criacao");
                        medico.setDataCriacao(timestamp2.toLocalDateTime());
                        java.sql.Timestamp dataMod2 = rs2.getTimestamp("data_modificacao");
                        if(dataMod2 != null)
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
        setMedicos();
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
            it.next();
            it.remove();
        }
    }
}
