# NotPaint
Dette prosjektet er en tegneapp som skal gjøre det mulig for brukere å tegne på et lerret, og så kunne lagre/hente bildene fra en ekstern server. Bruker skal kunne velge størrelse på pensel, form og farge, og ha muligheten til å viske vekk tegningen. Det er planlagt at man skal kunne være flere som tegner på lerretet samtidig. 

Dersom det blir en for stor utfordring å få til at flere personer kan tegne samtidig, er plan B å dreie prosjektet mot et spill. Bruker får et randomisert ord som de skal tegne, og de resterende spillerne skal gjette først hvilket ord som tegnes. 

## Bilde av grensesnittet
![](PaintViewBilde.png "Grensesnitt")
Dette er det foreløpige grensesnittet. Videre skal flere funksjonsknapper legges til, som farger og fill-in.
- Sirklene setter pensel til indikert radius
- Firkantene setter pensel til indikert
størrelse
- Viskelæret setter pensel til hvit, slik at tegnede områder kan viskes ut
- Blyanten setter pensel til farge (foreløpigt svart)
- Clear canvas skal resette lerretet.
- Load-knappen skal hente opp fillager
- Save skal åpne lageret, så nåværende tegning kan lagres
- View2 er en placeholder for når vi etterhvert skal style fillageret i et sekundært view.

## Brukerhistorie (US-1)

Som bruker ønsker jeg å kunne tegne på et lerret og lagre tegningen lokalt.

Brukeren skal kunne tegne med forskjellige farger, samt størrelse og form på pensel. Det som har blitt tegnet skal også kunne viskes ut med et innebygd viskelær. Til slutt, når tegningen er ferdigstilt, skal bruker kunne lagre den, slik at de senere kan fortsette der de slapp og tegne videre.

Viktig å kunne se:
- Et lerret
- Valg av størrelse på pensel
- Valg av form på pensel
- Valg av farge
- Viskelær

Viktig å kunne gjøre:
- Velge pensel og male på lerretet med valgt farge
- Kunne viske ut det man har malt
- Kunne lagre tegningen til senere bruk

## Brukerhistorie US2-2:
Som bruker vil jeg kunne bli med i et spill og tegne på et lerret.

Brukeren skal kunne bli med i en tegning som andre personer har tegnet på. Brukeren skal motta et ord med en angitt tid og deretter kunne tegne på et lerret.

Viktig å kunne se: 
- en knapp som tilsier at du blir med i et lerret.
- se hva slags ord du skal tegne
- en tid som teller ned som viser hvor lang tid man har før tiden går ut
- en ferdig knapp man trykker på hvis man er ferdig før tiden er ute
- hvor mange som har tegnet før deg, samt hvor mange som skal få lov til å tegne på lerretet
- skal se farger man kan bruke, ulik størrelse og form på pensler, samt viskelær på den en selv har tegnet

Viktig å kunne gjøre:
- bli med i en tegning
- kunne tegne med ulike farger og ulik form og størrelse på pensler. 
- Bruke viskelær på det en selv har tegnet. 
