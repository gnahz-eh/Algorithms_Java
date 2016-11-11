public static int search(String pat, String txt) {
    int M = pat.length();
    int N = txt.length();
    for (int i = 0; i < N-M; i++) {
        int j;
        for (j = 0; j < m; j++) {
            if (txt.charAt(i+j) != pat.charAt(j)) {
                break;
            }
        }
        if (j == M) {
            return i;
        }
    }
    return N;
}
