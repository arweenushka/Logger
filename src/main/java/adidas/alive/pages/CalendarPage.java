package adidas.alive.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CalendarPage {

    private static final long EXPLICIT_WAIT = 100;
    private WebDriver webDriver;

    @FindBy(id = "__button4")
    WebElement monthBtn;

    @FindBy(id = "ivuFrm_page0ivu1")
    WebElement frame;

    @FindBy(xpath = "//span[contains(@aria-label,'icon-alert')]")
    List<WebElement> untrackedDaysInCalendar;

    @FindBy(xpath = "//div[contains(@id,'hbox')]//div[5]/div/div[2]/span[contains(@aria-label,'icon-alert')]")
    List<WebElement> untrackedFridaysInCalendar;

    @FindBy(id = "__picker0-inner")
    WebElement startTimeInput;

    @FindBy(id = "__picker1-inner")
    WebElement endTimeInput;

    @FindBy(xpath = "//input[contains(@placeholder,'SET START BREAK')]")
    WebElement startBreakInput;

    @FindBy(xpath = "//input[contains(@placeholder,'SET END BREAK')]")
    WebElement endBreakInput;

    @FindBy(id = "__button2")
    WebElement saveBtn;

    public CalendarPage(WebDriver driver) {
        webDriver = driver;
        PageFactory.initElements(webDriver, this);
    }

    public void switchToMonthView() throws InterruptedException {
        webDriver.switchTo().frame(frame);
        WebDriverWait wait = new WebDriverWait(webDriver, EXPLICIT_WAIT);
        wait.until(ExpectedConditions.elementToBeClickable(monthBtn)).click();

    }

    public void fillTimePerShortDay(String startTime, String endTime, String startBreak, String endBreak) throws InterruptedException {
        for (WebElement day : untrackedFridaysInCalendar) {

            Thread.sleep(7000);
            //wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("spinner")));

            //open the day in calendar
            WebDriverWait wait = new WebDriverWait(webDriver, EXPLICIT_WAIT);
            wait.until(ExpectedConditions.elementToBeClickable(day)).click();

            Thread.sleep(5000);
            //wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("spinner")));

            //fill all time inputs
            fillTimeInputs(startTime, endTime, startBreak, endBreak);
            //saveTime();
        }
    }

    public void fillTimePerFullDay(String startTime, String endTime, String startBreak, String endBreak) throws InterruptedException {
        for (WebElement day : untrackedDaysInCalendar) {

            Thread.sleep(7000);
            //open the day in calendar
            WebDriverWait wait = new WebDriverWait(webDriver, EXPLICIT_WAIT);
            wait.until(ExpectedConditions.elementToBeClickable(day)).click();

            Thread.sleep(5000);
            //fill all time inputs
            fillTimeInputs(startTime, endTime, startBreak, endBreak);
            //saveTime();
        }
    }

    private void fillTimeInputs(String startTime, String endTime, String startBreak, String endBreak) {
        startTimeInput.click();
        startTimeInput.sendKeys(startTime);
        endTimeInput.click();
        endTimeInput.sendKeys(endTime);
        startBreakInput.click();
        startBreakInput.sendKeys(startBreak);
        endBreakInput.click();
        endBreakInput.sendKeys(endBreak);
    }

    private void saveTime() {
        saveBtn.click();
    }
}
