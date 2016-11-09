//低位优先的字符串排序

public class LSD {

    public static void sort(String[] a, int W) {
        int N = a.length();
        int R = 256;
        String[] aux = new String[N];

        for (int d = W-1; d >= 0; d--) {
            int [] court = new int[R+1];

            //计算出现pinlv
            for (int i = 0; i < N; i++) {
                court[a[i].charAt(d) + 1]++;
            }

            //将频率转换为索引
            for (int r = 0; r < R; r++) {
                court[r+1] += court[r];
            }

            //将元素分类
            for (int i = 0; i < N; i++) {
                anx[court[a[i].charAt(d)]++] = a[i];
            }

            for (int i = 0; i < N; i++) {
                a[i] = aux[i];
            }
        }
    }
}
