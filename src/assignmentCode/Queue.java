package assignmentCode;

public class Queue {
    private int front;
    private int rear;
    private final int maxSize;
    private final Job[] queueArray;

    // Constructor for the Queue Class
    public Queue(int size) {
        maxSize = size;
        front = 0;
        rear = -1;
        queueArray = new Job[maxSize];
    }

    // Method to check if the queue is empty
    public boolean isEmpty() {
        return (rear < front);
    }

    // Method to check if the queue is full
    public boolean isFull() {
        return (rear == maxSize - 1);
    }

    // Method to add a job to the queue
    public void enqueue(Job job) {
        if (isFull()) {
            System.out.println("Queue is full. Cannot enqueue job " + job.jobID);
            return;
        }
        queueArray[++rear] = job;
    }

    // Method to remove a specific job from the queue by jobID
    public void dequeue(String jobID) {
        if (isEmpty()) {
            System.out.println("Queue is empty. Cannot dequeue.");
            return;
        }

        for (int i = front; i <= rear; i++) {
            if (queueArray[i].jobID.equals(jobID)) {
                for (int j = i; j < rear; j++) {
                    queueArray[j] = queueArray[j + 1];
                }
                queueArray[rear--] = null;
                break;
            }
        }
    }

    // Method to get the next ready job from the queue
    public Job getNextReadyJob(JobScheduler scheduler) {
        for (int i = front; i <= rear; i++) {
            if (scheduler.isReady(queueArray[i].jobID) && !queueArray[i].isCompleted) {
                return queueArray[i];
            }
        }
        return null;
    }

    // Method to display the jobs in the queue
    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
        } else {
            System.out.print("Jobs in the queue: ");
            for (int i = front; i <= rear; i++) {
                System.out.print(queueArray[i].jobID + " ");
            }
            System.out.println();
        }
    }
}
