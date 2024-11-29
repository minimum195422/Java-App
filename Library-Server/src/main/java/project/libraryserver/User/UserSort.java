package project.libraryserver.User;

import java.util.ArrayList;
import java.util.Comparator;

public class UserSort {
    public static void SortByIdAsc(ArrayList<User> users) {
        users.sort(Comparator.comparingInt(User::getId));
    }

    public static void SortByIdDesc(ArrayList<User> users) {
        users.sort(Comparator.comparingInt(User::getId).reversed());
    }

    public static void SortByNameAsc(ArrayList<User> users) {
        users.sort(Comparator.comparing((User user) -> (user.getFirst_name() + " " + user.getLast_name())));
    }

    public static void SortByNameDesc(ArrayList<User> users) {
        users.sort(Comparator.comparing((User user) -> (user.getFirst_name() + " " + user.getLast_name())).reversed());
    }

    public static void SortByEmailAsc(ArrayList<User> users) {
        users.sort(Comparator.comparing(User::getEmail));
    }

    public static void SortByEmailDesc(ArrayList<User> users) {
        users.sort(Comparator.comparing(User::getEmail).reversed());
    }

}
