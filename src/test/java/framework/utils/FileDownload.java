package framework.utils;

import framework.CommonFunctions;
import framework.PropertyReader;

import java.io.File;

public class FileDownload {

    public static boolean waitUntilFileToDownload() {

        String extension = CommonFunctions.getExtension(PropertyReader.getTestProperty("browser").toLowerCase());
        String tempExtension = ".tmp";
        String path = new StringBuilder().append(CommonFunctions.getCanonicalPathToResource()).append("downloads").toString();

        File directory = new File(path);
        boolean isDownload = false;
        while (!isDownload) {
            File[] filesList = directory.listFiles();
            for (File file : filesList) {
                if (!(file.getName().contains(extension)||file.getName().contains(tempExtension))){
                    isDownload = true;
                }
            }
        }
        return isDownload;
    }

}
