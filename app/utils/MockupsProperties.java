package utils;

import play.Play;

import static org.apache.commons.lang.StringUtils.isAlphanumeric;

public class MockupsProperties {

    public static final String MOCKUPS_PATH = "mockups.path";

    public static String getMockupPath() {
        String path = Play.configuration.getProperty(MOCKUPS_PATH);
        if (path == null) {
            return "/mockups/";
        } else {
            path = addSlashesIfNeeded(path);
            if (isAlphanumeric(removeSlashes(path))) {
                return path;
            } else {
                throw new IllegalStateException("Path should be alphanumeric; please review your mockups.path config property (current value: " + path + ")");
            }
        }
    }

    private static String removeSlashes(String path) {
        return path.substring(1, path.length() - 1);
    }

    private static String addSlashesIfNeeded(String path) {
        if (!path.startsWith("/"))
            path = "/" + path;
        if (!path.endsWith("/"))
            path = path + "/";
        return path;
    }
}
