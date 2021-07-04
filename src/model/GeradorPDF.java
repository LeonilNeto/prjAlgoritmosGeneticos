package model;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * @author Leonil
 */
public class GeradorPDF {
    /**
     * @param livros - Livro[][]
     */
    public static void geraPDF(Livro[][] livros) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new 
                FileOutputStream("C:\\Users\\Leonil\\Desktop\\LIVROS.pdf"));
            document.open();
            Livro[] livro = new Livro[10];
            for(int i = 0; i < 10; i++) {
                livro[i] = livros[0][i];
            }
            //document.addTitle("MELHORES SOLUÇÕES PARA O PROBLEMA COM USO DE ALGORITMOS GENÉTICOS");
            
            document.add(new Paragraph("MELHORES SOLUÇÕES PARA O PROBLEMA COM O USO DE ALGORITMOS GENÉTICOS\n\n"));
            //document.add(new Paragraph("TOMBO | "+" TÍTULO | "+"AUTOR"));            
            //document.addSubject("Solções");                                
            PdfPTable table = new PdfPTable(3);
            table.setWidths(new int[]{15, 40, 45});
            PdfPCell cell;

            cell = new PdfPCell(new Phrase("LIVROS REQUERIDOS"));
            cell.setColspan(3);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("TOMBO"));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("TÍTULO"));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase("AUTOR"));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            
            for(Livro l : livro) {
                table.addCell(Integer.toString(l.getIdLivro()));
                table.addCell(l.getTitulo());
                table.addCell(l.getAutor());                
                //document.add(new Paragraph(l.getIdLivro()+" | "+l.getTitulo()+ " | "+l.getAutor()));  
            }
            document.add(table);
        } catch(DocumentException de) {
            System.err.println(de.getMessage());

        } catch(IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        document.close();
    }
}
