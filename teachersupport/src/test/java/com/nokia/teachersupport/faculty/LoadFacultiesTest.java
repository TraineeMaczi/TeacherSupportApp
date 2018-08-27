package com.nokia.teachersupport.faculty;

import org.junit.Test;
import org.springframework.boot.context.event.ApplicationReadyEvent;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class LoadFacultiesTest {

    @Test
    public void onApplicationEvent() {
        List<String> umkFaculties = new ArrayList<>();

        umkFaculties.add("Wydzial Biologii i Ochrony Srodowiska");
        umkFaculties.add("Wydzial Chemii");
        umkFaculties.add("Wydzial Farmaceutyczny (Collegium Medicum w Bydgoszczy)");
        umkFaculties.add("Wydzial Filologiczny");
        umkFaculties.add("Wydzial Fizyki, Astronomii i Informatyki Stosowanej");
        umkFaculties.add("Wydzial Humanistyczny");
        umkFaculties.add("Wydzial Lekarski (Collegium Medicum w Bydgoszczy)");
        umkFaculties.add("Wydzial Matematyki i Informatyki");
        umkFaculties.add("Wydzial Nauk Ekonomicznych i Zarzadzania");
        umkFaculties.add("Wydzial Nauk Historycznych");
        umkFaculties.add("Wydzial Nauk o Zdrowiu (Collegium Medicum w Bydgoszczy)");
        umkFaculties.add("Wydzial Biologii i Ochrony Srodowiska");
        umkFaculties.add("Wydzial Nauk o Ziemi");
        umkFaculties.add("Wydzial Nauk Pedagogicznych");
        umkFaculties.add("Wydzial Politologii i Studiow Miedzynarodowych");
        umkFaculties.add("Wydzial Prawa i Administracji");
        umkFaculties.add("Wydzial Sztuk Pieknych");
        umkFaculties.add("Wydzial Teologiczny");

        //TO TO nie wiem jak wywolac zdarzenie

    }
}