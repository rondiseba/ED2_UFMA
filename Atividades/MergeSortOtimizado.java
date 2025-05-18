//UFMA - CIENCIAS DA COMPUTAÇÃO
//ESTRUTURA DE DADOS 2 - PROF. JOÃO DALLYSON
//RONDINELI SEBA SALOMAO 20200022063

import java.util.ArrayList;
import java.util.List;

public class MergeSortOtimizado {

    // Método principal que organiza a ordenação
    public static <T extends Comparable<T>> void ordenarMergeSort(T[] vetor) {
        if (vetor.length <= 1) return; // Vetor já está ordenado se tiver 1 ou nenhum elemento
        
        if (estaOrdenado(vetor)) return; // Verifica se o vetor já está ordenado

        ordenarMergeSortRecursivo(vetor, 0, vetor.length - 1);
    }

    // Método recursivo do MergeSort com otimizações
    private static <T extends Comparable<T>> void ordenarMergeSortRecursivo(T[] vetor, int inicio, int fim) {
        if (fim - inicio <= 15) { // Usa InsertionSort para subvetores pequenos
            ordenarPorInsercao(vetor, inicio, fim);
            return;
        }

        int meio = inicio + (fim - inicio) / 2;

        ordenarMergeSortRecursivo(vetor, inicio, meio);
        ordenarMergeSortRecursivo(vetor, meio + 1, fim);

        // Evita a fusão se a parte já estiver ordenada
        if (vetor[meio].compareTo(vetor[meio + 1]) <= 0) return;

        mesclar(vetor, inicio, meio, fim);
    }

    // Método para fundir duas metades de um vetor
    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> void mesclar(T[] vetor, int inicio, int meio, int fim) {
        T[] auxiliar = (T[]) new Comparable[fim - inicio + 1];
        int i = inicio, j = meio + 1, k = 0;

        while (i <= meio && j <= fim) {
            if (vetor[i].compareTo(vetor[j]) <= 0) {
                auxiliar[k++] = vetor[i++];
            } else {
                auxiliar[k++] = vetor[j++];
            }
        }

        while (i <= meio) {
            auxiliar[k++] = vetor[i++];
        }

        while (j <= fim) {
            auxiliar[k++] = vetor[j++];
        }

        System.arraycopy(auxiliar, 0, vetor, inicio, auxiliar.length);
    }

    // Implementação do InsertionSort
    private static <T extends Comparable<T>> void ordenarPorInsercao(T[] vetor, int inicio, int fim) {
        for (int i = inicio + 1; i <= fim; i++) {
            T chave = vetor[i];
            int j = i - 1;

            while (j >= inicio && vetor[j].compareTo(chave) > 0) {
                vetor[j + 1] = vetor[j];
                j--;
            }

            vetor[j + 1] = chave;
        }
    }

    // Verifica se o vetor está ordenado
    private static <T extends Comparable<T>> boolean estaOrdenado(T[] vetor) {
        for (int i = 0; i < vetor.length - 1; i++) {
            if (vetor[i].compareTo(vetor[i + 1]) > 0) {
                return false; // Não está ordenado
            }
        }
        return true; // Está ordenado
    }

    // Método para imprimir o vetor
    public static <T> void imprimirVetor(T[] vetor) {
        for (T elem : vetor) {
            System.out.print(elem + " ");
        }
        System.out.println();
    }

    // Método principal para testar o algoritmo com um vetor exemplo
    public static void main(String[] args) {
        Integer[] vetor = {35, 33, 42, 10, 14, 19, 27, 44, 26, 31, 2, 5, 16, 21, 30, 18, 12, 1, 9, 11};
        ordenarMergeSort(vetor);
        imprimirVetor(vetor);
    }
}
