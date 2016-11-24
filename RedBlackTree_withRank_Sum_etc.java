import java.util.Scanner;

 class RedBlackTree {

    private final int RED = 0;
    private final int BLACK = 1;
    static int sum=0;
    private class Node {

        int key = -1, color = BLACK,size=0,lSum=0,rSum=0;
        Node left = nil, right = nil, parent = nil;

        Node(int key) {
            this.key = key;
            this.size=0;
            this.rSum=0;
            this.lSum=0;
        } 
    }

    private final Node nil = new Node(-1); 
    private Node root = nil;

    public void printTree(Node node) {
        if (node == nil) {
            return;
        }
        printTree(node.left);
        System.out.println("Key: "+node.key+((node.color==RED)?" Color: Red ":" Color: Black")+" size "+node.size+" rSum "+node.rSum+" lSum "+node.lSum);
        printTree(node.right);
    }

    public int rank(int key,Node root) 
    {
        if (root == nil) return 0; 
        if      (key > root.key) return rank(key, root.right); 
        else if (key < root.key) return 1 + root.right.size + rank(key, root.left); 
        else              return 1+root.right.size;
    } 

    public Node select(Node x, int i) {
        int r = x.right.size+1; 
        if      (i < r) return select(x.right,  i); 
        else if (i > r) return select(x.left, i-r); 
        else            return x; 
    }

    public int prefixSum(Node x,int i) {
    	int s=0;
    	if(i==x.key)
    		return x.rSum;
    	else
    	{
    		while(x!=nil)
    		{
    			if(i<x.key)
    			{
    				s = s + x.rSum + x.key;
    				x=x.left;
    			}
    			else if(i>x.key)
    				x=x.right;
    			else
    			{
    				s = s + x.rSum;
    				break;
    			}
    		}
    	}
    	return s;
    } 
     
    public Node inorderSuccessor(Node x)
    {
    	if (x.right!=nil)
    		return treeMinimum(x.right);
    	Node y=x.parent;
    	while (y!=nil && x==y.right)
    	{
    		x=y;
    		y=y.parent;
    	}
    	return y;
    }

    private Node findNode(Node findNode, Node node) {
        if (root == nil) {
            return null;
        }

        if (findNode.key < node.key) {
            if (node.left != nil) {
                return findNode(findNode, node.left);
            }
        } else if (findNode.key > node.key) {
            if (node.right != nil) {
                return findNode(findNode, node.right);
            }
        } else if (findNode.key == node.key) {
            return node;
        }
        return null;
    }

    private void insert(Node z) {
        Node y=nil;
		Node x=root;
		int cmp=0;
		while(x!=nil)
		{
			y=x;

			/*Changing size*/
			y.size++;
			/*Changing size*/

			if (z.key<=x.key)
			{
				x.lSum+=z.key;
				x=x.left;				
			}	
			else
			{							
				x.rSum+=z.key;	
				x=x.right;
				//System.out.println("x.rSum "+x.rSum);
			}
		}
		z.parent=y;

		if (y==nil)
		{
			root=z;
			root.color=BLACK;
			root.parent=nil;
		}	
		else
		{
			if(z.key<=y.key)
				y.left=z;
			else y.right=z;
		}
		z.size=1;
		z.left=nil;
		z.right=nil;
		z.color=RED;
		rbInsertFixup(z);
    }

    private void rbInsertFixup(Node z) {
        while (z.parent.color == RED) {
            Node y = nil;
            if (z.parent == z.parent.parent.left) {
                y = z.parent.parent.right;

                if (y != nil && y.color == RED) {
                    z.parent.color = BLACK;
                    y.color = BLACK;
                    z.parent.parent.color = RED;
                    z = z.parent.parent;
                    continue;
                } 
                if (z == z.parent.right) {
                    z = z.parent;
                    rotateLeft(z);
                } 
                z.parent.color = BLACK;
                z.parent.parent.color = RED;
                rotateRight(z.parent.parent);
            } else {
                y = z.parent.parent.left;
                 if (y != nil && y.color == RED) {
                    z.parent.color = BLACK;
                    y.color = BLACK;
                    z.parent.parent.color = RED;
                    z = z.parent.parent;
                    continue;
                }
                if (z == z.parent.left) {
                    z = z.parent;
                    rotateRight(z);
                }
                z.parent.color = BLACK;
                z.parent.parent.color = RED;
                rotateLeft(z.parent.parent);
            }
        }
        root.color = BLACK;
    }

    void rotateLeft(Node x) {
    	Node y=x.right;
    	y.parent=x.parent;
    	
    	 //changing rightsubtrees
        if(y.left != nil)
            x.rSum = y.left.rSum+y.left.lSum+y.left.key;
        else
            x.rSum = 0;

        if(x.left!=nil)
            x.lSum = x.left.lSum+x.left.rSum+x.left.key;
        else
            x.lSum = 0;
        if(y.right!=nil)
            y.rSum = y.right.rSum+y.right.lSum+y.right.key;
        else
            y.rSum=0;
        y.lSum = x.rSum+x.lSum+x.key;
        
        //change complete
        x.right=y.left;
        if(y.left!=nil)
            y.left.parent=x;
        
        if(x.parent==nil)
            root=y;
        else if(x==x.parent.left)
            x.parent.left=y;
        else x.parent.right=y;

        y.left=x;
        x.parent=y;

        /*Changed here*/
        y.size = x.size;
        x.size = x.left.size + x.right.size + 1;
        /*Changed here*/
    }

    void rotateRight(Node y) {
    	Node x=y.left;
    	x.parent=y.parent;
    	
    	 //changing rightsubtrees
        if(x.right != nil)
            y.lSum = x.right.rSum+x.right.lSum+x.right.key;
        else
            y.lSum = 0;

        if(y.right!=nil)
            y.rSum = y.right.lSum+y.right.rSum+y.right.key;
        else
            x.rSum = 0;

        x.rSum = x.rSum+x.lSum+x.key;

        if(x.left!=nil)
        x.lSum = x.left.lSum+x.left.rSum+x.left.key;
        else
            x.lSum = 0;

        y.left=x.right;
        if(x.right!=nil)
            x.right.parent=y;

        if(y.parent==nil)
            root=x;
        else if (y==y.parent.right)
            y.parent.right=x;
        else y.parent.left=x;
        x.right=y;
        y.parent=x;

        /*Changed here*/
        x.size = y.size;
        y.size = y.left.size + y.right.size + 1;
        /*Changed here*/
    }
    
    void deleteTree(){
        root = nil;
    }
    
    void transplant(Node target, Node with){ 
          if(target.parent == nil){
              root = with;
          }else if(target == target.parent.left){
              target.parent.left = with;
          }else
              target.parent.right = with;
          with.parent = target.parent;
    }
    
    boolean delete(Node z){
        if((z = findNode(z, root))==null)return false;
        
        /*Changing size*/
        if (z != root)
        {
			Node tmp = z.parent;
			while (tmp != root) {
				tmp.size--;
				tmp.rSum-=tmp.key;
				tmp = tmp.parent;
			}
			root.size--;
			root.rSum-=z.key;
		}
		/*Changing size*/

        Node x;
        Node y = z;
        int y_original_color = y.color;
        
        if(z.left == nil){
            x = z.right;  
            transplant(z, z.right);  
        }else if(z.right == nil){
            x = z.left;
            transplant(z, z.left); 
        }else{
            y = treeMaximum(z.left);
            y_original_color = y.color;
            x = y.left;
            if(y.parent == z)
                x.parent = y;
            else{
                transplant(y, y.left);
                y.left = z.left;
                y.left.parent = y;

                /*Changing size*/
	            Node tmp = x.parent;
				while (tmp != y) {
					tmp.size--;
					tmp.rSum-=tmp.key;
					tmp = tmp.parent;
				}
				y.size = y.right.size + 1;
				y.rSum = y.right.rSum + y.key;
				/*Changing size*/

            }
            transplant(z, y);
            y.right = z.right;
            y.right.parent = y;
            y.color = z.color; 

            /*Changing size*/
            y.size = y.left.size + y.right.size + 1;
            y.rSum = y.left.rSum + y.right.rSum + y.key;
            /*Changing size*/

        }
        if(y_original_color==BLACK)
            deleteFixup(x);  
        return true;
    }
    
    void deleteFixup(Node x){
        while(x!=root && x.color == BLACK){ 
            if(x == x.parent.left){
                Node w = x.parent.right;
                if(w.color == RED){
                    w.color = BLACK;
                    x.parent.color = RED;
                    rotateLeft(x.parent);
                    w = x.parent.right;
                }
                if(w.left.color == BLACK && w.right.color == BLACK){
                    w.color = RED;
                    x = x.parent;
                    continue;
                }
                else if(w.right.color == BLACK){
                    w.left.color = BLACK;
                    w.color = RED;
                    rotateRight(w);
                    w = x.parent.right;
                }
                if(w.right.color == RED){
                    w.color = x.parent.color;
                    x.parent.color = BLACK;
                    w.right.color = BLACK;
                    rotateLeft(x.parent);
                    x = root;
                }
            }else{
                Node w = x.parent.left;
                if(w.color == RED){
                    w.color = BLACK;
                    x.parent.color = RED;
                    rotateRight(x.parent);
                    w = x.parent.left;
                }
                if(w.right.color == BLACK && w.left.color == BLACK){
                    w.color = RED;
                    x = x.parent;
                    continue;
                }
                else if(w.left.color == BLACK){
                    w.right.color = BLACK;
                    w.color = RED;
                    rotateLeft(w);
                    w = x.parent.left;
                }
                if(w.left.color == RED){
                    w.color = x.parent.color;
                    x.parent.color = BLACK;
                    w.left.color = BLACK;
                    rotateRight(x.parent);
                    x = root;
                }
            }
        }
        x.color = BLACK; 
    }
    
    Node treeMaximum(Node subTreeRoot){
        while(subTreeRoot.right!=nil){
            subTreeRoot = subTreeRoot.right;
        }
        return subTreeRoot;
    }

    Node treeMinimum(Node subTreeRoot){
        while(subTreeRoot.left!=nil){
            subTreeRoot = subTreeRoot.left;
        }
        return subTreeRoot;
    }
    
    public void mainM() {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("\n1.- Add items\n"
                    + "2.- Delete items\n"
                    + "3.- Check items\n"
                    + "4.- Print tree\n"
                    + "5.- Delete tree\n"
                    + "6.- Rank of element\n"
                    + "7.- Selct element given Rank\n"
                    + "8.- Prefix sum\n");
            int choice = scan.nextInt();
            
            int item;
            Node node;
            switch (choice) {
                case 1:
                    item = scan.nextInt();
                    while (item != -999) {
                    	sum+=item;
                        node = new Node(item);
                        insert(node);
                        item = scan.nextInt();
                    }
                    printTree(root);
                    break;
                case 2:
                    item = scan.nextInt();
                    while (item != -999) {
                        node = new Node(item);
                        System.out.print("\nDeleting item " + item);
                        if (delete(node)) {
                        	sum-=item;
                            System.out.print(": deleted!");
                        } else {
                            System.out.print(": does not exist!");
                        }
                        item = scan.nextInt();
                    }
                    System.out.println();
                    printTree(root);
                    break;
                case 3:
                    item = scan.nextInt();
                    while (item != -999) {
                        node = new Node(item);
                        System.out.println((findNode(node, root) != null) ? "found" : "not found");
                        item = scan.nextInt();
                    }
                    break;
                case 4:
                    printTree(root);
                    break;
                case 5:
                    deleteTree();
                    System.out.println("Tree deleted!");
                    break;
                case 6:
                	item=scan.nextInt();
                	while(item!= -999)
                	{
                		System.out.println(rank(item,root));
                		item=scan.nextInt();	
                	}
                    break;
                case 7:
                	item=scan.nextInt();
                	while(item!= -999)
                	{
                		System.out.println(select(root,item).key);
                		item=scan.nextInt();	
                	}
                    break;
                case 8:
                	item=scan.nextInt();
                	while(item!= -999)
                	{
                		int prefixS=prefixSum(root,item);
                		System.out.println("prefixS "+prefixS+" sum "+sum);
                		int newS=sum-prefixS-item;
                		System.out.println(newS);
                		item=scan.nextInt();	
                	}
                    break;
            }
        }
    }
    public static void main(String[] args) {
        RedBlackTree rbt = new RedBlackTree();
        rbt.mainM();
    }
}