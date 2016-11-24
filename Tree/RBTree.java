import java.io.*;
import java.util.*;

public class RBTree<T extends Comparable<T>>
{
private static final int BLACK = 1;
private static final int RED = 0;
class Node<T>
{
	T data;
	Node<T> left,right,parent;
	int color;
	public Node(T data)
	{
		this(data,null,null);
	}
	public Node(T data,Node<T> left,Node<T> right)
	{
		this.data=data;
		this.left=left;
		this.right=right;
		this.color=RED;	
		this.parent=null;	
	}
}
	private Node<T> root;

	public static void main(String[] args)throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		RBTree<Integer> rb=new RBTree<>();
		int i;
		System.out.println("Enter space separated number to insert ");
		String[] strs=br.readLine().trim().split("\\s+");
		for(i=0;i<strs.length;i++)
		{
			rb.insert(Integer.parseInt(strs[i]));
		}
		rb.printTree();
		System.out.println("Enter number to delete ");
		rb.remove(Integer.parseInt(br.readLine()));
		rb.printTree();
	}
	void printTree()
	{
		System.out.println("Tree is ");
		printTree(root);
	}
	public void printTree(Node<T> root)
	{		
		if(root!=null)
		{
			printTree(root.left);
			System.out.println(root.data+" color "+root.color);
			printTree(root.right);
		}
	}
	public void insert(T data)
	{
		Node<T> z=new Node<T>(data);

		Node<T> y=null;
		Node<T> x=root;
		int cmp=0;
		while(x!=null)
		{
			y=x;
			cmp=z.data.compareTo(y.data);
			if (cmp<=0)
				x=x.left;
			else
				x=x.right;
		}
		z.parent=y;

		if (y==null)
			root=z;
		else
		{
			cmp=data.compareTo(y.data);
			if(cmp<=0)
				y.left=z;
			else y.right=z;
		}
		insertFixup(z);
	}

	public void insertFixup(Node<T> z)
	{
		while(z.parent!=null && z.parent.parent!=null && z.parent.color==RED)
		{
			if(z.parent.equals(z.parent.parent.left))
			{
				Node<T> y=z.parent.parent.right;
				if (y!=null && y.color==RED)
				{
					z.parent.color=BLACK;
					y.color=BLACK;
					z.parent.parent.color=RED;
					z=z.parent.parent;
				}
				else if(z.equals(z.parent.right))
				{
					z=z.parent;
					zig_zig_rotateLeft(z);
				}
				else
				{
					z.parent.color=BLACK;
					z.parent.parent.color=RED;
					zig_zig_rotateRight(z.parent.parent);
				}	
			}
			else
			{
				Node<T> y=z.parent.parent.left;
				if (y!=null && y.color==RED)
				{
					z.parent.color=BLACK;
					y.color=BLACK;
					z.parent.parent.color=RED;
					z=z.parent.parent;
				}
				else if(z.equals(z.parent.left))
				{
					z=z.parent;
					zig_zig_rotateRight(z);
				}
				else
				{
					z.parent.color=BLACK;
					z.parent.parent.color=RED;
					zig_zig_rotateLeft(z.parent.parent);
				}				
			}			
		}
		root.color=BLACK;
	}
	
	public void remove(T data)
	{
		int cmp=0;
		Node<T> x=root;
		Node<T> y=null;
		Node<T> z=new Node<T>(data);
		while(x!=null && data!=x.data)
		{
			cmp=data.compareTo(x.data);
			if (cmp<0)
				x=x.left;
			else if(cmp>0)
				x=x.right;
		}
		remove(x);
	}
	public void remove(Node<T> z)
	{
		Node<T> y=z;
		int yOrigColor=y.color;
		Node<T> x;
		if(z.left==null)
		{
			x=z.right;
			RBTransplant(z,z.right);
		}
		else if(z.right==null)
		{	
			x=z.left;
			RBTransplant(z,z.left);
		}
		else
		{
			y=findMax(z.left);
			yOrigColor=y.color;
			x=y.left;
			//System.out.println("y.parent "+y.parent.data+" yOrigColor "+yOrigColor+" y data "+y.data);
			if(y.parent==z)
				x.parent=y;
			else if(y.left!=null)
			{
				RBTransplant(y,y.left);
				y.left=z.left;
				y.left.parent=y;
			}
			RBTransplant(z,y);
			y.right=z.right;
			y.right.parent=y;							
			y.color=z.color;
		}
		if (yOrigColor==BLACK && x!=null)
			deleteFixup(x);
	}
	public void deleteFixup(Node<T> x)
	{		
		Node<T> w;
		while(x!=root && x.color==BLACK)
		{			
			if(x==x.parent.left)
			{
				w=x.parent.right;
				if(w.color==RED)
				{
					w.color=BLACK;
					x.parent.color=RED;
					zig_zig_rotateLeft(x.parent);
					w=x.parent;
				}
				if(w.left.color==BLACK && w.right.color==BLACK)
				{
					w.color=RED;
					x=x.parent;
				}
				else if((w.left.color==RED && w.left!=null) || (w.right.color==RED && w.left!=null))
				{
					w.color=x.parent.color;
					x.parent.color=BLACK;
					w.right.color=BLACK;
					zig_zig_rotateLeft(x.parent);
				}
			}
			else
			{				
				w=x.parent.left;
				//System.out.println("else"+" w "+w.data+" x "+x.data);
				if(w.color==RED)
				{
					//System.out.println("x.parent.color "+x.parent.color+" x data "+x.parent.data);
					w.color=BLACK;
					x.parent.color=RED;
					zig_zig_rotateRight(x.parent);
					w=x.parent;	
				}
				else if(w.left.color==BLACK && w.right.color==BLACK)
				{
					w.color=RED;
					x=x.parent;					
				}
				else if(w.left.color==RED || w.right.color==RED)
				{
					w.color=x.parent.color;
					x.parent.color=BLACK;				
					w.right.color=BLACK;
					zig_zig_rotateRight(x.parent);
				}
			}
		}
		x.color=BLACK;
	}
	public void RBTransplant(Node<T> u,Node<T> v)
	{
		if (u.parent==null)
			root=v;
		else if(u==u.parent.left)
			u.parent.left=v;
		else u.parent.right=v;	
		v.parent=u.parent;					
	}

	public Node<T> findMax(Node<T> root)
	{
		if (root==null)
			return root;
		else
		{
			while(root.right!=null)
				root=root.right;
		}
		return root;
	}

	public Node<T> zig_zig_rotateRight(Node<T> y)
	{
		Node<T> x=y.left;
		y.left=x.right;
		if(x.right!=null)
			x.right.parent=y;
		x.parent=y.parent;
		if(y.parent==null)
			root=x;
		else if (y==y.parent.right)
			y.parent.right=x;
		else y.parent.left=x;
		x.right=y;
		y.parent=x;
		return x;
	}
	public Node<T> zig_zig_rotateLeft(Node<T> x)
	{
		Node<T> y=x.right;
		x.right=y.left;
		if(y.left!=null)
			y.left.parent=x;
		y.parent=x.parent;
		
		if(x.parent==null)
			root=y;
		else if(x==x.parent.left)
			x.parent.left=y;
		else x.parent.right=y;

		y.left=x;
		x.parent=y;
		return y;
	}
}