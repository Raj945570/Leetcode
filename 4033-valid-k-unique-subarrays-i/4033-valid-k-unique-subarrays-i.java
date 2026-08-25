class Solution {
    public boolean[] validSubarrays(int[] nums, int k, int[][] queries) {
        // 1. sort queries by Mo's Algrithm
        int n = nums.length;
        int block = (int)Math.sqrt(n);
        int ql = queries.length;
        int iqueries[][] = new int[ql][3];
        for(int i = 0;i<ql;i++){
            iqueries[i][0] = queries[i][0];
            iqueries[i][1] = queries[i][1];
            iqueries[i][2] = i;
        }
        Arrays.sort(iqueries,(a,b)->{
            if(a[0]/block != b[0]/block){
                return Integer.compare(a[0]/block,b[0]/block);
            }
            return Integer.compare(a[1],b[1]);
        });
        boolean ans[] = new boolean[ql];
        // 2. for first query update distinct,fre,odd
        int L = 0;
        int R = -1;
        
        int distinct = 0;
        int odd = 0;

        int maxElement = findMax(nums);
        int frequencies[] = new int[maxElement+1];
        for(int iQ[]: iqueries){
            int l = iQ[0];
            int r = iQ[1];
            int index = iQ[2];
             if(L>l){
                // expanding means adding (towords Left)
                while(L>l){
                    L--;
                    frequencies[nums[L]]++;
                    if(frequencies[nums[L]]==1) distinct++; 
                    if(frequencies[nums[L]]%2==1) odd++;
                    else odd--;

                }
            }    
            if(R<r){
                // I have to add Elements (towords right)
                while(R<r){
                    R++;
                    frequencies[nums[R]]++;
                    if(frequencies[nums[R]]==1) distinct++; 
                    if(frequencies[nums[R]]%2==1) odd++;
                    else odd--;
                }
            } 
           if(L<l){
                // I have to remove Elements(towords right)
                while(L<l){   
                    frequencies[nums[L]]--;
                    if(frequencies[nums[L]]==0) distinct--; 
                    if(frequencies[nums[L]]%2==0) odd--;
                    else odd++;
                    L++;
                } 
            }
            
           if(R>r){
                // shrinling means removing(towords left)
                while(r<R){
                    frequencies[nums[R]]--;
                    if(frequencies[nums[R]]%2==1) odd++;
                    else odd--;
                    if(frequencies[nums[R]]==0) distinct--;    
                    R--;
                }
            }
            if(distinct==k && odd==0){
                ans[index] = true;
            }

        }
        return ans;
    }
    public int findMax(int nums[]){
        int max = 0;
        for(int i = 0;i<nums.length;i++){
           max = Math.max(max,nums[i]);
        }
        return max;
    }
}