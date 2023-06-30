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

import static Controller.FranquiaController.Franquias;
import Model.Franquia;
import Model.FranquiaUnidade;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author jv232
 */
public class FranquiaUnidadeController{
    
    public static List<FranquiaUnidade> Unidades = new ArrayList();
            
    private Connection connection;
    public FranquiaUnidadeController() {
        this.connection = new DBConnect().getConnection();
    }
    
            
//    public static FranquiaUnidade preCadastraFranquiaUnidades(long idFranquia, long idResponsavel) {
//
//      FranquiaUnidade FranquiaUnidade = new FranquiaUnidade(idFranquia, idResponsavel);
//
//      boolean res = SalvaFranquiaUnidade(FranquiaUnidade);
//      
//      Franquia f = FranquiaController.buscaPorId((int)idFranquia);
//
//      if(res == true){
//          if(f != null) {
//            System.out.println("Cadastrado com sucesso");
//          }
//      } else {
//          System.out.println("Ocorreu um erro");
//      }
//
//      return (FranquiaUnidade);
//    }
    
    
    public static void cadastraFranquiaUnidades(int id_franquia, String cidade, String endereco, int id_responsavel) {
        String sql = "insert into unidade_franquia" + "(id_franquia, cidade, endereco, id_responsavel, data_criacao)" +
        "values (?, ?, ?, ?, current_timestamp())";
        try (Connection connection = new DBConnect().getConnection(); 
                PreparedStatement stmt = connection.prepareStatement(sql)){
//            stmt.setString(1, );
            stmt.setInt(1, id_franquia);
            stmt.setString(2, cidade);
            stmt.setString(3, endereco);
            stmt.setInt(4, id_responsavel);
            
            stmt.execute();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

//      FranquiaUnidade FranquiaUnidade = new FranquiaUnidade();
//  
//      boolean res = SalvaFranquiaUnidade(FranquiaUnidade);
//      
//      Franquia f = FranquiaController.buscaPorId((int) FranquiaUnidade.getFranquia());
//
//      if(res == true){
//          System.out.println("Cadastrado com sucesso");
//      } else {
//          System.out.println("Ocorreu um erro");
//      }

//      return (FranquiaUnidade);
    };
    
    public static List<FranquiaUnidade> listarUnidadesFranquia() {
        setFranquiaUnidade();
        return Unidades;
    }

        
    public static boolean SalvaFranquiaUnidade(FranquiaUnidade FranquiaUnidade) {
            Unidades.add(FranquiaUnidade);
            return true;
        
    }
       
    
      public static void deleteFranquiaUnidade(int id) {
        String sql = "delete from unidade_franquia where id = ?";

        try (Connection connection = new DBConnect().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.execute();

            System.out.println("Excluído com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
//         Iterator<FranquiaUnidade> iterator = Unidades.iterator();
//        while(iterator.hasNext()){
//            FranquiaUnidade f = iterator.next();
//            if(f.getId() == id){
//                iterator.remove();
//                return true;
//            }
//
//        }
//        return false;
    }
     
    public static FranquiaUnidade buscarPorId(int id) {
        setFranquiaUnidade();
          for(FranquiaUnidade fu: Unidades){
            if(fu.getId() == id){
                return fu;
            }
        }        

        return null;
    }
    
    public static boolean unidadeExiste(int id) {
        setFranquiaUnidade();
       for(FranquiaUnidade fu: Unidades){
           if(fu.getId() == id){
               return true;
           }
       }
       return false;
    }
    
    public static void setFranquiaUnidade(){
        listCleaner();
        try(Connection con = new DBConnect().getConnection(); 
                PreparedStatement stmt = con.prepareStatement("select * from unidade_franquia")){
            ResultSet rs = stmt.executeQuery();
            // itera no ResultSet
            while (rs.next()) {
                FranquiaUnidade uf = new FranquiaUnidade();
                uf.setId(rs.getInt("id"));
                uf.setCidade(rs.getString("cidade"));
                uf.setEndereço(rs.getString("endereco"));
                uf.setId_responsavel(rs.getInt("id_responsavel"));
                uf.setId_franquia(rs.getInt("id_franquia"));
                
                java.sql.Timestamp timestamp = rs.getTimestamp("data_criacao");
                uf.setDataCriacao(timestamp.toLocalDateTime());
                java.sql.Timestamp dataMod = rs.getTimestamp("data_modificacao");
                if(dataMod != null)
                    uf.setDataModificacao(dataMod.toLocalDateTime());
                
                SalvaFranquiaUnidade(uf);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
        
    public static void listCleaner(){
        Iterator<FranquiaUnidade> it = Unidades.iterator();
        while(it.hasNext()){
            it.next();
            it.remove();
        }
    }
    
}
