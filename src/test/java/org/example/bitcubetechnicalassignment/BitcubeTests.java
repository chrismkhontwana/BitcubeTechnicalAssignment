package org.example.bitcubetechnicalassignment;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
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
}