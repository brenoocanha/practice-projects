class BookingValidator {
  validateDates(startDate: Date, endDate: Date): void {
    if (startDate >= endDate) {
      throw new Error('Data de check-out deve ser após a data de check-in');
    }
  }
}

class BookingPriceCalculator {
  calculatePrice(dailyRate: number, startDate: Date, endDate: Date): number {
    const durationInDays = Math.ceil(
      (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24)
    );
    return dailyRate * durationInDays;
  }
}

class EmailService {
  sendConfirmation(email: string): void {
    console.log(`Enviando e-mail de confirmação para ${email}`);
  }
}

class BookingService {
  constructor(
    private validator: BookingValidator,
    private priceCalculator: BookingPriceCalculator,
    private emailService: EmailService
  ) {}

  processBooking(bookingDetails: any): void {
    this.validator.validateDates(
      bookingDetails.startDate,
      bookingDetails.endDate
    );

    const totalPrice = this.priceCalculator.calculatePrice(
      bookingDetails.dailyRate,
      bookingDetails.startDate,
      bookingDetails.endDate
    );
    console.log(`Preço total calculado: R$${totalPrice}`);
    this.emailService.sendConfirmation(bookingDetails.email);
  }
}
