package org.example.bitcubetechnicalassignment;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BitcubeTests extends BitcubeBase
{
    @Test
    void validateStylingForEmptyInput()
    {
        String expectedStyle = "border: 2px solid red";
        Locator calculateButton = page.locator("#getFactorial");
        calculateButton.click();
        String style = page.locator("#number").getAttribute("style");

        System.out.println("Style: " + style);
        assertTrue(style.contains(expectedStyle), "Expected red border, but actual style was: " + style);

    }

    @Test
    void calculateFactorialOf12()
    {
        String expectedNumber = "479001600";
        String factorOfNumber = "12";
        Locator numberInput = page.locator("#number");
        numberInput.fill(factorOfNumber);

        Locator calculateButton = page.locator("#getFactorial");
        calculateButton.click();

        Locator result = page.locator("#resultDiv");
        assertThat(result).containsText("The factorial of ");

        String text = result.textContent().trim();
        String number = text.substring(text.lastIndexOf(":") + 1).trim();

        assertEquals(expectedNumber, number);
    }

}