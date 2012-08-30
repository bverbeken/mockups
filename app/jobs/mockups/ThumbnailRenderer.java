package jobs.mockups;

import play.Play;
import play.exceptions.UnexpectedException;
import play.jobs.Job;
import utils.MockupsProperties;

import java.io.File;

import static java.io.File.createTempFile;

public class ThumbnailRenderer extends Job<File> {

    private final String mockupPath;

    public ThumbnailRenderer(String mockupPath) {
        this.mockupPath = mockupPath;
    }

    @Override
    public File doJobWithResult() {
        String url = absoluteUrl();
        try {
            String phantomJsExecutable = MockupsProperties.getPhantomJsPath();
            String rasterizeScript = Play.getVirtualFile("/public/mockups/js/rasterize.js").getRealFile().getAbsolutePath();
            File tmpFile = createTempFile("mockup", ".png");
            new ProcessBuilder(phantomJsExecutable, rasterizeScript, url, tmpFile.getAbsolutePath()).start().waitFor();
            return tmpFile;
        } catch (Exception e) {
            play.Logger.error("Failed to generate thumbnail for url " + url, e);
            throw new UnexpectedException(e);
        }
    }

    private String absoluteUrl() {
        return "http://localhost:9000/@mockups?m=" + mockupPath + "&showRibbon=false";
    }

}
