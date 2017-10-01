public class Solution {
    /*
     * @param A: an integer ratated sorted array and duplicates are allowed
     * @param target: An integer
     * @return: a boolean 
     */
    public boolean search(int[] A, int target) {
        // write your code here
        for (int i=0;i<A.length;i++){
            if(A[i]==target){
                return true;
            }
        }
        return false;
    }
}