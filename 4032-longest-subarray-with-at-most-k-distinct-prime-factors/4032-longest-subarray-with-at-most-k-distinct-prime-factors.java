class Solution {
    public int longestSubarray(int[] nums, int k) {
        int n = nums.length;
        int max = 0;
        for(int num : nums) {
            max = Math.max(num,max);
        }
        int []smallFact = new int[max+1];
        for(int i=0;i<=max;i++) {
            smallFact[i] = i;
        }
        for(int i=2;i*i<=max;i++) {
            if(smallFact[i] == i) {
                for(int j=i*i;j<=max;j+=i) {
                    if(smallFact[j] == j) {
                        smallFact[j] = i;
                    }
                }
            }
        }
        List<Integer> []fact = new ArrayList[n];
        for(int i=0;i<n;i++) {
            fact[i] = new ArrayList<>();
            int val = nums[i];
            while(val > 1) {
                int p = smallFact[val];
                fact[i].add(p);
                while(val%p == 0) {
                    val /= p;
                }
            }
        }
        int []freq = new int[max+1];
        int left = 0;
        int right = 0;
        int res = 0;
        int count = 0;
        while(right < n) {
            for(int p : fact[right]) {
                if(freq[p] == 0) {
                    count++;
                }
                freq[p]++;
            }
            while(count > k) {
                for(int p : fact[left]) {
                    if(freq[p] == 1) {
                        count--;
                    }
                    freq[p]--;
                }
                left++;
            }
            right++;
            res = Math.max(res,right-left);
        }
        return res;
    }
}