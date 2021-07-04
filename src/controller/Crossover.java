package controller;

import java.util.Random;
import model.Livro;
/**
 * @author Leonil
 */
public class Crossover {
    
    /**
     * Função responsável pelo método de CROSSOVER DE 2 PONTOS
     * @param indi - Livro[][] 
     * @return Livro[][] indi
     */
    public static Livro[][] twoPointCrossover(Livro indi[][]) {
        Random r = new Random();
        int xoverpoint = 0, xoverpoint2 = 0;        
        
        do {
            xoverpoint = r.nextInt(AlgoritmoGenetico.genes);
            xoverpoint2 = r.nextInt(AlgoritmoGenetico.genes);
        } while(xoverpoint == xoverpoint2 || 
            xoverpoint == 0 && xoverpoint2 == (AlgoritmoGenetico.genes-1) ||
            xoverpoint == (AlgoritmoGenetico.genes-1) && xoverpoint2 == 0);
        
        int temp;        
        //swap
        if(xoverpoint > xoverpoint2) {
            temp = xoverpoint;
            xoverpoint = xoverpoint2;
            xoverpoint2 = temp;
        }
        
        Livro troca = new Livro();
        
        for(int i = xoverpoint; i < xoverpoint2; i++) {
            troca = indi[1][i];
            indi[1][i] = indi[0][i];
            indi[0][i] = troca;
        }        
        return indi;
    }               
}
