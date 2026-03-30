package com.example.BookMyShow;

import java.time.LocalDateTime;

public class TicketCancellation {
    private final int ticketId;
    private final double refundAmount;
    private final LocalDateTime cancellationTime;
    private final RefundStatus refundStatus;

    public TicketCancellation(int ticketId, double refundAmount, LocalDateTime cancellationTime, RefundStatus refundStatus) {
        this.ticketId = ticketId;
        this.refundAmount = refundAmount;
        this.cancellationTime = cancellationTime;
        this.refundStatus = refundStatus;
    }

    public int getTicketId() {
        return ticketId;
    }

    public double getRefundAmount() {
        return refundAmount;
    }

    public LocalDateTime getCancellationTime() {
        return cancellationTime;
    }

    public RefundStatus getRefundStatus() {
        return refundStatus;
    }
}
