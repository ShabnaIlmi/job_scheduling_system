package assignmentCode;

public class Node {
    String jobID;
    String dependencyID;
    Node next;

    // Constructor for the Node class
    public Node(String jobID, String dependencyID) {
        this.jobID = jobID;
        this.dependencyID = dependencyID;
        this.next = null;
    }
}
