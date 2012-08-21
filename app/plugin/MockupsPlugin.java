package plugin;

import play.Play;
import play.PlayPlugin;

import static play.mvc.Router.addRoute;

public class MockupsPlugin extends PlayPlugin {

    @Override
    public void onRoutesLoaded() {
        if (Play.mode.isDev()) {
            addRoute("GET", "/@mockups/?", "mockups.Mockups.show");
            addRoute("GET", "/@mockupThumbnail/?", "mockups.Mockups.thumbnail");
        }
    }

}
