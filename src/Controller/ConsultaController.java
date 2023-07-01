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
import java.sql.Time;
import java.sql.Date;


import Model.Consulta;
import Model.DBConnect;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
//import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author yn719471
 */
public class ConsultaController {
    public static List<Consulta> consultas = new ArrayList();
    
    private Connection connection;
    public ConsultaController() {
        this.connection = new DBConnect().getConnection();
    }
    
//    public static Consulta[] consultas = new Consulta[100];
//    public static int count = 0;
    
    
    
    public static void cadastraConsulta(int idMed, int idPes, LocalDate data, LocalTime hora, int idUnidade) {
        String sql = "insert into consulta" + "(estado, id_medico, id_pessoa, valor, unidade_franquia, data_consulta, hora_consulta, data_criacao)" +
            "values (?, ?, ?, ? ,?, ?, ?, current_timestamp())";
        try (Connection connection = new DBConnect().getConnection(); 
                PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, 2);
            stmt.setInt(2, idMed);
            stmt.setInt(3, idPes);
            stmt.setInt(4, 200);
            stmt.setInt(5, idUnidade);
            
            Date sqlDate = Date.valueOf(data);
            Time sqlTime = Time.valueOf(hora);
                
            stmt.setDate(6, sqlDate);
            stmt.setTime(7, sqlTime);
            
            stmt.execute();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean salvaConsultas(Consulta c) {
        consultas.add(c);
        return true;
    }

    public static boolean deleteConsultas(int id) {
        String sql = "delete from consulta where id = ?";

        try (Connection connection = new DBConnect().getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.execute();

            System.out.println("Excluído com sucesso");
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void alteraConsulta(int idConsulta, int idUniFranq, int idMed, LocalDate dtConsulta, LocalTime hora){
        if(idMed != 0){
            try(Connection con = new DBConnect().getConnection(); 
                PreparedStatement stmt = con.prepareStatement("update consulta set id_medico = ?, data_modificacao = current_timestamp() where id = ?;")){
                
                stmt.setInt(1, idMed);
                stmt.setInt(2, idConsulta);
                
                stmt.execute();
                
                System.out.println("Consulta alterada com sucesso");

            }catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(dtConsulta != null){
            try(Connection con = new DBConnect().getConnection(); 
                PreparedStatement stmt = con.prepareStatement("update consulta set data_consulta = ?, data_modificacao = current_timestamp() where id = ?;")){
                
                Date sqlDate = Date.valueOf(dtConsulta);
                
                stmt.setDate(1, sqlDate);
                
                stmt.setInt(2, idConsulta);
                
                stmt.execute();
                
                System.out.println("Consulta alterada com sucesso");

            }catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        
        if(hora != null){
            try(Connection con = new DBConnect().getConnection(); 
                PreparedStatement stmt = con.prepareStatement("update consulta set hora_consulta = ?, data_modificacao = current_timestamp() where id = ?;")){
                
//                Date sqlDate = Date.valueOf(dtConsulta);
                Time sqlTime = Time.valueOf(hora);
                
                stmt.setTime(1, sqlTime);
                stmt.setInt(2, idConsulta);
                
                stmt.execute();
                
                System.out.println("Consulta alterada com sucesso");

            }catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        
        if(idUniFranq != 0){
            try(Connection con = new DBConnect().getConnection(); 
                PreparedStatement stmt = con.prepareStatement("update consulta set unidade_franquia = ?, data_modificacao = current_timestamp() where id = ?;")){
                
                stmt.setInt(1, idUniFranq);
                stmt.setInt(2, idConsulta);
                
                stmt.execute();
                
                System.out.println("Consulta alterada com sucesso");

            }catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        
//        for(Consulta c : consultas){
//            if(c.getId() == idConsulta){
//                if(idMed != 0){
//                    c.setIdMedico(idMed);
//                }
//                if(dtConsulta != null){
//                    c.setDataConsulta(dtConsulta);
//                }
//                if(hora != null){
//                    c.setHorario(hora);
//                }
//                if(idUniFranq != 0){
//                    c.setUnidade(idUniFranq);
//                }
//                return;
//            }
//        }
    }
    
    public static Consulta buscarPorId(int id) {
        for(Consulta c : consultas){
            if(c.getId() == id){
                return c;
            }
        }
        return null;
    }

    public static List<Consulta> listarConsultas() {
        setConsultas();
        return consultas;
    }
    
    public static boolean consultaExiste(int id) {
       setConsultas();
       for(Consulta c: consultas){
           if(c.getId() == id){
               return true;
           }
       }
       return false;
    }
    
    public static void setConsultas(){
        listCleaner();
        try(Connection con = new DBConnect().getConnection(); 
                PreparedStatement stmt = con.prepareStatement("select * from consulta")){
            ResultSet rs = stmt.executeQuery();
            // itera no ResultSet
            while (rs.next()) {
                Consulta consulta = new Consulta();
                consulta.setId(rs.getInt("id"));
                consulta.setIdMedico(rs.getInt("id_medico"));
                consulta.setIdPessoa(rs.getInt("id_pessoa"));
                consulta.setEstado(rs.getInt("estado"));
                consulta.setUnidade(rs.getInt("unidade_franquia"));
                consulta.setValor(rs.getInt("valor"));
                
                // convertendo date to local date
                java.sql.Date date = rs.getDate("data_consulta"); // Obtém a data do result set como java.sql.Date
                LocalDate localdate = date.toLocalDate(); // Converte para LocalDate
                consulta.setDataConsulta(localdate);
                
//                Date date = rs.getDate("data_consulta");
//                Instant inst = date.toInstant();
//                LocalDate localdate = inst.atZone(ZoneId.systemDefault()).toLocalDate();
//                consulta.setDataConsulta(localdate);

                Time hora = rs.getTime("hora_consulta");
                consulta.setHorario(hora.toLocalTime());
                               
                java.sql.Timestamp timestamp = rs.getTimestamp("data_criacao");
                consulta.setDataCriacao(timestamp.toLocalDateTime());
                java.sql.Timestamp dataMod = rs.getTimestamp("data_modificacao");
                if(dataMod != null)
                    consulta.setDataModificacao(dataMod.toLocalDateTime());
                salvaConsultas(consulta);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
        
    public static void listCleaner(){
        Iterator<Consulta> it = consultas.iterator();
        while(it.hasNext()){
            it.next();
            it.remove();
        }
    }
    
}
