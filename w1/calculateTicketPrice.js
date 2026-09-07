function calculateTicketPrice(age, isStudent, is3D) {
    if (!Number.isInteger(age) || age < 0 || age > 120) {
        throw new RangeError("Invalid age");
    }

    const basePrice = 3000;
    let discountRate = 0;

    if (age < 6) {
        return 0;
    }

    if (age <= 17) {
        discountRate = 0.30;
    } else if (age >= 65) {
        discountRate = 0.40;
    } else if (isStudent === true) {
        discountRate = 0.20;
    }

    const ticketPrice = basePrice * (1 - discountRate);
    return ticketPrice + (is3D === true ? 800 : 0);
}

export { calculateTicketPrice };
