import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import play.Play;
import play.test.UnitTest;
import utils.MockupsProperties;

import static utils.MockupsProperties.MOCKUPS_PATH;

public class MockupsPropertiesTest extends UnitTest {

    private static String originalConfig;

    @BeforeClass
    public static void keepCurrentConfigurationAside() {
        originalConfig = Play.configuration.getProperty(MOCKUPS_PATH);
    }

    @AfterClass
    public static void resetConfiguration() {
        if (originalConfig != null) {
            Play.configuration.setProperty(MOCKUPS_PATH, originalConfig);
        } else {
            Play.configuration.remove(MOCKUPS_PATH);
        }
    }

    @Test
    public void ifPathNotConfiguredThenTheDefaultIsReturned() {
        Play.configuration.remove(MOCKUPS_PATH);
        String actual = MockupsProperties.getMockupPath();
        assertEquals("/mockups/", actual);
    }

    @Test
    public void ifPathIsConfiguredWithoutTrailingSlashItIsAdded() {
        Play.configuration.setProperty(MOCKUPS_PATH, "/pathToMockups");
        String actual = MockupsProperties.getMockupPath();
        assertEquals("/pathToMockups/", actual);
    }

    @Test
    public void ifPathIsConfiguredWithoutStartingSlashItIsAdded() {
        Play.configuration.setProperty(MOCKUPS_PATH, "pathToMockups/");
        String actual = MockupsProperties.getMockupPath();
        assertEquals("/pathToMockups/", actual);
    }

    @Test
    public void ifPathIsConfiguredWithStartAndTrailingSlashNothingIsAdded() {
        Play.configuration.setProperty(MOCKUPS_PATH, "/pathToMockups/");
        String actual = MockupsProperties.getMockupPath();
        assertEquals("/pathToMockups/", actual);
    }

    @Test(expected = IllegalStateException.class)
    public void ifPathContainsSpacesAnExceptionIsThrown() {
        Play.configuration.setProperty(MOCKUPS_PATH, "this is not a path");
        MockupsProperties.getMockupPath();
    }


    @Test(expected = IllegalStateException.class)
    public void ifPathIsNotAlphaNumericAnExceptionIsThrown() {
        Play.configuration.setProperty(MOCKUPS_PATH, "a%[]djklsqd}@#");
        MockupsProperties.getMockupPath();
    }

}