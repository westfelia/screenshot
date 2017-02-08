/**
 * Created by ryan on 2017-02-08.
 */

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Screenshotter {

    public static void main(final String[] args) throws Exception {
        String link = "http://www.facebook.com";
        Screenshotter screenshotter = new Screenshotter();
        screenshotter.grabScreenshot(link);
        screenshotter.close();
    }

    private WebDriver driver;
    public String imageDirectory;

    //
    // Class invariant: driver and imageDirectory are both valid and non-null
    //

    /**
     * Creates a new instance of Screenshotter, opening a browser to take the screenshots in.
     */
    public Screenshotter() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        imageDirectory = "images/";
    }

    /**
     * Takes a screenshot of the webpage at the given url and saves it
     *
     * @param url A valid url to be screenshotted.
     * @return The path to the saved screenshot
     * @throws Exception If there's an error in the file system
     */
    public String grabScreenshot(String url) throws Exception {
        String fileName = imageDirectory + convertLinkToFileName(url, "png");
        File destination = new File(fileName).getAbsoluteFile();

        driver.get(url);

        // TimeUnit.SECONDS.sleep(2);

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, destination);

        return destination.getAbsolutePath();
    }

    /**
     * Closes the screenshotter. Be sure to call this when finished
     */
    public void close() {
        driver.close();
    }

    /**
     * Takes a link and returns a string representing that link to be used as a file name.
     *
     * @param link     A url to be converted to a file name e.g. www.google.com
     * @param fileType The type of file to convert to e.g. txt. *Do not include '.' before the file name, as in '.txt'
     * @return A String representing the link to be used as a file name
     */
    public static String convertLinkToFileName(String link, String fileType) {
        // Current format for saving: replace characters used in file systems with a '_'
        // and then append the date in parentheses

        // Sanitize
        String fileName = link.replace("://", "_");
        fileName = fileName.replaceAll("[^a-zA-Z0-9-]", "_");

        // Append date
        Date today = Calendar.getInstance().getTime();
        String dateString = today.toInstant().toString();
        dateString = dateString.replaceAll("[^a-zA-Z0-9-]", "_");
        fileName = fileName + "(" + dateString + ")." + fileType;
        System.out.println(fileName);
        return fileName;
    }

}
