# BG LagerSystem – Spring Boot / Thymeleaf

Migration of the supplied PHP/Symfony BG LagerSystem to Java Spring Boot 3 + Thymeleaf.

## Stack
- Java 21
- Spring Boot 3.5.6
- Spring MVC
- Spring Data JPA / Hibernate
- Thymeleaf
- MySQL 8
- Spring Mail
- MailHog for local SMTP

## Start with Docker

```bash
docker compose up --build
```

Application: http://localhost:9090  
MailHog: http://localhost:8025

The MySQL database is exposed on port `3307` from the host.

## Start locally

A local Maven installation is required:

```bash
mvn spring-boot:run
```

Set the environment variables from `.env.example` as needed.

## Important compatibility notes

The original application does not use Symfony Security to protect the dashboard after login; the migration keeps that behavior instead of introducing a new authentication flow.

The original PHP entity declares `minStock` and `useFor` without Doctrine column annotations, so these are transient here as well.

The original barcode branch contains a comparison between a Storage entity and a product-name string. That comparison is ineffective, so the migrated implementation preserves the observed behavior and still creates the product when the barcode checkbox is selected.

The original update-storage controller sends the email using the `$action` query parameter after the form POST. Since the normal form POST has no `action` query parameter, the resulting HTML email receives an empty action value. This is intentionally preserved.

The delete confirmation password is the same hard-coded value used by the original PHP application: `Sumafelo`.

## Database

The application uses `spring.jpa.hibernate.ddl-auto=update`, so the existing `storage` and `user` tables can be reused. Existing PHP-generated data is mapped to the same column names.

The supplied storage seed SQL is included under `doc/sql-commands/`.
