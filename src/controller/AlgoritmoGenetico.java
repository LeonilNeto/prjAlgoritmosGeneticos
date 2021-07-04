package controller;

import model.CreateCSV;
import model.Livro;
import java.util.ArrayList;
import java.util.Random;
/**
 * @author Leonil
 */
public class AlgoritmoGenetico {
    
    static final int populacao = 100;
    static final int genes = 10;
    //static final int valMax = 10;
    static int valMax = 0;
    static final int valMin = 0;
    static final int geracoes = 50;
    static final int pCrossover = 90;
    static final int pMutacao = 20;
    static final int k = 20;
    static final int penalidade = 30;
    static final int substituicao = 0;
    static final int numsteady = 20;
    static final int numgenerational = 40;
    static final int elistismo = 1;
    
    public static Livro[][] ModuloIa() {
        CreateCSV crv = new CreateCSV();
        Random r = new Random();
        
        int laco_geracao = 0, laco_reproducao = 0;
        ArrayList<Livro> livros = null;
        livros = Conexao.buscar(); //busca todos os livros que foram empréstados
        
        valMax = livros.size();
        //populacao = livros.size();        
        System.out.println("Valor max: "+valMax);
        
        Livro pop_pais[][] = new Livro[populacao][genes+1];
        Livro pop_filhos[][] = new Livro[populacao][genes+1];
        
        System.out.println("População inicial aleatória:");
        for(int aux = 0; aux < populacao; aux++) {
            for(int cont = 0; cont < genes; cont++) {
                //if(cont < 10 && aux == 0) {
                   // pop_pais[aux][cont] = livros.get(cont);
                //} else {
                    pop_pais[aux][cont] = livros.get(r.nextInt(valMax));
                //}
                System.out.print(pop_pais[aux][cont].getIdLivro()+" ");
            }
            pop_pais[aux][genes] = new Livro();
            System.out.println();
        }        
        livros.clear();
        livros = null;
        System.out.println();        
        Fitness.calcularFitness(pop_pais);        
        //Uteis.exibirPop(pop_pais);
        crv.openFile();        
         //de 0 até a geração
        do {
            //de o até (população/2)
            do {       
                Livro pais[][] = new Livro[2][genes];  
                int auxPai1 = 0, auxPai2 = 0;    
                
                do {                   
                    //50% de probabilidade para cada operador de seleção     
                    if(r.nextInt(2) == 1) {
                        auxPai1 = Uteis.metodoRoleta(pop_pais);
                        auxPai2 = Uteis.metodoRoleta(pop_pais);                                                
                    } else {
                        auxPai1 = Uteis.metodoTorneio(pop_pais);
                        auxPai2 = Uteis.metodoTorneio(pop_pais);                                             
                    }
                } while(auxPai1 == auxPai2);      
                
                for(int cont = 0; cont < genes; cont++) {
                    pais[0][cont] = pop_pais[auxPai1][cont]; 
                    pais[1][cont] = pop_pais[auxPai2][cont];                   
                }                  
                
                /**Se a geração de probabilidade for maior ou igual a p.Cross 60%*/
                if(pCrossover >= Uteis.gerarProbabilidade()) {                   
                    pais = Crossover.twoPointCrossover(pais);               
                }
              
                //gera_prob = r.nextInt(100)+1;
                /**Se a geração de probabilidade for maior ou igual a 20%*/
                if(pMutacao >= Uteis.gerarProbabilidade()) {
                    //int pontoMutacao = r.nextInt(valMax);
                    Livro a[] = new Livro[genes];
                    Livro b[] = new Livro[genes];
                    for(int i = 0; i < genes; i++) {
                        a[i] = pais[0][i];
                        b[i] = pais[1][i];
                    }
                    pais[0] = Uteis.orderMutation(a); 
                    pais[1] = Uteis.orderMutation(b);                    
                }
                for(int cont = 0; cont < genes; cont++) {
                    pop_filhos[laco_reproducao][cont] = pais[0][cont];
                    pop_filhos[laco_reproducao+(populacao/2)][cont] = pais[1][cont];                    
                }
                pais = null;
                laco_reproducao++;                
            } while(laco_reproducao < (populacao/2));
            laco_reproducao = 0;
            //System.out.println("População: "+laco_geracao);            
            Fitness.calcularFitness(pop_filhos);           
            HeapSort.sort(pop_filhos);            
            //Uteis.exibirPop(pop_filhos);
            //int substituicao = r.nextInt(2);
            if(substituicao == 1) {                
                /*if(elistismo == 1) {
                    System.out.println("generational:");
                    int auxGenerational = 0;
                    for(int aux = populacao-((numgenerational*populacao)/100); 
                            aux < populacao; aux++) {            
                        for(int cont = 0; cont <= genes; cont++) {
                            pop_filhos[aux][cont] = pop_pais[auxGenerational][cont];                     
                        }                
                        auxGenerational++;
                    }
                }
                for(int aux = 0; aux < populacao; aux++) {            
                    for(int cont = 0; cont <= genes; cont++) {
                        pop_pais[aux][cont] = pop_filhos[aux][cont];                     
                    }                
                }*/ 
            } else {
                //System.out.println("Steady:");
                //exibir(pop_pais);
                int auxSteady = 0;
                for(int aux = populacao-((numsteady*populacao)/100); aux < populacao; aux++) {            
                    for(int cont = 0; cont <= genes; cont++) {
                        pop_pais[aux][cont] = pop_filhos[auxSteady][cont];                     
                    }
                    auxSteady++;
                }                  
            }            
            HeapSort.sort(pop_pais);            
            System.out.println("População de Pais");
            Uteis.exibirPop(pop_pais);            
            laco_geracao++;
            crv.addRegistros(laco_geracao, 
                    pop_pais[populacao-1][genes].getEmprestimos(), 
                        (pop_pais[populacao-1][genes].getEmprestimos()+
                            pop_pais[0][genes].getEmprestimos())/2, 
                                    pop_pais[0][genes].getEmprestimos());
        } while(laco_geracao < geracoes);
        crv.closeFile();      
        return pop_pais;
    }   
}