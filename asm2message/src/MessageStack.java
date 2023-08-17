public class MessageStack {
    private MessageStack.Node top;
    private int size = 0;
    private static class Node {
        private String message;
        private MessageStack.Node prev;
        public Node(String value) {
            this.message = value;
            this.prev = null;
        }
    }

    public MessageStack(){
        this.top= null;
    }

    public void push (String element) {
        Node newNode = new Node(element);
        newNode.prev = top;
        top = newNode;
        this.size++;
    }

    public String pop(){
        ensureNonEmpty();
        String data = this.top.message;
        this.top = this.top.prev;
        this.size--;
        return data;
    }

    public void ensureNonEmpty(){
        if (this.top == null) throw new IllegalStateException("Stack empty");
    }

    public boolean isEmpty() { return top == null; }

    public void clear(){
        this.top = null;
        System.out.println("Stack cleared");
    }

    public void displayWithoutPopping() {
        ensureNonEmpty();
        Node temp = top;
        while (temp != null) {
            System.out.println(temp.message);
            temp = temp.prev;
        }
    }

    //Display and pop
    public void process() {
        ensureNonEmpty();
        while (isEmpty() == false){
            System.out.println(pop());
        }
    }

    public String find(String str) {
        ensureNonEmpty();
        Node temp = top;
        while (temp != null) {
            if (temp.message.contains(str.trim()) == true){
                return temp.message;
            }
            temp = temp.prev;
        }
        return null;
    }

}
