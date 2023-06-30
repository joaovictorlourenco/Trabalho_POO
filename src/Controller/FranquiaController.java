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

import Model.Franquia;
import Model.FranquiaUnidade;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


/**
 *
 * @author jd719829
 */
public class FranquiaController {
    
    public static List<Franquia> Franquias = new ArrayList();
        
    private Connection connection;
    public FranquiaController() {
        this.connection = new DBConnect().getConnection();
    }
    
//    public static void preCadastraFranquia(String nome, long idResponsavel) {
//      
//        Franquia Franquia = new Franquia(nome, idResponsavel);
//
//        boolean res = SalvaFranquia(Franquia);
//        if(res == true){
//            System.out.println("Cadastrado com sucesso");
//        } else {
//            System.out.println("Ocorreu um erro");
//        }
//    }
//    
    public static void cadastraFranquia(String nome, String cnpj, String cidade, String endereco, int idResponsavel) {
        String sql = "insert into franquia" + "(nome, cnpj, cidade, endereco, id_responsavel, data_criacao)" +
        "values (?, ?, ?, ? ,?, current_timestamp())";
        try (Connection connection = new DBConnect().getConnection(); 
                PreparedStatement stmt = connection.prepareStatement(sql)){
//            stmt.setString(1, );
            stmt.setString(1, nome);
            stmt.setString(2, cnpj);
            stmt.setString(3, cidade);
            stmt.setString(4, endereco);
            stmt.setInt(5, idResponsavel);
//            stmt.setDate(5, java.sql.Date.valueOf(elemento.getDataNascimento()));
//            LocalDateTime now = LocalDateTime.now();
//            java.sql.Timestamp dateNow = java.sql.Timestamp.valueOf(now);
//            stmt.setTimestamp(5, dateNow);
            
            stmt.execute();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
      
      
//        Franquia Franquia = new Franquia();
//
//        boolean res = SalvaFranquia(Franquia);
//        if(res == true){
//            System.out.println("Cadastrado com sucesso");
//        } else {
//            System.out.println("Ocorreu um erro");
//        }
//        
//        return (Franquia);
    }
  
    public static boolean SalvaFranquia(Franquia franquia) {
        Franquias.add(franquia);
        return true;
    }
    
    
    public static boolean deleteFranquias(int id) {
        String sqlstring = "select * from unidade_franquia where id_franquia = ?";
        boolean check = false;

        try (Connection connection = new DBConnect().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sqlstring)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                check = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        if(check != true) {
            String sql = "delete from franquia where id = ?";

            try (Connection connection = new DBConnect().getConnection();
                    PreparedStatement stmt = connection.prepareStatement(sql)) {

                stmt.setInt(1, id);

                stmt.execute();

                System.out.println("Excluído com sucesso");
                return true;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else {
            System.out.println("ERRO: HÁ UNIDADES CADASTRADAS COM O ID DESSA FRANQUIA");
            return false;
        }
//        Iterator<Franquia> iterator = Franquias.iterator();
//        while(iterator.hasNext()){
//            Franquia f = iterator.next();
//            if(f.getId() == id){
//                iterator.remove();
//                return true;
//            }
//            
//        }
//        return false;
    }
    
    public static List<Franquia> listarFranquias() {
        setFranquia();
        return Franquias;
        
    }
    
    public static Franquia buscaPorId(int id) {
        setFranquia();
        for(Franquia f: Franquias){
            if(f.getId() == id){
                return f;
            }
        }        
        return null;
    }
    
        
    public static void setFranquia(){
        listCleaner();
        try(Connection con = new DBConnect().getConnection(); 
                PreparedStatement stmt = con.prepareStatement("select * from franquia")){
            ResultSet rs = stmt.executeQuery();
            // itera no ResultSet
            while (rs.next()) {
                Franquia fr = new Franquia();
                fr.setId(rs.getInt("id"));
                fr.setNome(rs.getString("nome"));
                fr.setCnpj(rs.getString("cnpj"));
                fr.setCidade(rs.getString("cidade"));
                fr.setEndereço(rs.getString("endereco"));
                fr.setId_responsavel(rs.getInt("id_responsavel"));
                
                java.sql.Timestamp timestamp = rs.getTimestamp("data_criacao");
                fr.setDataCriacao(timestamp.toLocalDateTime());
                java.sql.Timestamp dataMod = rs.getTimestamp("data_modificacao");
                if(dataMod != null)
                    fr.setDataModificacao(dataMod.toLocalDateTime());
                
                SalvaFranquia(fr);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
        
    public static void listCleaner(){
        Iterator<Franquia> it = Franquias.iterator();
        while(it.hasNext()){
            it.next();
            it.remove();
        }
    }
    
}
