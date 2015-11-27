package Sorts;

public class SelectionSort {
	
	public static void swap(int a[],int i,int j)
	{
		if(a[i]!=a[j])
		{
			a[i]^=a[j];
			a[j]^=a[i];
			a[i]^=a[j];
		}
	}
	
	
	
	public static void sort(int[] a)
	{
	    // find the smallest element starting from position i
	    for (int i = 0; i < a.length - 1; i++)
	    {
	        int min = i;  // record the position of the smallest
	        for (int j = i + 1; j < a.length; j++)
	        {
	            // update min when finding a smaller element
	            if (a[j] < a[min])
	                min = j;
	        }
	 
	        // put the smallest element at position i
	        swap(a, i, min);
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
