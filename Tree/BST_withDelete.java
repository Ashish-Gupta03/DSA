import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

class Node<T extends Comparable<T>>{
    T elem;
    Node<T> leftChild;
    Node<T> rightChild;
    
    Node(T elem, Node<T> left, Node<T> right){
        this.elem = elem;
        leftChild = left;
        rightChild = right;
    }
}

public class BST3<T extends Comparable<T>> {
    Node<T> root;
    
    public BST3(T elem) {
        root = new Node<T>(elem,null,null);
    }
    
    public void add(T elem) {
        Node<T> node = new Node<T>(elem,null,null);
        insertNode(root,node);
    }
    
    private Node<T> insertNode(Node<T> subTree, Node<T> newNode) {
        
        if (subTree == null) {
            subTree = newNode;
        } else if ((subTree.elem).compareTo(newNode.elem) < 0){
                subTree.rightChild = insertNode(subTree.rightChild, newNode);
        } else if ((subTree.elem).compareTo(newNode.elem) > 0){
                subTree.leftChild = insertNode(subTree.leftChild, newNode);
        } else {
            throw 
                new IllegalArgumentException("Duplicate Element " + newNode.elem);
        }
        return subTree;
    }
        
    public void delete(T item) {
        this.deleteNode(root,item);
    }
    
    private Node<T> deleteNode(Node<T> subtree,  T item) { 
        if (subtree != null) {
            if ((subtree.elem).compareTo(item) < 0) {
                subtree.rightChild = deleteNode(subtree.rightChild, item);
            } else if ((subtree.elem).compareTo(item) > 0) {
                subtree.leftChild = deleteNode(subtree.leftChild, item);
            } else {
                if ((subtree.leftChild != null) && (subtree.rightChild != null)) {
                    Node<T> node = findLeftmostChild(subtree.rightChild);
                    subtree.elem = node.elem;
                    subtree.rightChild = deleteNode(subtree.rightChild,node.elem);
                } else if (subtree.leftChild != null) {
                    subtree = subtree.leftChild;
                } else {
                    subtree = subtree.rightChild;    
                }
            }
            
        } else{
            throw new IllegalArgumentException("No such element");
        }
        return subtree;
    }
    
    private Node<T> findLeftmostChild(Node<T> subtree){
        assert (subtree != null);
        while (subtree.leftChild != null) {
            subtree = subtree.leftChild;
        }
        return subtree;
    }
    
    public List<T> depthFirstTraversal() {
        List<T> l = new ArrayList<T>();
        Stack<Node<T>> s = new Stack<Node<T>>();
        s.push(root);
        while (!s.isEmpty()){
            Node<T> node = s.pop();
            l.add(node.elem);
            if (node.rightChild != null) {
                s.push(node.rightChild);
            }
            if (node.leftChild != null) {
                s.push(node.leftChild);
            }
        }
        return l;
    }
    
    public List<T> breadthFirstTraversal() {
        List<T> l = new ArrayList<T>();
        Queue<Node<T>> q = new LinkedList<Node<T>>();
        q.add(root);
        while (!q.isEmpty()) {
            Node<T> node = q.poll();
            l.add(node.elem);
            if (node.leftChild != null) {
                q.add(node.leftChild);
            } 
            if (node.rightChild != null) {
                q.add(node.rightChild);
            }
        }
        return l;
    }
    
    public Node<T> findNode(T item) {
        if (item == null) return null;
        Node<T> current = root;
        while ((current.elem).compareTo(item) != 0) {
            if ((current.elem).compareTo(item) > 0) {
                current = current.leftChild;
            } else if ((current.elem).compareTo(item) < 0) {
                current = current.rightChild;
            }
            if (current == null) return null;
        }
        return current;
        
    }    
    
    public static void main(String[] args) {
        BST3<Integer> tree = new BST3<Integer>(20);
        tree.add(30);
        tree.add(10);
        tree.add(15);
        tree.add(24);
        tree.add(36);
        
        List<Integer> l = tree.depthFirstTraversal();
        System.out.println("Depth First Order");
        printTree(l);
        l = tree.breadthFirstTraversal();
        System.out.println("Breadth First Order");
        printTree(l);
        
        tree.delete(30);
        System.out.println("Tree after deleting a node");
        l = tree.depthFirstTraversal();
        printTree(l);
    }
    
    public static <T> void printTree(List<T> l) {
        for(T i: l) {
            System.out.println(i);
        }
    }

}

