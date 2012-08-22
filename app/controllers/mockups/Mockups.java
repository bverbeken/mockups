package controllers.mockups;

import jobs.mockups.ThumbnailRenderer;
import models.mockups.Mockup;
import org.apache.commons.codec.binary.Base64OutputStream;
import play.mvc.Controller;
import play.mvc.results.NotFound;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

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
        BufferedImage img = ImageIO.read(thumbnail);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        OutputStream b64 = new Base64OutputStream(os);
        ImageIO.write(img, "png", b64);
        renderText("data:image/png;base64," + os.toString("UTF-8"));
    }

}
