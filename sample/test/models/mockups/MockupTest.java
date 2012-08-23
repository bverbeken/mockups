package models.mockups;

import com.google.common.collect.Lists;
import org.junit.Test;
import play.test.UnitTest;

import java.util.Collections;
import java.util.List;

import static models.mockups.Mockup.mockupByPath;

public class MockupTest extends UnitTest {

    @Test
    public void getFileNameInRoot() {
        Mockup mockup = mockupByPath("/mockups/mockup1.html");
        assertEquals("mockup1.html", mockup.getFileName());
    }

    @Test
    public void getFileNameInSubDir() {
        Mockup mockup = mockupByPath("/mockups/dir1/mockup12.html");
        assertEquals("mockup12.html", mockup.getFileName());
    }

    @Test
    public void getPathInRoot() {
        Mockup mockup = mockupByPath("/mockups/mockup1.html");
        assertEquals("/mockups/mockup1.html", mockup.getPath());
    }

    @Test
    public void getPathInSubdir() {
        Mockup mockup = mockupByPath("/mockups/dir1/mockup11.html");
        assertEquals("/mockups/dir1/mockup11.html", mockup.getPath());
    }

    @Test
    public void ordering() {

        Mockup mockup1 = mockupByPath("/mockups/mockup1.html");
        Mockup mockup2 = mockupByPath("/mockups/mockup2.html");
        Mockup dir1 = mockupByPath("/mockups/dir1");
        Mockup dir2 = mockupByPath("/mockups/dir2");

        List<Mockup> mockupList = Lists.newArrayList(mockup2, dir1, dir2, mockup1);
        Collections.sort(mockupList);

        assertEquals(dir1, mockupList.get(0));
        assertEquals(dir2, mockupList.get(1));
        assertEquals(mockup1, mockupList.get(2));
        assertEquals(mockup2, mockupList.get(3));

    }
}
