import java.util.Stack;

public class Histogram {

    public static int[] smallerLeft(int[] A) {
        int[] ans = new int[A.length];
        Stack<Integer> s = new Stack<>();
        for(int i = 0; i < A.length; i++) {
            while(!s.empty() && A[s.peek()] > A[i]) s.pop();
            if(s.empty()) ans[i] = -1;
            else ans[i] = s.peek();
            s.push(i);
        }
        return ans;
    }

    public static int[] smallerRight(int[] A) {
        int[] ans = new int[A.length];
        Stack<Integer> s = new Stack<>();
        for(int i = A.length - 1; i >= 0; i--) {
            while(!s.empty() && A[s.peek()] > A[i]) s.pop();
            if(s.empty()) ans[i] = ans.length;
            else ans[i] = s.peek();
            s.push(i);
        }
        return ans;
    }

    public static int[] largerLeft(int[] A) {
        int[] ans = new int[A.length];
        Stack<Integer> s = new Stack<>();
        for(int i = 0; i < A.length; i++) {
            while(!s.empty() && A[s.peek()] < A[i]) s.pop();
            if(s.empty()) ans[i] = -1;
            else ans[i] = s.peek();
            s.push(i);
        }
        return ans;
    }

    public static int[] largerRight(int[] A) {
        int[] ans = new int[A.length];
        Stack<Integer> s = new Stack<>();
        for(int i = A.length - 1; i >= 0; i--) {
            while(!s.empty() && A[s.peek()] < A[i]) s.pop();
            if(s.empty()) ans[i] = ans.length;
            else ans[i] = s.peek();
            s.push(i);
        }
        return ans;
    }
}
