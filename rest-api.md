- Pfad
- Query Parameter
- Http Verb: GET, POST, PUT, DELETE, (HEAD, OPTIONS, PATCH, TRACE)
- Request Body (Wenn du z.B ein Formular schicken willst)

REST: Ressourcen
Produkte
Kunden
Bestellungen


Lade alle Produkte vom Server:
GET /api/products

Erzeuge neues Produkt
POST /api/products

Lösche Produkt
DELETE /api/products/{id}

Lade Produkt mit bestimmter ID
GET /api/products/{id}

Lade Produkt mit bestimmtem Tag
GET /api/products?tag={tag}

Ändere Preis eines Produkts
POST /api/products/{id}/price

Update Produktbeschreibung
PUT /api/products/{id}/description


-Die 2 oberen Befehlen können durch folgenden Befehl ersetzt werden
PUT /api/products/{id}
-also wir ändern das gesamte Produkt


Bestelle Produkt
POST /api/orders

Füge Produkt zu Bestellung hinzu
POST /api/orders/{id}/products

Füge Tags zu Produkt hinzu
POST /api/products/{id}/tags

