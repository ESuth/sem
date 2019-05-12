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
        language2.language = "Spanish";
        language2.population = 280403952;
        language2.percentage = 4.61;

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
        city.country = "United Kingdom";
        city.population = 7285000;

        cities.add(city);

        City city2 = new City();
        city2.name = "Dublin";
        city2.country = "Ireland";
        city2.population = 481854;

        cities.add(city2);

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
       city.name = "Moscow";
       city.country = "Russian Federation";
       city.population = 8389200;

       cities.add(city);

       City city2 = new City();
       city2.name = "London";
       city2.country = "United Kingdom";
       city2.population = 7285000;

       cities.add(city2);

       City city3 = new City();
       city3.name = "Berlin";
       city3.country = "Germany";
       city3.population = 3386667;

       cities.add(city3);

       app.printContinentCapitalCities(cities);
   }


    /**
     *  Task 11
     */
    @Test
    void printDistrictCitiesTestEmpty()
    {
        ArrayList<City> city = new ArrayList<>();

        app.printDistrictCities(city);
    }

    @Test
    void printDistrictCities()
    {

        ArrayList<City> cities = new ArrayList<City>();

        City city = new City();
        city.name = "Herat";
        city.district = "Herat";
        city.population = 186800;

        cities.add(city);

        City city2 = new City();
        city2.name = "Tafunda";
        city2.district = "Tutuila";
        city2.population = 5200;

        cities.add(city2);

        City city3 = new City();
        city3.name = "Newcastle";
        city3.district = "New South Wales";
        city3.population = 270324;

        cities.add(city3);

        app.printDistrictCities(cities);
    }

    /**
     *  Task 13
     */
    @Test
    void printRegionCitiesTestEmpty()
    {
        ArrayList<City> city = new ArrayList<>();

        app.printRegionCities(city);
    }

    @Test
    void printRegionCities()
    {
        ArrayList<City> cities = new ArrayList<City>();

        City city = new City();
        city.name = "Lyon";
        city.region = "Western Europe";
        city.population = 445452;

        cities.add(city);

        City city2 = new City();
        city2.name = "Porto";
        city2.region = "Southern Europe";
        city2.population = 273060;

        cities.add(city2);

        City city3 = new City();
        city3.name = "Fortaleza";
        city2.region = "South America";
        city3.population = 2097757;

        cities.add(city3);

    }

    /**
     *  Task 15
     */
    void printWorldCitiesTestEmpty()
    {
        ArrayList<City> city = new ArrayList<>();

        app.printWorldCities(city);
    }

    @Test
    void printWorldCities()
    {
        ArrayList<City> cities = new ArrayList<City>();

        City city = new City();
        city.name = "Amsterdam";
        city.population = 731200;
        city.district = "Noord-Holland";
        city.country = "Netherlands";

        cities.add(city);

        City city2 = new City();
        city.name = "San Juan";
        city.population = 119152;
        city.district = "San Juan";
        city.country = "Argentina";

        cities.add(city2);
    }

    /**
     * Task 17
     */
    void printContinentCitiesTestEmpty()
    {
        ArrayList<City> city = new ArrayList<>();

        app.printContinentCities(city);
    }

    @Test
    void printContinentCities()
    {
        ArrayList<City> cities = new ArrayList<>();

        City city = new City();
        city.name = "La Paz";
        city.country = "Bolivia";
        city.population = 8329000;
        city.continent = "South America";

        cities.add(city);

        City city2 = new City();
        city2.name = "Iran";
        city2.country = "Teheran";
        city2.population = 67702000;
        city2.continent = "Asia";

        cities.add(city2);

        City city3 = new City();
        city3.country = "Turkey";
        city3.name = "Istanbul";
        city3.population = 66591000;
        city3.continent = "Asia";

        cities.add(city3);
    }

    /**
     * Task 19
     */
    void printRuralUrbanCountryTestEmpty()
    {
        ArrayList<Country> country = new ArrayList<>();

        app.printRuralUrbanCountries(country);
    }

    @Test
    void printRuralUrbanCountry()
    {
        ArrayList<Country> countries = new ArrayList<>();

    }

    /**
     * Task 23
     */
    @Test
    void printContinentCapitalCitiesTestEmptyWithLimit()
    {
        ArrayList<City> city = new ArrayList<City>();

        app.printContinentCapitalCities(city);
    }

    @Test
    void printContinentCapitalCitiesWithLimit()
    {
        ArrayList<City> cities = new ArrayList<City>();

        City city = new City();
        city.name = "Canberra";
        city.country = "Australia";
        city.population = 322723;

        cities.add(city);

        City city2 = new City();
        city2.name = "Port Moresby";
        city2.country = "Papua New Guinea";
        city2.population = 247000;

        cities.add(city2);

        City city3 = new City();
        city3.name = "Wellington";
        city3.country = "New Zealand";
        city3.population = 166700;

        cities.add(city3);

        app.printContinentCapitalCities(cities);
    }
}