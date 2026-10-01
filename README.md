# Webshop backend

A small Spring Boot REST API written in Java and Kotlin. Products, customers,
orders and order positions are stored in MySQL using Spring Data JPA.
Flyway creates the database tables; Hibernate validates the schema on startup.

## Run locally

Requirements: Java 17 and Docker with Docker Compose.

1. Copy `.env.example` to `.env` and replace both example passwords.
2. Start the database: `docker compose up -d --wait`.
3. Set `DB_PASSWORD` in the terminal to the same value as in `.env`.
   Docker Compose reads `.env`; Spring Boot requires the environment variable.
4. Start the backend with the optional demo profile.

Windows PowerShell:

```powershell
$env:DB_PASSWORD="your-local-password"
.\gradlew.bat bootRun --args="--spring.profiles.active=demo"
```

Linux/macOS:

```bash
export DB_PASSWORD='your-local-password'
bash gradlew bootRun --args='--spring.profiles.active=demo'
```

The API runs at `http://localhost:8080/api`.
The `demo` profile creates customer `1` and three products for an empty product
catalog. Existing data is kept. Leave out this profile to start without demo data.

For an existing MySQL instance, create a database and a dedicated application
user and set `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD`. The default URL is
`jdbc:mysql://localhost:3306/webshop` and the default user is `webshop`.
Passwords and `.env` files must not be committed.

## Try the order flow

1. `GET /api/products` — choose a product ID.
2. `POST /api/orders` with `{"customerId":"1"}` — copy the returned order ID.
3. `POST /api/orders/{orderId}/positions` with
   `{"productId":"your-product-id","quantity":2}`.
4. `GET /api/customers/1/shoppingcart` — inspect the positions and total.

Prices are integer cents. The cart adds a fixed delivery charge of 500 cents.
Orders with status `NEW` make up the cart; there is no checkout endpoint yet.

Other endpoints:

- `GET /api/products?tag=CPU` (case-insensitive tag filter)
- `GET /api/products/{id}`
- `POST /api/products` with `name`, `description`, `priceInCent`, and `tags`
- `DELETE /api/products/{id}` (products referenced by orders cannot be deleted)
- `GET /api/customers/{id}`

## Persistence check

Create a product/order, restart the backend, then read it again.
`docker compose down` keeps the named database volume, so restarting MySQL also
keeps the data. **`docker compose down -v` deletes the database data.**

## Tests

```bash
bash gradlew test
```

On Windows use `.\gradlew.bat test`.
The tests use an isolated H2 database in MySQL compatibility mode, apply the
Flyway migration and verify API writes followed by reads in separate transactions,
including tag filtering, order linkage, cart totals, and invalid requests.
H2 does not replace testing with MySQL: GitHub Actions also runs the suite against
MySQL 8.0 using `TEST_DB_URL`, `TEST_DB_DRIVER`, `TEST_DB_USERNAME`, and
`TEST_DB_PASSWORD` overrides.

## Next steps

Add a small Angular frontend, then prepare and verify a public demo deployment.
Before public deployment, update framework dependencies and add input validation,
access control for write endpoints, and production configuration.
