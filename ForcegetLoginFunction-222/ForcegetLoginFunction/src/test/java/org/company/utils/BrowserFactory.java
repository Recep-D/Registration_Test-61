package org.company.utils;


import com.microsoft.playwright.*;

import java.nio.file.Paths;

public class BrowserFactory {

    public static Playwright playwright;

    public static BrowserType browserType;

    public static Browser browser;

    public static BrowserContext browserContext;


    public static Page initBrowser(String browserName, boolean headless) {

        playwright = Playwright.create();

        switch (browserName.toLowerCase()) {
            case "chromium":
                browserType = playwright.chromium();
                break;

            case "firafox":
                browserType = playwright.firefox();
                break;

            case "webkit":
                browserType = playwright.webkit();
                break;

            default:
                throw new RuntimeException("ınvalid browser name");

        }
        browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(headless));
        browserContext = browser.newContext(new Browser.NewContextOptions().
                setRecordVideoDir(Paths.get("src/test/resources/videos")));

        Page page = browserContext.newPage();
        return page;
    }


    public static void closeItems() {
        if (browserContext != null) {
            browserContext.close();
        }
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}
