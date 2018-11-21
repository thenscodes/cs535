package com.example.thens;

import java.util.Arrays;

public class Foo {
    public static void main(String[] arg) {
        String hi = "Hello World";
        System.out.println(hi);
        String pattern = "abcabcacab";
        String text = "abcabcacad";
        //System.out.println(KMPmatch(pattern, text));
        FillPrefixUsingExtend(pattern);
    }

    static int[] Prefix;


    public static void FillPrefixUsingExtend(String pattern) {
        Prefix = new int[pattern.length()];
        for (int i = 0; i < pattern.length(); i++)
            Prefix[i] = -1;
        extend(pattern, pattern.length()-1,pattern.charAt(0));
    }
    // Specification: Return position of the first occurrence of
    // pattern in text (or -1 if pattern does not occur in text).
    public  static int KMPmatch(String pattern, String text) {
        Prefix = new int[pattern.length()];
        for (int i = 0; i < pattern.length(); i++)
            Prefix[i] = -1;
        // Q holds and P holds for n = m = 0
        return match(pattern, 0, text, 0);
    }

    // Specification: Given that P and Q hold for parameters n and
    // m, return position in text of first occurrence of pattern
    // (or -1 if pattern does not occur in text).
    private static int match(String pattern, int m, String text, int n) {
        if (m == pattern.length()) // End of pattern ...
            return n - m; // ... a match
        if (n == text.length()) // End of text ...
            return -1; // ... no match
        // S holds
        return match(pattern, extend(pattern, m, text.charAt(n)),
                text, n + 1);
    }

    // Specification: Given Q and 0 <= j < #pattern, return
    // length of longest prefix of pattern that is a suffix of
    // pattern[0..j-1]+c.
    private static int extend(String pattern, int j, char c) {
        System.out.println("extend:" + "j =" +j + " char=" + c);
        if (pattern.charAt(j) == c)
            return j + 1;
        if (j == 0)
            return 0;
        return extend(pattern, prefix(pattern, j), c);
    }

    // Specification: Given Q and 0 < i < #pattern, return length
    // of longest proper prefix of pattern[0..i-1] that is a suffix
    // of pattern[0..i-1]. Also, store computed values in array
    // Prefix, in order to maintain Q.
    private static int prefix(String pattern, int i) {
        System.out.println("prefix:" + i + " Array:" + Arrays.toString(Prefix));
        if (Prefix[i] == -1)
            if (i == 1)
                Prefix[i] = 0;
            else
                Prefix[i] = extend(pattern, prefix(pattern, i - 1),
                        pattern.charAt(i - 1));
        return Prefix[i];
    }
}