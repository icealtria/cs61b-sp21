package timingtest;

import edu.princeton.cs.algs4.Stopwatch;

import java.util.stream.IntStream;

/**
 * Created by hug.
 */
public class TimeAList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        AList Ns = new AList<Integer>();
        AList times = new AList<Double>();
        AList opCounts = new AList<Integer>();

        int[] N = new int[8];
        for (int i = 0, j = 1000; i < N.length; i++, j *= 2) {
            N[i] = j;
        }

        for (int n : N) {
            AList list = new AList();

            Stopwatch sw = new Stopwatch();
            for (int i = 0; i < n; i++) {
                list.addLast(i);
            }
            double timeInSeconds = sw.elapsedTime();

            times.addLast(timeInSeconds);
            Ns.addLast(n);
            opCounts.addLast(n);
        }
        printTimingTable(Ns, times, opCounts);
    }
}
