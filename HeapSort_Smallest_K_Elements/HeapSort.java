import java.util.Scanner;
public class HeapSort
{
	public static void main(String args[])
    {
    	int i=0,j=0;
    	HeapSort hs=new HeapSort();
    	int[] arr=new int[5];
    	Scanner sc=new Scanner(System.in);
    	for(i=0;i<5;i++){    	
    	arr[i]=sc.nextInt();
    	}
    	hs.buildMaxHeap(arr);
        System.out.println("Sorted output is ");
    	for(j=0;j<5;j++)
    	System.out.println(arr[j]);
    	}

    void buildMaxHeap(int a[])
    {
    int heapSize=a.length;
    int i;
    for(i=heapSize/2-1;i>=0;i--)
        MaxHeapify(a,i,heapSize);
    int l=heapSize;
    for(i=heapSize-1;i>=0;i--)
    {
        swap(a,0,i);        
        l--;       
        MaxHeapify(a,0,l);
    }
    }

    void MaxHeapify(int a[],int i,int arrLength)
    {
        int largest;
        int l=2*i+1;
        int r=2*i+2;
        if (l<arrLength && a[l]>a[i])
            largest=l;
        else largest=i;
        if (r<arrLength && a[r]>a[largest])
            largest=r;
        if (largest!=i)
            {
                swap(a,i,largest);
        MaxHeapify(a,largest,arrLength);
    }
    }

    void swap(int a[],int i,int j)
    {
        int temp=a[i];
        a[i]=a[j];
        a[j]=temp;
    }
}