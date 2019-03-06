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

    @Test
    void testGetCity()
    {
        City city = app.getCityEdinburghPop("Edinburgh", "GBR");
        assertEquals(city.name, "Edinburgh");
        assertEquals(city.country, "United Kingdom");
        assertEquals(city.district, "Scotland");
        assertEquals(city.population, 450180);
    }
}
