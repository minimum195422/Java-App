package project.libraryclient.Client;

public interface ClientListener {
    void onUserInfoUpdated(String userId, String userName, String userMail);
}
