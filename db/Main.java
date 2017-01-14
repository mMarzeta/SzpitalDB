package db;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maciejmarzeta on 07.01.2017.
 */
public class Main {
    public static void main(String args[]) {

        Plec plec = new Plec(1, "Kobieta");
        plec.insert(plec.generate_insert_query(2, "Mezczyzna"));
        plec.insert(plec.generate_insert_query());
        plec.select();
        //plec.drop();


        Lek lek = new Lek(1, "Aspiryna");
        lek.insert(lek.generate_insert_query());


        Uczulenie uczulenie = new Uczulenie(1, "Orzechy", 1);
        uczulenie.insert(uczulenie.generate_insert_query());
        uczulenie.select();


        Choroba choroba = new Choroba(1, "Bol Glowy");
        choroba.insert(choroba.generate_insert_query());


        Adres adres = new Adres(1, "Lubelszczyzna", "Lublin",
                "ul.Piekna", "Polska", "22-222");
        adres.insert(adres.generate_insert_query());
        adres.select();

        Lekarz lekarz = new Lekarz(1, "Andrzej", "House", "123123123",
                1, 2);
        lekarz.insert(lekarz.generate_insert_query());
        lekarz.select();


        Oddzial oddzial = new Oddzial(1, "Chirurgia", 1);
        oddzial.insert(oddzial.generate_insert_query());

        Zabieg zabieg = new Zabieg(1, "Badanie", 1);
        zabieg.insert(zabieg.generate_insert_query());


        Wizyta wizyta = new Wizyta(1, "2017-01-01", 1, 1, 1, 1);
        wizyta.insert(wizyta.generate_insert_query());

        Pacjent pacj = new Pacjent(1, "Maciej", "Marzeta",
                "1994-09-30", "444333444",
                1, 1, 1, 1);
        pacj.insert(pacj.generate_insert_query());

        plec.drop();
        lek.drop();
        uczulenie.drop();
        choroba.drop();
        adres.drop();
        lekarz.drop();
        oddzial.drop();
        zabieg.drop();
        wizyta.drop();
        pacj.drop();
    }
}
