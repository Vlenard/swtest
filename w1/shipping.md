A szállítási díj – hibát kimutató tesztesetek

Ezek a tesztek a régi kód hibáit mutatják meg. A régi kód eredménye eltér a helyes eredménytől, vagy nem jelez hibát, amikor kellene.

1. Pontosan 5 kg

Bemenet:
calculateShippingCost(10000, 5, "HU", false)

Régi kód eredménye: 5000 Ft.
Helyes eredmény: 2500 Ft.

A pontosan 5 kg-os csomagnak a 5 és 20 kg közötti sávba kell kerülnie.

2. Pontosan 20 kg

Bemenet:
calculateShippingCost(10000, 20, "HU", false)

Régi kód eredménye: 5000 Ft.
Helyes eredmény: 2500 Ft.

A pontosan 20 kg-os csomag még nem tartozik a 20 kg feletti kategóriába.

3. Kisbetűs magyar országkód

Bemenet:
calculateShippingCost(10000, 4, "hu", false)

Régi kód eredménye: 3000 Ft.
Helyes eredmény: 1500 Ft.

A kisbetűs "hu" országkódot is Magyarországként kell elfogadni.

4. Expressz szállítás

Bemenet:
calculateShippingCost(10000, 4, "HU", true)

Régi kód eredménye: 1550 Ft.
Helyes eredmény: 2250 Ft.

Az expressz szállításnál 50%-kal kell növelni az 1500 Ft-os alapdíjat. A régi kód csak 50 Ft-ot ad hozzá.

5. Pontosan 20000 Ft-os rendelés

Bemenet:
calculateShippingCost(20000, 4, "HU", false)

Régi kód eredménye: 0 Ft.
Helyes eredmény: 1500 Ft.

Az ingyenes szállítás csak 20000 Ft feletti rendelésnél jár, ezért a pontosan 20000 Ft-os rendelés még nem ingyenes.

6. Negatív rendelési érték

Bemenet:
calculateShippingCost(-1, 4, "HU", false)

Régi kód eredménye: 1500 Ft.
Helyes eredmény: hiba.

A rendelési érték nem lehet negatív. A régi kód ezt nem ellenőrzi.

7. Negatív tömeg

Bemenet:
calculateShippingCost(10000, -1, "HU", false)

Régi kód eredménye: 1500 Ft.
Helyes eredmény: hiba.

A tömeg nem lehet negatív. A régi kód ezt sem ellenőrzi.

8. Nem kétbetűs országkód

Bemenet:
calculateShippingCost(10000, 4, "HUN", false)

Régi kód eredménye: 3000 Ft.
Helyes eredmény: hiba.

Az országkódnak pontosan két betűből kell állnia. A régi kód a hárombetűs kódot is elfogadja.

A javított függvény a shipping.js fájlban található. A futtatható tesztek a shipping.test.js fájlban vannak.
0
