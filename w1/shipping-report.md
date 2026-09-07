# A szállítási díj – tesztelési dokumentáció

## 1. A specifikáció rövid értelmezése

- 5 kg alatt a díj 1500 Ft.
- 5 kg-tól 20 kg-ig a díj 2500 Ft. Ezért a pontosan 5 kg és a pontosan 20 kg is ebbe a sávba tartozik.
- 20 kg felett a díj 5000 Ft.
- Magyarországon 20 000 Ft feletti rendelésnél a díj 0 Ft. A pontosan 20 000 Ft még nem ingyenes.
- Külföldön az alapdíj kétszeresét kell fizetni.
- Expressz szállításnál az így kapott díj 50%-kal nő.
- A rendelési érték és a tömeg nem lehet negatív.
- Az országkód pontosan két betűből áll, és a kisbetűs forma is elfogadott.

## 2–3. Tesztesetek és elvárt eredmények

A részletes, futtatható tesztek a `shipping.test.js` fájlban vannak. A tesztek a következő eseteket fedik le:

| # | Eset | Bemenet `(érték, tömeg, ország, expressz)` | Elvárt eredmény |
|---|---|---|---:|
| 1 | 5 kg alatt, belföld | `(10000, 4.99, HU, false)` | 1500 Ft |
| 2 | Pontosan 5 kg | `(10000, 5, HU, false)` | 2500 Ft |
| 3 | Pontosan 20 kg | `(10000, 20, HU, false)` | 2500 Ft |
| 4 | 20 kg felett | `(10000, 20.01, HU, false)` | 5000 Ft |
| 5 | Külföld, 5 kg alatt | `(10000, 4, DE, false)` | 3000 Ft |
| 6 | Külföld, 20 kg felett | `(10000, 21, DE, false)` | 10000 Ft |
| 7 | Pontosan 20000 Ft | `(20000, 4, HU, false)` | 1500 Ft |
| 8 | 20000 Ft felett, belföld | `(20001, 21, HU, false)` | 0 Ft |
| 9 | Kisbetűs országkód | `(10000, 4, hu, false)` | 1500 Ft |
| 10 | Expressz, belföld | `(10000, 4, HU, true)` | 2250 Ft |
| 11 | Expressz, külföld | `(10000, 5, de, true)` | 7500 Ft |
| 12 | Nulla érték és tömeg | `(0, 0, HU, false)` | 1500 Ft |
| 13 | Negatív rendelési érték | `(-1, 4, HU, false)` | Hiba |
| 14 | Negatív tömeg | `(10000, -1, HU, false)` | Hiba |
| 15 | Hibás országkód | `(10000, 4, HUN, false)` | Hiba |

## 4. Kézi futtatás

A teszteket a következő paranccsal futtattam:

```text
node w1/shipping.test.js
```

Mind a 15 teszteset sikeres lett.

## 5–6. A megtalált hibák és reprodukáló tesztek

Az eredeti függvényben az alábbi hibákat találtam:

1. **Az országkód kisbetűs formája nem működik.** A kód csak pontosan `HU` esetén kezeli Magyarországként. Reprodukció: `calculateShippingCost(10000, 4, "hu", false)` az eredeti kóddal 3000 Ft-ot ad, a helyes eredmény 1500 Ft.
2. **A pontosan 5 kg-os tömeg hibásan a legdrágább sávba kerül.** Az `else if (weight > 5 ...)` miatt az eredeti eredmény 5000 Ft, a helyes 2500 Ft. Reprodukció: `calculateShippingCost(10000, 5, "HU", false)`.
3. **A pontosan 20 kg-os tömeg hibásan a 20 kg feletti sávba kerül.** Az eredeti eredmény 5000 Ft, a helyes 2500 Ft. Reprodukció: `calculateShippingCost(10000, 20, "HU", false)`.
4. **Az expressz felár 50 Ft, nem 50%.** Például 1500 Ft alapdíjnál az eredeti eredmény 1550 Ft, a helyes 2250 Ft. Reprodukció: `calculateShippingCost(10000, 4, "HU", true)`.
5. **A 20 000 Ft-os határértéket az eredeti kód ingyenesnek veszi.** A specifikáció szerint csak a 20 000 Ft feletti rendelés ingyenes. Reprodukció: `calculateShippingCost(20000, 4, "HU", false)` az eredeti kóddal 0 Ft, a helyes 1500 Ft.
6. **Hiányzik a negatív bemenetek ellenőrzése.** Reprodukció: `calculateShippingCost(-1, 4, "HU", false)` és `calculateShippingCost(10000, -1, "HU", false)`; ezeknek hibát kell jelezniük.

A javított megvalósítás a `shipping.js` fájlban található.
