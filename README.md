# quizzard
College quiz project


ukoliko netko nema instaliran postgreSQL ili PGadmin ili ga nikad zapravo nije koristio, ---- upute za instalaciju postgreSQL i PGadmin aplikacije: https://www.w3schools.com/postgresql/index.php
(OBAVEZNO ZAPAMTITE USERNAME I PASSWORD kod kreiranja servera)
to ćete morati upisati u application.properties pod 
spring.datasource.username=postgres
spring.datasource.password=pass

desni klik na databases da kreirate bazu i samo ostavite sve kako je (default) i upišite quizzard
![quizzardPG](https://github.com/user-attachments/assets/c84e4452-e54b-4880-b91c-5eacb8640482)

nakon što ste to sve napravili i prvi put pokrenuli aplikaciju i sve je prošlo u redu, zaustavite aplikaciju, rename datoteke "data-backup.sql" u "data.sql" i ponovno pokrenite aplikaciju kako bi dobili nešto testnih podataka u samu bazu;
nakon što ste se uvjerili da su podaci dostupni u bazi, vratite naziv datoteke u "data-backup.sql"

isto tako, u httpTesting direktoriju imate testing.http datoteku za jednostavno testiranje, čisto da se uvjerite da radi (može se i dodavati pos requestovi itd. to će vjerojatno biti sa kasnijim commitovima)

koristit ćemo model mapper umjesto mapstruct zbog portability

NAPOMENE:
1.) ukoliko vam se javlja problem sa spremanjem novih entiteta u bazu u vidu "id already exists" potrebno je više puta pokušati unijeti i tad će se upisati (to se dešava jer već imamo neke podatke koji nisu uneseni preko requesta nego manualno kroz skriptu)
2.) dodao sam swagger koji je dostupan na http://localhost:8080/swagger-ui/index.html nakon što pokrenete aplikaciju -- VRLO LAKO TESTIRANJE (npr POST requestovi već imaju složen model koji je potreban za unos) a koristi i frontendu jer zna što mora slati