package assignmentCode;

public class customLinkedList {
    Node head;
    Node tail;

    // Constructor for the customLinkedList class
    public customLinkedList() {
        this.head = null;
        this.tail = null;
    }

    // Method to add a new dependency node
    public void addNode(String jobID, String dependencyID) {
        Node newNode = new Node(jobID, dependencyID);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    // Method to check if a dependency already exists
    public boolean isAlready(String jobID, String dependencyID) {
        Node current = head;
        while (current != null) {
            if (current.jobID.equals(jobID) && current.dependencyID.equals(dependencyID)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Method to check if a job is ready to be executed
    public boolean isReady(String jobID, Job[] jobDetails, int jobCount) {
        Node current = head;
        while (current != null) {
            if (current.jobID.equals(jobID)) {
                if (!isDependencyCompleted(current.dependencyID, jobDetails, jobCount)) {
                    return false;
                }
            }
            current = current.next;
        }
        return true;
    }

    // Method to check if a dependency job is completed
    private boolean isDependencyCompleted(String dependencyID, Job[] jobDetails, int jobCount) {
        for (int i = 0; i < jobCount; i++) {
            if (jobDetails[i].jobID.equals(dependencyID) && !jobDetails[i].isCompleted) {
                return false;
            }
        }
        return true;
    }

    // Method to display the dependencies
    public void display() {
        Node current = head;
        while (current != null) {
            System.out.println("JobID: " + current.jobID + " -> DependencyID: " + current.dependencyID);
            current = current.next;
        }
    }
}
