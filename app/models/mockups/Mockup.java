package models.mockups;

import play.vfs.VirtualFile;

import java.util.List;

import static play.Play.getVirtualFile;
import static utils.MockupsProperties.getMockupPath;

public class Mockup {

    public static List<VirtualFile> findAllMockups() {
        return getVirtualFile(getMockupPath()).list();
    }
}
