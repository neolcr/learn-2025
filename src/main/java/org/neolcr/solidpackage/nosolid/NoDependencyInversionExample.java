package org.neolcr.solidpackage.nosolid;

// Usage example
class NoDependencyInversionExample {
    public static void main(String[] args) {
        EmailService emailService = new EmailService();
        Notification notification = new Notification(emailService);
        notification.notifyUser("Hello, no DIP!", "user@example.com");
    }
}

// Low-level module
class EmailService {
    public void sendEmail(String message, String recipient) {
        System.out.println("Email sent to " + recipient + ": " + message);
    }
}

// High-level module directly depends on EmailService
class Notification {
    private final EmailService emailService;

    public Notification(EmailService emailService) {
        this.emailService = emailService;
    }

    public void notifyUser(String message, String recipient) {
        emailService.sendEmail(message, recipient);
    }
}

