//UFMA - CIENCIAS DA COMPUTAÇÃO
//ESTRUTURA DE DADOS 2 - PROF. JOÃO DALLYSON
//RONDINELI SEBA SALOMAO 20200022063

import java.util.Arrays;
import java.util.Random;

public class QuickSortMedianaDeTres {

    // Método principal para iniciar o QuickSort com BubbleSort para pequenas partições
    public static <T extends Comparable<T>> void quickSort(T[] vetor, int esq, int dir, int limiteBubbleSort) {
        if (dir <= esq) return;

        if (dir - esq <= limiteBubbleSort) {
            bubbleSort(vetor, esq, dir);
        } else {
            int indicePivo = particionar(vetor, esq, dir);
            quickSort(vetor, esq, indicePivo - 1, limiteBubbleSort);
            quickSort(vetor, indicePivo + 1, dir, limiteBubbleSort);
        }
    }

    // Método para particionar usando a mediana de três como pivô
    private static <T extends Comparable<T>> int particionar(T[] vetor, int esq, int dir) {
        int meio = esq + (dir - esq) / 2;
        int medianaIndice = medianaDeTres(vetor, esq, meio, dir);
        trocar(vetor, medianaIndice, dir);

        T pivo = vetor[dir];
        int i = esq - 1;
        for (int j = esq; j < dir; j++) {
            if (vetor[j].compareTo(pivo) <= 0) {
                i++;
                trocar(vetor, i, j);
            }
        }
        trocar(vetor, i + 1, dir);
        return i + 1;
    }

    // Método para encontrar a mediana entre três posições
    private static <T extends Comparable<T>> int medianaDeTres(T[] vetor, int a, int b, int c) {
        T va = vetor[a];
        T vb = vetor[b];
        T vc = vetor[c];
        if (va.compareTo(vb) > 0) {
            if (vb.compareTo(vc) > 0) {
                return b;
            } else if (va.compareTo(vc) > 0) {
                return c;
            } else {
                return a;
            }
        } else {
            if (va.compareTo(vc) > 0) {
                return a;
            } else if (vb.compareTo(vc) > 0) {
                return c;
            } else {
                return b;
            }
        }
    }

    // Método para trocar elementos no vetor
    private static <T> void trocar(T[] vetor, int i, int j) {
        T temp = vetor[i];
        vetor[i] = vetor[j];
        vetor[j] = temp;
    }

    // Implementação do BubbleSort para subvetores
    private static <T extends Comparable<T>> void bubbleSort(T[] vetor, int esq, int dir) {
        boolean trocado;
        for (int i = esq; i < dir; i++) {
            trocado = false;
            for (int j = esq; j < dir - i + esq; j++) {
                if (vetor[j].compareTo(vetor[j + 1]) > 0) {
                    trocar(vetor, j, j + 1);
                    trocado = true;
                }
            }
            if (!trocado) break;
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        Integer[] vetor = random.ints(100, 0, 100).boxed().toArray(Integer[]::new);

        quickSort(vetor, 0, vetor.length - 1, 10); // Exemplo com L = 10
        System.out.println(Arrays.toString(vetor));
    }
}
