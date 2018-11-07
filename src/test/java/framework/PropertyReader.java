package framework;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertyReader {
    private static FileInputStream fileInputStream;
    private static Properties property;
    private static Logger log = Logger.getLogger(PropertyReader.class.getName());

    static {
        try {
            String canonPath = CommonFunctions.getCanonicalPathToResource();
            fileInputStream = new FileInputStream(new StringBuilder().append(canonPath).append("config.properties").toString());
            property = new Properties();
            property.load(fileInputStream);
        } catch (Exception ex) {
            log.log(Level.SEVERE, "Error with canonical path!", ex);
        } finally {
            if (fileInputStream != null)
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    log.log(Level.SEVERE, "Error with reading property!", e);
                }
        }
    }
    public static String getTestProperty(String key) {
        return property.getProperty(key);
    }
}
