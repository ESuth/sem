package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printEdinburghPopNull()
    {
        app.printPopulationEdinburgh(null);
    }

    @Test
    void printEdinburghPopTestEmpty()
    {
        City city = new City();
        app.printPopulationEdinburgh(city);
    }

    @Test
    void printCity()
    {
        City city = new City();
        city.name = "Edinburgh";
        city.country = "United Kingdom";
        city.district = "Scotland";
        city.population = 500500;
        app.printPopulationEdinburgh(city);
    }

    @Test
    void printScotlandPopNull()
    {
        app.printPopulationScotland(null);
    }

    @Test
    void printScotlandPopTestEmpty()
    {
        City city = new City();
        app.printPopulationScotland(city);
    }

    @Test
    void printDistrict()
    {
        City city = new City();
        city.country = "United Kingdom";
        city.district = "Scotland";
        city.population = 500500;
        app.printPopulationScotland(city);
    }

    @Test
    void printUKPopNull()
    {
        app.printPopulationUK(null);
    }

    @Test
    void printUKPopTestEmpty()
    {
        Country country = new Country();
        app.printPopulationUK(country);
    }

    @Test
    void printCountry()
    {
        Country uK = new Country();
        uK.code = "GBR";
        uK.name = "United Kingdom";
        uK.continent = "Europe";
        uK.region = "British Islands";
        uK.population = 1005000;
        uK.capital = 32;
        app.printPopulationUK(uK);
    }
}