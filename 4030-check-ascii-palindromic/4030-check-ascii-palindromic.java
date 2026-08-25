class Solution {
    public boolean isPalindromic(String s) {
        int n = s.length();

        for(int i = 0; i <= n/2; i++) {
            
            int lptr = s.charAt(i);
            int rptr = s.charAt(n - i - 1);
            
            for(int j = 0; j < 8; j++) {
                int left = (lptr >> j) & 1;
                int right = (rptr >> (7 - j)) & 1;

                if(left != right) return false;
            }
        }

        return true;
    }
}