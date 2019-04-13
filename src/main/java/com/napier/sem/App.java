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
     * Connect to the MySQL database.
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
                Thread.sleep(30000);
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
     * @param code code of the country the city is in.
     * @return The record of the city and its population.
     */
    @RequestMapping("citypop")
    public City getCityEdinburghPop(@RequestParam(value = "city") String city, @RequestParam(value = "code") String code)
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
                               "AND country.Code = '" + code + "'";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next())
            {
                City edinburgh = new City();
                edinburgh.name = rset.getString("Name");
                edinburgh.country = rset.getString("country.Name");
                edinburgh.district = rset.getString("District");
                edinburgh.population = rset.getLong("Population");
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
     * @param code code of the country the district is in.
     * @return The record of the district and its population.
     */
    @RequestMapping("districtpop")
    public City getDistrictScotlandPop(@RequestParam(value = "district") String district, @RequestParam(value = "code") String code)
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
                    "AND country.Code = '" + code + "'";
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
            String strSelect = "SELECT Code, Name, Continent, Region, Capital, Population " +
                    "FROM world.country " +
                    "WHERE Name = '" + country + "'";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next())
            {
                Country uK = new Country();
                uK.code = rset.getString("Code");
                uK.name = rset.getString("Name");
                uK.continent = rset.getString("Continent");
                uK.region = rset.getString("Region");
                uK.population = rset.getLong("Population");
                uK.capital = rset.getInt("Capital");
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
                              "SELECT city.Name, city.Population "
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
     * Get a list of all capital cities in a region and their population from largest to smallest.
     * @param continent name of the continent to get.
     * @return The record of the capital cities in a continent and their population.
     */
    @RequestMapping("continentcitypop")
    public ArrayList<City> getContinentCapitalCityList (@RequestParam(value = "continent") String continent)
    {
        try {
            // Create SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, city.Population "
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
                    + "Capital: " + uK.capital + "\n"
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
        System.out.println(String.format("%-12s %-20s", " City", " Population"));
        System.out.println(String.format("%-12s %-20s", "======", "============"));
        // Loop over all employees in the list
        for (City city : cities)
        {
            String language_string =
                    String.format("%-12s %-20d",
                            city.name, city.population);
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

        SpringApplication.run(App.class, args);

        // Get details //
        /*Task 1 */City edinburgh = a.getCityEdinburghPop("Edinburgh", "GBR");
        /*Task 2 */City scotland = a.getDistrictScotlandPop("Scotland", "GBR");
        /*Task 3 */Country uK = a.getCountryUKPop("United Kingdom");
        /*Task 4 */Country britIsles = a.getRegionBritIslesPop("British Islands");
        /*Task 5 */Country europe = a.getContinentEuropePop("Europe");
        /*Task 6 Get List of Details */ArrayList<Language> languages = a.getLanguageList();
        /*Task 7 */Country world = a.getWorldPop();
        /*Task 8 Get List of Details */ArrayList<City> cities = a.getRegionCapitalCityList("British Islands");

        // Print Details //
        /*Task 1 */a.printPopulationEdinburgh(edinburgh);
        /*Task 2 */a.printPopulationScotland(scotland);
        /*Task 3 */a.printPopulationUK(uK);
        /*Task 4 */a.printPopulationBritIsles(britIsles);
        /*Task 5 */a.printPopulationEurope(europe);
        /*Task 6 Print list of details */a.printLanguages(languages);
        /*Task 7 */a.printPopulationWorld(world);
        /*Task 8 Print list of details */a.printRegionCapitalCities(cities);

        // Disconnect from database
        //a.disconnect();
    }
}
