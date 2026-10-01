package Practice.Graph;

// ============================================================================
// LC 1061. Lexicographically Smallest Equivalent String   [Medium]
// https://leetcode.com/problems/lexicographically-smallest-equivalent-string/
// Topics: String, Union-Find
// ============================================================================
//
// PROBLEM
//   s1 and s2 have the same length, and each pair s1[i], s2[i] says "these two letters are
//   equivalent". Equivalence chains: if a ~ b and b ~ c then a ~ c (and every letter is
//   equivalent to itself), so the 26 letters split into groups.
//   You may swap any letter of baseStr for any letter of its group. Return the
//   lexicographically smallest string you can build this way.
//
// EXAMPLES
//   s1 = "parker", s2 = "morris", baseStr = "parser"  ->  "makkek"
//        groups [m,p] [a,o] [k,r,s] [e,i]; every letter becomes its group's smallest
//   s1 = "hello", s2 = "world", baseStr = "hold"  ->  "hdld"
//        groups [h,w] [d,e,o] [l,r]; only 'o' has a smaller partner ('d')
//
// CONSTRAINTS
//   1 <= s1.length, s2.length, baseStr <= 1000
//   s1.length == s2.length
//   s1, s2 and baseStr hold lowercase English letters only
//
// INTUITION
//   Treat each letter as a node and each pair (s1[i], s2[i]) as an edge. A group is a
//   connected component. Positions of baseStr are independent, and the earliest position
//   matters most, so the best choice everywhere is simply the smallest letter of the group.
//   Union-find builds the groups; if the root of every group is kept as its smallest
//   letter, find(c) is directly the answer for c.
//
// APPROACH (intended - NOT implemented yet, the method below is a stub)
//   1. parent[26] with parent[c] = c (every letter is its own group).
//   2. For each i: ra = find(s1[i]), rb = find(s2[i]); if they differ, hang the LARGER
//      root under the SMALLER one, so a root is always the smallest letter of its group.
//   3. Build the result: for each c in baseStr append find(c).
//
// DRY RUN   (of the intended approach) s1 = "hello", s2 = "world", baseStr = "hold"
//   pairs h-w : parent[w] = h
//         e-o : parent[o] = e
//         l-r : parent[r] = l
//         l-l : same letter, nothing to do
//         o-d : find(o) = e, find(d) = d, d < e  -> parent[e] = d
//   baseStr   h -> h,  o -> e -> d,  l -> l,  d -> d   =>  "hdld"
//
// STATUS
//   Unfinished: smallestEquivalentString() is a stub that always returns "", and main()
//   is empty, so running the file prints nothing.
//
// COMPLEXITY
//   Current stub: O(1) time and space - it returns right away.
//   Intended    : Time O((n + m) * alpha(26)), about O(n + m) - one union per pair
//                 (n = s1.length) and one find per letter of baseStr (m = its length).
//                 Space O(26) for parent, plus the O(m) result string.
//
// GOTCHAS
//   - Compare the ROOTS, not the two letters, when deciding who becomes the parent;
//     otherwise a group's root may stop being its smallest letter.
//   - Letters that never appear in s1/s2 stay as they are ('u' in "sourcecode").
//
// RELATED
//   - LC 990 Satisfiability of Equality Equations - same union-find over 26 letters,
//     built from "a==b" equations.
// ============================================================================
public class LC1061LexicographicallySmallestEquivalentString {
    public static void main(String[] args) {
        // empty - nothing is run or printed yet (see STATUS)

    }
    // Should return baseStr with every letter replaced by the smallest letter of its group.
    // Stub: always returns "" for now - see APPROACH for the intended union-find.
    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        return "";
    }
}
