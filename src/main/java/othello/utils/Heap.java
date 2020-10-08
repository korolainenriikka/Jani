/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package othello.utils;

/**
 * Min/max heap data structure for int[2][2] arrays; comparator is array value
 * in index 1,0. For progressive deepening.
 *
 * Max amount of nodes will be small throughout the game, so any store-and-order
 * data structure would suffice. Heap implemented for the sake of the sport :)
 *
 * @author riikoro
 */
public class Heap {

    /**
     * array of form int[][] move entries
     */
    private int[][][] heap;
    /**
     * true = max heap
     */
    private boolean maximize;
    /**
     * Index of first free leaf of heap tree
     */
    private int firstFreeNode;

    /**
     * Create new heap comparing with [1][0] values.
     * 
     * @param maximize true = max heap
     */
    public Heap(boolean maximize) {
        // amount of moves in heap is always less than 30
        this.heap = new int[30][2][2];
        this.maximize = maximize;
        this.firstFreeNode = 1;
    }

    private int parent(int pos) {
        return pos / 2;
    }

    private int leftChild(int pos) {
        return (2 * pos);
    }

    private int rightChild(int pos) {
        return (2 * pos) + 1;
    }

    /**
     * Add a node to the heap and reorder heap nodes.
     *
     * @param nodeToAdd node to be added to the heap
     */
    public void add(int[][] nodeToAdd) {
        heap[firstFreeNode] = nodeToAdd;
        firstFreeNode++;

        //upheapify
        int indexToCheck = firstFreeNode - 1;
        while (indexToCheck != 1) {
            if (!comparesHigher(parent(indexToCheck), indexToCheck)) {
                int[][] parent = heap[parent(indexToCheck)];
                heap[parent(indexToCheck)] = heap[indexToCheck];
                heap[indexToCheck] = parent;
                indexToCheck = parent(indexToCheck);
            } else {
                break;
            }
        }
    }

    private boolean comparesHigher(int n1, int n2) {
        // if one's null the other compares higher
        if (heap[n1] == null) {
            return false;
        }

        if (heap[n2] == null) {
            return true;
        }

        // min heap higher compare: n1 < n2
        // max heap: n1 > n2
        // same value always returns false
        if (maximize) {
            return compare(n1, n2) == 1;
        } else {
            return compare(n1, n2) == -1;
        }
    }

    private int compare(int n1, int n2) {
        // 1: n1 > n2, 0: n1 = n2 -1: n1 < n2
        if (heap[n1][1][0] > heap[n2][1][0]) {
            return 1;
        } else if (heap[n1][1][0] < heap[n2][1][0]) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * Remove and return root node (min/max node).
     *
     * @return min/max node
     */
    public int[][] poll() {
        if (firstFreeNode == 1) {
            return null;
        }
        int[][] nodeToRemove = heap[1];

        //move last node to first
        heap[1] = heap[firstFreeNode - 1];
        heap[firstFreeNode - 1] = null;
        firstFreeNode--;

        //downheapify
        int indexToCheck = 1;
        while (leftChild(indexToCheck) < firstFreeNode || rightChild(indexToCheck) < firstFreeNode) {

            if (comparesHigher(leftChild(indexToCheck), rightChild(indexToCheck))
                    && comparesHigher(leftChild(indexToCheck), indexToCheck)) {
                // swap node checked with left child
                int[][] toCheckNode = heap[indexToCheck];
                heap[indexToCheck] = heap[leftChild(indexToCheck)];
                heap[leftChild(indexToCheck)] = toCheckNode;
                indexToCheck = leftChild(indexToCheck);

            } else if (comparesHigher(rightChild(indexToCheck), leftChild(indexToCheck))
                    && comparesHigher(rightChild(indexToCheck), indexToCheck)) {
                // swap node checked with right child
                int[][] toCheckNode = heap[indexToCheck];
                heap[indexToCheck] = heap[rightChild(indexToCheck)];
                heap[rightChild(indexToCheck)] = toCheckNode;
                indexToCheck = rightChild(indexToCheck);

            } else {
                break;
            }
        }

        return nodeToRemove;
    }

    /**
     * Return min/max node without modifying the heap.
     *
     * @return node in root of heap
     */
    public int[][] peek() {
        if (firstFreeNode == 1) {
            return null;
        }

        int[][] copyOfRootNode
                = new int[][]{{heap[1][0][0], heap[1][0][1]}, {heap[1][1][0], heap[1][1][1]}};
        return copyOfRootNode;
    }

    /**
     * Check if heap is currently empty.
     *
     * @return true if empty
     */
    public boolean isEmpty() {
        return firstFreeNode == 1;
    }
}
