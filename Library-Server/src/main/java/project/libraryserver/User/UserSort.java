package project.libraryserver.User;
import java.util.Comparator;
import java.util.List;

public class UserSort {
    public static void SortByIdAsc(List<User> users) {
        users.sort(Comparator.comparingInt(User::getId));
    }

    public static void SortByIdDesc(List<User> users) {
        users.sort(Comparator.comparingInt(User::getId).reversed());
    }

    public static void SortByNameAsc(List<User> users) {
        users.sort(Comparator.comparing((User user) -> (user.getFirst_name() + " " + user.getLast_name())));
    }

    public static void SortByNameDesc(List<User> users) {
        users.sort(Comparator.comparing((User user) -> (user.getFirst_name() + " " + user.getLast_name())).reversed());
    }

    public static void SortByEmailAsc(List<User> users) {
        users.sort(Comparator.comparing(User::getEmail));
    }

    public static void SortByEmailDesc(List<User> users) {
        users.sort(Comparator.comparing(User::getEmail).reversed());
    }

}