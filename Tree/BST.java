import java.util.*;


public class BST<T extends Comparable<T>> {
    Node<T> root;
    
class Node<T extends Comparable<T>>{
    T data;
    Node<T> left;
    Node<T> right;
    
    Node(T data, Node<T> left, Node<T> right){
        this.data = data;
        left = left;
        right = right;
    }
}

    
    public BST(T data) {
        root = new Node<T>(data,null,null);
    }
    
    public void add(T data) {
        Node<T> node = new Node<T>(data,null,null);
        insertNode(root,node);
    }
         
    private Node<T> insertNode(Node<T> root, Node<T> node) {
        
        if (root == null) {
            root = node;
            //System.out.println(root.data);
        } else if ((root.data).compareTo(node.data) < 0){
                root.right = insertNode(root.right, node);
        } else if ((root.data).compareTo(node.data) > 0){
                root.left = insertNode(root.left, node);
        } 
        return root;
    }
            
    public static void main(String[] args) {
        BST<Integer> tree = new BST<Integer>(20);
        tree.add(26);
        tree.add(36);
        tree.add(38);
        tree.add(6);
        tree.add(46);
        //tree.add(30);
        tree.inorderT();
    }

     void inorderT()
    {
        inorder(root);
    }
    
    void inorder(Node<T> root)
    {
        if(root!=null)
        {
            inorder(root.left);
            System.out.println(root.data);
            inorder(root.right);
        }
    }    

}
