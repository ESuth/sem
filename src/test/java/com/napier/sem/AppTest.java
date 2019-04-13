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


 //   /**
 //    * Task 11
 //    */
 //   @Test
 //   void printDistrictCitiesTestEmpty()
 //   {
 //       ArrayList<City> city = new ArrayList<City>();
//
 //       app.printWorldCapitalCities(city);
 //   }
//
 //   @Test
 //   void printDistrictCities()
 //   {
 //       ArrayList<City> cities = new ArrayList<City>();
//
 //       City city = new City();
 //       city.name = "Hamburg";
 //       city.population = 1810000;
//
 //       cities.add(city);
//
 //       City city2 = new City();
 //       city2.name = "Tokyo";
 //       city2.population = 9273000;
//
 //       cities.add(city2);
//
 //       City city3 = new City();
 //       city3.name = "Moscow";
 //       city3.population = 11920000;
//
 //       cities.add(city3);
//
 //       app.printDistrictCities(cities);
 //   }
//
//
 //   /**
 //    * Task 12
 //    */
 //   @Test
 //   void printCountryCitiesTestEmpty()
 //   {
 //       ArrayList<City> city = new ArrayList<City>();
//
 //       app.printCountryCities(city);
 //   }
//
 //   @Test
 //   void printCountryCities()
 //   {
 //       ArrayList<City> cities = new ArrayList<City>();
//
 //       City city = new City();
 //       city.name = "Edinburgh";
 //       city.population = 482005;
//
 //       cities.add(city);
//
 //       City city2 = new City();
 //       city2.name = "Glasgow";
 //       city2.population = 598830;
//
 //       cities.add(city2);
//
 //       City city3 = new City();
 //       city3.name = "Stirling";
 //       city3.population = 92830;
//
 //       cities.add(city3);
//
 //       app.printCountryCities(cities);
 //   }
//
//
 //   /**
 //    * Task 13
 //    */
 //   @Test
 //   void printRegionCitiesTestEmpty()
 //   {
 //       ArrayList<City> city = new ArrayList<City>();
//
 //       app.printRegionCities(city);
 //   }
//
 //   @Test
 //   void printRegionCities()
 //   {
 //       ArrayList<City> cities = new ArrayList<City>();
//
 //       City city = new City();
 //       city.name = "Edinburgh";
 //       city.population = 482005;
//
 //       cities.add(city);
//
 //       City city2 = new City();
 //       city2.name = "Glasgow";
 //       city2.population = 598830;
//
 //       cities.add(city2);
//
 //       City city3 = new City();
 //       city3.name = "Stirling";
 //       city3.population = 92830;
//
 //       cities.add(city3);
//
 //       app.printRegionCities(cities);
 //   }
//
//
 //   /**
 //    * Task 14
 //    */
 //   @Test
 //   void printContinentCitiesTestEmpty()
 //   {
 //       ArrayList<City> city = new ArrayList<City>();
//
 //       app.printContinentCities(city);
 //   }
//
 //   @Test
 //   void printContinentCities() {
 //       ArrayList<City> cities = new ArrayList<City>();
//
 //       City city = new City();
 //       city.name = "Hamburg";
 //       city.population = 1810000;
//
 //       cities.add(city);
//
 //       City city2 = new City();
 //       city2.name = "Warsaw";
 //       city2.population = 1745000;
//
 //       cities.add(city2);
//
 //       City city3 = new City();
 //       city3.name = "Paris";
 //       city3.population = 2200000;
//
 //       cities.add(city3);
//
 //       app.printContinentCities(cities);
 //   }
//
//
 //   /**
 //    * Task 15
 //    */
 //   @Test
 //   void printWorldCitiesTestEmpty()
 //   {
 //       ArrayList<City> city = new ArrayList<City>();
//
 //       app.printWorldCities(city);
 //   }
//
 //   @Test
 //   void printWorldCities()
 //   {
 //       ArrayList<City> cities = new ArrayList<City>();
//
 //       City city = new City();
 //       city.name = "Hamburg";
 //       city.population = 1810000;
//
 //       cities.add(city);
//
 //       City city2 = new City();
 //       city2.name = "Warsaw";
 //       city2.population = 1745000;
//
 //       cities.add(city2);
//
 //       City city3 = new City();
 //       city3.name = "Paris";
 //       city3.population = 2200000;
//
 //       cities.add(city3);
//
 //       app.printWorldCities(cities);
 //   }
//
//
 //   /**
 //    * Task 16
 //    */
 //   @Test
 //   void printRegionCountriesTestEmpty()
 //   {
 //       ArrayList<Country> country = new ArrayList<Country>();
//
 //       app.printRegionCountries(country);
 //   }
//
 //   @Test
 //   void printRegionCountries()
 //   {
 //       ArrayList<Country> countries = new ArrayList<Country>();
//
 //       Country country = new Country();
 //       country.name = "Spain";
 //       country.population = 1810000;
//
 //       countries.add(country);
//
 //       Country country2 = new Country();
 //       country2.name = "Ukraine";
 //       country2.population = 1810000;
//
 //       countries.add(country2);
//
 //       Country country3 = new Country();
 //       country3.name = "Germany";
 //       country3.population = 1810000;
//
 //       countries.add(country3);
//
 //       app.printRegionCountries(countries);
 //   }
//
//
 //   /**
 //    * Task 17
 //    */
//
 //   @Test
 //   void printCountryPopFromContinentEmpty()
 //   {
 //       ArrayList<Country> country = new ArrayList();
//
 //       app.printPopulationCountryFromContinent(country);
 //   }
//
 //   @Test
 //   void printCountryPopFromContinent()
 //   {
 //       ArrayList<Country> countries = new ArrayList();
//
 //       Country country = new Country();
 //       country.name = "France";
 //       country.population = 65480710;
 //       country.continent = "Europe";
//
 //       countries.add(country);
//
 //       Country country2 = new Country();
 //       country2.name = "Germany";
 //       country2.population = 82393880;
 //       country2.continent = "Europe";
//
 //       countries.add(country2);
//
 //       app.printPopulationCountry(countries);
 //   }
//
//
 //   /**
 //    * Task 18
 //    */
 //   @Test
 //   void printCountryPopFromWorldEmpty()
 //   {
 //       ArrayList<Country> Country = new ArrayList();
//
 //       app.printPopulationCountryFromWorld(Country);
 //   }
//
 //   @Test
 //   void printCountryFromWorld() {
 //       ArrayList<Country> countries = new ArrayList();
//
 //       Country country = new Country();
 //       country.name = "Germany";
 //       country.continent = "Europe";
 //       country.population = 82393880;
//
 //       countries.add(country);
//
 //       Country country2 = new Country();
 //       country2.name = "France";
 //       country2.continent = "Europe";
 //       country2.population = 59225700;
//
 //       countries.add(country2);
//
 //       app.printPopulationCountryFromWorld(countries);
 //   }
//
//
 //   /**
 //    * Task 19
 //    */
 //   @Test
 //   void printPopInUrbanAndRuralCountryEmpty()
 //   {
 //       ArrayList<Country> Country = new ArrayList<Country>();
//
 //       app.printPopulationUrbanRural(Country);
 //   }
//
 //   @Test
 //   void printPopInUrbanAndRuralCountry()
 //   {
 //       ArrayList<Country> countries = new ArrayList<Country>();
//
 //       Country country = new Country();
 //       country.name = "United Kingdom";
 //       country.populationRural = 9458100;
 //       country.populationUrban = 55055726;
//
 //       countries.add(country);
//
 //       Country country2 = new Country();
 //       country2.name = "France";
 //       country2.populationUrban = 49225700;
 //       country2.populationRural = 13250698;
//
 //       countries.add(country2);
//
 //       app.printPopInUrbanAndRuralCountry(countries);
 //   }
//
//
 //   /**
 //    * Task 20
 //    */
 //   @Test
 //   void printPopInUrbanAndRuralRegionEmpty()
 //   {
 //       ArrayList<Country> country = new ArrayList<Country>();
//
 //       app.printPopulationUrbanRural(country);
 //   }
//
 //   @Test
 //   void printPopInUrbanAndRuralRegion()
 //   {
 //       ArrayList<Country> countries = new ArrayList();
//
 //       Country country = new Country();
 //       country.region = "Western Europe";
 //       country.populationRural = 102543123;
 //       country.populationUrban = 295632344;
 //       country.population = 397654173;
//
 //       countries.add(country);
//
 //       Country country2 = new Country();
 //       country2.region = "Eastern Europe";
 //       country2.populationRural = 95654163;
 //       country2.populationUrban = 198422463;
 //       country2.population = 291953328;
//
 //       countries.add(country2);
//
 //       app.printPopInUrbanAndRuralRegion(countries);
 //   }
//
//
 //   /**
 //    * Task 21
 //    */
 //   @Test
 //   void printPopInCityAndRuralContinentEmpty()
 //   {
 //       ArrayList<City> City = new ArrayList<City>();
//
 //       app.printPopulationUrbanRural(continent);
 //   }
//
 //   @Test
 //   void printPopInCityAndContinentRegion()
 //   {
 //       ArrayList<City> cities = new ArrayList<City>();
//
 //       City city = new City();
 //       city.name = "London";
 //       city.country = "United Kingdom";
 //       city.population = 8136000;
//
 //       cities.add(city);
//
 //       City city2 = new City();
 //       city2.name = "Paris";
 //       city2.country = "France";
 //       city2.population = 2000000;
//
 //       cities.add(city2);
//
 //       app.printPopInCityAndContinentRegion(cities);
 //   }
//
//
 //   /**
 //    * Task 22
 //    */
 //   @Test
 //   void printGivenCapitalCitiesInRegionEmpty()
 //   {
 //       ArrayList<City> City = new ArrayList<City>();
//
 //       app.printCapitalCitiesInGivenRegion(City);
 //   }
//
 //   @Test
 //   void printGivenCapitalCitiesInRegion()
 //   {
 //       ArrayList<City> cities = new ArrayList<City>();
//
 //       City city = new City();
 //       city.name = "London";
 //       city.country = "United Kingdom";
 //       city.region = "British Isles";
 //       city.population = 123456;
//
 //       cities.add(city);
//
 //       City city2 = new City();
 //       city2.name = "Paris";
 //       city2.country = "France";
 //       city2.region = "Western Europe";
 //       city2.population = 223456;
//
 //       cities.add(city2);
//
 //       app.printCapitalCitiesInRegion(cities);
 //   }
//
//
//
//
 //   /**
 //    * Task 24
 //    */
 //   @Test
 //   void printGivenCapitalCitiesInWorldEmpty()
 //   {
 //       ArrayList<City> city = new ArrayList<City>();
//
 //       app.printCapitalCitiesInGivenContinent(city);
 //   }
//
 //   @Test
 //   void printGivenCapitalCitiesInWorld()
 //   {
 //       ArrayList<City> cities = new ArrayList<City>();
//
 //       City city = new City();
 //       city.name = "London";
 //       city.population = 23456;
 //       cities.add(city);
//
 //       City city2 = new City();
 //       city2.name = "Budapest";
 //       city2.population = 33456;
 //       cities.add(city2);
//
 //       City city3 = new City();
 //       city3.name = "Bucharest";
 //       city3.population = 43456;
 //       cities.add(city3);
//
 //       City city4 = new City();
 //       city4.name = "Barcelona";
 //       city4.population = 53456;
 //       cities.add(city4);
//
 //       City city5 = new City();
 //       city5.name = "Milan";
 //       city5.population = 63456;
 //       cities.add(city5);
//
 //       app.printCapitalCitiesInWorld(cities);
 //   }
//
//
 //   /**
 //    * Task 25
 //    */
 //   @Test
 //   void printGivenCapitalCitiesInWorldCountry()
 //   {
 //       ArrayList<City> city = new ArrayList<City>();
//
 //       app.printCapitalCitiesInGivenContinent(city);
 //   }
//
 //   @Test
 //   void printGivenCapitalCitiesInCountry()
 //   {
 //       ArrayList<City> cities = new ArrayList<City>();
//
 //       City city = new City();
 //       city.name = "London";
 //       city.country = "United Kingdom";
 //       cities.add(city);
//
 //       City city2 = new City();
 //       city2.name = "Paris";
 //       city2.country = "France";
 //       cities.add(city2);
//
 //       City city3 = new City();
 //       city3.name = "Lisbon";
 //       city3.country = "Portugal";
 //       cities.add(city3);
//
 //       app.printCapitalCitiesInCountry(cities);
 //   }
//
//
 //   /**
 //    * Task 26
 //    */
 //   @Test
 //   void toppopulatedcitiescountryempty()
 //   {
 //       ArrayList<Country> countries = new ArrayList<Country>();
//
 //       app.printtoppopulatedcitiescountry(countries);
 //   }
//
 //   @Test
 //   void printtoppoplatedcitiescountry()
 //   {
 //       ArrayList<Country> countries = new ArrayList<Country>();
//
 //       Country country = new Country();
 //       country.city = "London";
 //       country.name = "United Kingdom";
 //       country.population = 7285000;
//
 //       countries.add(country);
//
 //       Country country2 = new Country();
 //       country2.city = "Dublin";
 //       country2.name = "Ireland";
 //       country2.population = 481854;
//
 //       countries.add(country2);
//
 //       Country country3 = new Country();
 //       country3.city = "Paris";
 //       country3.name = "France";
 //       country3.population = 59225700;
//
 //       countries.add(country3);
//
 //       app.printtoppoplatedcitiescountry(countries);
 //   }
//
//
 //   /**
 //    * Task 27
 //    */
 //   @Test
 //   void topregionsempty()
 //   {
 //       ArrayList<Region> regions = new ArrayList<Region>();
//
 //       app.printtopregions(regions);
 //   }
//
 //   @Test
 //   void printtopregions()
 //   {
 //       ArrayList<Region> regions = new ArrayList<Region>();
//
 //       Region region = new Region();
 //       region.city = "London";
 //       region.name = "British Islands";
 //       region.population = 7285000;
//
 //       regions.add(region);
//
 //       Region region2 = new Region();
 //       region2.city = "Dublin";
 //       region2.name = "British Islands";
 //       region2.population = 481854;
//
 //       regions.add(region2);
//
 //       Region region3 = new Region();
 //       region3.city = "Paris";
 //       region3.name = "Western Europe";
 //       region3.population = 59225700;
//
 //       regions.add(region3);
//
 //       app.printtopregions(regions);
 //   }
//
//
 //   /**
 //    * Task 28
 //    */
 //   @Test
 //   void topcontinentempty()
 //   {
 //       ArrayList<Continent> continents = new ArrayList<Continent>();
//
 //       app.printtopcontinent(continents);
 //   }
//
 //   @Test
 //   void printtopcontinents()
 //   {
 //       ArrayList<Continent> continents = new ArrayList<Continent>();
//
 //       Continent continent = new Continent();
 //       continent.city = "London";
 //       continent.name = "Europe";
 //       continent.population = 7285000;
//
 //       continents.add(continent);
//
 //       Continent continent2 = new Continent();
 //       continent2.city = "Dublin";
 //       continent2.name = "Europe";
 //       continent2.population = 481854;
//
 //       continents.add(continent2);
//
 //       Continent continent3 = new Continent();
 //       continent3.city = "Paris";
 //       continent3.name = "Europe";
 //       continent3.population = 59225700;
//
 //       continents.add(continent3);
//
 //       app.printtopcontinents(continents);
 //   }
//
//
 //   /**
 //    * Task 29
 //    */
 //   @Test
 //   void topworldempty()
 //   {
 //       ArrayList<World> worlds = new ArrayList<World>();
//
 //       app.printtopworld();
 //   }
//
 //   @Test
 //   void printtopworld()
 //   {
 //       ArrayList<World> worlds = new ArrayList<World>();
//
 //       World world = new World();
 //       world.city = "London";
 //       world.region = "Europe";
 //       world.population = 7285000;
//
 //       worlds.add(world);
//
 //       World world2 = new World();
 //       world2.city = "Dublin";
 //       world2.region = "Europe";
 //       world2.population = 481854;
//
 //       worlds.add(world2);
//
 //       World world3 = new World();
 //       world3.city = "Paris";
 //       world3.region = "Europe";
 //       world3.population = 59225700;
//
 //       worlds.add(world3);
//
 //       app.printtopworld(worlds);
 //   }
//
//
 //   /**
 //    * Task 30
 //    */
 //   @Test
 //   void topregionsempty2()
 //   {
 //       ArrayList<Region> regions = new ArrayList<Region>();
//
 //       app.printtopregions2(regions);
 //   }
//
 //   @Test
 //   void printtopregions2()
 //   {
 //       ArrayList<Region> regions = new ArrayList<Region>();
//
 //       Region region = new Region();
 //       region.city = "London";
 //       region.name = "British Islands";
 //       region.population = 7285000;
//
 //       regions.add(region);
//
 //       Region region2 = new Region();
 //       region2.city = "Dublin";
 //       region2.name = "British Islands";
 //       region2.population = 481854;
//
 //       regions.add(region2);
//
 //       Region region3 = new Region();
 //       region3.city = "Paris";
 //       region3.name = "Western Europe";
 //       region3.population = 59225700;
//
 //       regions.add(region3);
//
 //       app.printtopregions2(regions);
 //   }
//
//
 //   /**
 //    * Task 31
 //    */
 //   @Test
 //   void topcontinentempty2()
 //   {
 //       ArrayList<Continent> continents = new ArrayList<Continent>();
//
 //       app.printtopcontinent2(continents);
 //   }
//
 //   @Test
 //   void printtopcontinents2()
 //   {
 //       ArrayList<Continent> continents = new ArrayList<Continent>();
//
 //       Continent continent = new Continent();
 //       continent.city = "London";
 //       continent.name = "Europe";
 //       continent.population = 7285000;
//
 //       continents.add(continent);
//
 //       Continent continent2 = new Continent();
 //       continent2.city = "Dublin";
 //       continent2.name = "Europe";
 //       continent2.population = 481854;
//
 //       continents.add(continent2);
//
 //       Continent continent3 = new Continent();
 //       continent3.city = "Paris";
 //       continent3.name = "Europe";
 //       continent3.population = 59225700;
//
 //       continents.add(continent3);
//
 //       app.printtopcontinents2(continents);
 //   }
//
//
 //   /**
 //    * Task 32
 //    */
 //   @Test
 //   void topworldempty2()
 //   {
 //       ArrayList<World> worlds = new ArrayList<World>();
//
 //       app.printtopworld2();
 //   }
//
 //   @Test
 //   void printtopworld2()
 //   {
 //       ArrayList<World> worlds = new ArrayList<World>();
//
 //       World world = new World();
 //       world.city = "London";
 //       world.region = "Europe";
 //       world.population = 7285000;
//
 //       worlds.add(world);
//
 //       World world2 = new World();
 //       world2.city = "Dublin";
 //       world2.region = "Europe";
 //       world2.population = 481854;
//
 //       worlds.add(world2);
//
 //       World world3 = new World();
 //       world3.city = "Paris";
 //       world3.region = "Europe";
 //       world3.population = 59225700;
//
 //       worlds.add(world3);
//
 //       app.printtopworld2(worlds);
 //   }
}