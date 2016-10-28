public class Quick3way {
    
    private static void sort(Comparable[] a, int lo, int hi) {
        if(hi <= lo) return;
        int lt = lo, i = lo + 1, gt = hi;
        Comparable v = a[lo];
        while(i <= gt) {
            int cmp = a[i].compareTo(v);
            if(cmp < 0) exch(a, lt++, i++);
            else if(cmp > 0) exch(a, gt--, i);
            else i++;
        }
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }
}
