package utils;

import play.Play;

public class MockupsProperties {

    public static final String MOCKUPS_PATH = "mockups.path";

    public static String getMockupPath() {
        String path = Play.configuration.getProperty(MOCKUPS_PATH);
        if (path == null) {
            return "/mockups/";
        } else {
            if (path.contains(" "))
                throw new IllegalStateException("Path shouldn't contain spaces; please review your mockups.path config property");
            if (!path.startsWith("/"))
                path = "/" + path;
            if (!path.endsWith("/"))
                path = path + "/";
            return path;
        }
    }
}
