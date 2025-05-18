import java.util.ArrayList;
import java.util.List;

public class SelectSortMinMax {

    // Método para ordenar um vetor usando SelectSort modificado
    public static <T extends Comparable<T>> void selectSortMinMax(T[] vetor) {
        int inicio = 0;
        int fim = vetor.length - 1;
        T temp;

        while (inicio < fim) {
            int minIndex = inicio;
            int maxIndex = fim;

            // Iniciar a busca pelos índices do menor e do maior elemento
            for (int i = inicio; i <= fim; i++) {
                if (vetor[i].compareTo(vetor[minIndex]) < 0) {
                    minIndex = i;
                }
                if (vetor[i].compareTo(vetor[maxIndex]) > 0) {
                    maxIndex = i;
                }
            }

            // Trocar o menor elemento encontrado com o elemento na posição de início
            temp = vetor[inicio];
            vetor[inicio] = vetor[minIndex];
            vetor[minIndex] = temp;

            // Se o maior elemento está na posição de início (que acabou de ser trocada),
            // atualizar maxIndex para a posição de onde o menor elemento veio
            if (maxIndex == inicio) {
                maxIndex = minIndex;
            }

            // Trocar o maior elemento encontrado com o elemento na posição de fim
            temp = vetor[fim];
            vetor[fim] = vetor[maxIndex];
            vetor[maxIndex] = temp;

            // Ajustar os limites
            inicio++;
            fim--;
        }
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
        selectSortMinMax(vetor);
        imprimirVetor(vetor);
    }
}
