function classifyPackage(weight, length, width, height) {
    const values = [weight, length, width, height];

    if (values.some(value => !Number.isFinite(value) || value <= 0)) {
        return "INVALID";
    }

    if (weight <= 2 && length <= 30 && width <= 30 && height <= 30) {
        return "SMALL";
    }

    if (weight <= 10 && length <= 60 && width <= 60 && height <= 60) {
        return "MEDIUM";
    }

    if (weight <= 30 && length <= 120 && width <= 120 && height <= 120) {
        return "LARGE";
    }

    return "OVERSIZE";
}

export { classifyPackage };
