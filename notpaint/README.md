# NotPaint
Dette prosjektet er en tegneapp som skal gjøre det mulig for brukere å tegne på et lerret, og så kunne lagre/hente bildene fra en ekstern server. Bruker skal kunne velge størrelse på brush, form og farge, og ha muligheten til å viske vekk tegningen. Det er planlagt at man skal kunne være flere som tegner på lærretet samtidig. 

Dersom det blir en for stor utfordring å få til at flere personer kan tegne samtidig, er plan B å dreie prosjektet mot et spill. Bruker får et randomisert ord som de skal tegne, og de resterende spillerne skal gjette først hvilket ord som tegnes. 

## Bilde av grensesnittet
![](PaintViewBilde.png "Grensesnitt")
Dette er det foreløpige grensesnittet. Videre skal flere funksjonsknapper legges til, som farger og fill-in.
- Sirklene setter brushen til indikert radius
- Firkantene setter brushen til indikert
størrelse
- Viskelæret setter brushen til hvit, slik at tegnede områder kan viskes ut
- Blyanten setter brushen til farge (foreløpigt svart)
- Clear canvas skal resette canvasen.
- Load-knappen skal hente opp fillager
- Save skal åpne lageret, så nåværende tegning kan lagres
- View2 er en placeholder for når vi etterhvert skal style fillageret i et sekundært view.

## Brukerhistorie (US-1)

Som bruker ønsker jeg å kunne tegne på et lerret og lagre tegningen lokalt.

Brukeren skal kunne tegne med forskjellige farger, samt størrelse og form på pensel. Det som har blitt tegnet skal også kunne viskes ut med et innebygd viskelær. Til slutt, når tegningen er ferdigstilt, skal bruker kunne lagre den, slik at de senere kan fortsette der de slapp og tegne videre.

Viktig å kunne se:
- et lerret
- valg av størrelse på pensel
- valg av form på pensel
- valg av farge
- viskelær

Viktig å kunne gjøre:
- velge pensel og male på lerretet med valgt farge
- kunne viske ut det man har malt
- kunne lagre tegningen til senere bruk