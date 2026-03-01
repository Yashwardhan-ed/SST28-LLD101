public class WhatsAppSender extends NotificationSender {
    public WhatsAppSender(AuditLog audit) { super(audit); }

    @Override
    public void sendNotification(Notification n) {
        System.out.println("WA -> to=" + n.phone + " body=" + n.body);
        audit.add("wa sent");
    }
    @Override
    public Notification validateNotification(Notification n) {
        if (n.phone == null || !n.phone.startsWith("+")) {
            audit.add("WA failed");
            throw new IllegalArgumentException("phone must start with + and country code");
        }
        return n;
    }
}
