package com.probs;

import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * Created by msoliman on 11/5/17.
 * Given a binary search tree, find the K smallest number in BST in case of found, otherwise return -1
 */

class BST {
    Node root;
    public BST(){
        root = null;
    }

    public void add(int n){
        if(root == null){
            root =new Node(n);
            return;
        }
        add(root, n);
    }

    void add(Node node, int n){
        if(node == null)
            return;

        if(n < node.val){
            if(node.left == null)
                node.left = new Node(n);
            else
                add(node.left, n);
        }
        else{
            if(node.right == null)
                node.right = new Node(n);
            else
                add(node.right, n);
        }

    }
}

class Node {
    Node left;
    Node right;
    int val;
    public Node(int n){
        val = n;
        left = right = null;
    }


}


public class p_29_FindKSmallestNumberInBST {

    public static void main(String[] args){

        BST bst = new BST();
        bst.add(10);
        bst.add(5);
        bst.add(6);
        bst.add(11);

        System.out.println(findSmallest(bst.root, 2));
        System.out.println(findSmallest1(bst.root, 2));
        System.out.println(findSmallest2(bst.root, 2));
        System.out.println(findSmallest3(bst.root, 2));


    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Non-Recursive Algorithm
    //Same concept however we used here Stack and HashSet to preserve visited nodes
    //O(n), space: O(n) for stack, also  O(n) space more for HashSet
    //(worst especially in case of all nodes were stored to the left side or right side - e.g. 9 8 7 6 5 4 3 2 1
    static int findSmallest(Node node, int n) {
        //we need stack as auxiliary space
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        HashSet<Node> visited = new HashSet<>();


        while(!stack.isEmpty()){

            //notice this PEEK
            Node current = stack.peek();

            if(current.left != null && !visited.contains(current.left)){
                stack.push(current.left);
                continue;
            }

            //visit node
            visited.add(stack.pop());
            if(--n  == 0){
                return current.val;
            }

            if(current.right != null)
                stack.push(current.right);
        }

        //exhausted in search and n was greater than total size of the tree
        return  -1;

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Non-Recursive Algorithm
    //Used here stack only
    //O(n), Space: O(n) for stack (worst especially in case of all nodes were stored to the left side or right side - e.g. 9 8 7 6 5 4 3 2 1
    static int findSmallest1(Node node, int n){
        Stack<Node> stack = new Stack<>();

        Node current = node;

        boolean done = false;

        while(!done){

            if(current != null) {
                //current has value, push it to the stack and go left
                stack.push(current);
                current = current.left;
            }else{
                //we finished left side, let's pick (pop) the least left node, visit it and check if there is a right node to do the same actions with it
                if(!stack.isEmpty()){
                    //visit node here
                    current = stack.pop();

                    if(--n == 0)
                        return current.val;
                    //add right side here
                    current = current.right;

                }else{
                    done = true;
                }
            }



        }

        //all tree traversed without reaching n elements
        //n is greater than total size of tree
        return -1;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Recursive Algorithm
    //this is in-order traversal, each visit I should increment "visited" until we reach "n"
    //O(n)
    static int visited = 0;

    static int findSmallest2(Node node, int n){

        if(node.left != null)
            return findSmallest2(node.left, n);

        //visit node
        //node.val
        if(++visited == n)
            return node.val;
        //else continue we don't need any further processing as we care about only number at n position


        if(node.right != null)
            return findSmallest2(node.right, n);


        //number specified (n) is greater than size of tree (-1)
        return -1;

    }

    //O(n logk) where k is the size of the heap
    //Only shown as possible solution.
    static  int findSmallest3(Node node, int n){
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(Collections.reverseOrder());

        findSmallest3(node, n, minHeap);

        //make sure we have a heap of the same size as n
        if(minHeap.size() < n)
            return  -1;

        return  minHeap.poll();
    }

    static void findSmallest3(Node node, int n, PriorityQueue<Integer> minHeap){
        if(node == null)
            return;

        //this is inorder traversal

        //visit all left side
        if(node.left != null)
            findSmallest3(node.left, n, minHeap);

        //node
        if(minHeap.size() < n )
            minHeap.add(node.val);
        else
            return;


        //visit all right side
        if(node.right != null)
            findSmallest3(node.right, n, minHeap);




    }

}
