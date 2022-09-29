package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    public static void main(String[] args) {
//        randomizedTest();
        Test();
    }

    @Test
    public static void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        int N = 2000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 2);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 2) {
                // size
                int size = L.size();
                System.out.println("size: " + size);
            } else if (operationNumber == 1) {
                if (L.size() > 0) {
                    L.removeLast();
                    System.out.println("removeLast");
                }
            }
        }
    }

    public static void Test(){
        AListNoResizing A = new AListNoResizing<>();
        BuggyAList B = new BuggyAList();

        int N = 2000;

        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 3);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                A.addLast(randVal);
                B.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 2) {
                assertEquals(A.size(), B.size());
            } else if (operationNumber == 1) {
                if (A.size() > 0) {
                    assertEquals(A.removeLast(), B.removeLast());
                    System.out.println("removeLast");
                }
            }
        }
    }
}
