# Jak uruchomic aplikacje EattingApp?
1.Pobieramy z git
2.Otwieramy folder G1B + reload pliku pom.xml z urzyciem Maven
3.Ustawienie ściezki do pliku bazy danych w 2 miejscach:
    - ./src/main/java/ConnectionMenager
    - ./src/main/resources/application.properties
4.Uruchamiamy aplikacje uruchamiajac funkcje main w:
    - ./src/main/java/EattingappApplication
    aplikacja dostepna domyslnie pod localhost:8080 (mozna zmienic w application.properties)

#Logowanie do panemu admina:
Aktuane dane logowania to: 
login: user
haslo: password

#Wejscie do bazy danych H2:
Jest ona ukryta i bez zalogowania wczesniejszego do panelu admina nie bedzie mozna sie tam dostac.
Po zalogowania wchodzimy do /console i wyswietla sie panel H2.
Nalezy scopiowac sciezke zapisana z punku 3 i wkleic ja do JDBC URL. Haslo i login sa domyslne(nic nie wpisywac).

Przyklad:
Driver Class: org.h2.Driver
JDBC URL: jdbc:h2:file:/home/student/G1B_FoodOrderApp/src/main/resources/data/users
User Name:
Password:

Klikamy connect. Po zalogowaniu powinno byc widac kilka tabel (mi.: dishes).