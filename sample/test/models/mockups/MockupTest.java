package models.mockups;

import com.google.common.collect.Lists;
import org.junit.Test;
import play.test.UnitTest;

import java.util.Collections;
import java.util.List;

import static models.mockups.Mockup.mockupByName;

public class MockupTest extends UnitTest {

    @Test
    public void getName() {
        Mockup mockup = mockupByName("mockup1.html");
        assertEquals("mockup1.html", mockup.getName());
    }

    @Test
    public void getPath() {
        Mockup mockup = mockupByName("mockup1.html");
        assertEquals("mockup1.html", mockup.getPath());
    }

    @Test
    public void getPath_subDir() {
        Mockup mockup = mockupByName("dir1/mockup11.html");
        assertEquals("dir1/mockup11.html", mockup.getPath());
    }

    @Test
    public void ordering() {

        Mockup mockup1 = mockupByName("mockup1.html");
        Mockup mockup2 = mockupByName("mockup2.html");
        Mockup dir1 = mockupByName("dir1");
        Mockup dir2 = mockupByName("dir2");

        List<Mockup> mockupList = Lists.newArrayList(mockup2, dir1, dir2, mockup1);
        Collections.sort(mockupList);

        assertEquals(dir1, mockupList.get(0));
        assertEquals(dir2, mockupList.get(1));
        assertEquals(mockup1, mockupList.get(2));
        assertEquals(mockup2, mockupList.get(3));

    }
}
