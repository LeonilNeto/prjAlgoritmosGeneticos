
package controller;

import model.Livro;
/** Classe responsável por ordenar todos os elementos do array
 * @author Leonil
 */
public class HeapSort {
    
    private static int N;
    
    /**
     * @param arr  - array para ordenar
     */
    
    public static void sort(Livro arr[][]) {
        heapify(arr);
        for(int i = N; i > 0; i--) {
            swap(arr, 0, i);
            N = N -1;
            maxheap(arr, 0);
        }        
    }
    
    private static void heapify(Livro arr[][]) {
        N = arr.length-1;//ex: 100 recebe o tamanho do array - 1
        for(int i = N/2; i >= 0; i--)//Ex: i = (99/2 ou 44.5); i<=0; i--
            maxheap(arr, i);
    }
    
    private static void maxheap(Livro arr[][], int i) {
        int left = 2*i;//ex 99
        int right = 2*i + 1;//99+1 = 100
        
        int max = i;//44.5
        //ex 99 <= 100 && 200 < 77
        if(left <= N && arr[left][AlgoritmoGenetico.genes].getEmprestimos() 
                < arr[i][AlgoritmoGenetico.genes].getEmprestimos())
            max = left;
        //100 <= 100 && 100 < 77
        if(right <= N && arr[right][AlgoritmoGenetico.genes].getEmprestimos() 
                < arr[max][AlgoritmoGenetico.genes].getEmprestimos())
            max = right;
        
        if(max != i) {
            swap(arr, i, max);
            
            maxheap(arr, max);
        }
    }
    
    //Troca as posições dos elementos
    private static void swap(Livro arr[][], int i, int j) {
        for(int aux = 0; aux <= AlgoritmoGenetico.genes; aux++) {
            Livro mudar = arr[i][aux];
            arr[i][aux] = arr[j][aux];
            arr[j][aux] = mudar;
        }
    }    
    
}
