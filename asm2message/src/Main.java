import java.util.Scanner;

public class Main {
    public static void menu(){
        System.out.println("Choose an option");
        System.out.println("1. Enqueue message");
        System.out.println("2. View current message queue");
        System.out.println("3. Clear queue");
        System.out.println("4. Process queue (Push in stack the dequeue'd message)");
        System.out.println("5. View current message stack");
        System.out.println("6. Process stack (Display and pop all message in stack)");
        System.out.println("7. Check if stack contain a certain message (case sensitive)");
        System.out.println("8. Clear stack");
        System.out.println("0. Exit");
    }
    public static void main(String[] args) {
        MessageQueue queue = new MessageQueue();
        MessageStack stack = new MessageStack();
        Scanner scn =  new Scanner(System.in);
        String i = "";
        while (i.equals("0") == false){
            menu();
            i = scn.nextLine().trim();
            switch (i) {
                case "1": {
                    System.out.println("Input new message for queue");
                    String str = scn.nextLine();
                    try{
                        queue.offer(str);
                        System.out.println("Message enqueue'd. Press enter to go back to the menu");
                    }catch (IllegalArgumentException e){
                        System.out.println(e.getMessage());
                        System.out.println("Press enter to go back to the menu");
                    }
                    scn.nextLine();
                    System.out.println();
                    break;
                }
                case "2":{
                    try {
                        queue.displayWithoutPolling();
                    }catch (IllegalStateException e){
                        System.out.println(e.getMessage());
                    }
                    System.out.println("Press enter to go back to the menu");
                    scn.nextLine();
                    System.out.println();
                    break;
                }
                case "3":{
                    queue.clear();
                    System.out.println("Press enter to return to menu");
                    scn.nextLine();
                    System.out.println();
                    break;
                }
                case "4":{
                    try{
                        long startTime = System.nanoTime();
                        queue.process(stack);
                        long endTime = System.nanoTime();
                        System.out.println("Queue to stack ran in: " +(endTime - startTime)+ "ns");
                    }catch (IllegalStateException e){
                        System.out.println(e.getMessage());
                    }
                    System.out.println("Press enter to return to menu");
                    scn.nextLine();
                    System.out.println();
                    break;
                }
                case "5":{
                    try{
                        stack.displayWithoutPopping();
                    }catch (IllegalStateException e){
                        System.out.println(e.getMessage());
                    }
                    System.out.println("Press enter to return to menu");
                    scn.nextLine();
                    System.out.println();
                    break;
                }
                case "6":{
                    try{
                        stack.process();
                    }catch (IllegalStateException e){
                        System.out.println(e.getMessage());
                    }
                    System.out.println("Press enter to return to menu");
                    scn.nextLine();
                    System.out.println();
                    break;
                }
                case "7":{
                    System.out.println("Input content for searching");
                    String str = scn.nextLine();
                    try{
                        String res = null;
                        long startTime = System.nanoTime();
                        res = stack.find(str);
                        long endTime = System.nanoTime();
                        System.out.println("Search ran in: " +(endTime - startTime)+ "ns");
                        if (res == null){
                            System.out.println("There is no message with: \"" + str + "\"" );
                        }else{
                            System.out.print("First found message with \"" + str + "\": " );
                            System.out.println(res);
                        }
                    }catch (IllegalStateException e){
                        System.out.println(e.getMessage());
                    }
                    System.out.println("Press enter to return to menu");
                    scn.nextLine();
                    System.out.println();
                    break;
                }
                case "8":{
                    stack.clear();
                    System.out.println("Press enter to return to menu");
                    scn.nextLine();
                    System.out.println();
                    break;
                }
                case "0":{
                    break;
                }
                default:{
                    System.out.println("Invalid option, press enter to return");
                    scn.nextLine();
                    System.out.println();
                    break;
                }
            }
        }
    }
}