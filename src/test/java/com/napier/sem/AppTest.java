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

    /**
     * Task 1
     */
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

    /**
     * Task 2
     */
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

    /**
     * Task 3
     */
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

    /**
     * Task 4
     */
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

    /**
     * Task 5
     */
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

    /**
     * Task 6
     */
    @Test
    void printLanguageTestEmpty()
    {
        ArrayList<Language> language = new ArrayList<Language>();
        app.printLanguages(language);
    }

    @Test
    void printLanguages()
    {
        ArrayList<Language> languages = new ArrayList<Language>();
        Language language = new Language();
        language.language = "English";
        language.population = 237134840;
        language.percentage = 3.90;
        languages.add(language);
        Language language2 = new Language();
        language.language = "Spanish";
        language.population = 280403952;
        language.percentage = 4.61;
        languages.add(language2);
        app.printLanguages(languages);
    }

    /**
     * Task 7
     */
    @Test
    void printWorldPopNull()
    {
        app.printPopulationWorld(null);
    }

    @Test
    void printWorldPopTestEmpty()
    {
        Country world = new Country();
        app.printPopulationWorld(world);
    }

    @Test
    void printWorld()
    {
        Country world = new Country();
        world.population = 6078749450L;
        app.printPopulationWorld(world);
    }
}