package controllers.mockups;

import jobs.mockups.ThumbnailRenderer;
import models.mockups.Mockup;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.results.NotFound;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static javax.imageio.ImageIO.read;
import static models.mockups.Mockup.allMockups;
import static models.mockups.Mockup.mockupByName;
import static org.apache.commons.lang.StringUtils.isBlank;

@SuppressWarnings("UnusedDeclaration")
public class Mockups extends Controller {

    public static void show(String m) {
        if (isBlank(m)) {
            List<Mockup> mockups = allMockups();
            render(mockups);
        } else {
            Mockup mockup = mockupByName(m);
            if (mockup == null) {
                throw new NotFound("Mockup [" + m + "] not found");
            } else if (mockup.isDirectory()) {
                List<Mockup> mockups = allMockups(m);
                render(mockups);
            } else {
                throw new RenderMockup(mockup);
            }
        }
    }

    public static void thumbnail(String m) throws IOException {
        File thumbnail = await(new ThumbnailRenderer(mockupByName(m).getFullPath()).now());
        Http.Response.current().contentType = "image/png";
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(read(thumbnail), "png", byteArrayOutputStream);
        renderBinary(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
    }

}
