import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;

public class FirstComeFirstServe {
    public static void main(String[] args) {
        int n; // n is the variable representing the number of processes in the algorithm
        int[] burst_time = new int[10];
        int[] wait_time = new int[10];
        int[] turnaround_time = new int[10];
        int average_wait_time = 0;
        int average_turnaround_time = 0;
        int i;
        int j;
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Enter total number of processes: ");
        n = sc.nextInt();

        System.out.print("\nEnter Process Burst Time: \n");
        for (i = 0; i < n; i++) {
            System.out.print("P[" + (i + 1) + "]: ");
            burst_time[i] = sc.nextInt();
        }

        wait_time[0] = 0;

        for (i = 1; i < n; i++) {
            wait_time[i] = 0;
            for (j = 0; j < i; j++) {
                wait_time[i] += burst_time[j];
            }
        }

        BufferedWriter writer = null;

        try {
            FileWriter fileWriter = new FileWriter("FCFS.txt");
            writer = new BufferedWriter(fileWriter);

            writer.write("Process\tBurst Time\tWaiting Time\tTurnaround Time");
            writer.newLine();

            for (i = 0; i < n; i++) {
                turnaround_time[i] = burst_time[i] + wait_time[i];
                average_wait_time += wait_time[i];
                average_turnaround_time += turnaround_time[i];

                String result = "P[" + (i + 1) + "]\t" + burst_time[i] + "\t\t" + wait_time[i] + "\t\t" + turnaround_time[i];
                System.out.println(getRandomColor(random) + result + "\u001B[0m");  // Print the colored result to the terminal
                writer.write(result);
                writer.newLine();
            }

            average_wait_time /= i;
            average_turnaround_time /= i;
            writer.write("\nAverage Waiting Time:\t" + average_wait_time);
            writer.newLine();
            writer.write("Average Turnaround Time:\t" + average_turnaround_time);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Results saved to FCFS.txt");
    }

    private static String getRandomColor(Random random) {
        String[] ANSI_COLORS = {
            "\u001B[31m", // Red
            "\u001B[32m", // Green
            "\u001B[33m", // Yellow
            "\u001B[34m"  // Blue
        };
        return ANSI_COLORS[random.nextInt(ANSI_COLORS.length)];
    }
}

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

{
    "editor.accessibilitySupport": "on",
    "haselerdev.aiquickfix.apiKey": "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",
    "haselerdev.aiquickfix.problemPrefix": "//fix this",
    "[python]": {
        "editor.formatOnType": true
    },
    "redhat.telemetry.enabled": true,
    "liveServer.settings.port": 3001,
    "git.autofetch": true,
    "liveServer.settings.donotShowInfoMsg": true,
    "workbench.colorTheme": "Visual Studio Dark",
    "extensions.autoUpdate": "onlyEnabledExtensions"
}
