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
        app.connect("localhost:33060");
    }


    /**
     * Task 1
     */
    @Test
    void testGetCityPop()
    {
        City city = app.getCityEdinburghPop("Edinburgh");
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
        City district = app.getDistrictScotlandPop("Scotland");
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
        Country country = app.getCountryUKPop("United Kingdom");
        assertEquals(country.code, "GBR");
        assertEquals(country.name, "United Kingdom");
        assertEquals(country.continent, "Europe");
        assertEquals(country.region, "British Islands");
        assertEquals(country.population, 59623400);
        assertEquals(country.city, "London");
    }


    /**
     * Task  4
     */
    @Test
    void testGetRegionPop()
    {
        Country region = app.getRegionBritIslesPop("British Islands");
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
        Country continent = app.getContinentEuropePop("Europe");
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
        assertEquals(world.population, 6078749450L);
    }


    /**
     * Task 8
     */
    @Test
    void testGetRegionCapitalsPop()
    {
        ArrayList<City> regions = app.getRegionCapitalCityList("British Islands");
        City city = new City();
        city.name = "London";
        city.country = "United Kingdom";
        city.population = 7285000;
        regions.add(city);
        assertEquals(city.name, "London");
        assertEquals(city.country, "United Kingdom");
        assertEquals(city.population, 7285000);
    }


    /**
     * Task 9
     */
    @Test
    void testGetContinentCapitalsPop()
    {
        ArrayList<City> continents = app.getContinentCapitalCityList("Africa");
        City continent = new City();
        continent.name = "Cairo";
        continent.country = "Egypt";
        continent.population = 6789479;
        continents.add(continent);
        assertEquals(continent.name, "Cairo");
        assertEquals(continent.country, "Egypt");
        assertEquals(continent.population, 6789479);
    }

    /**
     * Task 11
     */
    @Test
    void testGetDistrictCitiesPop()
    {
        ArrayList<City> districts = app.getDistrictCityList("Herat");
        City district = new City();
        district.name = "Herat";
        district.district = "Herat";
        district.population = 186800;
        districts.add(district);
        assertEquals(district.name, "Herat");
        assertEquals(district.district, "Herat");
        assertEquals(district.population, 186800);
    }

    /**
     * Task 13
     */
    @Test
    void testGetRegionCitiesPop()
    {
        ArrayList<City> regions = app.getRegionCityList("Western Europe");
        City region = new City();
        region.name = "Lyon";
        region.region = "Western Europe";
        region.population = 445452;
        regions.add(region);
        assertEquals(region.name, "Lyon");
        assertEquals(region.region, "Western Europe");
        assertEquals(region.population, 445452);
    }

    /**
     * Task 15
     */
    @Test
    void testGetWorldCitiesPop()
    {
        ArrayList<City> worlds = app.getWorldCityPopList();
        City world = new City();
        world.name = "Amsterdam";
        world.population = 731200;
        world.district = "Noord-Holland";
        world.country = "Netherlands";
        worlds.add(world);
        assertEquals(world.name, "Amsterdam");
        assertEquals(world.population, 731200);
        assertEquals(world.district, "Noord-Holland");
        assertEquals(world.country, "Netherlands");
    }

    /**
     * Task 23
     */
    @Test
    void testGetContinentCapitalsPopWithLimit()
    {
        ArrayList<City> continents = app.getContinentCapitalCityListWithLimit("Africa", "5");
        City continent = new City();
        continent.name = "Canberra";
        continent.country = "Australia";
        continent.population = 322723;
        continents.add(continent);
        assertEquals(continent.name, "Canberra");
        assertEquals(continent.country, "Australia");
        assertEquals(continent.population, 322723);
    }
}