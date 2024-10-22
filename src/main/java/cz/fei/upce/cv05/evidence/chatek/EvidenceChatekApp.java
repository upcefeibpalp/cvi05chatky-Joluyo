package cz.fei.upce.cv05.evidence.chatek;

import java.util.Scanner;

public class EvidenceChatekApp {

    public static void main(String[] args) {
        final int KONEC_PROGRAMU = 0;
        final int VYPIS_CHATEK = 1;
        final int VYPIS_KONKRETNI_CHATKU = 2;
        final int PRIDANI_NAVSTEVNIKU = 3;
        final int ODEBRANI_NAVSTEVNIKU = 4;
        final int CELKOVA_OBSAZENOST = 5;
        final int VYPIS_PRAZDNE_CHATKY = 6;

        final int VELIKOST_KEMPU = 5;
        final int MAX_VELIKOST_CHATKY = 10;

        Scanner scanner = new Scanner(System.in);

        int[] chatky = new int[VELIKOST_KEMPU];
        int operace;

        do {
            System.out.println("""
                    MENU:
                                        
                    1 - vypsani vsech chatek
                    2 - vypsani konkretni chatky
                    3 - Pridani navstevniku
                    4 - Odebrani navstevniku
                    5 - Celkova obsazenost kempu
                    6 - Vypis prazdne chatky
                    0 - Konec programu
                    """);

            System.out.print("Zadej volbu: ");
            operace = scanner.nextInt();

            switch (operace) {
                case VYPIS_CHATEK -> {
                    vypisSeznamuChatek(chatky);
                }

                case VYPIS_KONKRETNI_CHATKU -> {
                    vypisJedneChatky(scanner, chatky);
                }

                case PRIDANI_NAVSTEVNIKU -> {
                    imputPridaniNavstevniku(scanner, chatky, MAX_VELIKOST_CHATKY);
                }

                case ODEBRANI_NAVSTEVNIKU -> {
                    imputOdebraniNavstevniku(scanner, chatky, MAX_VELIKOST_CHATKY);
                }

                case CELKOVA_OBSAZENOST -> {
                    celkovaObsazenostChatek(chatky);
                }

                case VYPIS_PRAZDNE_CHATKY -> {
                    vypisPrazdnychChatek(chatky);
                }

                case KONEC_PROGRAMU -> {
                    System.out.println("Konec programu");
                }

                default -> {
                    neplatnaVolba();
                }
            }
        } while (operace != 0);
    }

    private static void neplatnaVolba() {
        System.err.println("Neplatna volba");
    }

    private static void imputPridaniNavstevniku(Scanner scanner, int[] chatky, final int MAX_VELIKOST_CHATKY) {
        int cisloChatky = imputCisloChatky(scanner);

        chatkaNeexistuje(cisloChatky, chatky);

        int pocetNavstevniku = imputPocetNavstevniku(scanner);

        if (pocetNavstevniku <= 0 || pocetNavstevniku > MAX_VELIKOST_CHATKY) {
            neplatnaHodnota();
        } else if ((chatky[cisloChatky] + pocetNavstevniku) > MAX_VELIKOST_CHATKY) {
            prekrocenaHodnota();
        } else {
            chatky[cisloChatky] = pocetNavstevniku + chatky[cisloChatky];
        }
    }

    private static void chatkaNeexistuje(int cisloChatky, int[] chatky) {
        if (cisloChatky < 0 || cisloChatky >= chatky.length) {
            neplatnaVolba();
        } else {
        }
    }

    private static int imputCisloChatky(Scanner scanner) {
        System.out.print("Zadej cislo chatky: ");
        int cisloChatky = scanner.nextInt() - 1;
        return cisloChatky;
    }

    private static int imputPocetNavstevniku(Scanner scanner) {
        System.out.print("Zadej pocet navstevniku: ");
        int pocetNavstevniku = scanner.nextInt();
        return pocetNavstevniku;
    }

    private static void vypisJedneChatky(Scanner scanner, int[] chatky) {
        int cisloChatky = imputCisloChatky(scanner);

        chatkaNeexistuje(cisloChatky, chatky);

        System.out.println("Chatka [" + (cisloChatky + 1) + "] = " + chatky[cisloChatky]);
    }

    private static void vypisSeznamuChatek(int[] chatky) {
        for (int i = 0; i < chatky.length; i++) {
            System.out.println("Chatka [" + (i + 1) + "] = " + chatky[i]);
        }
    }

    private static void imputOdebraniNavstevniku(Scanner scanner, int[] chatky, final int MAX_VELIKOST_CHATKY) {
        int cisloChatky = imputCisloChatky(scanner);

        chatkaNeexistuje(cisloChatky, chatky);

        int pocetNavstevniku = imputPocetNavstevniku(scanner);

        if (pocetNavstevniku <= 0 || pocetNavstevniku > MAX_VELIKOST_CHATKY) {
            neplatnaHodnota();
        } else if ((chatky[cisloChatky] + pocetNavstevniku) > MAX_VELIKOST_CHATKY) {
            prekrocenaHodnota();
        } else {
            chatky[cisloChatky] = chatky[cisloChatky] - pocetNavstevniku;
        }
    }

    private static void prekrocenaHodnota() {
        System.err.println("Prekrocen maximalni pocet navstevniku chatky");
    }

    private static void neplatnaHodnota() {
        System.err.println("Neplatna hodnota pro pocet navstevniku");
    }

    private static void celkovaObsazenostChatek(int[] chatky) {
        int obsazenost = 0;
        for (int i = 0; i < chatky.length; i++) {
            obsazenost = chatky[i] + obsazenost;
        }
        System.out.println("Celková obsazenost = " + obsazenost);
    }

    private static void vypisPrazdnychChatek(int[] chatky) {
        for (int i = 0; i < chatky.length; i++) {
            if (chatky[i] == 0) {
                System.out.println("Chatka [" + (i + 1) + "] = " + chatky[i]);
            }
        }
    }
}
