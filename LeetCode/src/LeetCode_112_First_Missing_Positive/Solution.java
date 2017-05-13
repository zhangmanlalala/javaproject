package LeetCode_112_First_Missing_Positive;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public int firstMissPos(int[] A){
		int n = A.length;  
        for(int i=0; i<n; i++){  
            //此处看上去是两层循环，但是在寻找A[i] == i+1 的过程中，  
            //不断将A[i]放在A[A[i]-1]处，循环到A[i]-1的时候就不用再处理,所以整体是O(n)  
            while(A[i] != i+1){  
                if(A[i]<=0 || A[i]>n || A[i]==A[A[i]-1])  
                    break;   
                else{  
                    //把A[i]放在A[i]-1处  
                    int temp = A[i];  
                    A[i] = A[temp-1];  
                    A[temp-1] = temp;  
                }  
            }  
        }  
        for(int i=0; i<n; i++){  
            if(A[i] != i+1)  
                return i+1;  
        }  
        return n+1;  
	}

}
