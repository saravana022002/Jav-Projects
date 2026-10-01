package Practice.Strings;

// ============================================================================
// LC 165. Compare Version Numbers   [Medium]
// https://leetcode.com/problems/compare-version-numbers/
// Topics: Two Pointers, String
// ============================================================================
//
// PROBLEM
//   A version string is a list of revisions joined by dots, like "1.2.10". Each revision is
//   a number; leading zeros do not count ("01" is 1). Compare two versions revision by
//   revision from the left. If one version has fewer revisions, the missing ones count as 0.
//   Return -1 if version1 is smaller, 1 if it is larger, and 0 if they are equal.
//
// EXAMPLES
//   "1.2"  vs "1.10"     ->  -1   second revisions: 2 < 10
//   "1.01" vs "1.001"    ->  0    both second revisions are 1 once leading zeros are ignored
//   "1.0"  vs "1.0.0.0"  ->  0    the missing revisions of "1.0" count as 0
//
// CONSTRAINTS
//   1 <= version1.length, version2.length <= 500,   only digits and '.',
//   both are valid version numbers, every revision fits in a 32-bit integer
//
// INTUITION
//   Versions are compared like words in a dictionary, except that the "letters" are whole
//   numbers. The first revision where the two differ decides the answer; if none differs,
//   the versions are equal. Two details make it tricky: revisions must be compared as
//   NUMBERS (10 is bigger than 2, although "10" < "2" as text), and a shorter version is
//   padded with zeros.
//
// APPROACH (as implemented below)
//   1. Split both strings on the dot: ver1strs, ver2strs.
//   2. For i from 0 to max(ver1strs.length, ver2strs.length) - 1:
//      - num1 = Integer.parseInt(ver1strs[i]) if that revision exists, else 0 (same for num2).
//        parseInt already ignores leading zeros.
//      - num1 < num2 -> return -1;   num1 > num2 -> return 1;   equal -> next revision.
//   3. Every revision was equal -> return 0.
//
// DRY RUN   version1 = "1.01", version2 = "1.001"   (the input main() uses)
//   ver1strs = ["1","01"]   ver2strs = ["1","001"]   loop runs max(2, 2) = 2 times
//   i=0  1 vs 1   equal
//   i=1  parseInt("01") = 1, parseInt("001") = 1   equal
//   loop ends -> return 0
//   Padding, "1.0" vs "1.0.0.0":  i=0 1 vs 1, i=1 0 vs 0, i=2 (0 by default) vs 0,
//   i=3 (0 by default) vs 0 -> return 0
//
// COMPLEXITY   (n, m = lengths of the two strings)
//   Time : O(n + m) - split and parse each character a constant number of times; the loop
//          compares at most max(revisions) numbers
//   Space: O(n + m) - the two arrays of revision strings. Walking two pointers over the
//          strings and parsing each revision in place would need only O(1).
//
// GOTCHAS
//   - Compare numbers, not text: "1.2" vs "1.10" is -1 only because 2 < 10 as integers.
//   - split takes a regex, so the dot must be escaped as "\\."; "1.2".split(".") would
//     return an empty array because every character matches.
//   - The loop runs to the LONGER length so that trailing revisions are still examined:
//     "1.0.1" vs "1" must give 1, not 0.
//   - Integer.parseInt is safe only because every revision fits in 32 bits (the constraints
//     say so); a longer one would throw NumberFormatException.
//
// STATUS
//   The method is correct; main() prints 0.
// ============================================================================
public class LC165CompareVersionNumbers {
    public static void main(String[] args) {
        // "01" and "001" are both revision 1, so the versions are equal. Expected output: 0
        String version1 = "1.01";
        String version2 = "1.001";
        System.out.println(compareVersion(version1, version2));
    }
    // Returns -1, 0 or 1 depending on whether version1 is smaller than, equal to or larger
    // than version2
    public static int compareVersion(String version1, String version2) {
        // revisions as text, e.g. "1.01" -> ["1", "01"]
        String[] ver1strs = version1.split("\\.");
        String[] ver2strs = version2.split("\\.");

        int num1;
        int num2;
        // go to the longer length; a missing revision counts as 0
        for (int i = 0; i < Math.max(ver1strs.length, ver2strs.length); i++) {
            num1 = i < ver1strs.length ? Integer.parseInt(ver1strs[i]) : 0;
            num2 = i < ver2strs.length ? Integer.parseInt(ver2strs[i]) : 0;
            // the first revision that differs decides the answer
            if(num1 < num2){
                return -1;
            }
            if (num1 > num2) {
                return 1;
            }
        }
        // no revision differed
        return 0;
    }
}
