import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class SwitchBlade {

    private static void processCommand(String input, myList taskList) {
        String command = input.split("\\s+")[0];

        switch (command.toLowerCase(Locale.ROOT)) {
            case "list":
                Ui.printList(taskList);
                break;
            case "done":
                if (input.split("\\s+").length == 2) {
                    int index = Integer.parseInt(input.split("\\s+")[1]);
                    taskList.markCompleted(index - 1);
                } else {
                    System.out.println("Too many arguments, please give me just 1 task to mark as completed");
                }
                break;
            case "todo":
                addTask(input,taskList);
                break;
            case "deadline":
                addDeadline(input, taskList);
                break;
            case "event":
                addEvent(input, taskList);
                break;
            case "delete":
                if (input.split("\\s+").length == 2) {
                    int index = Integer.parseInt(input.split("\\s+")[1]);
                    taskList.delete(index - 1);
                } else {
                    System.out.println("Too many arguments, please give me just 1 task to mark as completed");
                }
                break;
            default:
                System.out.println("Unfortunately I don't know what you want me to do :L");
        }
    }

    private static void addTask(String input, myList taskList) {
        if (input.replaceAll("todo", "").length() > 0) {
            taskList.addTask(input);
        } else {
            System.out.println("It seems like you have not specified the description correctly :(");
        }
    }

    private static void addDeadline(String input, myList taskList) {
        if (input.contains("/by") && (findDatetime(input, "/by") != null)) {
            String datetime = findDatetime(input, "/by");
            String description = input.replace("/by" + datetime, "");
            taskList.addDeadline(description, datetime);
        } else {
            System.out.println("It seems like you have not specified the deadline correctly :(");
        }
    }

    private static void addEvent(String input, myList taskList) {
        if (input.contains("/at") && (findDatetime(input, "/at") != null)) {
            String datetime = findDatetime(input, "/at");
            String description = input.replace("/at" + datetime, "");
            taskList.addEvent(description, datetime);
        } else {
            System.out.println("It seems like you have not specified the date and time correctly :(");
        }
    }

    private static String findDatetime(String input, String argument) {
        int argumentIndex = input.lastIndexOf(argument);
        String output = input.substring(argumentIndex + argument.length());

        if (output.replaceAll("\\s", "").length() < 1) {
            System.out.println("Please enter a valid date or time");
            return null;
        }
        return output;
    }

    public static void main(String[] args) {

        Ui.init();
        myList taskList = new myList();
        taskList.retrieve();

        Scanner sc = new Scanner(System.in);
        String input = "bye";

        if (sc.hasNext())
            input = sc.nextLine();

        while (!input.equalsIgnoreCase("bye")) {
            processCommand(input, taskList);

            if (sc.hasNext())
                input = sc.nextLine();
        }

        Ui.shutdown();
    }
}
