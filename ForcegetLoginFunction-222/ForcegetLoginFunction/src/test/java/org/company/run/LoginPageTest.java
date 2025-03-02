package org.company.run;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.company.utils.BrowserFactory;
import org.company.utils.ConfigurationReader;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class LoginPageTest {
    public static BrowserFactory browserFactory;
    public static Page page;

    //test
    @BeforeAll
    public static void generalSetUp() {
        String browserFromConfig = ConfigurationReader.getProperty("browserName");
        boolean isHeadless = Boolean.parseBoolean(ConfigurationReader.getProperty("headless"));

        browserFactory = new BrowserFactory();
        page = BrowserFactory.initBrowser(browserFromConfig, isHeadless);
        page.navigate(ConfigurationReader.getProperty("url"));
    }

    @AfterAll
    public static void generalCloseItems() {
        BrowserFactory.closeItems();
    }


    @Test
    public void testHomePageTitle() {

        page.locator("#firstName").fill("Laz");
        page.waitForTimeout(3000);
        page.locator("#lastName").fill("Lazoğlu");

        page.locator("forceget-country-dropdown nz-select").click();
        page.waitForTimeout(10000);

        Locator dropdown = page.locator("forceget-country-dropdown nz-select");
        dropdown.click();
        page.waitForTimeout(2000);

        Locator dropdownMenu = page.locator("cdk-virtual-scroll-viewport");
        dropdownMenu.waitFor(new Locator.WaitForOptions().setTimeout(5000));

        dropdownMenu.evaluate("el => el.scrollTop = 6500");
        page.waitForTimeout(1000);


        Locator turkeyOption = page.locator("//nz-option-item[@title='+90']//span[text()='Turkey']");
        turkeyOption.waitFor(new Locator.WaitForOptions().setTimeout(6000));

        turkeyOption.scrollIntoViewIfNeeded();
        turkeyOption.hover();
        turkeyOption.click();

        page.locator("#phoneNumber").fill("5538544521");
        page.locator("#companyName").fill("Startup");
        page.locator("input[placeholder='E-mail']").fill("laz@gmail.com");

        page.locator("nz-select[nzplaceholder='Choose...']").click();
        page.waitForSelector("[title='CEO']");
        page.locator("[title='CEO']").click();

        page.locator("input[placeholder='Password']").fill("Merhaba123!");
        page.locator("input[placeholder='Confirm password']").fill("Merhaba123!");
        page.locator("span[class='checkbox-box']").click();

        // page.locator("button[class='ant-btn w-full ant-btn-primary']").click();

        page.waitForTimeout(5000);


    }
}
