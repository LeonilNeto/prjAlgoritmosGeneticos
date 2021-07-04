package controller;

import java.util.ArrayList;
import java.util.Random;
import model.Livro;
/**Classes com útildades para o Algoritmo Genético
 * @author Leonil
 */
public class Uteis {
    /** Variável stática r Random - randomizar probabilidade */
    static Random r = new Random();
    
    /** Método responsável por exibir os indivíduos no terminal
    * @param vetor Livro[][] - recebe a população de pais ou filhos
    */
    public static void exibirPop(Livro vetor[][]) {
        for(int lin = 0; lin < AlgoritmoGenetico.populacao; lin++) {
            for(int col = 0; col < AlgoritmoGenetico.genes; col++) {
                System.out.printf(vetor[lin][col].getIdLivro()+" ");
                if(col == AlgoritmoGenetico.genes-1) {
                    System.out.printf(vetor[lin][AlgoritmoGenetico.genes].getEmprestimos()+" ");
                }
            }
            System.out.println();
        }
    }  
    
    /** Método para retorno de uma porcentagem inteira
     * @return probabilidade int
     */    
    public static int gerarProbabilidade() {
        //System.out.println("Porcentagem"+(r.nextInt(100)+1));
        return (r.nextInt(10)+1)*10;
    }
    
    /*public static int roulletteSelection(Livro vetor[][], int popFitness) {
        int parent = 0;
        int randNum = r.nextInt(popFitness)+1;
        int rollingSum = 0;
        for(int i = 0; i < populacao; i++) {
            rollingSum += vetor[i][AlgoritmoGenetico.genes].getEmprestimos();
            if(rollingSum >= randNum) {
                parent = i;
                break;
            }
            System.out.println(parent);
        }
        return parent;
    }*/
    
    public static int metodoRoleta(Livro vetor[][]) {
        int fitnessRoleta = 0;
        int aptidaofitness = 0;
        int soma = 0, indice = 0;
        
        for(int it = 0; it < AlgoritmoGenetico.populacao; it++) {
            fitnessRoleta += vetor[it][AlgoritmoGenetico.genes].getEmprestimos();                              
        }
        
        aptidaofitness = r.nextInt(fitnessRoleta)+1;
        
        for(int aux = 0; aux < AlgoritmoGenetico.populacao; aux++){
            soma = soma + vetor[aux][AlgoritmoGenetico.genes].getEmprestimos();
            if(soma <= aptidaofitness) {
                indice = aux;               
            }                                
        }
        
        return indice;     
    }
    
    public static int metodoTorneio(Livro vetor[][]) {
        int numValores = ((AlgoritmoGenetico.populacao * AlgoritmoGenetico.k)/100);
        int competidor[][] = new int[numValores][2];
        int auxiliarTorneio = 0, ganhador = 0;
        
         do {
            int aux = r.nextInt(AlgoritmoGenetico.valMax);
            competidor[auxiliarTorneio][0] = vetor[aux][AlgoritmoGenetico.genes].getEmprestimos();
            competidor[auxiliarTorneio][1] = aux;
            auxiliarTorneio++;
        } while(auxiliarTorneio < numValores);

        for(int i = 0; i < numValores-1; i++){
            if(competidor[i][1] > competidor[i+1][1]) {
                if(competidor[i][1] > ganhador) {
                    ganhador = competidor[i][1];
                }
            } else {
                if(competidor[i+1][1] > ganhador) {
                    ganhador = competidor[i+1][1];
                }
            }
        }        
        return ganhador;
    }
    
    /**Método para retorno de um vetor de Livro após efetuado a Mutação
     * @param vetor Livro[] - Indivíduos que vão passar pela Mutação
     * @return array Livro[] - indivíduos que passaram pela mutação
     */
    /*public static Livro[] inversionMutation(Livro vetor[]) {
        Livro[] array = vetor.clone();
        int l = AlgoritmoGenetico.genes;
        
        //for(int k = 0; k < 10; k++) {
            int r1 = r.nextInt(l);
            int r2 = r.nextInt(l);
            
            while(r1 >= r2) {
                r1 = r.nextInt(l);
                r2 = r.nextInt(l);
            }
            int mid = r1 +((r2+1) - r1)/2;
            int endCount = r2;
            for(int i = r1; i < mid; i++) {
                Livro temp = array[i];
                array[i] = array[endCount];
                array[endCount] = temp;
                endCount--;
            }
        //}        
        return array;
    }*/
    
    public static Livro[] orderMutation(Livro vetor[]) {
        Livro[] array = vetor.clone();
        int l = AlgoritmoGenetico.genes;
    
        int r1 = r.nextInt(l);
        int r2 = r.nextInt(l);

        while(r1 >= r2 || (r1 == 0 && r2 == (l-1))) {
            r1 = r.nextInt(l);
            r2 = r.nextInt(l);
        }          
        int endCount = r2;
        ArrayList<Livro> ordem = new ArrayList<>();
        for(int i = r1; i < endCount; i++) {
            ordem.add(array[i]);
        }
            //Livro temp = array[i];
            //array[i] = array[endCount];
            //array[endCount] = temp;
            //endCount--;
        int aux = 1;    
        for(int i = r1; i < endCount; i++) {
            array[i] = ordem.get(ordem.size()-aux);
            aux++;
        }       
        return array;
    }
    
    /*public static Livro[] orderMutationOnePoint(Livro vetor[]) {
        Livro[] array = vetor.clone();
        int l = AlgoritmoGenetico.genes;
        int r1 = r.nextInt(l);
        //System.out.println("Valor de r1 "+r1);
        if(r1 <= (l/2)) {
            int indice = (l-1);
            for(int i = r1; i < l; i++) {
                array[i] = vetor[indice];
                indice--;
                //System.out.println("i++ "+i);
            }        
        } else {
            int indice = 0;
            for(int i = r1; i > 0; i--) {
                array[i] = vetor[indice];
                indice++;
                //System.out.println("i-- "+i);
            }
        }
        return array;
    }*/
}
