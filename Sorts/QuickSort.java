package Sorts;

public class QuickSort {
	
	public static void swap(int a[],int i,int j)
	{
		if(a[i]!=a[j])
		{
			a[i]^=a[j];
			a[j]^=a[i];
			a[i]^=a[j];
		}
	}
	
	
	public static int partition(int a[],int p,int r)
	{
		int x=a[r];
		int i=p-1;
		
		for(int j=p;j<=r-1;j++)
		{
			if(a[j]<=x)
			{
				i++;
				swap(a,i,j);
				
			}
		}
		
		swap(a,r,i+1);
		
		return i+1;
	}
	
	public static void sort(int a[],int p,int r)
	{
		if(p<r)
		{
			int q=partition(a,p,r);
			sort(a,p,q-1);// q-1 is correct 
			sort(a,q+1,r);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
int a[]={2,8,7,1,3,5,6,4};
		
		sort(a,0,a.length-1);
		
		for(int element:a)
			System.out.print(element+" ");

	}

}
