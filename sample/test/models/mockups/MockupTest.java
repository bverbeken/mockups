package models.mockups;

import org.junit.Test;
import play.test.UnitTest;

import static models.mockups.Mockup.mockupByName;

public class MockupTest extends UnitTest {

    @Test
    public void getName() {
        Mockup mockup = mockupByName("existingMockup.html");
        assertEquals("existingMockup.html", mockup.getName());
    }

    @Test
    public void getPath(){
        Mockup mockup = mockupByName("existingMockup.html");
        assertEquals("existingMockup.html", mockup.getPath());
    }

    @Test
    public void getPath_subDir(){
        Mockup mockup = mockupByName("subdir/mockup1.html");
        assertEquals("subdir/mockup1.html", mockup.getPath());
    }
}
