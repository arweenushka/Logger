package adidas.alive;

import adidas.alive.pages.CalendarPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.BrowsersFactory;

public class Logger {

    WebDriver webDriver;
    CalendarPage calendarPage;

    private String url = "https://internal-portal.adidas-group.com/irj/portal/NewOnlineTime?";

    //time for mondays-thursdays
    private String startTime = "0900";
    private String endTime = "1830";
    private String startBreak = "1300";
    private String endBreak = "1400";
    //time for fridays
    private String startTimeF = "0800";
    private String endTimeF = "1530";
    private String startBreakF = "1200";
    private String endBreakF = "1230";

    @BeforeTest
    public void setUp() {
        webDriver = BrowsersFactory.startBrowser(url);
        calendarPage = new CalendarPage(webDriver);
    }

    @Test
    public void logTime() throws InterruptedException {
        calendarPage.switchToMonthView();

        //fill fridays
        calendarPage.fillTimePerShortDay(startTimeF, endTimeF, startBreakF, endBreakF);

        //fill all mondays-thursdays
        //if it is summer schedule, then fill whole week  with short days
        if (calendarPage.getSelectedMonthText().equalsIgnoreCase("jul")
                || calendarPage.getSelectedMonthText().equalsIgnoreCase("ago")) {
            calendarPage.fillTimePerFullDay(startTimeF, endTimeF, startBreakF, endBreakF);
        } else {
            calendarPage.fillTimePerFullDay(startTime, endTime, startBreak, endBreak);
        }

    }

    @AfterTest
    public void tearDown() {
        if (webDriver != null)
            webDriver.quit();
    }
}
