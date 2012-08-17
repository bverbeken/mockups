package controllers.mockups;

import models.mockups.Mockup;
import play.mvc.Controller;
import play.mvc.results.RenderTemplate;
import play.templates.Template;
import play.templates.TemplateLoader;
import play.vfs.VirtualFile;
import utils.MockupsProperties;

import java.util.HashMap;
import java.util.List;

import static utils.MockupsProperties.getMockupPath;

@SuppressWarnings("UnusedDeclaration")
public class Mockups extends Controller {

    public static void list() {
        List<VirtualFile> mockups = Mockup.findAllMockups();
        render(mockups);
    }

    public static void show(String mockup) {
        Template template = TemplateLoader.load(getMockupPath() + mockup + ".html");
        throw new RenderTemplate(template, new HashMap<String, Object>());
    }

}
