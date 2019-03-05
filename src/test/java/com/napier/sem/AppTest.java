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
        City district = new City();
        app.printPopulationScotland(district);
    }

    @Test
    void printDistrict()
    {
        City district = new City();
        district.country = "United Kingdom";
        district.district = "Scotland";
        district.population = 500500;
        app.printPopulationScotland(district);
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
        Country country = new Country();
        country.code = "GBR";
        country.name = "United Kingdom";
        country.continent = "Europe";
        country.region = "British Islands";
        country.population = 5005000;
        country.capital = 32;
        app.printPopulationUK(country);
    }

    @Test
    void printBritIslesPopNull()
    {
        app.printPopulationBritIsles(null);
    }

    @Test
    void printBritIslesPopTestEmpty()
    {
        Country region = new Country();
        app.printPopulationBritIsles(region);
    }

    @Test
    void printRegion()
    {
        Country region = new Country();
        region.continent = "Europe";
        region.region = "British Islands";
        region.population = 6505000;
        app.printPopulationBritIsles(region);
    }

    @Test
    void printEuropePopNull()
    {
        app.printPopulationEurope(null);
    }

    @Test
    void printEuropePopTestEmpty()
    {
        Country continent = new Country();
        app.printPopulationEurope(continent);
    }

    @Test
    void printContinent()
    {
        Country continent = new Country();
        continent.continent = "Europe";
        continent.population = 7505000;
        app.printPopulationEurope(continent);
    }
}