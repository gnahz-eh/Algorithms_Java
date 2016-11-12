//Boyer-Moore字符串匹配算法（启发式地处理不匹配的字符）

public class BoyerMoore {

    private int[] right;
    private String pat;
    BoyerMoore(String pat) {
        this.pat = pat;
        int M = pat.length();
        int R = 256;
        right = new int[R];

        for (int c = 0; c < R; c++) {
            right[c] = -1;
        }
        for (int j < 0; j < M; j++) {
            right[pat.charAt(j)] = j;
        }
    }

    public int search(String txt) {
        int N = txt.length();
        int M = pat.length();
        int skip;
        for (int i = 0; i <= N-M; i += skip) {
            skip = 0;
            for (int j = M-1; j >= 0; j--) {
                if (pat.charAt(j) != txt.charAt(i+j)) {
                    skip = j - right[txt.charAt(i+j)];
                    if(skip < 1) skip = 1;
                    break;
                }
            }

            if (skip == 0) {
                return i;
            }
        }
        return N;
    }
}
############################################################################
         i       i+j
.  .  .  .  .  .  T  L  E  .  .  .  .  .
         N  E  E  D  L  E
                  j
                  T不在pattern中，将i增加就j+1              @启发式处理不匹配的字符@
                                                           @不匹配的字符不包含在模式字符串中@
.  .  .  .  .  .  T  L  E  .  .  .  .  .
                     N  E  E  D  L  E
                                    j(将j重置为M-1)
############################################################################
         i       i+j
.  .  .  .  .  .  N  L  E  .  .  .  .  .
         N  E  E  D  L  E
                  j
                  将i增大j-right['N']来将文本和模式中的N对齐  @启发式处理不匹配的字符@
                                                            @不匹配的字符包含在模式字符串中@
.  .  .  .  .  .  N  L  E  .  .  .  .  .
                  N  E  E  D  L  E
                                 j(将j重置为M-1)
############################################################################
启发式方法没有起作用
         i       i+j
.  .  .  .  .  .  E  L  E  .  .  .  .  .
         N  E  E  D  L  E
                  j
                  如果将文本和模式最右端的E对齐则会将       
                  模式字符串向左移动                         @启发式处理不匹配的字符@
                                                            @不匹配的字符包含在模式字符串中@ 
                  
.  .  .  .  .  .  E  L  E  .  .  .  .  .                
   N  E  E  D  L  E
因此只能将i加1
            i
.  .  .  .  .  .  E  L  E  .  .  .  .  .
            N  E  E  D  L  E
                           j(将j重置为M-1)
############################################################################
