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
     * Task  4
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

    /**
     * Task 9
     */
    @Test
    void testGetContinentCapitalsPop()
    {
        Continent continent = app.getContinentCapitalsPop();
        City city = new City();
        city.population = 1890000;
        ArrayList<City> cityList = new ArrayList<City>();
        cityList.add(city);
        continent.capitals = cityList;


        assertEquals(continent.capitals.get(0).population, 1890000);
    }

    /**
     * Task 10
     */
    @Test
    void testGetWorldCapitalsPop()
    {
        Continent world = app.getWorldCapitalsPop();
        City city = new City();
        city.population = 1890000;
        ArrayList<City> cityList = new ArrayList<City>();
        cityList.add(city);
        world.capitals = cityList;


        assertEquals(world.capitals.get(0).population, 1890000);
    }

    /**
     * Task 11
     */
    @Test
    void testGetDistrictCitiesPop()
    {
        District district = app.getDistrictCitiesPop();
        City city = new City();
        city.population = 1890000;
        ArrayList<City> cityList = new ArrayList<City>();
        cityList.add(city);
        district.cities = cityList;


        assertEquals(district.cities.get(0).population, 1890000);
    }

    /**
     * Task 12
     */
    @Test
    void testGetCountryCitiesPop()
    {
        Country country = app.getCountryCitiesPop();
        City city = new City();
        city.population = 1890000;
        ArrayList<City> cityList = new ArrayList<City>();
        cityList.add(city);
        country.cities = cityList;


        assertEquals(country.cities.get(0).population, 1890000);
    }

    /**
     * Task 13
     */
    @Test
    void testGetRegionCitiesPop()
    {
        Region region = app.getRegionCitiesPop();
        City city = new City();
        city.population = 1890000;
        ArrayList<City> cityList = new ArrayList<City>();
        cityList.add(city);
        region.cities = cityList;


        assertEquals(region.cities.get(0).population, 1890000);
    }

    /**
     * Task 14
     */
    @Test
    void testGetContinentCitiesPop()
    {
        Continent continent = app.getContinentCitiesPop();
        City city = new City();
        city.population = 1890000;
        ArrayList<City> cityList = new ArrayList<City>();
        cityList.add(city);
        continent.cities = cityList;




        assertEquals(continent.cities.get(0).population, 1890000);
    }

    /**
     * Task 15
     */
    @Test
    void testGetWorldCitiesPop()
    {
        World world = app.getWorldCitiesPop();
        City city = new City();
        city.population = 1890000;
        ArrayList<City> cityList = new ArrayList<City>();
        cityList.add(city);
        world.cities = cityList;


        assertEquals(world.cities.get(0).population, 1890000);
    }

    /**
     * Task 16
     */
    @Test
    void testGetRegionCountriesPop()
    {
        Region region = app.getRegionCountriesPop();
        Country country = new Country();
        country.population = 1890000;
        ArrayList<Country> countryList = new ArrayList<Country>();
        countryList.add(country);
        region.countries = countryList;


        assertEquals(region.countries.get(0).population, 1890000);
    }
}