import commands.EventCommands;
import manager.EventManager;
import manager.UserManager;
import model.Event;
import model.EventType;
import model.User;

import java.util.List;
import java.util.Scanner;

public class EventRegister implements EventCommands {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static UserManager userManager = new UserManager();
    private static EventManager eventManager = new EventManager();

    public static void main(String[] args) {
        boolean isRun = true;
        while (isRun) {
            EventCommands.printCommands();
            int command = Integer.parseInt(SCANNER.nextLine());
            switch (command) {
                case EXIT:
                    isRun = false;
                    break;
                case ADD_EVENT:
                    addEvent();
                    break;
                case ADD_USER:
                    addUser();
                    break;
                case SHOW_ALL_EVENTS:
                    showAllEvents();
                    break;
                case SHOW_ALL_USER:
                    showAllUser();
                    break;
                default:
                    System.out.println("Invalid command!");
            }
        }
    }

    private static void showAllUser() {
        List<User> all = userManager.getAll();
        for (User user : all) {
            System.out.println(user);
        }
    }

    private static void showAllEvents() {
        List<Event> all = eventManager.getAll();
        for (Event event : all) {
            System.out.println(event);
        }
    }

    private static void addUser() {
        showAllEvents();
        System.out.println("please choose event's id");
        int eventId = Integer.parseInt(SCANNER.nextLine());

        System.out.println("Please input user's name,surname,email");
        String userDataStr = SCANNER.nextLine();
        String[] userData = userDataStr.split(",");
        User user = User.builder()
                .name(userData[0])
                .surname(userData[1])
                .email(userData[2])
                .event(eventManager.getById(eventId))
                .build();
        userManager.add(user);
        System.out.println("user added");
    }

    private static void addEvent() {
        System.out.println("Please input event's name,place,isOnline,eventType(FILM,SPORT,GAME),price");
        String eventDataStr = SCANNER.nextLine();
        String[] eventData = eventDataStr.split(",");
        Event event = Event.builder()
                .name(eventData[0])
                .place(eventData[1])
                .isOnline(Boolean.valueOf(eventData[2]))
                .eventType(EventType.valueOf(eventData[3]))
                .price(Double.parseDouble(eventData[4]))
                .build();
        eventManager.add(event);
        System.out.println("event added");
    }
}
