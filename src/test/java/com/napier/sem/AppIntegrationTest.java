package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("db");
    }

    /**
     * Task 1
     */
    @Test
    void testGetCityPop()
    {
        City city = app.getCityEdinburghPop("'Edinburgh'", "'GBR'");
        assertEquals(city.name, "Edinburgh");
        assertEquals(city.country, "United Kingdom");
        assertEquals(city.district, "Scotland");
        assertEquals(city.population, 450180);
    }

    /**
     * Task 2
     */
    @Test
    void testGetDistrictPop()
    {
        City district = app.getDistrictScotlandPop("'Scotland'", "'GBR'");
        assertEquals(district.district, "Scotland");
        assertEquals(district.country, "United Kingdom");
        assertEquals(district.population, 1429620);
    }

    /**
     * Task 3
     */
    @Test
    void testGetCountryPop()
    {
        Country country = app.getCountryUKPop("'United Kingdom'");
        assertEquals(country.code, "GBR");
        assertEquals(country.name, "United Kingdom");
        assertEquals(country.continent, "Europe");
        assertEquals(country.region, "British Islands");
        assertEquals(country.population, 59623400);
        assertEquals(country.capital, 456);
    }

    /**
     * Task 4
     */
    @Test
    void testGetRegionPop()
    {
        Country region = app.getRegionBritIslesPop("'British Islands'");
        assertEquals(region.continent, "Europe");
        assertEquals(region.region, "British Islands");
        assertEquals(region.population, 63398500);
    }

    /**
     * Task 5
     */
    @Test
    void testGetContinentPop()
    {
        Country continent = app.getContinentEuropePop("'Europe'");
        assertEquals(continent.continent, "Europe");
        assertEquals(continent.population, 730074600);
    }

    /**
     * Task 6
     */
    @Test
    void testGetLanguageList()
    {
        ArrayList<Language> languages = app.getLanguageList();
        Language language = new Language();
        language.language = "English";
        language.population = 237134840;
        language.percentage = 3.90;
        assertEquals(language.language, "English");
        assertEquals(language.population, 237134840);
        assertEquals(language.percentage, 3.90);
    }

    /**
     * Task 7
     */
    @Test
    void testGetWorldPop()
    {
        Country world = app.getWorldPop();
        assertEquals(world.population, 730074600);
    }
}