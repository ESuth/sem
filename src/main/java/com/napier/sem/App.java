package com.napier.sem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;

@SpringBootApplication
@RestController
public class App
{
    /**
     * Connection to MySQL database..
     */
    private static Connection con = null;



    /**
     * Connect to the MySQL database..
     */
    public static void connect(String location)
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(3000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location + "/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }



    /**
     * Disconnect from the MySQL database.
     */
    public static void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to world database");
            }
        }
    }

    //Get Methods

    /**
     * Task 1
     */
    /**
     * Get a single city's population.
     * @param city name of the city to get.
     * @return The record of the city and its population.
     */
    @RequestMapping("citypop")
    public City getCityEdinburghPop(@RequestParam(value = "city") String city)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT city.Name, country.Name, city.District, city.Population " +
                               "FROM world.city, world.country " +
                               "WHERE city.CountryCode = country.Code " +
                               "AND city.Name = '" + city + "' " +
                               "AND country.Code = city.CountryCode";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next())
            {
                City edinburgh = new City();
                edinburgh.name = rset.getString("city.Name");
                edinburgh.country = rset.getString("country.Name");
                edinburgh.district = rset.getString("city.District");
                edinburgh.population = rset.getLong("city.Population");
                return edinburgh;
            }
            else
                return null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Edinburgh's details");
            return null;
        }
    }

    /**
     * Task 2
     */
    /**
     * Get a single district's population.
     * @param district name of the district to get.
     * @return The record of the district and its population.
     */
    @RequestMapping("districtpop")
    public City getDistrictScotlandPop(@RequestParam(value = "district") String district)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT country.Name, city.District, SUM(city.Population) " +
                    "FROM world.city, world.country " +
                    "WHERE city.CountryCode = country.Code " +
                    "AND city.District = '" + district + "' " +
                    "AND country.Code = city.CountryCode " +
                    "GROUP BY country.Name, city.District";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next())
            {
                City scotland = new City();
                scotland.country = rset.getString("country.Name");
                scotland.district = rset.getString("city.District");
                scotland.population = rset.getLong("SUM(city.Population)");
                return scotland;
            }
            else
                return null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Scotland's details");
            return null;
        }
    }

    /**
     * Task 3
     */
    /**
     * Get a single country's population.
     * @param country name of the country to get.
     * @return The record of the country and its population.
     */
    @RequestMapping("countrypop")
    public Country getCountryUKPop(@RequestParam(value = "country") String country)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT country.Code, country.Name, country.Continent, country.Region, city.Name, country.Population " +
                    "FROM world.country, world.city " +
                    "WHERE country.Name = '" + country + "' " +
                    "AND country.Capital = city.ID";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next())
            {
                Country uK = new Country();
                uK.code = rset.getString("country.Code");
                uK.name = rset.getString("country.Name");
                uK.continent = rset.getString("country.Continent");
                uK.region = rset.getString("country.Region");
                uK.city = rset.getString("city.Name");
                uK.population = rset.getLong("country.Population");
                return uK;
            }
            else
                return null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get United Kingdom's details");
            return null;
        }
    }

    /**
     * Task 4
     */
    /**
     * Get a single region's population.
     * @param region name of the region to get.
     * @return The record of the region and its population.
     */
    @RequestMapping("regionpop")
    public Country getRegionBritIslesPop(@RequestParam(value = "region") String region)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT Region, Continent, SUM(Population) " +
                    "FROM world.country " +
                    "WHERE Region = '" + region + "' " +
                    "GROUP BY Region, Continent";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next())
            {
                Country britIsles = new Country();
                britIsles.continent = rset.getString("Continent");
                britIsles.region = rset.getString("Region");
                britIsles.population = rset.getLong("SUM(Population)");
                return britIsles;
            }
            else
                return null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get British Islands's details");
            return null;
        }
    }

    /**
     * Task 5
     */
    /**
     * Get a single continent's population.
     * @param continent name of the region to get.
     * @return The record of the continent and its population.
     */
    @RequestMapping("continentpop")
    public Country getContinentEuropePop(@RequestParam(value = "continent") String continent)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT Continent, SUM(Population) " +
                    "FROM world.country " +
                    "WHERE Continent = '" + continent + "'";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next())
            {
                Country europe = new Country();
                europe.continent = rset.getString("Continent");
                europe.population = rset.getLong("SUM(Population)");
                return europe;
            }
            else
                return null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Europe's details");
            return null;
        }
    }

    /**
     * Task 6
     */
    /**
     * Get a list of specific languages and the population of the world that speaks them in a specific order and there percentages.
     * @return The record of the languages and there populations.
     */
    @RequestMapping("languages")
    public ArrayList<Language> getLanguageList()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT DISTINCT countrylanguage.Language, SUM(city.Population) as worldPopulation, ROUND((SUM(city.Population) / 6078749450 * 100), 2) as WorldPercentage "
                            + "FROM world.countrylanguage, world.city "
                            + "WHERE city.CountryCode = countrylanguage.CountryCode "
                            + "AND countrylanguage.Language in ('Chinese', 'English', 'Hindi', 'Spanish', 'Arabic') "
                            + "GROUP BY countrylanguage.Language "
                            + "ORDER BY worldPopulation DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract employee information
            ArrayList<Language> languages = new ArrayList<Language>();
            while (rset.next())
            {
                Language language = new Language();
                language.language = rset.getString("countrylanguage.Language");
                language.population = rset.getLong("worldPopulation");
                language.percentage = rset.getDouble("WorldPercentage");
                languages.add(language);
            }
            return languages;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get language details");
            return null;
        }
    }

    /**
     * Task 7
     */
    /**
     * Get a single city's population.
     * @return The record of the world population.
     */
    @RequestMapping("worldpop")
    public Country getWorldPop()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT SUM(Population) " +
                    "FROM world.country ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next())
            {
                Country world = new Country();
                world.population = rset.getLong("SUM(Population)");
                return world;
            }
            else
                return null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get World's details");
            return null;
        }
    }

    /**
     * Task 8
     */
    /**
     * Get a list of all the capital cities in a region and their populations in a specific order.
     * @param region name of the region to get.
     * @return The record of the capital cities in a region and there populations.
     */
    @RequestMapping("regioncapcitypop")
    public ArrayList<City> getRegionCapitalCityList(@RequestParam(value = "region") String region)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                              "SELECT city.Name, country.Name, city.Population "
                            + "FROM world.city, world.country "
                            + "WHERE country.Capital = city.ID "
                            + "AND country.Region = '" + region + "' "
                            + "ORDER BY city.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract employee information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next())
            {
                City city = new City();
                city.name = rset.getString("city.Name");
                city.country = rset.getString("country.Name");
                city.population = rset.getLong("city.Population");
                cities.add(city);
            }
            return cities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital cities by region's details");
            return null;
        }
    }

    /**
     *  Task 9
     */
    /**
     * Get a list of all capital cities in a continent and their population from largest to smallest.
     * @param continent name of the continent to get.
     * @return The record of the capital cities in a continent and their population.
     */
    @RequestMapping("continentcapcitypop")
    public ArrayList<City> getContinentCapitalCityList (@RequestParam(value = "continent") String continent)
    {
        try {
            // Create SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, country.Name, city.Population "
                            +   "FROM world.city, world.country "
                            +   "WHERE country.Capital = city.ID "
                            +   "AND country.Continent = '" + continent + "' "
                            +   "ORDER BY city.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract city information
            ArrayList<City> cities = new ArrayList<City>();
            while(rset.next()) {
                City city = new City();
                city.name = rset.getString("city.Name");
                city.country = rset.getString("country.Name");
                city.population = rset.getLong("city.Population");
                cities.add(city);
            }
            return cities;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital cities by continent's details");
            return null;
        }
    }

/**
 *  Task 10
 */
    /**
     * Get a list of all capital cities in the world and their population from largest to smallest.
     * @return The record of the capital cities in the world and their population.
     */
    @RequestMapping("worldcapcitypop")
    public ArrayList<City> getWorldCapitalCityList()
    {
        try {
            // Create SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, country.Name, city.Population "
                            +   "FROM world.city, world.country "
                            +   "WHERE country.Capital = city.ID "
                            +   "ORDER BY city.Population DESC "
                            +   "LIMIT 50";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract city information
            ArrayList<City> cities = new ArrayList<City>();
            while(rset.next()) {
                City city = new City();
                city.name = rset.getString("city.Name");
                city.country = rset.getString("country.Name");
                city.population = rset.getLong("city.Population");
                cities.add(city);
            }
            return cities;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital cities of the world");
            return null;
        }
    }

    /**
     *  Task 11
     *  Get a list of all cities in a district and their population from largest to smallest
     *  @param district name of the district to search
     *  @return The record of the capital cities in a district and their population.
     */
    @RequestMapping("districtcitypop")
    public ArrayList<City> getDistrictCityList (@RequestParam(value = "district") String district)
    {
        try {
            // Create SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT DISTINCT city.Name, country.Name, city.District, city.Population "
                            +   "FROM world.city, world.country "
                            +   "WHERE city.District = '" + district + "' "
                            +   "AND country.Code = city.CountryCode "
                            +   "ORDER BY city.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract city information
            ArrayList<City> cities = new ArrayList<City>();
            while(rset.next()) {
                City city = new City();
                city.name = rset.getString("city.Name");
                city.country = rset.getString("country.Name");
                city.district = rset.getString("city.District");
                city.population = rset.getLong("city.Population");
                cities.add(city);
            }
            return cities;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get cities by district's details");
            return null;
        }
    }


    /**
     *  Task 12
     */
    /**
     * Get a list of all cities in a country and their population from largest to smallest.
     * @param country name of the country to get.
     * @return The record of the cities in a country and their population.
     */
    @RequestMapping("countrycitypop")
    public ArrayList<City> getCountryCityList (@RequestParam(value = "country") String country)
    {
        try {
            // Create SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, country.Name, city.District, city.Population "
                            +   "FROM world.city, world.country "
                            +   "WHERE country.Name = '" + country + "' "
                            +   "AND country.Code = city.CountryCode "
                            +   "ORDER BY city.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract city information
            ArrayList<City> cities = new ArrayList<City>();
            while(rset.next()) {
                City city = new City();
                city.name = rset.getString("city.Name");
                city.country = rset.getString("country.Name");
                city.district = rset.getString("city.District");
                city.population = rset.getLong("city.Population");
                cities.add(city);
            }
            return cities;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get cities by country's details");
            return null;
        }
    }

    /**
     *  Task 13
     */
    /**
     * Get a list of all cities in a region and their population from largest to smallest.
     * @param region name of the region to get.
     * @return The record of the cities in a region and their population.
     */
    @RequestMapping("regioncitypop")
    public ArrayList<City> getRegionCityList (@RequestParam(value = "region") String region)
    {
        try {
            // Create SQL statement
            Statement stmt = con.createStatement();
            // create string for SQL statement
            String strSelect =
                    "SELECT DISTINCT city.Name, country.Name, country.Region, city.Population "
                            +   "FROM world.city, world.country "
                            +   "WHERE country.region = '" + region + "' "
                            +   "AND country.Code = city.CountryCode "
                            +   "ORDER BY city.Population DESC "
                            +   "LIMIT 25";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract city information
            ArrayList<City> cities = new ArrayList<City>();
            while(rset.next()) {
                City city = new City();
                city.name = rset.getString("city.Name");
                city.country = rset.getString("country.Name");
                city.region = rset.getString("country.Region");
                city.population = rset.getLong("city.Population");
                cities.add(city);
            }
            return cities;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get cities by distric's details");
            return null;
        }
    }

    /**
     *  Task 14
     */
    /**
     * Get a list of all cities in a continent and their population from largest to smallest.
     * @param continent name of the continent to get.
     * @return The record of the cities in a continent and their population.
     */
    @RequestMapping("continentcitypop")
    public ArrayList<City> getContinentCityList (@RequestParam(value = "continent") String continent)
    {
        try {
            // Create SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, country.Name, country.Continent, city.Population "
                            +   "FROM world.city, world.country "
                            +   "WHERE country.Continent = '" + continent + "' "
                            +   "AND city.CountryCode = country.Code "
                            +   "ORDER BY city.Population DESC "
                            +   "LIMIT 25";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract city information
            ArrayList<City> cities = new ArrayList<City>();
            while(rset.next()) {
                City city = new City();
                city.name = rset.getString("city.Name");
                city.country = rset.getString("country.Name");
                city.continent = rset.getString("country.Continent");
                city.population = rset.getLong("city.Population");
                cities.add(city);
            }
            return cities;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get cities by continent's details");
            return null;
        }
    }

    /**
     *  Task 15
     */
    /**
     * @return The record of the cities in the world and their population.
     */
    @RequestMapping("worldcitypop")
    public ArrayList<City> getWorldCityPopList()
    {
        try {
            // Create SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, country.Name, city.District, city.Population "
                            +   "FROM world.city, world.country "
                            +   "WHERE country.Code = city.CountryCode "
                            +   "ORDER BY city.Population DESC "
                            +   "LIMIT 50";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract city information
            ArrayList<City> cities = new ArrayList<City>();
            while(rset.next()) {
                City city = new City();
                city.name = rset.getString("city.Name");
                city.country = rset.getString("country.Name");
                city.district = rset.getString("city.District");
                city.population = rset.getLong("city.Population");
                cities.add(city);
            }
            return cities;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get cities of the world");
            return null;
        }
    }

    /**
     * Task 16
     */
    /**
     * Get a list of all the countries in a region and their populations from largest to smallest
     * @param region name of the region to get.
     * @return The record of the countries in a region and there populations.
     */
    @RequestMapping("regioncountrypop")
    public ArrayList<Country> getRegionCountryList(@RequestParam(value = "region") String region)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Code, country.Name, country.Continent, country.Region, city.Name, country.Population "
                            + "FROM world.country, world.city "
                            + "WHERE country.Region = '" + region + "' "
                            + "AND country.Code = city.CountryCode "
                            + "ORDER BY country.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract employee information
            ArrayList<Country> countries = new ArrayList<Country>();
            while (rset.next())
            {
                Country country = new Country();
                country.code = rset.getString("country.Code");
                country.name = rset.getString("country.Name");
                country.continent = rset.getString("country.Continent");
                country.region = rset.getString("country.Region");
                country.city = rset.getString("city.Name");
                country.population = rset.getLong("country.Population");
                countries.add(country);
            }
            return countries;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get countries by region's details");
            return null;
        }
    }

    /**
     *  Task 17
     */
    /**
     *  Get a list of all countries in a continent and their population from largest to smallest
     *  @return The record of all the countries in a continent and there populations
     */
    @RequestMapping("continentcountrypop")
    public ArrayList<Country> getContinentCountryPop(@RequestParam(value = "continent")String continent)
    {
        try
        {
            // Create and SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Code, country.Name, country.Continent, country.Region, city.Name, country.Population "
                            + "FROM world.country, world.city "
                            + "WHERE country.Continent = '" + continent + "' "
                            + "AND country.Capital = city.ID "
                            + "ORDER BY country.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract employee information
            ArrayList<Country> countries = new ArrayList<Country>();
            while (rset.next())
            {
                Country country = new Country();
                country.code = rset.getString("country.Code");
                country.name = rset.getString("country.Name");
                country.continent = rset.getString("country.Continent");
                country.region = rset.getString("country.Region");
                country.city = rset.getString("city.Name");
                country.population = rset.getLong("country.Population");
                countries.add(country);
            }
            return countries;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get countries by continent's details");
            return null;
        }
    }

    /**
     * Task 18
     */
    /**
     * Get a list of all the countries in the world and their populations from largest to smallest
     * @return The record of the countries in the world and there populations.
     */
    @RequestMapping("worldcountrypop")
    public ArrayList<Country> getWorldCountryList()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Code, country.Name, country.Continent, country.Region, city.Name, country.Population "
                            + "FROM world.country, world.city "
                            + "WHERE country.Capital = city.ID "
                            +   "ORDER BY country.Population DESC "
                            +   "LIMIT 50";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract employee information
            ArrayList<Country> countries = new ArrayList<Country>();
            while (rset.next())
            {
                Country country = new Country();
                country.code = rset.getString("country.Code");
                country.name = rset.getString("country.Name");
                country.continent = rset.getString("country.Continent");
                country.region = rset.getString("country.Region");
                country.city = rset.getString("city.Name");
                country.population = rset.getLong("country.Population");
                countries.add(country);
            }
            return countries;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get countries in world");
            return null;
        }
    }

    /**
     *  Task 19
     */
    /**
     *  Get a list of all people living in cities and people not living in cities in each country
     */
    @RequestMapping("populationruralurbancountry")
    public ArrayList<Country> getPopulationFromRuralUrbanCountry (@RequestParam(value = "country") String country)
    {
        try {
            // Create SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Code, country.Name, country.Continent, country.Region, city.Name, country.Population "
                            +   "FROM world.country, world.city "
                            +   "WHERE country.Name = '" + country + "' "
                            +   "AND country.Capital = city.ID "
                            +   "ORDER BY country.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract Information
            ArrayList<Country> countries = new ArrayList<Country>();
            while(rset.next())
            {
                Country country1 = new Country();
                country1.code = rset.getString("country.Code");
                country1.name = rset.getString("country.Name");
                country1.continent = rset.getString("country.Continent");
                country1.region = rset.getString("country.Region");
                country1.population = rset.getLong("country.Population");
                country1.city = rset.getString("city.Name");
            }
            return countries;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get urban/rural pop from countries");
            return null;
        }
    }

    /**
     *
     *  Task 20
     */
    /**
     * Get a list of all people living in cities and people not living in cities in each region
     *
     */
    @RequestMapping("populationruralurbanregion")
    public ArrayList<Country> getPopulationFromRuralUrbanRegion (@RequestParam(value = "region") String region)
    {
        try {
            // Create SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT DISTINCT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name "
                            +   "FROM world.country, world.city "
                            +   "WHERE country.Region = '" + region + "' "
                            +   "AND country.Capital = city.ID "
                            +   "ORDER BY country.Code DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract Information
            ArrayList<Country> countries = new ArrayList<>();
            while(rset.next())
            {
                Country country1 = new Country();
                country1.code = rset.getString("country.Code");
                country1.name = rset.getString("country.Name");
                country1.continent = rset.getString("country.Continent");
                country1.region = rset.getString("country.Region");
                country1.population = rset.getLong("country.Population");
                country1.city = rset.getString("city.Name");
            }
            return countries;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get urban/rural pop from continent");
            return null;
        }
    }

    /**
     *
     *  Task 21
     */
    /**
     * Get a list of all people living in cities and people not living in cities in each continent
     *
     */
    @RequestMapping("populationruralurbancontinent")
    public ArrayList<Country> getPopulationFromRuralUrbanContinent (@RequestParam(value = "continent") String continent)
    {
        try {
            // Create SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Code, country.Name, country.Continent, country.Region, city.Name, country.Population "
                            +   "FROM world.country, world.city "
                            +   "WHERE country.Continent = '" + continent + "' "
                            +   "AND country.Capital = city.ID "
                            +   "ORDER BY country.Population DESC "
                            +   "LIMIT 15";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract Information
            ArrayList<Country> countries = new ArrayList<Country>();
            while(rset.next())
            {
                Country country1 = new Country();
                country1.code = rset.getString("country.Code");
                country1.name = rset.getString("country.Name");
                country1.continent = rset.getString("country.Continent");
                country1.region = rset.getString("country.Region");
                country1.city = rset.getString("city.Name");
                country1.population = rset.getLong("country.Population");
            }
            return countries;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get urban/rural pop from continent");
            return null;
        }
    }

    /**
     *  Task 22
     */
    /**
     * Get a list of the top N capital cities in a region and their population from largest to smallest. Where N is supplied by user
     * @param limit amount of rows to return.
     * @return The record of the top N capital cities in the world and their population.
     */
    @RequestMapping("regioncapitalcitypoplimit")
    public ArrayList<City> getRegionCapitalCityListWithLimit (@RequestParam(value = "region") String region, @RequestParam(value = "limit") String limit)
    {
        try {
            // Create SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, country.Name, city.Population "
                            +   "FROM world.city, world.country "
                            +   "WHERE country.Region = '" + region + "' "
                            +   "AND country.Capital = city.ID "
                            +   "ORDER BY city.Population DESC "
                            +   "LIMIT " + limit;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract city information
            ArrayList<City> cities = new ArrayList<City>();
            while(rset.next()) {
                City city = new City();
                city.name = rset.getString("city.Name");
                city.country = rset.getString("country.Name");
                city.population = rset.getLong("city.Population");
                cities.add(city);
            }
            return cities;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital cities by regions details");
            return null;
        }
    }

    /**
     *  Task 23
     */
    /**
     * Get a list of the top N capital cities in a continent and their population from largest to smallest. Where N is supplied by user
     * @param continent name of the continent to get.
     * @param limit amount of rows to return.
     * @return The record of the top N capital cities in a continent and their population.
     */
    @RequestMapping("continentcitypoplimit")
    public ArrayList<City> getContinentCapitalCityListWithLimit (@RequestParam(value = "continent") String continent, @RequestParam(value = "limit") String limit)
    {
        try {
            // Create SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, country.Name, city.Population "
                            +   "FROM world.city, world.country "
                            +   "WHERE country.Capital = city.ID "
                            +   "AND country.Continent = '" + continent + "' "
                            +   "ORDER BY city.Population DESC "
                            +   "LIMIT " + limit;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract city information
            ArrayList<City> cities = new ArrayList<City>();
            while(rset.next()) {
                City city = new City();
                city.name = rset.getString("city.Name");
                city.country = rset.getString("country.Name");
                city.population = rset.getLong("city.Population");
                cities.add(city);
            }
            return cities;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital cities by continent's details");
            return null;
        }
    }
    /**
     *  Task 24
     */
    /**
     * Get a list of the top N capital cities in the world and their population from largest to smallest. Where N is supplied by user
     * @param limit amount of rows to return.
     * @return The record of the top N capital cities in the world and their population.
     */
    @RequestMapping("worldcapitalcitypoplimit")
    public ArrayList<City> getworldCapitalCityListWithLimit ( @RequestParam(value = "limit") String limit)
    {
        try {
            // Create SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, country.Name, city.Population "
                            +   "FROM world.city, world.country "
                            +   "WHERE country.Capital = city.ID "
                            +   "AND country.Code = city.CountryCode "
                            +   "ORDER BY city.Population DESC "
                            +   "LIMIT " + limit;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract city information
            ArrayList<City> cities = new ArrayList<City>();
            while(rset.next()) {
                City city = new City();
                city.name = rset.getString("city.Name");
                city.country = rset.getString("country.Name");
                city.population = rset.getLong("city.Population");
                cities.add(city);
            }
            return cities;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital cities by worlds details");
            return null;
        }
    }


/**
 *  Task 25
 */
    /**
     * Get a list of the top N populated cities in a district where N is provided by the user
     * @param district name of the continent to get.
     * @param limit amount of rows to return.
     * @return The record of the top N  cities in a district and their population.
     */
    @RequestMapping("districtcitypoplimit")
    public ArrayList<City> getDistrictCityListWithLimit (@RequestParam(value = "district") String district, @RequestParam(value = "limit") String limit)
    {
        try {
            // Create SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, country.Name, city.Population "
                            +   "FROM world.city, world.country "
                            +   "WHERE country.Code = city.CountryCode "
                            +   "AND city.District = '" + district  + "' "
                            +   "ORDER BY city.Population DESC "
                            +   "LIMIT " + limit;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract city information
            ArrayList<City> cities = new ArrayList<City>();
            while(rset.next()) {
                City city = new City();
                city.name = rset.getString("city.Name");
                city.country = rset.getString("country.Name");
                city.population = rset.getLong("city.Population");
                cities.add(city);
            }
            return cities;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital cities by district's details");
            return null;
        }
    }
    /**
     *  Task 26
     */
    /**
     * Get a list of the top N populated cities in a country where N is provided by the user
     * @param country name of the country to get.
     * @param limit amount of rows to return.
     * @return The record of the top N  cities in a country and their population.
     */
    @RequestMapping("countrycitypoplimit")
    public ArrayList<City> getCountryCityListWithLimit (@RequestParam(value = "country") String country, @RequestParam(value = "limit") String limit)
    {
        try {
            // Create SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, country.Name, city.Population "
                            +   "FROM world.city, world.country "
                            +   "WHERE country.Code = city.CountryCode "
                            +   "AND country.Name = '" + country  + "' "
                            +   "ORDER BY city.Population DESC "
                            +   "LIMIT " + limit;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract city information
            ArrayList<City> cities = new ArrayList<City>();
            while(rset.next()) {
                City city = new City();
                city.name = rset.getString("city.Name");
                city.country = rset.getString("country.Name");
                city.population = rset.getLong("city.Population");
                cities.add(city);
            }
            return cities;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital cities by countries details");
            return null;
        }
    }



/**
 *  Task 27
 */
    /**
     * Get a list of the top N populated cities in a region where N is provided by the user
     * @param region name of the continent to get.
     * @param limit amount of rows to return.
     * @return The record of the top N  cities in a region and their population.
     */
    @RequestMapping("regioncitypoplimit")
    public ArrayList<City> getregionCityListWithLimit (@RequestParam(value = "region") String region, @RequestParam(value = "limit") String limit)
    {
        try {
            // Create SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, country.Name, city.Population "
                            +   "FROM world.city, world.country "
                            +   "WHERE country.Code = city.CountryCode "
                            +   "AND country.Region = '" + region  + "' "
                            +   "ORDER BY city.Population DESC "
                            +   "LIMIT " + limit;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract city information
            ArrayList<City> cities = new ArrayList<City>();
            while(rset.next()) {
                City city = new City();
                city.name = rset.getString("city.Name");
                city.country = rset.getString("country.Name");
                city.population = rset.getLong("city.Population");
                cities.add(city);
            }
            return cities;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital cities by region's details");
            return null;
        }
    }

    /**
     *  Task 28
     */
    /**
     * Get a list of the top N populated cities in a continent where N is provided by the user
     * @param continent name of the continent to get.
     * @param limit amount of rows to return.
     * @return The record of the top N  cities in a continent and their population.
     */
    @RequestMapping("continentcitypoplimit2")
    public ArrayList<City> getContinentsCityListWithLimit2 (@RequestParam(value = "continent") String continent, @RequestParam(value = "limit") String limit)
    {
        try {
            // Create SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, country.Name, city.Population "
                            +   "FROM world.city, world.country "
                            +   "WHERE country.Code = city.CountryCode "
                            +   "AND country.continent = '" + continent  + "' "
                            +   "ORDER BY city.Population DESC "
                            +   "LIMIT " + limit;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract city information
            ArrayList<City> cities = new ArrayList<City>();
            while(rset.next()) {
                City city = new City();
                city.name = rset.getString("city.Name");
                city.country = rset.getString("country.Name");
                city.population = rset.getLong("city.Population");
                cities.add(city);
            }
            return cities;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital cities by continent's details");
            return null;
        }
    }

    /**
     *  Task 29
     */
    /**
     * Get a list of the top N populated cities in the world where N is provided by the user
     * @param limit amount of rows to return.
     * @return The record of the top N  cities in the world and their population.
     */
    @RequestMapping("worldcitypoplimit")
    public ArrayList<City> getworldCityListWithLimit (@RequestParam(value = "limit") String limit)
    {
        try {
            // Create SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, country.Name, city.Population "
                            +   "FROM world.city, world.country "
                            +   "WHERE country.Code = city.CountryCode "
                            +   "ORDER BY city.Population DESC "
                            +   "LIMIT " + limit;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract city information
            ArrayList<City> cities = new ArrayList<City>();
            while(rset.next()) {
                City city = new City();
                city.name = rset.getString("city.Name");
                city.country = rset.getString("country.Name");
                city.population = rset.getLong("city.Population");
                cities.add(city);
            }
            return cities;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital cities by world details");
            return null;
        }
    }
    /**
     *  Task 30
     */
    /**
     * Get a list of the top N populated countries in the Region where N is provided by the user
     * @param region the region the country is from.
     *  @param limit amount of rows to return.
     * @return The record of the top N  cities in the world and their population.
     */
    @RequestMapping("regioncountrypoplimit")
    public ArrayList<Country> getRegionCountryListWithLimit (@RequestParam(value = "region") String region ,@RequestParam(value = "limit") String limit)
    {
        try {
            // Create SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT DISTINCT country.Code, country.Name, country.Continent, country.Region, country.Population "
                            +   "FROM world.country, world.city "
                            +   "WHERE country.Region = '" + region  + "' "
                            +   "ORDER BY country.Population DESC "
                            +   "LIMIT " + limit;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract city information
            ArrayList<Country> countries = new ArrayList<Country>();
            while(rset.next()) {
                Country country = new Country();
                country.code = rset.getString("country.Code");
                country.name = rset.getString("country.Name");
                country.continent = rset.getString("country.Continent");
                country.region = rset.getString("country.Region");
                country.population = rset.getLong("country.Population");
                countries.add(country);
            }
            return countries;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country population by world details");
            return null;
        }
    }
    /**
     *  Task 31
     */
    /**
     * Get a list of the top N populated countries in the continent where N is provided by the user
     * @param  continent the region the country is from.
     *  @param limit amount of rows to return.
     * @return The record of the top N  cities in the world and their population.
     */
    @RequestMapping("continentcountrypoplimit")
    public ArrayList<Country> getContinentCountryListWithLimit (@RequestParam(value = "continent") String continent ,@RequestParam(value = "limit") String limit)
    {
        try {
            // Create SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT DISTINCT country.Code, country.Name, country.Continent, country.Region, country.Population "
                            +   "FROM world.country, world.city "
                            +   "WHERE country.Continent = '" + continent  + "' "
                            +   "ORDER BY country.Population DESC "
                            +   "LIMIT " + limit;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract city information
            ArrayList<Country> countries = new ArrayList<Country>();
            while(rset.next()) {
                Country country = new Country();
                country.code = rset.getString("country.Code");
                country.name = rset.getString("country.Name");
                country.continent = rset.getString("country.Continent");
                country.region = rset.getString("country.Region");
                country.population = rset.getLong("country.Population");
                countries.add(country);
            }
            return countries;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country population by world details");
            return null;
        }
    }


    /**
     *  Task 32
     */
    /**
     * Get a list of the top N populated countries in the world where N is provided by the user
     * @param limit amount of rows to return.
     * @return The record of the top N  cities in the world and their population.
     */
    @RequestMapping("worldcountrypoplimit")
    public ArrayList<Country> getWorldCountryListWithLimit (@RequestParam(value = "limit") String limit)
    {
        try {
            // Create SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT DISTINCT country.Code, country.Name, country.Continent, country.Region, country.Population "
                            +   "FROM world.country, world.city "
                            +   "ORDER BY country.Population DESC "
                            +   "LIMIT " + limit;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract city information
            ArrayList<Country> countries = new ArrayList<Country>();
            while(rset.next()) {
                Country country = new Country();
                country.code = rset.getString("country.Code");
                country.name = rset.getString("country.Name");
                country.continent = rset.getString("country.Continent");
                country.region = rset.getString("country.Region");
                country.population = rset.getLong("country.Population");
                countries.add(country);
            }
            return countries;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country population by world details");
            return null;
        }
    }

    //Print Methods

    /**
     * Task 1
     */
    public void printPopulationEdinburgh(City edinburgh)
    {
        if (edinburgh != null)
        {
            System.out.println("\nTask: 1, Details retrieved for city Edinburgh as follows: \n\n"
                + " Population of a City"
                + "\n======================\n"
                + "Name: " + edinburgh.name + "\n"
                + "Country: " + edinburgh.country + "\n"
                + "District: " + edinburgh.district + "\n"
                + "Population: " + edinburgh.population + "\n");
        }
    }

    /**
     * Task 2
     */
    public void printPopulationScotland(City scotland)
    {
        if (scotland != null)
        {
            System.out.println("\nTask: 2, Details retrieved for district Scotland as follows: \n\n"
                    + " Population of a District"
                    + "\n==========================\n"
                    + "District: " + scotland.district + "\n"
                    + "Country: " + scotland.country + "\n"
                    + "Population: " + scotland.population + "\n");
        }
    }

    /**
     * Task 3
     */
    public void printPopulationUK(Country uK)
    {
        if (uK != null)
        {
            System.out.println("\nTask: 3, Details retrieved for Country United Kingdom as follows: \n\n"
                    + " Population of a Country"
                    + "\n=========================\n"
                    + "Code: " + uK.code + "\n"
                    + "Name: " + uK.name + "\n"
                    + "Continent: " + uK.continent + "\n"
                    + "Region: " + uK.region + "\n"
                    + "Capital: " + uK.city + "\n"
                    + "Population: " + uK.population + "\n");
        }
    }

    /**
     * Task 4
     */
    public void printPopulationBritIsles(Country BritIsles)
    {
        if (BritIsles != null)
        {
            System.out.println("\nTask: 4, Details retrieved for Region British Islands as follows: \n\n"
                    + " Population of a Region"
                    + "\n========================\n"
                    + "Region: " + BritIsles.region + "\n"
                    + "Continent: " + BritIsles.continent + "\n"
                    + "Population: " + BritIsles.population + "\n");
        }
    }

    /**
     * Task 5
     */
    public void printPopulationEurope(Country europe)
    {
        if (europe != null)
        {
            System.out.println("\nTask: 5, Details retrieved for Continent Europe as follows: \n\n"
                    + " Population of a Continent"
                    + "\n===========================\n"
                    + "Continent: " + europe.continent + "\n"
                    + "Population: " + europe.population + "\n");
        }
    }

    /**
     * Task 6
     */
    public void printLanguages(ArrayList<Language> languages)
    {
        // Print header
        System.out.println("\nTask: 6, Details retrieved for languages as follows: \n");
        System.out.println(String.format("%-12s %-20s %-20s", " Language", " World Population", " World Percentage"));
        System.out.println(String.format("%-12s %-20s %-20s", "==========", "==================", "=================="));
        // Loop over all employees in the list
        for (Language language : languages)
        {
            String language_string =
                    String.format("%-12s %-20d %-20.2f",
                            language.language, language.population, language.percentage);
            System.out.println(language_string);
        }
        System.out.println(" ");
    }

    /**
     * Task 7
     */
    public void printPopulationWorld(Country world)
    {
        if (world != null)
        {
            System.out.println("\nTask: 7, Details retrieved for World as follows: \n\n"
                    + " Population of the World"
                    + "\n=========================\n"
                    + "World Population: " + world.population + "\n");
        }
    }

    /**
     * Task 8
     */
    public void printRegionCapitalCities(ArrayList<City> cities)
    {
        // Print header
        System.out.println("\nTask: 8, Details retrieved for region capital cities as follows: \n");
        System.out.println(String.format("%-12s %-18s %-20s", " City", " Country", " Population"));
        System.out.println(String.format("%-12s %-18s %-20s", "======", "=========", "============"));

        for (City city : cities)
        {
            String language_string =
                    String.format("%-12s %-18s %-20d",
                            city.name, city.country, city.population);
            System.out.println(language_string);
        }
        System.out.println(" ");
    }

    /**
     * Task 9
     */
    public void printContinentCapitalCities(ArrayList<City> cities)
    {
        // Print header
        System.out.println("\nTask: 9, Details retrieved for continent capital cities as follows: \n");
        System.out.println(String.format("%-15s %-38s %-20s", " City", " Country", " Population"));
        System.out.println(String.format("%-15s %-38s %-20s", "======", "=========", "============"));

        for (City city : cities)
        {
            String language_string =
                    String.format("%-15s %-38s %-20d",
                            city.name, city.country, city.population);
            System.out.println(language_string);
        }
        System.out.println(" ");
    }


    /**
     * Task 10
     */
    public void printWorldCapitalCities(ArrayList<City> cities)
    {
        // Print header
        System.out.println("\nTask: 10, Details retrieved for world capital cities as follows: \n");
        System.out.println(String.format("%-25s %-38s %-20s", " City", " Country", " Population"));
        System.out.println(String.format("%-25s %-38s %-20s", "======", "=========", "============"));
        // Loop over all employees in the list
        for (City city : cities)
        {
            String language_string =
                    String.format("%-25s %-38s %-20d",
                            city.name, city.country, city.population);
            System.out.println(language_string);
        }
        System.out.println(" ");
    }

    /**
     * Task 11
     */
    public void printDistrictCities(ArrayList<City> cities)
    {
        // Print header
        System.out.println("\nTask: 11, Details retrieved for cities in a district as follows: \n");
        System.out.println(String.format("%-15s %-25s %-20s", " City", " District", "Population"));
        System.out.println(String.format("%-15s %-25s %-20s", "=====", "=========", "============"));
        // Loop over all cities in the list
        for(City city : cities)
        {
            String language_string =
                    String.format("%-15s %-25s %-20s" ,
                            city.name, city.district, city.population);
            System.out.println(language_string);
        }
        System.out.println(" ");
    }

    /**
     * Task 12
     */
    public void printCountryCities(ArrayList<City> cities)
    {
        // Print header
        System.out.println("\nTask: 12, Details retrieved for cities in a country as follows: \n");
        System.out.println(String.format("%-25s %-15s %-20s %-20s", " City", " Country", " District", " Population"));
        System.out.println(String.format("%-25s %-15s %-20s %-20s", "======", "=========", "===========", "============"));
        // Loop over all employees in the list
        for (City city : cities)
        {
            String language_string =
                    String.format("%-25s %-15s %-20s %-20d",
                            city.name, city.country, city.district, city.population);
            System.out.println(language_string);
        }
        System.out.println(" ");
    }


    /**
     *  Task 13
     */
    public void printRegionCities(ArrayList<City> cities)
    {
        // Print header
        System.out.println("\nTask 13, Details retrieved for cities in a region as follows: \n");
        System.out.println(String.format("%-20s %-25s %-20s", " City", "Region", "Population"));
        System.out.println(String.format("%-20s %-25s %-20s", "=====", "=========", "============"));
        // Loop over all cities in the list
        for(City city : cities)
        {
            String language_string =
                    String.format("%-20s %-25s %-20s" , city.name, city.region, city.population);
            System.out.println(language_string);
        }
        System.out.println(" ");
    }

    /**
     * Task 14
     */
    public void printContinentCities(ArrayList<City> cities)
    {
        // Print header
        System.out.println("\nTask: 14, Details retrieved for cities in a continent as follows: \n");
        System.out.println(String.format("%-15s %-38s %-38s %-20s", " City", " Country", " Continent", " Population"));
        System.out.println(String.format("%-15s %-38s %-38s %-20s", "======", "=========", "===========", "============"));
        // Loop over all employees in the list
        for (City city : cities)
        {
            String language_string =
                    String.format("%-15s %-38s %-38s %-20d",
                            city.name, city.country, city.continent, city.population);
            System.out.println(language_string);
        }
        System.out.println(" ");
    }

    /**
     *  Task 15
     */
    public void printWorldCities(ArrayList<City> cities)
    {
        // Print Header
        System.out.println("\nTask: 15, Details retrieved for all cities in the world as follows: \n");
        System.out.println(String.format("%-25s %-38s %-20s", " City", " Country", " Population"));
        System.out.println(String.format("%-25s %-38s %-20s", "======", "=========", "===========", "============"));
        // Loop over all employees in the list
        for (City city : cities)
        {
            String language_string =
                    String.format("%-25s %-38s %-20d",
                            city.name, city.country, city.population);
            System.out.println(language_string);
        }
        System.out.println(" ");
    }


    /**
     * Task 16
     */
    public void printRegionCountries(ArrayList<Country> countries)
    {
        // Print header
        System.out.println("\nTask: 16, Details retrieved for all countries in a region as follows: \n");
        System.out.println(String.format("%-12s %-25s %-25s %-25s %-25s %-25s", " Code", " Country", " Continent", " Region", " Capital", " Population"));
        System.out.println(String.format("%-12s %-25s %-25s %-25s %-25s %-25s", "======", "=========", "===========", "========", "=========", "============"));
        // Loop over all employees in the list
        for (Country country : countries)
        {
            String language_string =
                    String.format("%-12s %-25s %-25s %-25s %-25s %-25s",
                            country.code, country.name, country.continent, country.region, country.city, country.population);
            System.out.println(language_string);
        }
        System.out.println(" ");
    }

    /**
     *  Task 17
     */
    public void printContinentCountries(ArrayList<Country> countries)
    {
        // Print header
        System.out.println("\nTask: 17, Details retrieved for countries in a continent as follows: \n");
        System.out.println(String.format("%-12s %-25s %-25s %-25s %-25s %-25s", " Code", " Country", " Continent", " Region", " Capital", " Population"));
        System.out.println(String.format("%-12s %-25s %-25s %-25s %-25s %-25s", "======", "=========", "===========", "========", "=========", "============"));
        // Loop over all employees in the list
        for (Country country : countries)
        {
            String language_string =
                    String.format("%-12s %-25s %-25s %-25s %-25s %-25s",
                            country.code, country.name, country.continent, country.region, country.city, country.population);
            System.out.println(language_string);
        }
        System.out.println(" ");
    }

    /**
     * Task 18
     */
    public void printWorldCountries(ArrayList<Country> countries)
    {
        // Print header
        System.out.println("\nTask: 18, Details retrieved for wall countries in the world as follows: \n");
        System.out.println(String.format("%-12s %-38s %-25s %-25s %-25s %-25s", " Code", " Country", " Continent", " Region", " Capital", " Population"));
        System.out.println(String.format("%-12s %-38s %-25s %-25s %-25s %-25s", "======", "=========", "===========", "========", "=========", "============"));
        // Loop over all employees in the list
        for (Country country : countries)
        {
            String language_string =
                    String.format("%-12s %-38s %-25s %-25s %-25s %-25s",
                            country.code, country.name, country.continent, country.region, country.city, country.population);
            System.out.println(language_string);
        }
        System.out.println(" ");
    }

    /**
     *  Task 19
     */
    public void printRuralUrbanCountries(ArrayList<Country> countries)
    {
        // Print header
        System.out.println("\nTask: 19, Details retrieved for Urban/Rural population in each country: \n");
        System.out.println(String.format("%-12s %-25s %-25s %-25s %-25s %-25s", " Code", " Country", " Continent", " Region", " Population", " Capital"));
        System.out.println(String.format("%-12s %-25s %-25s %-25s %-25s %-25s", "======", "=========", "===========", "========", "============", "========"));
        // Loop over all employees in the list
        for (Country country : countries)
        {
            String language_string =
                    String.format("%-12s %-25s %-25s %-25s %-25s %-25s",
                            country.code, country.name, country.continent, country.region, country.population, country.city);
            System.out.println(language_string);
        }
        System.out.println(" ");
    }

    /**
     *  Task 20
     */
    public void printRuralUrbanRegion(ArrayList<Country> countries)
    {
        // Print header
        System.out.println("\nTask: 20, Details retrieved for Rural/Urban population in a region: \n");
        System.out.println(String.format("%-12s %-25s %-25s %-25s %-25s %-25s", " Code", " Country", " Continent", " Region", " Population", " Capital"));
        System.out.println(String.format("%-12s %-25s %-25s %-25s %-25s %-25s", "======", "=========", "===========", "========", "============","========"));
        // Loop over all employees in the list
        for (Country country : countries)
        {
            String language_string =
                    String.format("%-12s %-25s %-25s %-25s %-25s %-25s",
                            country.code, country.name, country.continent, country.region, country.population, country.city);
            System.out.println(language_string);
        }
        System.out.println(" ");

    }

    /**
     *  Task 21
     */
    public void printRuralUrbanContinent(ArrayList<Country> countries)
    {
        // Print header
        System.out.println("\nTask: 21, Details retrieved for Rural/Urban population in a continent: \n");
        System.out.println(String.format("%-12s %-25s %-25s %-25s %-25s %-25s", " Code", " Country", " Continent", " Region", " Population", " Capital"));
        System.out.println(String.format("%-12s %-25s %-25s %-25s %-25s %-25s", "======", "=========", "===========", "========", "============",  "========"));
        // Loop over all employees in the list
        for (Country country : countries)
        {
            String language_string =
                    String.format("%-12s %-25s %-25s %-25s %-25d %-25s",
                            country.code, country.name, country.continent, country.region, country.population, country.city);
            System.out.println(language_string);
        }
        System.out.println(" ");

    }

    /**
     * Task 22
     */
    public void printRegionCapitalCitiesListWithN(ArrayList<City> cities)
    {
        // Print header
        System.out.println("\nTask: 22, Details retrieved for the top N capital cities in a region as follows: \n");
        System.out.println(String.format("%-22s %-25s %-20s", " City", " Country", " Population"));
        System.out.println(String.format("%-22s %-25s %-20s", "======", "=========", "============"));
        // Loop over all employees in the list
        for (City city : cities)
        {
            String language_string =
                    String.format("%-22s %-25s %-20d",
                            city.name, city.country, city.population);
            System.out.println(language_string);
        }
        System.out.println(" ");
    }

    /**
     * Task 23
     */
    public void printContinentCapitalCitiesListWithN(ArrayList<City> cities)
    {
        // Print header
        System.out.println("\nTask: 23, Details retrieved for the top N capital cities in a continent as follows: \n");
        System.out.println(String.format("%-22s %-25s %-20s", " City", " Country", " Population"));
        System.out.println(String.format("%-22s %-25s %-20s", "======", "=========", "============"));
        // Loop over all employees in the list
        for (City city : cities)
        {
            String language_string =
                    String.format("%-22s %-25s %-20d",
                            city.name, city.country, city.population);
            System.out.println(language_string);
        }
        System.out.println(" ");
    }
    /**
     * Task 24
     */
    public void printworldCapitalCitiesListWithN(ArrayList<City> cities)
    {
        // Print header
        System.out.println("\nTask: 24, Details retrieved for the top N capital cities in the world as follows: \n");
        System.out.println(String.format("%-25s %-38s %-20s", " City", " Country", " Population"));
        System.out.println(String.format("%-25s %-38s %-20s", "======", "=========", "============"));
        // Loop over all employees in the list
        for (City city : cities)
        {
            String language_string =
                    String.format("%-25s %-38s %-20d",
                            city.name, city.country, city.population);
            System.out.println(language_string);
        }
        System.out.println(" ");
    }

    /**
     * Task 25
     */
    public void printDistrictCitiesListWithN(ArrayList<City> cities)
    {
        // Print header
        System.out.println("\nTask: 25, Details retrieved for the top N capital cities in a district as follows: \n");
        System.out.println(String.format("%-22s %-25s %-20s", " City", " Country", " Population"));
        System.out.println(String.format("%-22s %-25s %-20s", "======", "=========", "============"));
        // Loop over all employees in the list
        for (City city : cities)
        {
            String language_string =
                    String.format("%-22s %-25s %-20d",
                            city.name, city.country, city.population);
            System.out.println(language_string);
        }
        System.out.println(" ");
    }
    /**
     * Task 26
     */
    public void printCountryCitiesListWithN(ArrayList<City> cities)
    {
        // Print header
        System.out.println("\nTask: 26, Details retrieved for the top N capital cities in a country as follows: \n");
        System.out.println(String.format("%-22s %-25s %-20s", " City", " Country", " Population"));
        System.out.println(String.format("%-22s %-25s %-20s", "======", "=========", "============"));
        // Loop over all employees in the list
        for (City city : cities)
        {
            String language_string =
                    String.format("%-22s %-25s %-20d",
                            city.name, city.country, city.population);
            System.out.println(language_string);
        }
        System.out.println(" ");
    }
    /**
     * Task 27
     */
    public void printRegionCitiesListWithN(ArrayList<City> cities)
    {
        // Print header
        System.out.println("\nTask: 27, Details retrieved for the top N cities in a region as follows: \n");
        System.out.println(String.format("%-22s %-25s %-20s", " City", " Country", " Population"));
        System.out.println(String.format("%-22s %-25s %-20s", "======", "=========", "============"));
        // Loop over all employees in the list
        for (City city : cities)
        {
            String language_string =
                    String.format("%-22s %-25s %-20d",
                            city.name, city.country, city.population);
            System.out.println(language_string);
        }
        System.out.println(" ");
    }
    /**
     * Task 28
     */
    public void printCountinentCitiesListWithN(ArrayList<City> cities)
    {
        // Print header
        System.out.println("\nTask: 28, Details retrieved for the top N cities in a continent as follows: \n");
        System.out.println(String.format("%-22s %-25s %-20s", " City", " Country", " Population"));
        System.out.println(String.format("%-22s %-25s %-20s", "======", "=========", "============"));
        // Loop over all employees in the list
        for (City city : cities)
        {
            String language_string =
                    String.format("%-22s %-25s %-20d",
                            city.name, city.country, city.population);
            System.out.println(language_string);
        }
        System.out.println(" ");
    }

    /**
     * Task 29
     */
    public void printWorldCitiesListWithN(ArrayList<City> cities)
    {
        // Print header
        System.out.println("\nTask: 29, Details retrieved for the top N cities in the world as follows: \n");
        System.out.println(String.format("%-22s %-25s %-20s", " City", " Country", " Population"));
        System.out.println(String.format("%-22s %-25s %-20s", "======", "=========", "============"));
        // Loop over all employees in the list
        for (City city : cities)
        {
            String language_string =
                    String.format("%-22s %-25s %-20d",
                            city.name, city.country, city.population);
            System.out.println(language_string);
        }
        System.out.println(" ");
    }
    /**
     * Task 30
     */
    public void printRegionCountriesWithLimit(ArrayList<Country> countries)
    {
        // Print header
        System.out.println("\nTask: 30, Details retrieved for the top N countries in a region as follows: \n");
        System.out.println(String.format("%-12s %-25s %-25s %-25s %-25s", " Code", " Country", " Continent", " Region", " Population"));
        System.out.println(String.format("%-12s %-25s %-25s %-25s %-25s", "======", "=========", "===========", "========", "============"));
        // Loop over all employees in the list
        for (Country country : countries)
        {
            String language_string =
                    String.format("%-12s %-25s %-25s %-25s %-25s",
                            country.code, country.name, country.continent, country.region, country.population);
            System.out.println(language_string);
        }
        System.out.println(" ");
    }

    /**
     * Task 31
     */
    public void printContinentCountriesWithLimit(ArrayList<Country> countries)
    {
        // Print header
        System.out.println("\nTask: 31, Details retrieved for the top N countries in a continent as follows: \n");
        System.out.println(String.format("%-12s %-25s %-25s %-25s %-25s", " Code", " Country", " Continent", " Region", " Population"));
        System.out.println(String.format("%-12s %-25s %-25s %-25s %-25s", "======", "=========", "===========", "========", "============"));
        // Loop over all employees in the list
        for (Country country : countries)
        {
            String language_string =
                    String.format("%-12s %-25s %-25s %-25s %-25s",
                            country.code, country.name, country.continent, country.region, country.population);
            System.out.println(language_string);
        }
        System.out.println(" ");
    }
    /**
     * Task 32
     */
    public void printWorldCountriesWithLimit(ArrayList<Country> countries)
    {
        // Print header
        System.out.println("\nTask: 32, Details retrieved for the top N countries in the world as follows: \n");
        System.out.println(String.format("%-12s %-25s %-25s %-25s %-25s", " Code", " Country", " Continent", " Region", " Population"));
        System.out.println(String.format("%-12s %-25s %-25s %-25s %-25s", "======", "=========", "===========", "========", "============"));
        // Loop over all employees in the list
        for (Country country : countries)
        {
            String language_string =
                    String.format("%-12s %-25s %-25s %-25s %-25s",
                            country.code, country.name, country.continent, country.region, country.population);
            System.out.println(language_string);
        }
        System.out.println(" ");
    }

    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();

        // Connect to database
        if (args.length < 1)
        {
            a.connect("localhost:33060");
        }
        else
        {
            a.connect(args[0]);
        }

        //Run the URL app compatibility
        SpringApplication.run(App.class, args);

        // Get details //
        /*Task 1 */City edinburgh = a.getCityEdinburghPop("Edinburgh");
        /*Task 2 */City scotland = a.getDistrictScotlandPop("Scotland");
        /*Task 3 */Country uK = a.getCountryUKPop("United Kingdom");
        /*Task 4 */Country britIsles = a.getRegionBritIslesPop("British Islands");
        /*Task 5 */Country europe = a.getContinentEuropePop("Europe");
        /*Task 6 Get List of Details */ArrayList<Language> languages = a.getLanguageList();
        /*Task 7 */Country world = a.getWorldPop();
        /*Task 8 Get List of Details */ArrayList<City> regionCapCities = a.getRegionCapitalCityList("British Islands");
        /*Task 9 Get List of Details */ArrayList<City> continentCapCities = a.getContinentCapitalCityList("Africa");
        /*Task 10 Get List of Details */ArrayList<City> worldCapCities = a.getWorldCapitalCityList();
        /*Task 11 Get List of Details */ArrayList<City> districtCities = a.getDistrictCityList("Scotland");
        /*Task 12 Get List of Details */ArrayList<City> countryCities = a.getCountryCityList("Germany");
        /*Task 13 Get List of Details */ArrayList<City> regionCities = a.getRegionCityList("Western Europe");
        /*Task 14 Get List of Details */ArrayList<City> continentCities = a.getContinentCityList("Africa");
        /*Task 15 Get List of Details */ArrayList<City> worldCities = a.getWorldCityPopList();
        /*Task 16 Get List of Details */ArrayList<Country> regionCountries = a.getRegionCountryList("Caribbean");
        /*Task 17 Get List of Details */ArrayList<Country> countriesContinent = a.getContinentCountryPop("Asia");
        /*Task 18 Get List of Details */ArrayList<Country> worldCountries = a.getWorldCountryList();
        /*Task 19 Get List of Details */ArrayList<Country> countryRuralUrban = a.getPopulationFromRuralUrbanCountry("Caribbean");
        /*Task 20 Get List of Details */ArrayList<Country> regionRuralUrban = a.getPopulationFromRuralUrbanRegion("Central Africa");
        /*Task 21 Get List of Details */ArrayList<Country> continentRuralUrban = a.getPopulationFromRuralUrbanContinent("Europe");
        /*Task 23 Get List of Details */ArrayList<City> regionCapCitiesWithLimit = a.getRegionCapitalCityListWithLimit("Caribbean", "10");
        /*Task 23 Get List of Details */ArrayList<City> continentCapCitiesWithLimit = a.getContinentCapitalCityListWithLimit("Oceania", "10");
        /*Task 24 Get List of Details */ArrayList<City> worldCapitalCitiesWithLimit = a.getworldCapitalCityListWithLimit("15");
        /*Task 25 Get List of Details */ArrayList<City> districtCitiesWithLimit = a.getDistrictCityListWithLimit("Scotland","10");
        /*Task 26 Get List of Details */ArrayList<City> countryCitiesWithLimit = a.getCountryCityListWithLimit("Germany","5" );
        /*Task 27 Get List of Details */ArrayList<City> regionCitiesWithLimit = a.getregionCityListWithLimit("British Islands", "4");
        /*Task 28 Get List of Details */ArrayList<City> continentCitiesWithLimit = a.getContinentsCityListWithLimit2("Europe","2");
        /*Task 29 Get List of Details */ArrayList<City> worldCitiesWithLimit = a.getworldCityListWithLimit("10");
        /*Task 30 Get List of Details */ArrayList<Country> RegionCountriesWithLimit = a.getRegionCountryListWithLimit("Middle East", "5");
        /*Task 31 Get List of Details */ArrayList<Country> ContientCountriesWithLimit = a.getContinentCountryListWithLimit("Europe","3");
        /*Task 32 Get List of Details */ArrayList<Country> worldCountriesWithLimit = a.getWorldCountryListWithLimit("10");
        // Print Details //
        /*Task 1 */a.printPopulationEdinburgh(edinburgh);
        /*Task 2 */a.printPopulationScotland(scotland);
        /*Task 3 */a.printPopulationUK(uK);
        /*Task 4 */a.printPopulationBritIsles(britIsles);
        /*Task 5 */a.printPopulationEurope(europe);
        /*Task 6 Print list of details */a.printLanguages(languages);
        /*Task 7 */a.printPopulationWorld(world);
        /*Task 8 Print list of details */a.printRegionCapitalCities(regionCapCities);
        /*Task 9 Print list of details */a.printContinentCapitalCities(continentCapCities);
        /*Task 10 Get List of Details */a.printWorldCapitalCities(worldCapCities);
        /*Task 11 Print list of Details */a.printDistrictCities(districtCities);
        /*Task 12 Get List of Details */a.printCountryCities(countryCities);
        /*Task 13 Print list of Details */a.printRegionCities(regionCities);
        /*Task 14 Get List of Details */a.printContinentCities(continentCities);
        /*Task 15 Print list of Details */a.printWorldCities(worldCities);
        /*Task 16 Get List of Details */a.printRegionCountries(regionCountries);
        /*Task 17 Print list of Details */a.printContinentCountries(countriesContinent);
        /*Task 18 Get List of Details */a.printWorldCountries(worldCountries);
        /*Task 19 Print list of Details */a.printRuralUrbanCountries(countryRuralUrban);
        /*Task 20 Print list of Details */a.printRuralUrbanRegion(regionRuralUrban);
        /*Task 21 Print list of Details */a.printRuralUrbanContinent(continentRuralUrban);
        /*Task 23 Print list of details */a.printRegionCapitalCitiesListWithN(regionCapCitiesWithLimit);
        /*Task 23 Print list of details */a.printContinentCapitalCitiesListWithN(continentCapCitiesWithLimit);
        /*Task 24 Get List of Details */ a.printworldCapitalCitiesListWithN(worldCapitalCitiesWithLimit);
        /*Task 25 Get List of Details */ a.printDistrictCitiesListWithN(districtCitiesWithLimit);
        /*Task 26 Get List of Details */ a.printCountryCitiesListWithN(countryCitiesWithLimit);
        /*Task 27 Get List of Details */ a.printRegionCitiesListWithN(regionCitiesWithLimit);
        /*Task 28 Get List of Details */ a.printCountinentCitiesListWithN(continentCitiesWithLimit);
        /*Task 29 Get List of Details */ a.printWorldCitiesListWithN(worldCitiesWithLimit);
        /*Task 30 Get List of Details */ a.printRegionCountriesWithLimit(RegionCountriesWithLimit);
        /*Task 31 Get List of Details */ a.printContinentCountriesWithLimit(ContientCountriesWithLimit);
        /*Task 32 Get List of Details */ a.printWorldCountriesWithLimit(worldCountriesWithLimit);
        // Disconnect from database
        //a.disconnect();
    }
}
