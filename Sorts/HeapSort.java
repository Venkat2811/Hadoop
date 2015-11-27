package Sorts;

public class HeapSort {
	
	static int heapsize;
	
	public static int getParent(int i)
	{
		return i>>1;//-->i/2
	}
	
	public static int getLeft(int i)
	{
		return (i<<1);//2*i
		
	}
	
	public static int getRight(int i)
	{
		return (i<<1)+1;//2*i+1
	}
	
	public static void swap(int a[],int i,int j)
	{
		if(a[i]!=a[j])
		{
			a[i]^=a[j];
			a[j]^=a[i];
			a[i]^=a[j];
		}
	}
	
	public static void maxHeapify(int a[],int i)
	{
		int left=getLeft(i);
		int right=getRight(i);
		
		int smallest=i;
		
		if(left<=heapsize && a[left]<a[i])
			smallest=left;

		if(right<=heapsize && a[right]<a[smallest])
			smallest=right;
		
		if(smallest!=i)
		{
			swap(a,smallest,i);
			maxHeapify(a,smallest);
		}
	}
	
	public static void buildMaxHeap(int a[])
	{
		heapsize=a.length-1;
		
		
		for(int i=(int)Math.floor(a.length/2);i>=0;i--)
			maxHeapify(a,i);
		
	}
	
	public static void heapSort(int a[])
	{
		buildMaxHeap(a);
		for(int i=heapsize;i>0;i--)
		{
			swap(a,0,i);
			heapsize-=1;
			maxHeapify(a,0);
		}
	}
	
	public static int extractMax(int a[])
	{
		int max=a[0];
		a[0]=a[heapsize];
		heapsize--;
		maxHeapify(a,0);
		return max;
	}
	
	public static int extract(int a[],int k)
	{
		int res=0;
		
		for(int i=0;i<k;i++)
			res=extractMax(a);
	
		return res;
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int a[]={25,12,16,13,10,8,14};
		
	buildMaxHeap(a);
	for(int elements:a)
		System.out.print(elements+" ");
	System.out.println("kth smallest element is:"+extract(a,3));
		
		

	}

}
