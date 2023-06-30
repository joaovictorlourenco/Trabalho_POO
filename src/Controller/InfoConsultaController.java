/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.InfoConsulta;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import Model.DBConnect;
import java.sql.Time;
import java.sql.Date;


/**
 *
 * @author yn719471
 */
public class InfoConsultaController {

    public static List<InfoConsulta> infoConsultas = new ArrayList();
    
    private Connection connection;
    public InfoConsultaController() {
        this.connection = new DBConnect().getConnection();
    }
//    public static InfoConsulta[] infoConsultas = new InfoConsulta[100];
//    public static int count = 0;

    public static void cadastraInfoConsulta(long idMedico, long idConsulta, String descricao) {
        String sql = "insert into consulta" + "(id_consulta, id_medico, descricao, data_criacao)" +
            "values (?, ?, ?, current_timestamp())";
        try (Connection connection = new DBConnect().getConnection(); 
                PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, (int)idConsulta);
            stmt.setInt(2, (int)idMedico);
            stmt.setString(3, descricao);
            
            stmt.execute();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

//        InfoConsulta info = new InfoConsulta(idMedico, idConsulta, descricao);

//        boolean res = salvaInfoConsultas(info);
//        if (res == true) {
//            System.out.println("Informacao sobre a consulta salva com SUCESSO");
//        } else {
//            System.out.println("Ocorreu um ERRO");
//        }

    }

    public static List<InfoConsulta> listarInfoConsultas() {
        setInfoConsultas();
        return infoConsultas;
    }

    public static boolean salvaInfoConsultas(InfoConsulta info) {
        infoConsultas.add(info);
        return true;

    }

    public static boolean deleteInfoConsultas(int id) {
        String sql = "delete from info_consulta where id = ?";

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
    
//    public static boolean removeInfoConsultas(int id) {
//        Iterator<InfoConsulta> it = infoConsultas.iterator();
//        while(it.hasNext()){
//            InfoConsulta ic = it.next();
//            if(ic.getIdInfoConsulta() == id){
//                it.remove();
//                return true;
//            }
//        }
    
//        for (int i = 0; i < InfoConsultaController.count; i++) {
//
//            if (InfoConsultaController.infoConsultas[i].getIdInfoConsulta() == id) {
//
//                InfoConsultaController.infoConsultas[i] = null;
//
//                for (int j = i; j < InfoConsultaController.count - 1; j++) {
//                    InfoConsultaController.infoConsultas[j] = InfoConsultaController.infoConsultas[j + 1];
//                }
//
//                InfoConsultaController.count--;
//
//                return true;
//            }
//        }
//        return false;
//    }

    public static InfoConsulta buscarPorId(int id) {
        setInfoConsultas();
        for(InfoConsulta ic : infoConsultas){
            if(ic.getIdInfoConsulta() == id){
                return ic;
            }
        }
        return null;
    }

    public static boolean infoExiste(int id) {
        setInfoConsultas();
        for (InfoConsulta ic : infoConsultas) {
            if (ic.getIdInfoConsulta() == id) {
                return true;
            }
        }
        return false;
    }

    public static boolean alteraInfoConsulta(String descricao, int idConsulta) {
        try(Connection con = new DBConnect().getConnection(); 
            PreparedStatement stmt = con.prepareStatement("update info_consulta set descricao = ?, data_modificacao = current_timestamp() where id = ?;")){

            stmt.setString(1, descricao);
            stmt.setInt(2, idConsulta);
//            stmt.setInt(2, idConsulta);

            stmt.execute();

            System.out.println("Informacao da consulta alterada com sucesso");

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        for(InfoConsulta ic: infoConsultas){
            if (ic.getIdInfoConsulta() == idConsulta) {
                ic.setDescricao(descricao);
                return true;
            }
        }
        return false;
    }
    
        
    public static void setInfoConsultas(){
        listCleaner();
        try(Connection con = new DBConnect().getConnection(); 
                PreparedStatement stmt = con.prepareStatement("select * from info_consulta")){
            ResultSet rs = stmt.executeQuery();
            // itera no ResultSet
            while (rs.next()) {
                InfoConsulta ic = new InfoConsulta();
                ic.setIdInfoConsulta(rs.getInt("id"));
                ic.setIdMedico(rs.getInt("id_medico"));
                ic.setIdConsulta(rs.getInt("id_consulta"));
                ic.setDescricao(rs.getString("descricao"));

                
//                // convertendo date to local date
//                Date date = rs.getDate("data_consulta");
//                Instant inst = date.toInstant();
//                LocalDate localdate = inst.atZone(ZoneId.systemDefault()).toLocalDate();
//                consulta.setDataConsulta(localdate);
//
//                Time hora = rs.getTime("hora_consulta");
//                consulta.setHorario(hora.toLocalTime());
                               
                java.sql.Timestamp timestamp = rs.getTimestamp("data_criacao");
                ic.setDataCriacao(timestamp.toLocalDateTime());
                java.sql.Timestamp dataMod = rs.getTimestamp("data_modificacao");
                ic.setDataModificacao(dataMod.toLocalDateTime());
                
                salvaInfoConsultas(ic);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
        
    public static void listCleaner(){
        Iterator<InfoConsulta> it = infoConsultas.iterator();
        while(it.hasNext()){
            it.next();
            it.remove();
        }
    }
    
}
