# KTS-NVT
Članovi tima:
1. Dragana Grbić SW22/2017
2. Marko Mijatović SW30/2017
3. Petar Nikolić SW31/2017

Napomene:
1. Baze koje koristimo su postgre (za samu aplikaciju) i h2 (za testiranje)
2. Kako cuvamo slike na backend-u (u static folderu), moze se desiti (u zavisnosti od IDE-a koji koristite) da ne mozete da im pristupite neposredno nakon upload-a, jer projekat nije osvezen. Ako koristite Eclipse, treba da uradite sledece: Window -> Preferences -> General -> Workspace -> Refresh using native hooks -> Apply and close. Tako ce se projekat automatski osvezavati. Za IntelliJ ne znam sta je potrebno i da li je potrebno, ali ako primetite da ne mozete da vidite slike nakon upload-a, znajte da je to problem.
3. Posto koristimo https sa self signed certificate, kako se ne bi mucili sa namestanjem windowsa da veruje certifikatima, potrebno je chrome pokrenuti na sledeci nacin:
<folter_u_kome_je_chrome> chrome.exe --ignore-certificate-errors
4. Kako i frontend koristi https, potrebno ga je pokrenuti sa ng serve --ssl

Kolicina podataka koje smo generisali:
1. 1000 kulturnih ponuda
2. 15000 komentara
3. 15000 novosti
4. 10000 slika
5. 10000 veza komentar-slika
6. 10000 veza novost-slika
7. 103 kategorija
8. 106 tipova
9. 1003 korisnika
