package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {
    private static final String PATH = System.getProperty("user.home") + System.getProperty("file.separator");
    private File dest = new File(PATH + "output.txt");

    public File getCurrFile() {
        return this.dest;
    }

    public void setCurrFile(final File f) {
        File parent = f.getParentFile(); //Storing the directory containing f in "parent" (dirs are files!)
        if (parent.exists()) {
            this.dest = f;
        } else {
            throw new IllegalArgumentException("Error: " + parent + "Is not an existing directory");
        }
    }

    public void setPath(final String path) {
        setCurrFile(new File(path));
    }

    public void setFileContent(final String in) throws IOException {
        try(PrintStream ps = new PrintStream(dest, StandardCharsets.UTF_8)) {
            ps.print(in);
        }
    }

}
