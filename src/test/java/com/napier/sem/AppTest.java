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

    /**
     * Task 8
     */
    @Test
    void printRegionCapitalCitiesTestEmpty()
    {
        ArrayList<City> city = new ArrayList<City>();
        app.printRegionCapitalCities(city);
    }

    @Test
    void printRegionCapitalCities()
    {
        ArrayList<City> cities = new ArrayList<City>();
        City city = new City();
        city.name = "London";
        city.population = 7285000;
        cities.add(city);
        City city2 = new City();
        city2.name = "Dublin";
        city2.population = 481854;
        cities.add(city2);
        City city3 = new City();
        city3.name = "Edinburgh";
        city3.population = 450180;
        cities.add(city3);
        app.printRegionCapitalCities(cities);
    }

    /**
     * Task 9
     */
    @Test
    void printContinentCapitalCitiesTestEmpty()
    {
        ArrayList<City> city = new ArrayList<City>();
        app.printContinentCapitalCities(city);
    }

    @Test
    void printContinentCapitalCities()
    {
        ArrayList<City> cities = new ArrayList<City>();
        City city = new City();
        city.name = "Hamburg";
        city.population = 1810000;
        cities.add(city);
        City city2 = new City();
        city2.name = "Warsaw";
        city2.population = 1745000;
        cities.add(city2);
        City city3 = new City();
        city3.name = "Paris";
        city3.population = 2200000;
        cities.add(city3);
        app.printContinentCapitalCities(cities);
    }

    /**
     * Task 10
     */
    @Test
    void printWorldCapitalCitiesTestEmpty()
    {
        ArrayList<City> city = new ArrayList<City>();
        app.printWorldCapitalCities(city);
    }

    @Test
    void printWorldCapitalCities()
    {
        ArrayList<City> cities = new ArrayList<City>();
        City city = new City();
        city.name = "Hamburg";
        city.population = 1810000;
        cities.add(city);
        City city2 = new City();
        city2.name = "Tokyo";
        city2.population = 9273000;
        cities.add(city2);
        City city3 = new City();
        city3.name = "Moscow";
        city3.population = 11920000;
        cities.add(city3);
        app.printWorldCapitalCities(cities);
    }

    /**
     * Task 11
     */
    @Test
    void printDistrictCitiesTestEmpty()
    {
        ArrayList<City> city = new ArrayList<City>();
        app.printWorldCapitalCities(city);
    }

    @Test
    void printDistrictCities()
    {
        ArrayList<City> cities = new ArrayList<City>();
        City city = new City();
        city.name = "Hamburg";
        city.population = 1810000;
        cities.add(city);
        City city2 = new City();
        city2.name = "Tokyo";
        city2.population = 9273000;
        cities.add(city2);
        City city3 = new City();
        city3.name = "Moscow";
        city3.population = 11920000;
        cities.add(city3);
        app.printDistrictCities(cities);
    }

    /**
     * Task 12
     */
    @Test
    void printCountryCitiesTestEmpty()
    {
        ArrayList<City> city = new ArrayList<City>();
        app.printCountryCities(city);
    }

    @Test
    void printCountryCities()
    {
        ArrayList<City> cities = new ArrayList<City>();
        City city = new City();
        city.name = "Edinburgh";
        city.population = 482005;
        cities.add(city);
        City city2 = new City();
        city2.name = "Glasgow";
        city2.population = 598830;
        cities.add(city2);
        City city3 = new City();
        city3.name = "Stirling";
        city3.population = 92830;
        cities.add(city3);
        app.printCountryCities(cities);
    }

    /**
     * Task 13
     */
    @Test
    void printRegionCitiesTestEmpty()
    {
        ArrayList<City> city = new ArrayList<City>();
        app.printRegionCities(city);
    }

    @Test
    void printRegionCities()
    {
        ArrayList<City> cities = new ArrayList<City>();
        City city = new City();
        city.name = "Edinburgh";
        city.population = 482005;
        cities.add(city);
        City city2 = new City();
        city2.name = "Glasgow";
        city2.population = 598830;
        cities.add(city2);
        City city3 = new City();
        city3.name = "Stirling";
        city3.population = 92830;
        cities.add(city3);
        app.printRegionCities(cities);
    }
}