package assignmentCode;


public class JobScheduler {
    private final Job[] jobDetails;
    private int count;
    private final customLinkedList dependencyList;
    private final int max_size = 100;

    // Constructor to initialize the JobScheduler
    public JobScheduler() {
        jobDetails = new Job[max_size];
        count = 0;
        dependencyList = new customLinkedList();
    }

    // Method to add a new job to the scheduler
    public void addJob(Job job) {
        if (isFull()) {
            System.out.println("Job Scheduler is full and cannot add any more jobs.");
            return;
        }
        if (jobIDExists(job.jobID)) {
            System.out.println("Job with ID '" + job.jobID + "' is already added.");
            return;
        }
        System.out.println("Job with ID '" + job.jobID + "' added successfully");
        jobDetails[count++] = job;
    }

    // Method to check if the job scheduler is empty
    public boolean isEmpty() {
        return count == 0;
    }

    // Method to check if the job scheduler is full
    public boolean isFull() {
        return count == max_size;
    }

    // Method to check if a job ID already exists in the scheduler
    public boolean jobIDExists(String jobID) {
        for (int i = 0; i < count; i++) {
            if (jobDetails[i].jobID.equals(jobID)) {
                return true;
            }
        }
        return false;
    }

    // Method to get a job by its ID
    public Job getJob(String jobID) {
        for (int i = 0; i < count; i++) {
            if (jobDetails[i].jobID.equals(jobID)) {
                return jobDetails[i];
            }
        }
        return null;
    }

    // Method to add a dependency between two jobs
    public void addDependency(String jobID, String dependencyID) {
        if (!jobIDExists(jobID)) {
            System.out.println("Job with given ID does not exist.");
            return;
        }
        if (dependencyList.isAlready(jobID, dependencyID)) {
            System.out.println("Dependency from Job " + jobID + " to Job " + dependencyID + " already exists.");
            return;
        }
        dependencyList.addNode(jobID, dependencyID);
    }

    // Method to check if a job is ready to be executed
    public boolean isReady(String jobID) {
        if (!jobIDExists(jobID)) {
            System.out.println("Job with ID " + jobID + " does not exist.");
            return false;
        }
        Job job = getJob(jobID);
        if (job != null && job.isCompleted) {
            return false;
        }
        return dependencyList.isReady(jobID, jobDetails, count);
    }

    // Method to execute a job
    public void executeJob(String jobID) {
        Job job = getJob(jobID);
        if (job != null) {
            if (job.isCompleted) {
                System.out.println("Job " + jobID + " has already been executed.");
            } else {
                job.isCompleted = true;
                System.out.println("Job " + jobID + " executed successfully.");
            }
        } else {
            System.out.println("Job with ID " + jobID + " does not exist.");
        }
    }

    // Method to check if a job is completed
    public boolean isJobCompleted(String jobID) {
        Job job = getJob(jobID);
        if (job != null) {
            return job.isCompleted;
        } else {
            System.out.println("Job with ID " + jobID + " does not exist.");
            return false;
        }
    }

    // Method to display all jobs and their dependencies
    public void display() {
        if (isEmpty()) {
            System.out.println("No jobs to display.");
        } else {
            for (int i = 0; i < count; i++) {
                System.out.println("JobID: " + jobDetails[i].jobID + ", Completed: " + jobDetails[i].isCompleted);
            }
            dependencyList.display();
        }
    }
}
