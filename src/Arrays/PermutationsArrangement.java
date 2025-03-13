package Arrays;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PermutationsArrangement {
    public static void main(String[] args) {
        List<Integer> fixedSizeList = Arrays.asList(12, 2, 3);
        ArrayList<Integer> arrayList = new ArrayList<>(fixedSizeList);
//        approach1(integers);
        approach2( arrayList);
    }

    private static  ArrayList<Integer> approach2( ArrayList<Integer> integers) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        recursivelyGeneratePermutationBySwap(0, integers, ans);
        ans.sort((a, b) -> {
            int n = Math.min(a.size(), b.size());
            for (int i = 0; i < n; i++) {
                if (a.get(i) < b.get(i)) return -1;
                if (a.get(i) > b.get(i)) return 1;
            }
            return a.size() - b.size();
        });

        int index = 0;
        for(int i= 0; i< ans.size(); i++){
            if (arrayToLexicographical(ans.get(i)) == arrayToLexicographical(integers)){
                index = i;
                break;
            }
        }

        return  ans.get(index+1);

    }

    private static Integer arrayToLexicographical(ArrayList<Integer> array){
        StringBuilder string = new StringBuilder();
        for(Integer num : array){
            string.append(num.toString());
        }
        return Integer.parseInt(string.toString());
    }

    private static void recursivelyGeneratePermutationBySwap(int index, ArrayList<Integer> integers, ArrayList<ArrayList<Integer>> ans){
        if(index == integers.size()){
            ans.add(new ArrayList<>(integers));
            return;
        }
        for(int i=index; i < integers.size(); i++){
            swapToNumberInAList(index, i, integers);
            recursivelyGeneratePermutationBySwap(index + 1, integers, ans);
            swapToNumberInAList(index, i, integers);
        }
    }

    private static void swapToNumberInAList(int posA, int posB, ArrayList<Integer> integers){
        int temp = integers.get(posA);
        integers.set(posA, integers.get(posB));
        integers.set(posB, temp);
    }

    private static void approach1(List<Integer> integers) {
        Map<Integer, Boolean> takenOrNot = integers.stream().collect(Collectors.toMap(Function.identity(), i -> false));
        List<Integer> permutation = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        recursivelyGeneratePermutation(ans, permutation, integers, takenOrNot);
        ans.forEach(System.out::println);
    }

    private static void recursivelyGeneratePermutation(List<List<Integer>> ans, List<Integer> permutation, List<Integer> integers, Map<Integer, Boolean> takenOrNot) {
        if(permutation.size() == integers.size() ){
            ans.add(new ArrayList<>(permutation));
            return;
        }
        for(Map.Entry<Integer, Boolean> entry : takenOrNot.entrySet()){
            Integer takenElement = entry.getKey();
            boolean ifTaken = entry.getValue();
            if(!ifTaken){
                takenOrNot.put(takenElement, true);
                permutation.add(takenElement);
                recursivelyGeneratePermutation(ans, permutation, integers, takenOrNot);
                permutation.remove(takenElement);
                takenOrNot.put(takenElement, false);
            }
        }
    }
}
