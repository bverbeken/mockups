package controllers.mockups;

import jobs.mockups.ThumbnailRenderer;
import models.mockups.Mockup;
import org.apache.commons.codec.binary.Base64OutputStream;
import play.mvc.Controller;
import play.mvc.results.NotFound;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static javax.imageio.ImageIO.read;
import static javax.imageio.ImageIO.write;
import static models.mockups.Mockup.allMockups;
import static models.mockups.Mockup.mockupByPath;
import static org.apache.commons.lang.StringUtils.isBlank;
import static utils.MockupsProperties.isPhantomJsEnabled;

@SuppressWarnings("UnusedDeclaration")
public class Mockups extends Controller {

    public static void show(String m, boolean showRibbon) {
        boolean useThumbnails = isPhantomJsEnabled();
        if (isBlank(m)) {
            List<Mockup> mockups = allMockups();
            render(mockups, useThumbnails);
        } else {
            Mockup mockup = mockupByPath(m);
            if (mockup == null) {
                throw new NotFound("Mockup [" + m + "] not found");
            } else if (mockup.isDirectory()) {
                List<Mockup> mockups = allMockups(m);
                render(mockups, useThumbnails);
            } else {
                throw new RenderMockup(mockup, showRibbon);
            }
        }
    }

    public static void thumbnail(String m) throws IOException {
        File thumbnail = await(new ThumbnailRenderer(mockupByPath(m).getPath()).now());
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        write(read(thumbnail), "png", new Base64OutputStream(os));
        renderText("data:image/png;base64," + os.toString("UTF-8"));
    }

}
