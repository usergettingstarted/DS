package bullyalgo;

import java.util.Scanner;

public class Bully {
    static boolean[] state = new boolean[5]; // true = up, false = down

    public static void up(int processId) {
        if (state[processId - 1]) {
            System.out.println("Process " + processId + " is already up.");
        } else {
            state[processId - 1] = true;
            System.out.println("Process " + processId + " is brought up and held election.");
            for (int i = processId + 1; i <= 5; i++) {
                System.out.println("Election message sent from Process " + processId + " to Process " + i);
            }
            for (int i = 5; i > processId; i--) {
                if (state[i - 1]) {
                    System.out.println("Alive message received from Process " + i + " to Process " + processId);
                    break;
                }
            }
        }
    }

    public static void down(int processId) {
        if (!state[processId - 1]) {
            System.out.println("Process " + processId + " is already down.");
        } else {
            state[processId - 1] = false;
            System.out.println("Process " + processId + " is brought down.");
        }
    }

    public static void message(int senderId) {
        if (!state[senderId - 1]) {
            System.out.println("Process " + senderId + " is down and cannot send messages.");
            return;
        }

        if (state[4]) {
            System.out.println("Process 5 is alive. Message delivered successfully.");
        } else {
            System.out.println("Process 5 (Coordinator) is down.");
            System.out.println("Process " + senderId + " is initiating an election.");
            for (int i = senderId + 1; i <= 5; i++) {
                System.out.println("Election message sent from Process " + senderId + " to Process " + i);
            }

            for (int i = 5; i >= 1; i--) {
                if (state[i - 1]) {
                    System.out.println("Process " + i + " is elected as the new Coordinator.");
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        // Initially, all processes are up
        for (int i = 0; i < 5; i++) {
            state[i] = true;
        }

        System.out.println("5 Active Processes: P1, P2, P3, P4, P5");
        System.out.println("Process 5 is initially the Coordinator.");

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Bring up a process");
            System.out.println("2. Bring down a process");
            System.out.println("3. Send a message");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter process number to bring up (1-5): ");
                    int up = sc.nextInt();
                    if (up < 1 || up > 5) {
                        System.out.println("Invalid process number.");
                    } else if (up == 5) {
                        state[4] = true;
                        System.out.println("Process 5 is brought up and becomes Coordinator.");
                    } else {
                        up(up);
                    }
                    break;

                case 2:
                    System.out.print("Enter process number to bring down (1-5): ");
                    int down = sc.nextInt();
                    if (down < 1 || down > 5) {
                        System.out.println("Invalid process number.");
                    } else {
                        down(down);
                    }
                    break;

                case 3:
                    System.out.print("Enter sender process number (1-5): ");
                    int sender = sc.nextInt();
                    if (sender < 1 || sender > 5) {
                        System.out.println("Invalid process number.");
                    } else {
                        message(sender);
                    }
                    break;

                case 4:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 4);

        sc.close();
    }
}
