/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.sql.ResultSet;
import Model.DBConnect;
import Model.Procedimento;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author yn719471
 */
public class ProcedimentoController {
//    public static Procedimento[] procedimentos = new Procedimento[100];
//    public static int count = 0;
    public static List<Procedimento> procedimentos = new ArrayList();
    
    private Connection connection;
    public ProcedimentoController() {
        this.connection = new DBConnect().getConnection();
    }

    public static void cadastraProcedimento(String nome, LocalDate data, LocalTime hora, String laudo, int uni, int idMed) {
        String sql = "insert into procedimento" + "(estado, id_medico, valor, nome, laudo, unidade_franquia, data_procedimento, hora_procedimento, data_criacao)" +
            "values (?, ?, ?, ?, ?, ?, ?, ?, current_timestamp())";
        try (Connection connection = new DBConnect().getConnection(); 
                PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, 2);
            stmt.setInt(2, idMed);
            stmt.setInt(3, 200);
            stmt.setString(4, nome);
            stmt.setString(5, laudo);
            stmt.setInt(6, uni);
            
            Date sqlDate = Date.valueOf(data);
            Time sqlTime = Time.valueOf(hora);
                
            stmt.setDate(7, sqlDate);
            stmt.setTime(8, sqlTime);
            
            stmt.execute();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean salvaProcedimentos(Procedimento p) {
        procedimentos.add(p);
        return true;
    }

    public static boolean deleteProcedimentos(int id) {
        String sql = "delete from procedimento where id = ?";

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
    
    public static Procedimento buscarPorId(int id) {
        for(Procedimento p : procedimentos){
            if(p.getIdProcedimento() == id){
                return p;
            }
        }
        return null;
    }

    public static List<Procedimento> listarProcedimentos() {
        setInfoConsultas();
        return procedimentos;
    }

    public static Procedimento procedimentoExiste(int id) {
       for(Procedimento p: procedimentos){
           if(p.getIdProcedimento()== id){
               return p;
           }
       }
       return null;
    }
    
            
    public static void setInfoConsultas(){
        listCleaner();
        try(Connection con = new DBConnect().getConnection(); 
                PreparedStatement stmt = con.prepareStatement("select * from procedimento")){
                ResultSet rs = stmt.executeQuery();
            // itera no ResultSet
            while (rs.next()) {
                Procedimento p = new Procedimento();
                p.setIdProcedimento(rs.getInt("id"));
                p.setIdMedico(rs.getInt("id_medico"));
                p.setEstado(rs.getInt("estado"));
                p.setIdUnidade(rs.getInt("unidade_franquia"));
                p.setValor(rs.getDouble("valor"));
                p.setNome(rs.getString("nome"));
                p.setLaudo(rs.getString("laudo"));

                
                // convertendo date to local date
                Date date = rs.getDate("data_procedimento");
                Instant inst = date.toInstant();
                LocalDate localdate = inst.atZone(ZoneId.systemDefault()).toLocalDate();
                p.setdataProcedimento(localdate);

                Time hora = rs.getTime("hora_consulta");
                p.setHorario(hora.toLocalTime());
                               
                java.sql.Timestamp timestamp = rs.getTimestamp("data_criacao");
                p.setDataCriacao(timestamp.toLocalDateTime());
                java.sql.Timestamp dataMod = rs.getTimestamp("data_modificacao");
                p.setDataModificacao(dataMod.toLocalDateTime());
                
                salvaProcedimentos(p);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
        
    public static void listCleaner(){
        Iterator<Procedimento> it = procedimentos.iterator();
        while(it.hasNext()){
            it.next();
            it.remove();
        }
    }
}
