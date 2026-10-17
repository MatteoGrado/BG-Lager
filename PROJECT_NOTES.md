# Projektinformationen

## Projekt

- Projektname: BG LagerSystem
- Technologie: Spring Boot 3.5.6
- Java-Version: 21
- Build-System: Maven
- Datenbank: MySQL
- Template-Engine: Thymeleaf
- Mail-Versand: Spring Mail
- Scheduling: Spring Scheduling mit `@EnableScheduling`
- Standard-Port: `8000`

## Wichtige Bereiche

- `src/main/java/de/bg/lagersystem/model/`: JPA-Modelle
- `src/main/java/de/bg/lagersystem/repository/`: Spring-Data-Repositories
- `src/main/java/de/bg/lagersystem/service/`: Geschäftslogik
- `src/main/java/de/bg/lagersystem/controller/`: Web-Controller
- `src/main/java/de/bg/lagersystem/component/`: Komponenten wie die Bestandsprüfung
- `src/main/resources/templates/`: Thymeleaf-Seiten
- `src/main/resources/static/`: CSS und Bilder
- `src/main/resources/application.yml`: Datenbank-, Mail- und Server-Konfiguration

## Bestandsprüfung

`CheckStock` prüft alle 10 Minuten die Produkte. Wenn der Bestand unter `minStock` fällt, wird eine Mail verschickt. Der Versandstatus wird im Feld `storage_alert_sent` des Produkts gespeichert, damit dasselbe Produkt nicht bei jedem Prüfintervall erneut gemeldet wird. Sobald der Bestand wieder mindestens `minStock` erreicht, wird der Status zurückgesetzt.

## Arbeitsregeln

- Nur die Änderungen durchführen, die der Benutzer ausdrücklich verlangt.
- Keine bestehenden Annotationen, Imports, Methoden oder sonstigen Codebestandteile entfernen oder umstrukturieren, wenn das nicht ausdrücklich beauftragt wurde.
- Bestehende Änderungen im Arbeitsverzeichnis respektieren und nicht überschreiben.
- Vor Änderungen zuerst die betroffene Datei und den vorhandenen Code prüfen.
- Nach Änderungen nur die direkt betroffenen Tests bzw. den Build ausführen.

