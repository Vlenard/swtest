function calculateParkingFee(minutes, isWeekend, isVip) {
    if (!Number.isFinite(minutes) || minutes < 0) {
        throw new RangeError("Invalid parking time");
    }

    if (minutes < 15) {
        return 0;
    }

    let fee = Math.ceil(minutes / 60) * 600;

    if (isWeekend === true) {
        fee *= 0.5;
    }

    if (isVip === true) {
        fee = Math.max(0, fee - 20);
    }

    return Math.min(fee, 5000);
}

export { calculateParkingFee };
