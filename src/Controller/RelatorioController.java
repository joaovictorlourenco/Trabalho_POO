/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.FinanceiroMedico;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
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
                

        document.add(new Paragraph("Relatorio de Financas Franquia"));
        document.add(new Paragraph());

        document.add(createTable(id));
//        for(FinanceiroMedico fm : dados){
//            table.addCell(table);
//            table.addCell("row 1; cell 1");
//        }

        
        document.close();
    }
    
    public void createPdf(String filename)
       throws DocumentException, IOException {

        Document document = new Document();

        PdfWriter.getInstance(document, new FileOutputStream(filename));

        document.open();

        document.add(new Paragraph("Hello World!"));
        document.add(new Paragraph(filename));
        
        document.close();
    }
    
    public static PdfPTable createTable(int id) throws SQLException {
        List<FinanceiroMedico> dados = FinanceiroMedicoController.DadosRelatorioMensal(id);
    	// a table with three columns
        PdfPTable table = new PdfPTable(5);
        
        PdfPCell cel = new PdfPCell(new Paragraph("ID DOC FIN MED"));
        table.addCell(cel);
        cel = new PdfPCell(new Paragraph("ID FRANQUIA"));
        table.addCell(cel);
        cel = new PdfPCell(new Paragraph("ID MEDICO"));
        table.addCell(cel);
        cel = new PdfPCell(new Paragraph("VALOR"));
        table.addCell(cel);
        cel = new PdfPCell(new Paragraph("ESTADO"));
        table.addCell(cel);

        // Preencher a tabela com os dados
        for (FinanceiroMedico fm : dados) {
            for (int j = 0; j <= 4; j++) {
                switch(j){
                    case 0:
                        PdfPCell cell = new PdfPCell(new Paragraph(""+fm.getId()));
                        table.addCell(cell);
                        break;
                    case 1:
                        PdfPCell cell1 = new PdfPCell(new Paragraph(""+fm.getIdFranquia()));
                        table.addCell(cell1);
                        break;
                    case 2:
                        PdfPCell cell2 = new PdfPCell(new Paragraph(""+fm.getIdMedico()));
                        table.addCell(cell2);
                        break;
                    case 3:
                        PdfPCell cell3 = new PdfPCell(new Paragraph(""+(float) fm.getValor()));
                        table.addCell(cell3);
                        break;
                    case 4:
                        PdfPCell cell4 = new PdfPCell(new Paragraph(fm.getEstado() == 1 ? "à pagar" : "pago"));
                        table.addCell(cell4);
                        break;
                }
                
            }
        }
        return table;
    }  
     
}
