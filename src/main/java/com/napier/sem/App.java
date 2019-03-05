package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

public class App
{
    /**
     * Connection to MySQL database.
     */
    private Connection con = null;



    /**
     * Connect to the MySQL database.
     */
    public void connect(String location)
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
    public void disconnect()
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


    /**
     * Task 1
     */
    public City getCityEdinburghPop(String city, String code)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT city.Name, country.Name, city.District, city.Population " +
                               "FROM world.city, world.country " +
                               "WHERE city.CountryCode = country.Code " +
                               "AND city.Name = " + city + " " +
                               "AND country.Code = " + code;
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
                edinburgh.population = rset.getInt("Population");
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
    public City getDistrictScotlandPop(String district, String code)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT country.Name, city.District, SUM(city.Population) " +
                    "FROM world.city, world.country " +
                    "WHERE city.CountryCode = country.Code " +
                    "AND city.District = " + district + " " +
                    "AND country.Code = " + code;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next())
            {
                City scotland = new City();
                scotland.country = rset.getString("country.Name");
                scotland.district = rset.getString("city.District");
                scotland.population = rset.getInt("SUM(city.Population)");
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
    public Country getCountryUKPop(String country)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT Code, Name, Continent, Region, Capital, Population " +
                    "FROM world.country " +
                    "WHERE Name = " + country;
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
                uK.population = rset.getInt("Population");
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
    public Country getRegionBritIslesPop(String region)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT Region, Continent, SUM(Population) " +
                    "FROM world.country " +
                    "WHERE Region = " + region + " " +
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
                britIsles.population = rset.getInt("SUM(Population)");
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
    public Country getContinentEuropePop(String continent)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT Continent, SUM(Population) " +
                    "FROM world.country " +
                    "WHERE Continent = " + continent;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next())
            {
                Country europe = new Country();
                europe.continent = rset.getString("Continent");
                europe.population = rset.getInt("SUM(Population)");
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
     * Task 7
     */
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


//    /**
//     * Gets all the current employees and salaries.
//     * @return A list of all employees and salaries, or null if there is an error.
//     */
//    public ArrayList<Employee> getAllSalaries()
//    {
//        try
//        {
//            // Create an SQL statement
//            Statement stmt = con.createStatement();
//            // Create string for SQL statement
//            String strSelect =
//                    "SELECT employees.emp_no, employees.first_name, employees.last_name, salaries.salary "
//                            + "FROM employees, salaries "
//                            + "WHERE employees.emp_no = salaries.emp_no AND salaries.to_date = '9999-01-01' "
//                            + "ORDER BY employees.emp_no ASC";
//            // Execute SQL statement
//            ResultSet rset = stmt.executeQuery(strSelect);
//            // Extract employee information
//            ArrayList<Employee> employees = new ArrayList<Employee>();
//            while (rset.next())
//            {
//                Employee emp = new Employee();
//                emp.emp_no = rset.getInt("employees.emp_no");
//                emp.first_name = rset.getString("employees.first_name");
//                emp.last_name = rset.getString("employees.last_name");
//                emp.salary = rset.getInt("salaries.salary");
//                employees.add(emp);
//            }
//            return employees;
//        }
//        catch (Exception e)
//        {
//            System.out.println(e.getMessage());
//            System.out.println("Failed to get salary details");
//            return null;
//        }
//    }
//
//
//
//    /**
//     * Prints a list of employees.
//     * @param employees The list of employees to print.
//     */
//    public void printSalaries(ArrayList<Employee> employees)
//    {
//        // Print header
//        System.out.println(String.format("%-10s %-15s %-20s %-8s", "Emp No", "First Name", "Last Name", "Salary"));
//        // Loop over all employees in the list
//        for (Employee emp : employees)
//        {
//            String emp_string =
//                    String.format("%-10s %-15s %-20s %-8s",
//                            emp.emp_no, emp.first_name, emp.last_name, emp.salary);
//            System.out.println(emp_string);
//        }
//    }



    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect("db:3306");

        // Get details //
        /*Task1*/City edinburgh = a.getCityEdinburghPop("'Edinburgh'", "'GBR'");
        /*Task2*/City scotland = a.getDistrictScotlandPop("'Scotland'", "'GBR'");
        /*Task3*/Country uK = a.getCountryUKPop("'United Kingdom'");
        /*Task4*/Country britIsles = a.getRegionBritIslesPop("'British Islands'");
        /*Task5*/Country europe = a.getContinentEuropePop("'Europe'");
        /*Task7*/Country world = a.getWorldPop();

        // Print Details //
        /*Task1*/a.printPopulationEdinburgh(edinburgh);
        /*Task2*/a.printPopulationScotland(scotland);
        /*Task3*/a.printPopulationUK(uK);
        /*Task4*/a.printPopulationBritIsles(britIsles);
        /*Task5*/a.printPopulationEurope(europe);
        /*Task7*/a.printPopulationWorld(world);

        // Extract employee salary information
        //ArrayList<Employee> employees = a.getAllSalaries();
        // Test the size of the returned data - should be 240124
        //System.out.println(employees.size());

        //a.printSalaries(employees);

        // Disconnect from database
        a.disconnect();
    }
}
