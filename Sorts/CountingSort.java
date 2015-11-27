package Sorts;



public class CountingSort {

    private static int TOTAL = 10;


    public void sort(int arr[],int max) {

        int count[] = new int[max];
        int output[] = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            count[arr[i]]++;
        }
        
        for(int i=1; i < max; i++){
            count[i] += count[i-1];
        }
        
        for(int i=0; i <arr.length; i++){
            output[count[arr[i]]-1] = arr[i];
            count[arr[i]]--;
        }
        
        for(int i=0; i < arr.length; i++){
            arr[i] = output[i];
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

    public static void main(String args[]) {
        int arr[] = { 6, 1, 6, 7, 3, 1 };
        CountingSort cs = new CountingSort();
        cs.sort(arr,max(arr)+1);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
