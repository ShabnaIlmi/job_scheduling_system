package assignmentCode;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        JobScheduler scheduler = new JobScheduler();
        Queue jobQueue = new Queue(100);
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nJob Scheduler Menu:");
            System.out.println("1. Add Job");
            System.out.println("2. Add Dependency");
            System.out.println("3. Display Jobs, their status, and Dependencies");
            System.out.println("4. Execute Next Ready Job");
            System.out.println("5. Check if a Job is Ready to Execute");
            System.out.println("6. Check if a Job is Completed");
            System.out.println("7. Display Jobs in Queue");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: // Adding a new Job
                    System.out.print("Enter Job ID: ");
                    String jobID = scanner.nextLine();
                    Job job = new Job(jobID);
                    scheduler.addJob(job);
                    jobQueue.enqueue(job);
                    break;

                case 2: // Adding a dependency between two existing Jobs
                    System.out.print("Enter Job ID: ");
                    String jobID1 = scanner.nextLine();
                    System.out.print("Enter Dependency Job ID: ");
                    String dependencyID = scanner.nextLine();
                    scheduler.addDependency(jobID1, dependencyID);
                    break;

                case 3: // Displaying the Jobs, their status and relevant dependencies
                    scheduler.display();
                    break;

                case 4: // Execute the next ready Job
                    Job nextJob = jobQueue.getNextReadyJob(scheduler);
                    if (nextJob != null) {
                        System.out.print("Confirm execution of Job " + nextJob.jobID + " (yes/no): ");
                        String confirmation = scanner.nextLine();
                        if (confirmation.equalsIgnoreCase("yes")) {
                            System.out.println("Executing Job: " + nextJob.jobID);
                            scheduler.executeJob(nextJob.jobID);
                            jobQueue.dequeue(nextJob.jobID);
                        } else {
                            System.out.println("Execution of Job " + nextJob.jobID + " cancelled.");
                        }
                    } else {
                        System.out.println("No ready job found.");
                    }
                    break;

                case 5: // Check if a Job is ready to be executed
                    System.out.print("Enter Job ID to check if it is ready: ");
                    String checkJobID = scanner.nextLine();
                    if (scheduler.isJobCompleted(checkJobID)) {
                        System.out.println("Job " + checkJobID + " has already been executed.");
                    } else if (scheduler.isReady(checkJobID)) {
                        System.out.println("Job " + checkJobID + " is ready to be executed.");
                    } else {
                        System.out.println("Job " + checkJobID + " is not ready to be executed.");
                    }
                    break;

                case 6: // Check if a Job is completed
                    System.out.print("Enter Job ID to check if it is completed: ");
                    String completedJobID = scanner.nextLine();
                    boolean isCompleted = scheduler.isJobCompleted(completedJobID);
                    if (isCompleted) {
                        System.out.println("Job " + completedJobID + " is completed.");
                    } else {
                        System.out.println("Job " + completedJobID + " is not completed.");
                    }
                    break;

                case 7: // Display Jobs in Queue
                    jobQueue.displayQueue();
                    break;

                case 8: // Exiting the program
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}
