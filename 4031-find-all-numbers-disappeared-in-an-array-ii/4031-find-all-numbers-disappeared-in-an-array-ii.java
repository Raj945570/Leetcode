class Solution { 
    public List<List<Integer>> findDisappearedNumbers(int[] nums, int lower, int upper) { 
        List<List<Integer>> list = new ArrayList<>(); 
        Set<Integer> set = new HashSet<>(); 

        for(int n : nums) set.add(n); 

        while(lower <= upper) { 
            if(set.contains(lower)) { 
                lower++; 
                continue; 
            } 

            int st = lower; 

            while(lower <= upper && !set.contains(lower)) 
                lower++; 

            List<Integer> temp = new ArrayList<>(); 
            temp.add(st); 
            temp.add(lower - 1); 
            list.add(temp); 
        } 

        return list; 
    } 
}