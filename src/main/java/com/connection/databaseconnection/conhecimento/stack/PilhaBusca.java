package com.connection.databaseconnection.conhecimento.stack;

public class PilhaBusca <T> {

    private int topo;
    private T[] pilha ;

    public PilhaBusca(int capacidade) {
        this.topo = -1;
        this.pilha = (T[]) new Object[capacidade];
    }

    public boolean isEmpty() {
        return topo == -1;
    }

    public boolean isFull() {
        if(topo < pilha.length ){
            return false;
        } else {
            return true;
        }
    }

    public void push(T value) {
        if(isFull()) {
            System.out.println("Lista cheia!");
        } else {
            pilha[++topo] = value;
        }
    }

    public T pop() {
        if(isEmpty()) {
            System.out.println("Lista vazia!");
            return null;
        } else {

            return pilha[topo--];
        }
    }

    public T peek() {

        if(isEmpty()) {
            return null;
        } else {
            return pilha[topo];
        }
    }

    public void exibe() {
        if (isEmpty()) {
            System.out.println("Lista vazia!");
        } else {
            System.out.println("Elementos da pilha:");
            for (int i = 0; i < topo + 1; i++) {
                System.out.println(pilha[i]);
            }
        }
    }

}
