package Sorts;

public class MergeSort {
		
	public static void merge(int a[],int p,int q,int r)
	{
		int i=p,k=p,j=q+1;
		
		int t[]=new int[r+1];
		
		while(i<=q && j<=r)
		{
			if(a[i]<a[j])
				t[k++]=a[i++];
			else
				t[k++]=a[j++];
		}
		
		if(i>q)
		{
			while(j<=r)
				t[k++]=a[j++];
		}
		else
		{
			while(i<=q)
				t[k++]=a[i++];
		}
		
		// be careful here donot use i=0
		for(i=p;i<r+1;i++)
			a[i]=t[i];
		
		
	}
	
	public static void merge1(int a[],int p,int q,int r)
	{
		int i=p,k=p,j=q+1;
		
		int t[]=new int[r+1];
		
		while(i<=q && j<=r)
		{
			if(a[i]<a[j])
				t[k++]=a[i++];
			else
				t[k++]=a[j++];
		}
		
		if(i>q)
		{
			while(j<=r)
				t[k++]=a[j++];
		}
		else
		{
			while(i<=q)
				t[k++]=a[i++];
		}
		
		// be careful here donot use i=0
		for(i=p;i<r+1;i++)
			a[i]=t[i];
		
		
	}
	
	public static void sort(int a[],int p,int r)
	{
		if(p<r)
		{
			int q=(int)Math.floor((p+r)/2);// q=floor of(p+r)/2
			
			sort(a,p,q);
			sort(a,q+1,r);
			merge(a,p,q,r);
				
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
