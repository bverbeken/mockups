package models.mockups;

import org.junit.Test;
import play.test.UnitTest;

public class MockupTest extends UnitTest {

    @Test(expected = IllegalArgumentException.class)
    public void constructorWithNameThrowsExceptionIfUnexistingFile() {
        new Mockup("thisFileDoesNotExist.html");
    }

    @Test
    public void getName() {
        Mockup mockup = new Mockup("existingMockup.html");
        assertEquals("existingMockup.html", mockup.getName());
    }
}
