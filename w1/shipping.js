function calculateShippingCost(orderValue, weight, country, express) {
    if (!Number.isFinite(orderValue) || orderValue < 0) {
        throw new Error("A rendelési érték nem lehet negatív, és számnak kell lennie.");
    }
    if (!Number.isFinite(weight) || weight < 0) {
        throw new Error("A tömeg nem lehet negatív, és számnak kell lennie.");
    }
    if (typeof country !== "string" || !/^[a-z]{2}$/i.test(country)) {
        throw new Error("Az országkód kétbetűs ISO-kód kell legyen.");
    }

    const normalizedCountry = country.toUpperCase();
    if (normalizedCountry === "HU" && orderValue > 20000) {
        return 0;
    }

    let shippingCost;
    if (weight < 5) {
        shippingCost = 1500;
    } else if (weight <= 20) {
        shippingCost = 2500;
    } else {
        shippingCost = 5000;
    }

    if (normalizedCountry !== "HU") {
        shippingCost *= 2;
    }
    if (express === true) {
        shippingCost *= 1.5;
    }

    return shippingCost;
}

module.exports = { calculateShippingCost };
