public class Main {

    static int maxDlugosc = 0;
    static String maxSekwencja = "";
    static int liczbaObliczen = 0;

    public static void main(String[] args) {
        String ciag1 = "AABCCDEHJG"; //"ACFE";//"ABBCCCDDDDEFG"; // kolumna
        String ciag2 = "ABBCCCDDDDEFG"; //"ABCDEF";//"AABCCDEHJG"; // wiersz

        // wszystkich sekwencji jest duzo np. A, AB, ABC, B, C, ABG (usuwamy dowolnie z obu)
        // najdluzsza sekwencja: ABCCDEG

        // wyznaczenie wszystkich sekwencji (wszystkich mozliwych)
        // wyznaczenie samej dlugosci najdluzszej sekwencji

        liczbaObliczen++;
        szukanie(ciag1, ciag2,0,0, "");
        System.out.println(maxSekwencja);
        System.out.println("Dlugosc " + maxDlugosc);
        System.out.println("Liczba wywolan / obliczen " + liczbaObliczen);
        liczbaObliczen = 0;
        int[][] tablica = wyznaczenieTablicy(ciag1, ciag2);
        System.out.println("Liczba wywolan / obliczen " + liczbaObliczen);
        liczbaObliczen = 0;
        System.out.println(szukanieWersjaIteracyjna(tablica, ciag1, ciag2));
        System.out.println("Liczba wywolan / obliczen " + liczbaObliczen);
        System.out.println(szukanieWersjaRekurencyjna(tablica, ciag1, ciag2));
    }

    public static void szukanie(String ciag1, String ciag2, int idx1, int idx2, String znalezionaSekwencja) {
        if(idx1 == ciag1.length() || idx2 == ciag2.length()) {
            if(!znalezionaSekwencja.isEmpty()) {
                if (znalezionaSekwencja.length()> maxDlugosc) {
                    maxDlugosc = znalezionaSekwencja.length();
                    maxSekwencja = znalezionaSekwencja;
                }
                //System.out.println(znalezionaSekwencja);
            }
            return;
        }
        if (ciag1.charAt(idx1)==ciag2.charAt(idx2)){
            //System.out.print(ciag1.charAt(idx1));
            liczbaObliczen++;
            szukanie(ciag1, ciag2, idx1+1, idx2+1, znalezionaSekwencja + ciag1.charAt(idx1));
        } else {
            liczbaObliczen++;
            szukanie(ciag1, ciag2, idx1+1, idx2, znalezionaSekwencja); // zmiana litery w pierwszym
            liczbaObliczen++;
            szukanie(ciag1, ciag2, idx1, idx2+1, znalezionaSekwencja); // zmiana litery w drugim
        }
        liczbaObliczen++;
        szukanie(ciag1,ciag2, idx1+1, idx2+1, znalezionaSekwencja); // pominiecie litery, nawet jesli sie zgadza
    }

    // wyznaczenie tablicy dla sekwencji <- zadanie domowe
    // osobna zmienna do liczenia liczby obliczen, zobaczyc ile ich bedzie (kazdy obieg petli to jedno obliczenie)
    // wypisac dlugosc najdluzszej sekwencji (na koniec)
    public static int[][] wyznaczenieTablicy(String ciag1, String ciag2) {
        int[][] tablica = new int[ciag1.length()+1][ciag2.length()+1]; // tablica zawiera tylko liczby - pierwszy wiersz, pierwsza kolumna to 0
        for (int i = 0; i <ciag1.length()+1; i++) {      // x
            for (int j = 0; j <ciag2.length()+1; j++) {  // y
                if(i==0 || j ==0){ // wypelnienie zerami wiersza i kolumny (pierwszych)
                    tablica[i][j] = 0;
                } else { // pozostale przypadki
                    if (ciag1.charAt(i-1) == ciag2.charAt(j-1)){
                        tablica[i][j] = tablica[i-1][j-1]+1;
                    }else {
                        if (tablica[i-1][j]>tablica[i][j-1]){
                            tablica[i][j] = tablica[i-1][j];
                        }else {
                            tablica[i][j] = tablica[i][j-1];
                        }
                    }
                }
                liczbaObliczen++;
            }
        }

        for(int i = 0; i < ciag1.length()+1; i++) {
            for(int j = 0; j < ciag2.length()+1; j++) {
                System.out.print(tablica[i][j] + " ");
                liczbaObliczen++;
            }
            System.out.println();
        }

        System.out.println("Dlugosc " + tablica[ciag1.length()][ciag2.length()]);
        return tablica;
    }

    // wyznaczenie najdluzszego wspolnego na podstawie tablicy
    // iteracyjna - taka ktora przechodzi po wszystkich elementach np. tablicy (w skrocie, uzywa forów)
    public static String szukanieWersjaIteracyjna(int[][] tablica, String ciag1, String ciag2) {
        int max = 0;
        String ciagMax = "";
        for (int i = 1; i < ciag1.length()+1; i++) {
            for (int j = 1; j < ciag2.length()+1; j++) {
                liczbaObliczen++;
                if (tablica[i][j] > max){
                    max = tablica[i][j];
                    ciagMax += ciag1.charAt(i-1); // odejmujemy 1 bo tablica jest wieksza o kolumne zer i jedynek
                }
            }
        }
        return ciagMax;
    }

    // mozna do tej rekurencji cos dopisac np. indeksy itp.
    public static String szukanieWersjaRekurencyjna(int[][] tablica, String ciag1, String ciag2) {

        return "";
    }
}
