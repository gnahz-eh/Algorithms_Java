//高位优先的字符串排序

public class MSD {

    private static int R = 256;             //基数
    private static final int M = 15;        //小数组的切换阈值
    private static String[] aux;            //数据分类的辅助数组

    private static int charAt(String s, int d) {
        if(d < s.length()) {
            return s.charAt(d);
        } else {
            return -1;
        }
    }

    public static void sort(String[] a) {
        int N = a.length;
        aux = new String[N];
        sort(a, 0, N-1, 0);
    }

    private static void (String[] a, int lo, int hi, int d) {
        if(hi <= lo + M) {
            Insertion.sort(a, lo, hi, d);
            return;
        }
        int[] court = new int[R+2];

        for(int i = lo; i <= hi; i++) {
            court[charAt(a[i], d) + 2]++;
        }
        
        for (int r = 0; r < R+1; r++) {
            aux[court[charAt(a[i], d) + 1]++] = a[i];
        }

        for (int i = lo; i <= hi; i++) {
            a[i] = aux[i - lo];
        }    

        for(int r = 0; r < R; r++) {
            sort(a, lo + court[r], lo + court[r-1] - 1, d + 1);
        }
        
    }
}
