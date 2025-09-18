package org.neolcr.solidpackage.solid;

/**
 * Dependency Inversion Principle (DIP) states that:
 *  1. High-level modules should not depend on low-level modules. Both should depend on abstractions.
 *  2. Abstractions should not depend on details. Details should depend on abstractions.
 *  This principle helps to reduce the coupling between high-level and low-level modules, making the system more flexible and easier to maintain.
 */
class DependencyInversionPrinciple {
    public static void main(String[] args) {
        MessageService emailService = new EmailService();
        Notification notification = new Notification(emailService);
        notification.notifyUser("Hello, DIP!", "user@example.com");

        MessageService smsService = new SMSService();
        Notification smsNotification = new Notification(smsService);
        smsNotification.notifyUser("Hello, DIP via SMS!", "123-456-7890");
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

class SMSService implements MessageService {
    @Override
    public void sendMessage(String message, String recipient) {
        System.out.println("SMS sent to " + recipient + ": " + message);
    }
}

// High-level module
// Depends on the abstraction (MessageService) rather than concrete implementations
class Notification {
    private final MessageService messageService;

    // MessageService abstraction is injected via constructor
    public Notification(MessageService messageService) {
        this.messageService = messageService;
    }

    public void notifyUser(String message, String recipient) {
        messageService.sendMessage(message, recipient);
    }
}

