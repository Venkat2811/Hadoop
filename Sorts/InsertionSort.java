package Sorts;

public class InsertionSort {
	
	public static void sort(int a[])
	{
	
		for(int j=1;j<a.length;j++)
		{
			int key=a[j];
			int i=j-1;
			
			while(i>=0 && a[i]>key)
			{
				a[i+1]=a[i];
				i--;
			}
			
			a[i+1]=key;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int a[]={5,2,4,6,1};
		
		sort(a);
		
		for(int element:a)
			System.out.print(element+" ");

	}

}
