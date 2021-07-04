package model;

import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.FormatterClosedException;
/**
 * @author Leonil
 */
public class CreateCSV {
    /** 
     * Objeto utilizado para gerar saída de texto no arquivo 
     */
     private Formatter output; 
         
    /** 
     * Método permite o usuário abrir o arquivo 
     */
    public void openFile() {
        try {
            output = new Formatter("biblioteca.csv");
        } catch(SecurityException se) {
            System.err.println("Você não tem acesso para escever neste aquivo!"+
                    "\n"+se.getMessage());
            System.exit(1);
        } catch(FileNotFoundException ffe) {
            System.err.println("Erro ao abrir ou criar o arquivo!"+
                    "\n"+ffe.getMessage());
            System.exit(1);
        }           
    }

    /** 
     * Método reponsável por adicionar os registros no arquivo .csv
     * @param geracao int - Posição das gerações
     * @param menor int - Menor fitness da população
     * @param media int - Médio fitness da população
     * @param maior int - Maior fintess da população
     */
    public void addRegistros(int geracao, int menor, int media, int maior) {
        try {
            if(geracao == 1) {
                output.format("%s %s %s %s \n", "GERACAO;", "MENOR;", "MEDIA;", "MAIOR;");
            }
            output.format("%s %s %s %s \n", String.valueOf(geracao)+";",
                    String.valueOf(menor)+";", String.valueOf(media)+";", 
                    String.valueOf(maior)+";");

        } catch(FormatterClosedException fce) {
            System.err.println("Error escrevendo no arquivo!"+"\n"+fce.getMessage());
        } 
    }

    /** 
     * Método responsável fechar o aqruivo .csv 
     */
    public void closeFile() {
        if(output != null) 
            output.close();
    }
}
