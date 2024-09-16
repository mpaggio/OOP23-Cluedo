# OBIETTIVO DEL GRUPPO
Il gruppo si pone come obiettivo quello di realizzare un’implementazione in grafica 2D, che riproduca il gioco da tavolo “Cluedo”.
All’avvio del gioco, tramite l’apertura di un menù, sarà possibile caricare la partita precedentemente iniziata (se esistente), oppure iniziarne una nuova, definendo le proprietà dei 3 giocatori che vi parteciperanno.
All’interno del menù iniziale, sarà presente il pulsante “start”, che, dopo essere stato premuto, darà inizio alla partita. All’avvio della partita, il gioco prenderà casualmente dal mazzo (che comprende 21 carte totali, divise in “arma”, “stanza” e “colpevole”) 3 carte (1 per tipologia), che costituiranno la scena del crimine da scoprire. Le carte rimanenti verranno poi suddivise fra i giocatori.
Ad ogni turno, ogni giocatore lancerà un dado, il cui risultato determina il numero di caselle di cui si può spostare sulla mappa. Il movimento è consentito solamente verso posizioni assiali, adiacenti alla posizione corrente.
Dopo questa azione, se il giocatore si trova all’interno di una stanza, potrà formulare una sola ipotesi di accusa, specificando 3 carte (1 per tipologia).
Dopo ogni ipotesi, a turno, il primo degli altri giocatori che possiede una carta corrispondente ad una di quelle proposte, dovrà dichiararla esclusivamente all’accusatore.
Un giocatore perde quando fa un’accusa finale errata (che non corrisponde alla scena del crimine da scoprire). Vince il primo giocatore che formula l’accusa finale corretta.

## Funzionalità minime ritenute obbligatorie:
- Implementazione logica e grafica del menù iniziale di gioco, con inserimento di 3 giocatori (nome e colore della pedina).
- Implementazione logica e grafica del pannello di gioco, suddiviso in tabellone (grafica semplificata) e scheda informativa del giocatore (dado, carte e taccuino).
- Gestione dei turni di gioco (3 giocatori).
- Gestione del movimento dei giocatori sulla mappa (il giocatore specifica volta per volta la casella successiva in cui spostarsi).
- Gestione della formulazione dell’ipotesi di accusa.
- Gestione della formulazione dell’accusa definitiva.
- Gestione della visualizzazione delle regole di gioco.
- Gestione del salvataggio dell’ultima partita in corso.
- Gestione delle statistiche della partita e schermata per la loro visualizzazione.
- Implementazione delle botole per spostarsi da una stanza all'altra ad inizio del turno.
- Implementazione di caselle bonus e trappola (le caselle bonus concedono un ulteriore lancio di dado e relativo movimento, mentre le caselle trappola causano la perdita del turno successivo).
- Implementazione delle carte imprevisto.

## Funzionalità opzionali:
- Miglioramento della grafica del tabellone di gioco e animazione del dado, per avvicinarlo quanto più possibile alla versione originale di “Cluedo”.
- Implementazione di differenti difficoltà di gioco.

## "Challenge" principali:
- Corretto utilizzo del pattern MVC, con relativa gestione delle dipendenze fra Model, View e Controller e buon design.
- Corretto utilizzo del software di controllo versione (git), come strumento per la collaborazione.
- Adeguata suddivisione del lavoro all’interno del gruppo.

## Suddivisione del lavoro:
- Shimaj:
    - Implementazione e visualizzazione dei giocatori.
    - Implementazione della scelta iniziale della soluzione del gioco.
    - Implementazione della visualizzazione delle regole di gioco.
    - Implementazione della gestione dei movimenti sul tabellone.
- Saponaro:
    - Implementazione dell’ipotesi di accusa.
    - Implementazione dell’accusa definitiva.
    - Implementazione e gestione delle condizioni di vittoria o sconfitta.
    - Gestione di statistiche della partita e schermata per la loro visualizzazione.
- Paggetti:
    - Implementazione e visualizzazione delle carte di gioco.
    - Implementazione e grafica del tabellone di gioco.
    - Implementazione di caselle bonus e caselle trappola.
    - Implementazione delle botole per spostarsi da una stanza all’altra ad inizio del turno.
- Brighi:
    - Implementazione e gestione del taccuino.
    - Implementazione di carte imprevisto.
    - Implementazione e grafica del dado.
    - Implementazione del menù iniziale di gioco.
    - Implementazione del salvataggio della partita.
    - Implementazione della gestione dei turni e dell’interazione fra i giocatori.
