package Sorts;

public class BubbleSort {
	
	public static void swap(int a[],int i,int j)
	{
		if(a[i]!=a[j])
		{
			a[i]^=a[j];
			a[j]^=a[i];
			a[i]^=a[j];
		}
	}
	
	
	
	public static void sort (int[] a)
	{
	    for (int i = a.length - 1; i >= 0; i--)
	    {
	        // bubble up
	        for (int j = 0; j <= i - 1; j++)
	        {
	            if (a[j] > a[j + 1])
	                swap(a, j, j + 1);
	        }
	    }
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
int a[]={2,8,7,1,3,5,6,4};
		
		sort(a);
		
		for(int element:a)
			System.out.print(element+" ");

	}

}
