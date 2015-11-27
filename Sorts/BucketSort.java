package Sorts;

public class BucketSort {
	
	public static void sort(int a[],int max)
	{
		int bucket[]=new int[max];
		
		for(int i=0;i<a.length;i++)
			bucket[a[i]]++;
		
		int index=0;
		for(int i=0;i<bucket.length;i++)
		{
			for(int j=0;j<bucket[i];j++)
			{
				a[index++]=i;
			}
		}
		
	}
	
	 public static int max(int arr[]){
	        int max = arr[0];
	        for(int i=1; i < arr.length; i++){
	            if(max < arr[i]){
	                max = arr[i];
	            }
	        }
	        return max;
	    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int a[]={5, 3, 0, 2, 4, 1, 0, 5, 2, 3, 1, 100};
		
		sort(a,max(a)+1);

		for(int element:a)
			System.out.print(element+" ");
	}

}
