
# Passwortmanager

Ein kleines Projekt mit Spring Boot und HTML/JavaScript. Damit kann man Passwörter speichern, bearbeiten und löschen. Die Passwörter werden verschlüsselt gespeichert und es gibt eine einfache Login-Funktion mit einem Master-Passwort.

## Funktionen

- Login mit Master-Passwort
- Passwörter speichern (mit Website, Benutzername und Passwort)
- Passwörter bearbeiten und löschen
- Passwörter werden AES-verschlüsselt in einer H2 Datenbank gespeichert
- Neue sichere Passwörter lassen sich automatisch generieren
- Einfache Benutzeroberfläche im Browser
- REST-API für alle Funktionen
- Lokale Datenbank

## Aufbau

Das Projekt besteht aus:

- einem Spring Boot Backend (Java)
- einer einfachen HTML-Seite mit CSS und JavaScript
- einer eingebauten Datenbank (H2)

## Starten
- PasswordmanagerApplication Starten.

- Danach läuft es unter:

```
http://localhost:8080
````

## Master-Passwort

Das Passwort zum Einloggen ist aktuell fest im Code eingestellt:

```java
private static final String MASTER_PASSWORD = "master123";
````

## API-Endpunkte

Ein paar Beispiele für die REST-Routen:

* `POST /api/auth/login` – Login mit Master-Passwort
* `GET /api/passwords` – alle Einträge anzeigen
* `POST /api/passwords` – neuen Eintrag speichern
* `PUT /api/passwords/{id}` – Eintrag ändern
* `DELETE /api/passwords/{id}` – Eintrag löschen
* `GET /api/passwords/generate` – zufälliges Passwort erstellen

## Hinweis

Das Projekt ist zu Lernzwecken gedacht. In einem echten Projekt müsste das Passwort und der Verschlüsselungsschlüssel sicher gespeichert werden.
