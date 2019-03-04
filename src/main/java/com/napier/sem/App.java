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



    public City getCityEdinburgh(String name, String code)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT city.Name, country.Name, city.District, city.Population " +
                               "FROM world.city, world.country " +
                               "WHERE city.CountryCode = country.Code " +
                               "AND city.Name = " + name + " " +
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

    public City getDistrictScotland(String district, String code)
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



    public void printPopulationEdinburgh(City edinburgh)
    {
        if (edinburgh != null)
        {
            System.out.println("\nDetails retrieved for city Edinburgh as follows: \n\n"
                + " Population of a City"
                + "\n======================\n"
                + "Name: " + edinburgh.name + "\n"
                + "Country: " + edinburgh.country + "\n"
                + "District: " + edinburgh.district + "\n"
                + "Population: " + edinburgh.population + "\n");
        }
    }

    public void printPopulationScotland(City scotland)
    {
        if (scotland != null)
        {
            System.out.println("\nDetails retrieved for district Scotland as follows: \n\n"
                    + " Population of a District"
                    + "\n==========================\n"
                    + "District: " + scotland.district + "\n"
                    + "Country: " + scotland.country + "\n"
                    + "Population: " + scotland.population + "\n");
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

        // Get City
        City edinburgh = a.getCityEdinburgh("'Edinburgh'", "'GBR'");

        City scotland = a.getDistrictScotland("'Scotland'", "'GBR'");
        // Display results
        a.printPopulationEdinburgh(edinburgh);
        a.printPopulationScotland(scotland);

        // Extract employee salary information
        //ArrayList<Employee> employees = a.getAllSalaries();
        // Test the size of the returned data - should be 240124
        //System.out.println(employees.size());

        //a.printSalaries(employees);

        // Disconnect from database
        a.disconnect();
    }
}
