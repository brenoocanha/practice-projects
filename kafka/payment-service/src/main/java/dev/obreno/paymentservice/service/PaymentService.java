package dev.obreno.paymentservice.service;

import dev.obreno.paymentservice.model.Payment;

public interface PaymentService {

    void sendPayment(Payment payment);
}
