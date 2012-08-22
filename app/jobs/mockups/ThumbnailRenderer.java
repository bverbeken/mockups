package jobs.mockups;

import play.exceptions.UnexpectedException;
import play.jobs.Job;

import java.io.*;
import java.util.UUID;

public class ThumbnailRenderer extends Job<File> {

    private final String url;

    public ThumbnailRenderer(String url) {
        this.url = url;
    }

    @Override
    public File doJobWithResult() {
        try {
            String phantomJsExecutable = "/usr/lib/phantomjs-1.6.1-linux-x86_64-dynamic/bin/phantomjs";
            String rasterizeScript = "/usr/lib/phantomjs-1.6.1-linux-x86_64-dynamic/examples/rasterize.js";
            String targetFilename = UUID.randomUUID() + ".png";
            String currentWorkingDir = "/home/bver";
            String[] command = new String[]{phantomJsExecutable, rasterizeScript, url, targetFilename};
            Process process = new ProcessBuilder(command).directory(new File(currentWorkingDir)).start();

            redirectOutputToStdOut(process);

            int result = process.waitFor();
            if (result != 0) {
                throw new RuntimeException("phantomjs exit code: " + result);
            }

            return new File(currentWorkingDir, targetFilename);
        } catch (Exception e) {
            play.Logger.error("Failed to generate thumbnail for url " + url, e);
            throw new UnexpectedException(e);
        }
    }

    private static void redirectOutputToStdOut(Process process) throws IOException {
        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
    }
}
