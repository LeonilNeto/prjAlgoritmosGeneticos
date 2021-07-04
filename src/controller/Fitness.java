package controller;

import model.Livro;
/**
 * @author Leonil
 */
public class Fitness {
    
    /** 
     * Método responsável pelo cálculo do fitness, valor de aptidão de cada indivíduo
     * @param vetor - Livro[][]
     */
    public static void calcularFitness(Livro vetor[][]) {
        int restricao = 0;
        for(int aux = 0; aux < AlgoritmoGenetico.populacao; aux++) {     
            //vetor[aux][genes].setIdLivro(0);
            int somador = 0;
            for(int cont = 0; cont < AlgoritmoGenetico.genes; cont++) {
                
                somador = somador + (vetor[aux][cont].getEmprestimos()*10);                
                if(repeticaoId(vetor, vetor[aux][cont].getIdLivro(),aux)) {                    
                   restricao = restricao + AlgoritmoGenetico.penalidade;
                }                
                if(repeticaoTitulo(vetor, vetor[aux][cont].getTitulo(), vetor[aux][cont].getAutor(), aux)) {                    
                   restricao = restricao + AlgoritmoGenetico.penalidade;                   
                }
                //Se o livro tiver menos de dez exemplares - ele tem prioridade
                if(vetor[aux][cont].getQuantidade() > 10) {
                    restricao = restricao + AlgoritmoGenetico.penalidade;
                }            
            }
            vetor[aux][AlgoritmoGenetico.genes] = new Livro();
            vetor[aux][AlgoritmoGenetico.genes].setEmprestimos(somador-restricao);
            restricao = 0;
        }
    }          
    
    /** 
     * Função responsável por verificar a ocorrência da repetição de objetos do tipo Livro pelo getIdLivro()
     * @param vetor - Livro[][]
     * @param valor - int
     * @param gene - int
     * @return boolean
     */
    private static boolean repeticaoId(Livro vetor[][], int valor, int gene) {
        int soma = 0;
        for(int col = 0; col < AlgoritmoGenetico.genes; col++) {
            if(valor == vetor[gene][col].getIdLivro()) {
                soma++;                
            }
        }                
        return (soma > 1);
    }
    
    /**
     * Dunção responsável por verificar a ocorrência da repetição de título de livros pelo getTitulo()
     * @param vetor - Livro[][]
     * @param valor1 - String
     * @param valor2 - String
     * @param gene - int
     * @return boolean
     */    
    private static boolean repeticaoTitulo(Livro vetor[][], String valor1, String valor2,int gene) {
        int soma = 0;
        for(int col = 0; col < AlgoritmoGenetico.genes; col++) {
            if(valor1.equalsIgnoreCase(vetor[gene][col].getTitulo()) && valor2.equalsIgnoreCase(vetor[gene][col].getAutor())) {
                soma++;                
            }
        }        
        return (soma > 1);
    }
}
