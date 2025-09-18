package org.neolcr.solidpackage.solid;

// Usage example
class DependencyInversionPrinciple {
    public static void main(String[] args) {
        MessageService emailService = new EmailService();
        Notification notification = new Notification(emailService);
        notification.notifyUser("Hello, DIP!", "user@example.com");
    }
}

// Abstraction
interface MessageService {
    void sendMessage(String message, String recipient);
}

// Low-level module
class EmailService implements MessageService {
    @Override
    public void sendMessage(String message, String recipient) {
        System.out.println("Email sent to " + recipient + ": " + message);
    }
}

// High-level module
class Notification {
    private final MessageService messageService;

    public Notification(MessageService messageService) {
        this.messageService = messageService;
    }

    public void notifyUser(String message, String recipient) {
        messageService.sendMessage(message, recipient);
    }
}

