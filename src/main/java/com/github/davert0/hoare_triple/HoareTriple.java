package com.github.davert0.hoare_triple;

public class HoareTriple {
    // { true } max(a,b) { r = max(a,b) }
    public static double max(double a, double b) {
        if (a > b) {
            return a;
        }
        return b;
    } 

    // { true } abs(x) { r = |x| ∧ r ≥ 0 }
    public static double abs(double a) {
        if (a >= 0) {
            return a;
        }
        return -a;
    }

    // { true }  u := abs(x); v := abs(y); w := max(u, v)  { w = max(|x|, |y|) ∧ w ≥ 0 }.
    public static double maxAbs(double a, double b) {
        return max(abs(a), abs(b));
    }
}
