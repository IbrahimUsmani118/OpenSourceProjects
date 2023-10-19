 import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;

public class ShortestJobFirst {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n, temp, tt = 0, i, j;
        float average_turnaround_time = 0f, average_wait_time = 0f;

        System.out.print("Enter number of processes: ");
        n = sc.nextInt();

        int[] a = new int[n];
        int[] b = new int[n];
        int[] e = new int[n];
        int[] turnaround_time = new int[n];
        int[] wait_time = new int[n];

        for (i = 0; i < n; i++) {
            System.out.print("Enter arrival time for process " + (i + 1) + ": ");
            a[i] = sc.nextInt();
            System.out.print("Enter burst time for process " + (i + 1) + ": ");
            b[i] = sc.nextInt();
        }

        for (i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (j = i + 1; j < n; j++) {
                if (b[j] < b[minIndex]) {
                    minIndex = j;
                }
            }
            temp = b[minIndex];
            b[minIndex] = b[i];
            b[i] = temp;
            temp = a[minIndex];
            a[minIndex] = a[i];
            a[i] = temp;
        }

        for (i = 0; i < n; i++) {
            if (a[i] > tt) {
                tt = a[i];
            }
            e[i] = tt + b[i];
            tt = e[i];
            turnaround_time[i] = e[i] - a[i];
            wait_time[i] = turnaround_time[i] - b[i];
            average_turnaround_time += turnaround_time[i];
            average_wait_time += wait_time[i];
        }

        average_turnaround_time /= n;
        average_wait_time /= n;

        // Build a string with the process table
        StringBuilder sb = new StringBuilder();
        sb.append("Process    Arrival-time      Burst-time        Wait-time     Turnaround-time\n");
        for (i = 0; i < n; i++) {
            sb.append(String.format("P%-10d%-18d%-18d%-15d%-15d\n", i + 1, a[i], b[i], wait_time[i], turnaround_time[i]));
        }
        String processTable = sb.toString();

        System.out.println(processTable);  // Print the process table to the terminal

        try {
            FileWriter fileWriter = new FileWriter("SJF.txt");
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write(processTable);
            writer.newLine();
            writer.write(String.format("Average Wait Time=%.2f Average Turnaround Time=%.2f", average_wait_time, average_turnaround_time));
            writer.close();
            System.out.println("Results saved to SJF.txt");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
