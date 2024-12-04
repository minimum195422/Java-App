package project.libraryclient.Client;

public interface ClientListener {
    void onUserInfoUpdated(int userId, String userName, String userMail);
}
