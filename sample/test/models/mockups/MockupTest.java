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

    @Test
    public void getPath(){
        Mockup mockup = new Mockup("existingMockup.html");
        assertEquals("existingMockup.html", mockup.getPath());
    }

    @Test
    public void getPath_subDir(){
        Mockup mockup = new Mockup("subdir/mockup1.html");
        assertEquals("subdir/mockup1.html", mockup.getPath());
    }
}
