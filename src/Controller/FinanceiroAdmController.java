/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.DBConnect;
import Model.FinanceiroAdm;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author jv232
 */
public class FinanceiroAdmController {
    private static List<FinanceiroAdm> FinanceiroAdm = new ArrayList();
    
    private Connection connection;
    
    public FinanceiroAdmController() {
        this.connection = new DBConnect().getConnection();
    }   
    
    public static void cadastraFinanceiroADM(int tipoMovimento,  int idFranquia, int idUnidade, String descritivoMovimento, double valor) {

        FinanceiroAdm mov = new FinanceiroAdm();
        
        String sql = "insert into financeiroadm" + "(tipoMovimento, idFranquia, idUnidade, descritivoMovimento, valor, dataCriacao)" +
        "values (?, ?, ?, ?, ?, ?)";
        
        try (Connection connection = new DBConnect().getConnection(); 
           PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setInt(1, tipoMovimento);
            stmt.setInt(2, idFranquia);
            stmt.setInt(3, idUnidade);
            stmt.setString(4, descritivoMovimento);
            stmt.setDouble(5, valor);
            LocalDateTime now = LocalDateTime.now();
            java.sql.Timestamp dateNow = java.sql.Timestamp.valueOf(now);
            stmt.setTimestamp(6, dateNow);
            stmt.execute();
            System.out.println("Movimentação cadastrada com sucesso");
            
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }
    
    public static boolean salvaFinanceiroADM(FinanceiroAdm mov){
        FinanceiroAdm.add(mov);
        return true;
    }
    
    public static boolean removeFinanceiroAdm(int id) {
        
        Iterator<FinanceiroAdm> iterator = FinanceiroAdm.iterator();
        
         while(iterator.hasNext()){
            FinanceiroAdm fa = iterator.next();
            if(fa.getId() == id){
                iterator.remove();
                return true;
            }
            
        }
        return false;
    }

 
    public static List<FinanceiroAdm> listarFinanceiroADM() {

        setFranquia();
        return FinanceiroAdm;
    }
    
    public static FinanceiroAdm buscarPorId(int id) {
        setFranquia();
        for(FinanceiroAdm fa: FinanceiroAdm){
            
            if(fa.getId() == id){
                return fa;
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
                FinanceiroAdm mov = new FinanceiroAdm();
                mov.setId(rs.getInt("id"));
                mov.setTipoMovimento(rs.getInt("tipoMovimento"));
                mov.setIdFranquia(rs.getInt("idFranquia"));
                mov.setIdUnidade(rs.getInt("idUnidade"));
                mov.setDescritivoMovimento(rs.getString("descritivoMovimento"));
                mov.setValor(rs.getDouble("valor"));
                
                java.sql.Timestamp timestamp = rs.getTimestamp("data_criacao");
                mov.setDataCriacao(timestamp.toLocalDateTime());
                
                java.sql.Timestamp dataMod = rs.getTimestamp("data_modificacao");
                
                if(dataMod != null){
                    mov.setDataModificacao(dataMod.toLocalDateTime());
                }

                salvaFinanceiroADM(mov);
                
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void listCleaner(){
    Iterator<FinanceiroAdm> it = FinanceiroAdm.iterator();
        while(it.hasNext()){
            it.next();
            it.remove();
        }
    }
    
}
