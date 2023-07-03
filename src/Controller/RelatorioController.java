/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Consulta;
import Model.FinanceiroMedico;
import Model.Procedimento;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import controller.PessoaController;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author yn719471
 */
public class RelatorioController {
    public static String result = "relatorios/";
     
     
     
    public static String getResult() {
        return result;
    }

    public static void setResult(String name) {
        RelatorioController.result = result + name;
    }
     
    public void createPdfMensalFranq(String filename, int id) throws DocumentException, IOException, SQLException {

        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, new FileOutputStream(filename));

        document.open();
                

        document.add(new Paragraph("Relatorio de Financas da Franquia"));
        document.add(new Paragraph(""));

        document.add(createTableFM(id));

        
        document.close();
    }
     
    public void createPdfMensalUnidadeFranq(String filename, int id) throws DocumentException, IOException, SQLException {

        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, new FileOutputStream(filename));

        document.open();
                

        document.add(new Paragraph("Relatorio de Financas da Unidade Franquia"));
        document.add(new Paragraph(""));

        document.add(createTableFMUnidade(id));

        
        document.close();
    }
     
    public void createPdfConsultasProcedimentos(String filename, int id) throws DocumentException, IOException, SQLException {

        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, new FileOutputStream(filename));

        document.open();
                

        document.add(new Paragraph("Relatorio de Paciente - Consultas e Procedimentos"));
        document.add(new Paragraph(""));

        document.add(createTableConsulta(id));
        document.add(createTableProcedimentos(id));

        
        document.close();
    }
     
    public void createPdfPagamentoMedico(String filename, int id) throws DocumentException, IOException, SQLException {

        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, new FileOutputStream(filename));

        document.open();
                

        document.add(new Paragraph("Relatorio Pagamento Medico"));
        document.add(new Paragraph(""));

        document.add(createTablePagamentoMedico(id));

        
        document.close();
    }
    
//    public void createPdf(String filename)
//       throws DocumentException, IOException {
//
//        Document document = new Document();
//
//        PdfWriter.getInstance(document, new FileOutputStream(filename));
//
//        document.open();
//
//        document.add(new Paragraph("Hello World!"));
//        document.add(new Paragraph(filename));
//        
//        document.close();
//    }
    
//    public void createPdf(String filename)
//       throws DocumentException, IOException {
//
//        Document document = new Document();
//
//        PdfWriter.getInstance(document, new FileOutputStream(filename));
//
//        document.open();
//
//        document.add(new Paragraph("Hello World!"));
//        document.add(new Paragraph(filename));
//        
//        document.close();
//    }
    
    public static PdfPTable createTableFM(int id) throws SQLException {
        List<FinanceiroMedico> dados = FinanceiroMedicoController.DadosRelatorioMensal(id);
    	// a table with three columns
        PdfPTable table = new PdfPTable(5);
        
        PdfPCell cell = new PdfPCell(new Paragraph("ID DOC FIN MED"));
        table.addCell(cell);
        cell = new PdfPCell(new Paragraph("ID FRANQUIA"));
        table.addCell(cell);
        cell = new PdfPCell(new Paragraph("ID MEDICO"));
        table.addCell(cell);
        cell = new PdfPCell(new Paragraph("VALOR"));
        table.addCell(cell);
        cell = new PdfPCell(new Paragraph("ESTADO"));
        table.addCell(cell);

        // Preencher a tabela com os dados
        for (FinanceiroMedico fm : dados) {
            for (int j = 0; j <= 4; j++) {
                switch(j){
                    case 0:
                        cell = new PdfPCell(new Paragraph(""+fm.getId()));
                        table.addCell(cell);
                        break;
                    case 1:
                        cell = new PdfPCell(new Paragraph(""+fm.getIdFranquia()));
                        table.addCell(cell);
                        break;
                    case 2:
                        cell = new PdfPCell(new Paragraph(""+fm.getIdMedico()));
                        table.addCell(cell);
                        break;
                    case 3:
                        cell = new PdfPCell(new Paragraph(""+(float) fm.getValor()));
                        table.addCell(cell);
                        break;
                    case 4:
                        cell = new PdfPCell(new Paragraph(fm.getEstado() == 1 ? "à pagar" : "pago"));
                        table.addCell(cell);
                        break;
                }
            }
        }
        return table;
    }  
    
    public static PdfPTable createTableFMUnidade(int id) throws SQLException {
        List<FinanceiroMedico> dados = FinanceiroMedicoController.listarFinanceiroMedico();
    	// a table with three columns
        PdfPTable table = new PdfPTable(5);
        
        PdfPCell cell = new PdfPCell(new Paragraph("ID DOC FIN MED"));
        table.addCell(cell);
        cell = new PdfPCell(new Paragraph("ID UNIDADE FRANQUIA"));
        table.addCell(cell);
        cell = new PdfPCell(new Paragraph("ID MEDICO"));
        table.addCell(cell);
        cell = new PdfPCell(new Paragraph("VALOR"));
        table.addCell(cell);
        cell = new PdfPCell(new Paragraph("ESTADO"));
        table.addCell(cell);

        // Preencher a tabela com os dados
        for (FinanceiroMedico fm : dados) {
            if(fm.getIdUnidade() == id) {
                for (int j = 0; j <= 4; j++) {
                    switch(j){
                        case 0:
                            cell = new PdfPCell(new Paragraph(""+ fm.getId()));
                            table.addCell(cell);
                            break;
                        case 1:
                            cell = new PdfPCell(new Paragraph(""+fm.getIdUnidade()));
                            table.addCell(cell);
                            break;
                        case 2:
                            cell = new PdfPCell(new Paragraph(""+fm.getIdMedico()));
                            table.addCell(cell);
                            break;
                        case 3:
                            cell = new PdfPCell(new Paragraph(""+(float) fm.getValor()));
                            table.addCell(cell);
                            break;
                        case 4:
                            cell = new PdfPCell(new Paragraph(fm.getEstado() == 1 ? "à pagar" : "pago"));
                            table.addCell(cell);
                            break;
                    }
                }
            }
        }
        return table;
    }  
    
    public static PdfPTable createTableConsulta(int id) throws SQLException {
        List<Consulta> consultas = ConsultaController.listarConsultas();
        
    	// a table with three columns
        PdfPTable table = new PdfPTable(6);
        
        PdfPCell cell = new PdfPCell(new Paragraph("ID CONSULTA"));
        table.addCell(cell);
        cell = new PdfPCell(new Paragraph("ID UNIDADE FRANQUIA"));
        table.addCell(cell);
        cell = new PdfPCell(new Paragraph("ID MEDICO"));
        table.addCell(cell);
        cell = new PdfPCell(new Paragraph("ID PACIENTE"));
        table.addCell(cell);
        cell = new PdfPCell(new Paragraph("VALOR"));
        table.addCell(cell);
        cell = new PdfPCell(new Paragraph("ESTADO"));
        table.addCell(cell);

        // Preencher a tabela com os dados
        for (Consulta c : consultas) {
            if(c.getIdPessoa() == id){
                for (int j = 0; j <= 5; j++) {
                    switch(j){
                        case 0:
                            cell = new PdfPCell(new Paragraph("" + c.getId()));
                            table.addCell(cell);
                            break;
                        case 1:
                            cell = new PdfPCell(new Paragraph("" + c.getUnidade()));
                            table.addCell(cell);
                            break;
                        case 2:
                            cell = new PdfPCell(new Paragraph("" + c.getIdMedico()));
                            table.addCell(cell);
                            break;
                        case 3:
                            cell = new PdfPCell(new Paragraph("" + c.getIdPessoa()));
                            table.addCell(cell);
                            break;
                        case 4:
                            cell = new PdfPCell(new Paragraph("" + (float) c.getValor()));
                            table.addCell(cell);
                            break;
                        case 5:
                            String estado = "";
                            switch(c.getEstado()){
                                case 1:
                                    estado = "vazio";
                                    break;
                                case 2:
                                    estado = "agendado";
                                    break;
                                case 3:
                                    estado = "cancelado";
                                    break;
                                case 4:
                                    estado = "realizado";
                                    break;
                            }
                            cell = new PdfPCell(new Paragraph(estado));
                            table.addCell(cell);
                            break;
                    }
                }
            }
        }
        return table;
    }  
    
    public static PdfPTable createTableProcedimentos(int id) throws SQLException {
        List<Procedimento> procedimentos = ProcedimentoController.listarProcedimentos();
        
        PdfPTable table = new PdfPTable(6);
        
        PdfPCell cell = new PdfPCell(new Paragraph("ID PROCEDIMENTO"));
        table.addCell(cell);
        cell = new PdfPCell(new Paragraph("NOME"));
        table.addCell(cell);
        cell = new PdfPCell(new Paragraph("LAUDO"));
        table.addCell(cell);
        cell = new PdfPCell(new Paragraph("ID MEDICO"));
        table.addCell(cell);
        cell = new PdfPCell(new Paragraph("VALOR"));
        table.addCell(cell);
        cell = new PdfPCell(new Paragraph("ESTADO"));
        table.addCell(cell);

        // Preencher a tabela com os dados
        for (Procedimento p : procedimentos) {
            if(p.getIdMedico() == id){
                for (int j = 0; j <= 5; j++) {
                    switch(j){
                        case 0:
                            cell = new PdfPCell(new Paragraph("" + p.getIdProcedimento()));
                            table.addCell(cell);
                            break;
                        case 1:
                            cell = new PdfPCell(new Paragraph("" + p.getNome()));
                            table.addCell(cell);
                            break;
                        case 2:
                            cell = new PdfPCell(new Paragraph("" + p.getIdMedico()));
                            table.addCell(cell);
                            break;
                        case 3:
                            cell = new PdfPCell(new Paragraph("" + p.getIdMedico()));
                            table.addCell(cell);
                            break;
                        case 4:
                            cell = new PdfPCell(new Paragraph("" + (float) p.getValor()));
                            table.addCell(cell);
                            break;
                        case 5:
                            String estado = "";
                            switch(p.getEstado()){
                                case 1:
                                    estado = "vazio";
                                    break;
                                case 2:
                                    estado = "agendado";
                                    break;
                                case 3:
                                    estado = "cancelado";
                                    break;
                                case 4:
                                    estado = "realizado";
                                    break;
                            }
                            cell = new PdfPCell(new Paragraph(estado));
                            table.addCell(cell);
                            break;
                    }
                }
            }
        }
        return table;
    }  
    
    public static PdfPTable createTablePagamentoMedico(int id) throws SQLException {
        List<FinanceiroMedico> fin = FinanceiroMedicoController.listarFinanceiroMedico();

        PdfPTable table = new PdfPTable(5);
        
        PdfPCell cell = new PdfPCell(new Paragraph("ID Medico"));
        table.addCell(cell);
        cell = new PdfPCell(new Paragraph("ID FRANQUIA"));
        table.addCell(cell);
        cell = new PdfPCell(new Paragraph("ID UNIDADE"));
        table.addCell(cell);
        cell = new PdfPCell(new Paragraph("VALOR"));
        table.addCell(cell);
        cell = new PdfPCell(new Paragraph("ESTADO"));
        table.addCell(cell);

        // Preencher a tabela com os dados
        for (FinanceiroMedico fm : fin) {
            if(fm.getIdMedico() == id && fm.getEstado() == 2){
                for (int j = 0; j <= 5; j++) {
                    switch(j){
                        case 0:
                            cell = new PdfPCell(new Paragraph("" + fm.getIdMedico()));
                            table.addCell(cell);
                            break;
                        case 1:
                            cell = new PdfPCell(new Paragraph("" + fm.getIdFranquia()));
                            table.addCell(cell);
                            break;
                        case 2:
                            cell = new PdfPCell(new Paragraph("" + fm.getIdUnidade()));
                            table.addCell(cell);
                            break;
                        case 3:
                            cell = new PdfPCell(new Paragraph("" + fm.getValor()));
                            table.addCell(cell);
                            break;
                        case 4:
                            cell = new PdfPCell(new Paragraph("pago"));
                            table.addCell(cell);
                            break;
                    }
                }
            }
        }
        return table;
    }  
     
}
