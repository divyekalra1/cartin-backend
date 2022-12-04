package io.cartin.backend.email;

public interface EmailSender {
    void send(String from, String to, String content);
}
