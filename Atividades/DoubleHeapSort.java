//UFMA - CIENCIAS DA COMPUTAÇÃO
//ESTRUTURA DE DADOS 2 - PROF. JOÃO DALLYSON
//RONDINELI SEBA SALOMAO 20200022063

import java.util.ArrayList;
import java.util.List;

// Classe que implementa a estrutura de um Heap Mínimo.
class HeapMinimo<T extends Comparable<T>> {
    private List<T> elementos = new ArrayList<>();

    // Adiciona um novo elemento no heap e o reordena para manter as propriedades do heap mínimo.
    public void adicionar(T valor) {
        elementos.add(valor);
        int i = elementos.size() - 1;
        while (i > 0) {
            int pai = (i - 1) / 2;
            if (elementos.get(i).compareTo(elementos.get(pai)) >= 0) {
                break;
            }
            T temp = elementos.get(i);
            elementos.set(i, elementos.get(pai));
            elementos.set(pai, temp);
            i = pai;
        }
    }

    // Remove e retorna o elemento raiz do heap, reordenando-o para manter as propriedades do heap mínimo.
    public T remover() {
        if (elementos.isEmpty()) {
            return null;
        }
        T raiz = elementos.get(0);
        elementos.set(0, elementos.remove(elementos.size() - 1));
        int i = 0;
        while (true) {
            int filhoEsquerdo = 2 * i + 1;
            int filhoDireito = 2 * i + 2;
            if (filhoEsquerdo >= elementos.size()) break;
            int filhoMenor = (filhoDireito >= elementos.size() || elementos.get(filhoEsquerdo).compareTo(elementos.get(filhoDireito)) < 0) ? filhoEsquerdo : filhoDireito;
            if (elementos.get(i).compareTo(elementos.get(filhoMenor)) <= 0) break;
            T temp = elementos.get(i);
            elementos.set(i, elementos.get(filhoMenor));
            elementos.set(filhoMenor, temp);
            i = filhoMenor;
        }
        return raiz;
    }
}

// Classe que implementa a estrutura de um Heap Máximo.
class HeapMaximo<T extends Comparable<T>> {
    private List<T> elementos = new ArrayList<>();

    // Adiciona um novo elemento no heap e o reordena para manter as propriedades do heap máximo.
    public void adicionar(T valor) {
        elementos.add(valor);
        int i = elementos.size() - 1;
        while (i > 0) {
            int pai = (i - 1) / 2;
            if (elementos.get(i).compareTo(elementos.get(pai)) <= 0) {
                break;
            }
            T temp = elementos.get(i);
            elementos.set(i, elementos.get(pai));
            elementos.set(pai, temp);
            i = pai;
        }
    }

    // Remove e retorna o elemento raiz do heap, reordenando-o para manter as propriedades do heap máximo.
    public T remover() {
        if (elementos.isEmpty()) {
            return null;
        }
        T raiz = elementos.get(0);
        elementos.set(0, elementos.remove(elementos.size() - 1));
        int i = 0;
        while (true) {
            int filhoEsquerdo = 2 * i + 1;
            int filhoDireito = 2 * i + 2;
            if (filhoEsquerdo >= elementos.size()) break;
            int filhoMaior = (filhoDireito >= elementos.size() || elementos.get(filhoEsquerdo).compareTo(elementos.get(filhoDireito)) > 0) ? filhoEsquerdo : filhoDireito;
            if (elementos.get(i).compareTo(elementos.get(filhoMaior)) >= 0) break;
            T temp = elementos.get(i);
            elementos.set(i, elementos.get(filhoMaior));
            elementos.set(filhoMaior, temp);
            i = filhoMaior;
        }
        return raiz;
    }
}

// Classe principal que implementa o DoubleHeapSort usando dois heaps.
public class DoubleHeapSort {
    // Método que ordena um vetor usando dois heaps, um mínimo e um máximo.
    public static void ordenar(Integer[] vetor) {
        HeapMinimo<Integer> heapMinimo = new HeapMinimo<>();
        HeapMaximo<Integer> heapMaximo = new HeapMaximo<>();
        for (int valor : vetor) {
            heapMinimo.adicionar(valor);
            heapMaximo.adicionar(valor);
        }

        int esquerda = 0, direita = vetor.length - 1;
        while (esquerda <= direita) {
            if (esquerda == direita) {
                vetor[esquerda] = heapMinimo.remover();
            } else {
                vetor[esquerda] = heapMinimo.remover();
                vetor[direita] = heapMaximo.remover();
            }
            esquerda++;
            direita--;
        }
    }

    // Método principal que executa o ordenador.
    public static void main(String[] args) {
        Integer[] vetor = {10, 2, 8, 6, 7, 4, 3, 5, 9, 1, 0, 11, 15, 14, 13, 12, 17, 16, 19, 18};
        ordenar(vetor);
        for (int i : vetor) {
            System.out.print(i + " ");
        }
    }
}
