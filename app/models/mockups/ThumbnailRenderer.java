package models.mockups;


import java.io.File;
import java.io.IOException;

public class ThumbnailRenderer {



    public static void main(String[] args) throws IOException, InterruptedException {
        String phantomJsExecutable = "/usr/lib/phantomjs-1.6.1-linux-x86_64-dynamic/bin/phantomjs";
        String rasterizeScript = "/usr/lib/phantomjs-1.6.1-linux-x86_64-dynamic/examples/rasterize.js";
        String url = "http://localhost:9000/";
        String targetFilename = "blabla.png";
        String currentWorkingDir = "/home/bver";
        String[] command= new String[]{phantomJsExecutable, rasterizeScript, url, targetFilename};
        Process process = new ProcessBuilder(command).directory(new File(currentWorkingDir)).start();
        if (process.waitFor() != 0){
            throw new RuntimeException("Failed to generate thumbnail");
        }
    }

}
