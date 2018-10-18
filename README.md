# 1819_5ahif_nvs_microprofile_microproject

Erstes Microprofile-Programm für Thorntail

## Aufbau

### Thorntail

* Das Projekt verwendet nur den Derby Driver und Libraries aus der Thorntail bom.
(Nur die nötigen, um eine möglichst kleine JAR zu generieren)
* Im project-defaults.yml ist die Datasource für Swarm (= Thorntail) konfiguriert.
Durch den Treibernamen "derby" wird automatisch die richtige Klasse aus der Derby dependency gesucht und initialisiert.
Die Datasource wird in der persistence.xml dann verwendet.

### Code

* Der Code ist fast gleich wie mein altes Microproject aufgebaut.
* Die Initbean belegt die Datenbank mit einigen Testdaten.
* Es gibt 2 Entities (Survey und User (Jeder User kann mehrere Surveys erstellen))
* Pro Entity gibt es ein Repo, dass von der generischen Repository Klasse erbt.
* Via Rest können die Repositories angesprochen werden. (Pro Repo ein JAX RS Endpoint)

## Benutzung

1. Das Projekt kann mit maven gebaut werden. (mvn package)
   Das erstellt dann ein war File (microprofile.war) und ein jar File (microprofile-thorntail.jar) 
2. Eine Derby Instanz muss gestart werden. (.../Derby/bin/startNetworkServer -noSecurityManager)
3. Dann kann das Projekt direkt als jar gestartet werden. (java -jar microprofile-thorntail.jar)
4. Jetzt kann auf den Server via Rest zugegriffen werden. z.B.
    + http://localhost:8080/user/all //Alle User
    + http://localhost:8080/user/name/user1 //Alle User, deren Username der Regex "user1" entspricht
    + http://localhost:8080/survey/all //Alle Surveys
    + http://localhost:8080/survey/id/2 //Der Survey mit der ID 2
    + http://localhost:8080/survey/user/2 //Alle Surveys des Users mit der ID 2
    + Pro Endpoint sind POST, PUT und DELETE ebenfalls möglich.

## Probleme

Hier noch ein paar Probleme und Differenzen zwischen Wildfly und Thorntail,
die schwer zu erkennen sind und mich Stunden gekostet haben :)

1. Egal welche JPA Implementierung aus dem BOM importiert wird, in der persistence.xml
   funktioniert nur property name="javax.persistence.schema-generation.database.action" value="drop-and-create",
   Hibernate und EclipseLink spezifische properties werfen Fehler.
2. javax.inject.Singleton ist nicht dasselbe wie javax.ejb.Singleton => Initbean wird dann nie initialisiert.
3. EJB Injection in JAX RS ist nur mit Workarounds möglich, wie hier beschrieben: https://stackoverflow.com/a/3029185
   Leider funktioniert bei Thorntail die 2te Option nicht. 
5. Die Dependency "io.thorntail.microprofile" kann nicht genommen werden, weil sie Fehler wirft, die
   verwendeten Libs müssen somit manuell gesucht und in die pom.xml eingetragen werden.
