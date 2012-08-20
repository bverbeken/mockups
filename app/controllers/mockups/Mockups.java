package controllers.mockups;

import models.mockups.Mockup;
import play.Logger;
import play.mvc.Controller;
import play.mvc.results.RenderTemplate;
import play.templates.Template;

import java.util.HashMap;
import java.util.List;

import static models.mockups.Mockup.allMockups;

@SuppressWarnings("UnusedDeclaration")
public class Mockups extends Controller {

    public static void list() {
        List<Mockup> mockups = allMockups();
        render(mockups);
    }

    public static void show(String mockupName) {
        Logger.warn("mockupName: " + mockupName);
        Mockup mockup = new Mockup(mockupName);
        if (mockup.isDirectory()) {
            List<Mockup> mockups = allMockups(mockupName);
            render("mockups/Mockups/list.html", mockups);
        } else {
            Template template = mockup.getTemplate();
            HashMap<String, Object> args = new HashMap<String, Object>();
            throw new RenderTemplate(template, args);
        }
    }


}
