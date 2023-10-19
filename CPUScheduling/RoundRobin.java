import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;

public class RoundRobin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i;
        int n;
        int time = 0;
        int remain;
        int temps = 0;
        int quantum;

        int wait_time = 0;
        int turnaround_time = 0;

        int[] arrival_time = new int[10];
        int[] burst_time = new int[10];
        int[] remaining_burst_time = new int[10];
        int[] completion_time = new int[10];
        int[] turnaround_time_list = new int[10];
        int[] waiting_time_list = new int[10];

        System.out.println("=============================================");
        System.out.println("              ROUND ROBIN SCHEDULING          ");
        System.out.println("============================================");
        System.out.print("Enter the total number of processes: ");
        n = sc.nextInt();
        remain = n;

        System.out.println("\nEnter the arrival time and burst time for each process: ");
        for (i = 0; i < n; i++) {
            System.out.print("\nProcess " + (i + 1) + ":\n");
            System.out.print("Arrival time: ");
            arrival_time[i] = sc.nextInt();
            System.out.print("Burst time: ");
            burst_time[i] = sc.nextInt();
            remaining_burst_time[i] = burst_time[i];
        }

        System.out.println("\n=============================================");
        System.out.print("Enter the quantum value: ");
        quantum = sc.nextInt();

        System.out.println("\n\n=========================================");
        System.out.println("                   GANTT CHART               ");
        System.out.println("=============================================");

        try {
            FileWriter fileWriter = new FileWriter("RoundRobin.txt");
            BufferedWriter writer = new BufferedWriter(fileWriter);

            writer.write("GANTT CHART");
            writer.newLine();

            System.out.print("0");
            writer.write("0");

            for (time = 0, i = 0; remain != 0;) {
                if (remaining_burst_time[i] <= quantum && remaining_burst_time[i] > 0) {
                    time += remaining_burst_time[i];
                    System.out.print("  |  P" + (i + 1) + "  |  " + time);
                    writer.write("  |  P" + (i + 1) + "  |  " + time);
                    remaining_burst_time[i] = 0;
                    temps = 1;
                } else if (remaining_burst_time[i] > 0) {
                    remaining_burst_time[i] -= quantum;
                    time += quantum;
                    System.out.print("  |  P" + (i + 1) + "  |  " + time);
                    writer.write("  |  P" + (i + 1) + "  |  " + time);
                }

                if (remaining_burst_time[i] == 0 && temps == 1) {
                    remain--;
                    completion_time[i] = time;
                    turnaround_time_list[i] = completion_time[i] - arrival_time[i];
                    waiting_time_list[i] = turnaround_time_list[i] - burst_time[i];
                    String result = "\n\nP[" + (i + 1) + "]\t:\tTurnaround Time = " + turnaround_time_list[i] + "\tWaiting Time = " + waiting_time_list[i];
                    System.out.println(result);  // Print the result to the terminal
                    writer.write(result);
                    wait_time += waiting_time_list[i];
                    turnaround_time += turnaround_time_list[i];
                    temps = 0;
                }

                if (i == n - 1) {
                    i = 0;
                } else if (arrival_time[i + 1] <= time) {
                    i++;
                } else {
                    i = 0;
                }
            }

            writer.newLine();
            writer.write("Average Waiting Time:\t" + wait_time / n);
            writer.newLine();
            writer.write("Average Turnaround Time:\t" + turnaround_time / n);

            writer.close();
            System.out.println("\nResults saved to RoundRobin.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
