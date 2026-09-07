const assert = require("node:assert/strict");
const { calculateShippingCost } = require("./shipping");

const testCases = [
    { name: "Magyarország, 5 kg alatt", input: [10000, 4.99, "HU", false], expected: 1500 },
    { name: "Pontosan 5 kg", input: [10000, 5, "HU", false], expected: 2500 },
    { name: "20 kg-ig", input: [10000, 20, "HU", false], expected: 2500 },
    { name: "20 kg felett", input: [10000, 20.01, "HU", false], expected: 5000 },
    { name: "Külföldi, 5 kg alatt", input: [10000, 4, "DE", false], expected: 3000 },
    { name: "Külföldi, 20 kg felett", input: [10000, 21, "DE", false], expected: 10000 },
    { name: "Magyarország, 20000 Ft pontosan", input: [20000, 4, "HU", false], expected: 1500 },
    { name: "Magyarország, 20000 Ft felett ingyenes", input: [20001, 21, "HU", false], expected: 0 },
    { name: "Kisbetűs országkód", input: [10000, 4, "hu", false], expected: 1500 },
    { name: "Expressz magyar szállítás", input: [10000, 4, "HU", true], expected: 2250 },
    { name: "Expressz külföldi szállítás", input: [10000, 5, "de", true], expected: 7500 },
    { name: "Nulla rendelési érték és tömeg", input: [0, 0, "HU", false], expected: 1500 },
    { name: "Negatív rendelési érték elutasítása", input: [-1, 4, "HU", false], expectedError: true },
    { name: "Negatív tömeg elutasítása", input: [10000, -1, "HU", false], expectedError: true },
    { name: "Nem kétbetűs országkód elutasítása", input: [10000, 4, "HUN", false], expectedError: true },
];

for (const testCase of testCases) {
    if (testCase.expectedError) {
        assert.throws(() => calculateShippingCost(...testCase.input));
    } else {
        assert.equal(calculateShippingCost(...testCase.input), testCase.expected);
    }
    console.log(`PASS: ${testCase.name}`);
}

console.log(`${testCases.length} teszteset sikeres.`);
