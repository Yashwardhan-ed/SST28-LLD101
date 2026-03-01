public abstract class NotificationSender {
    protected final AuditLog audit;
    protected NotificationSender(AuditLog audit) { this.audit = audit; }
    public void send(Notification n) {
        Notification validated = validateNotification(n);
        sendNotification(validated);
    }
    public Notification validateNotification(Notification n) {
        return n;
    }
    public abstract void sendNotification(Notification n);
}
