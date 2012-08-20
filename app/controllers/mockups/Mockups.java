package controllers.mockups;

import models.mockups.Mockup;
import play.mvc.Controller;
import play.mvc.results.RenderTemplate;
import play.templates.Template;
import play.templates.TemplateLoader;
import play.vfs.VirtualFile;

import java.util.HashMap;
import java.util.List;

import static models.mockups.Mockup.allMockups;
import static play.Play.getVirtualFile;
import static utils.MockupsProperties.getMockupPath;

@SuppressWarnings("UnusedDeclaration")
public class Mockups extends Controller {

    public static void list() {
        List<Mockup> mockups = allMockups();
        render(mockups);
    }

    public static void show(String mockupName) {
        Template template = new Mockup(mockupName).getTemplate();
        HashMap<String, Object> args = new HashMap<String, Object>();
        throw new RenderTemplate(template, args);
    }

}
