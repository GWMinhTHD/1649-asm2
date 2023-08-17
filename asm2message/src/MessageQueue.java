public class MessageQueue {
    private Node head;
    private Node tail;
    private int size = 0;
    private static class Node {
        private String message;
        private MessageQueue.Node next;
        public Node(String value) {
            this.message = value;
            this.next = null;
        }
    }

    public MessageQueue(){
        this.head= this.tail = null;
    }

    public void offer(String str) {
        if ((str.trim().length() > 250) || (str.trim().length() == 0)) throw new IllegalArgumentException("Message exceed 250 characters or empty");
        Node newNode = new Node(str.trim());
        if (this.head == null) {
            this.head = this.tail = newNode;
        } else {
            this.tail.next = newNode;
            this.tail = newNode;
            this.tail.next = null;
        }
        this.size++;
    }

    public String poll() {
        ensureNonEmpty();
        String element = this.head.message;
        if (this.size == 1) {
            this.head = this.tail = null;
        } else {
            this.head = this.head.next;
        }
        this.size--;
        return element;
    }

    public void ensureNonEmpty(){
        if (this.head == null) throw new IllegalStateException("Queue empty");
    }

    public String peek() {
        ensureNonEmpty();
        return this.head.message;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        if (head == null) return true;
        if (tail == null) return true;
        return false;
    }

    public void clear(){
        this.head = this.tail = null;
        System.out.println("Queue cleared");
    }

   public void displayWithoutPolling(){
        ensureNonEmpty();
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.message);
            temp = temp.next;
        }
   }

   //Poll queue and push stack
    public void process(MessageStack stack) {
        ensureNonEmpty();
        while (isEmpty() == false){
            stack.push(poll());
        }
    }
}
