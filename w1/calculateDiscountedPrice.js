function calculateDiscountedPrice(orderValue, isVip) {
    if (!Number.isFinite(orderValue) || orderValue < 0) {
        throw new RangeError("Invalid order value");
    }

    let discountRate = 0;

    if (orderValue > 10000 && orderValue < 25000) {
        discountRate = 0.05;
    } else if (orderValue >= 25000 && orderValue <= 50000) {
        discountRate = 0.10;
    } else if (orderValue > 50000) {
        discountRate = 0.15;
    }

    if (isVip === true) {
        discountRate += 0.05;
    }

    discountRate = Math.min(discountRate, 0.20);
    return orderValue * (1 - discountRate);
}

export { calculateDiscountedPrice };
