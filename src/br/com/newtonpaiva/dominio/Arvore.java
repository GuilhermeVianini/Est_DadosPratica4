package br.com.newtonpaiva.dominio;

public class Arvore {
    private No raiz;

    public Arvore() {
        raiz = null;
    }

    public No getRaiz() {
        return raiz;
    }

    public void inserir(int valor) {
        raiz = inserirRecursivo(raiz, valor);
    }

    private No inserirRecursivo(No node, int valor) {
        if (node == null) {
            node = new No(valor);
            return node;
        }

        if (valor < node.valor) {
            node.esquerda = inserirRecursivo(node.esquerda, valor);
        } else if (valor > node.valor) {
            node.direita = inserirRecursivo(node.direita, valor);
        }

        return node;
    }


    public boolean pesquisar(int valor) {
        return pesquisarRecursivo(raiz, valor);
    }

    private boolean pesquisarRecursivo(No node, int valor) {
        if (node == null) {
            return false;
        }

        if (valor == node.valor) {
            return true;
        }

        if (valor < node.valor) {
            return pesquisarRecursivo(node.esquerda, valor);
        }

        return pesquisarRecursivo(node.direita, valor);
    }

    public void imprimir() {
        imprimirRecursivo(raiz, 0);
    }

    private void imprimirRecursivo(No node, int nivel) {
        if (node != null) {
            imprimirRecursivo(node.direita, nivel + 1);
            for (int i = 0; i < nivel; i++) {
                System.out.print("    ");
            }
            System.out.println(node.valor);
            imprimirRecursivo(node.esquerda, nivel + 1);
        }
    }

    public void excluir(int valor) {
        raiz = excluirRecursivo(raiz, valor);
    }

    private No excluirRecursivo(No node, int valor) {
        if (node == null) {
            return node;
        }

        if (valor < node.valor) {
            node.esquerda = excluirRecursivo(node.esquerda, valor);
        } else if (valor > node.valor) {
            node.direita = excluirRecursivo(node.direita, valor);
        } else {
            if (node.esquerda == null) {
                return node.direita;
            } else if (node.direita == null) {
                return node.esquerda;
            }

            node.valor = encontrarMenorValor(node.direita);
            node.direita = excluirRecursivo(node.direita, node.valor);
        }

        return node;
    }

    private int encontrarMenorValor(No node) {
        int menorValor = node.valor;
        while (node.esquerda != null) {
            menorValor = node.esquerda.valor;
            node = node.esquerda;
        }
        return menorValor;
    }
}
