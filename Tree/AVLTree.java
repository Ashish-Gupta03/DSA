import java.io.*;
import java.util.*;

public class AVLTree<T extends Comparable<T>>
{

class Node<T>
{
	T data;
	Node<T> left,right;
	int height;
	public Node(T data)
	{
		this(data,null,null);
	}
	public Node(T data,Node<T> left,Node<T> right)
	{
		this.data=data;
		this.left=left;
		this.right=right;
		this.height=0;		
	}
}
	private Node<T> root;

	public static void main(String[] args)throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		AVLTree<Integer> avl=new AVLTree<>();
		int i;
		System.out.println("Enter space separated number to insert ");
		String[] strs=br.readLine().trim().split("\\s+");
		for(i=0;i<strs.length;i++)
		{
			avl.insert(Integer.parseInt(strs[i]));
		}
		avl.printTree();
		System.out.println("Enter number to delete ");
		avl.remove(Integer.parseInt(br.readLine()));
		avl.printTree();
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
			System.out.println(root.data+" "+root.height);
			printTree(root.right);
		}
	}
	public void insert(T data)
	{
		root=insert(data,root);
	}

	public Node<T> insert(T data,Node<T> root)
	{
		if(root==null)
			return new Node<T>(data);

		int cmp=data.compareTo(root.data);
		if(cmp<=0)
			root.left=insert(data,root.left);
		else root.right=insert(data,root.right);

		return balanced(root);
	}

	public void remove(T data)
	{
		root=remove(data,root);
	}
	public Node<T> remove(T data,Node<T> root)
	{
		if(root==null)
			return root;

		int cmp=data.compareTo(root.data);
		System.out.println("cmp is "+cmp);
		if(cmp<0)
			root.left=remove(data,root.left);
		else if(cmp>0)
			root.right=remove(data,root.right);
		else if(root.left!=null && root.right!=null)
		{
			root.data=findMax(root.left).data;
			root.left=remove(root.data,root.left);
		}
		else root=(root.left!=null)?root.left:root.right;
		return balanced(root);
	}
	public T findMax()
	{
		return findMax(root).data;
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

	public Node<T> balanced(Node<T> root)
	{
		if (root==null)
			return root;

		if(height(root.left)-height(root.right)>1)
			if(height(root.left.left)>=height(root.left.right))
				root=zig_zig_rotateRight(root);
			else root=zig_zag_rotateRight(root);

		else
		{
			if(-height(root.left)+height(root.right)>1)
			if(height(root.right.right)>=height(root.right.left))
				root=zig_zig_rotateLeft(root);
			else root=zig_zag_rotateLeft(root);
		}

		root.height=Math.max(height(root.left),height(root.right))+1;
		return root;
	}

	public Node<T> zig_zig_rotateRight(Node<T> root)
	{
		Node<T> temp=root.left;
		root.left=temp.right;
		temp.right=root;
		root.height=Math.max(height(root.left),height(root.right))+1;
		temp.height=Math.max(height(temp.left),root.height)+1;
		return temp;
	}
	public Node<T> zig_zag_rotateLeft(Node<T> root)
	{
		root.right=zig_zig_rotateRight(root.right);
		return zig_zig_rotateLeft(root);
	}
	public Node<T> zig_zig_rotateLeft(Node<T> root)
	{
		Node<T> temp=root.right;
		root.right=temp.left;
		temp.left=root;
		root.height=Math.max(height(root.left),height(root.right))+1;
		temp.height=Math.max(height(temp.left),root.height)+1;
		return temp;
	}
	public Node<T> zig_zag_rotateRight(Node<T> root)
	{
		root.left=zig_zig_rotateLeft(root.left);
		return zig_zig_rotateRight(root);
	}
	public int height(Node<T> root)
	{
		return root==null?-1:root.height;
	}

}