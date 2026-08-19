import java.util.*;

class Solution {
    static void solve(String digits, int index, String[] mapping,
                      List<String> result, StringBuilder output) {

        // base case
        if (index >= digits.length()) {
            result.add(output.toString());
            return;
        }

        int value = digits.charAt(index) - '0';
        String mappedString = mapping[value];

        for (int i = 0; i < mappedString.length(); i++) {
            output.append(mappedString.charAt(i));

            // recursive call
            solve(digits, index + 1, mapping, result, output);

            // backtracking
            output.deleteCharAt(output.length() - 1);
        }
    }

    public List<String> letterCombinations(String digits) {

        List<String> result = new ArrayList<>();

        // edge case
        if (digits == null || digits.length() == 0) {
            return result;
        }

        String[] mapping = {
            "", "", "abc", "def", "ghi",
            "jkl", "mno", "pqrs", "tuv", "wxyz"
        };

        StringBuilder output = new StringBuilder();

        solve(digits, 0, mapping, result, output);

        return result;
    }
}