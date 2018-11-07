package framework;

import org.openqa.selenium.By;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonFunctions {

    private static Logger log = Logger.getLogger(CommonFunctions.class.getName());

    public static String regexpGetGroup(String text, String regexp, int numberOfGroup) {
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(text);
        if(matcher.find()){
            return matcher.group(numberOfGroup);
        }
        return null;
    }

    public static List<Integer> stringListtoInt(List<String> listString){
        List<Integer> listInteger = new ArrayList<>();
        for (String string : listString){
            listInteger.add(Integer.parseInt(string));
        }
        return listInteger;
    }

    public static String getCanonicalPathToResource(){
        try{
            String resource = BrowserFactory.class.getClassLoader().getResource("").getPath();
            String canonPath = (new File(resource).getCanonicalPath());
            return new StringBuilder().append(canonPath).append(FileSystems.getDefault().getSeparator()).toString();
        } catch (IOException ex){
            log.log(Level.SEVERE, "Error with canonical path!", ex);
        }
        return null;
    }

    public static String getExtension(String browser){
        if (browser.toLowerCase().contains("firefox")) return ".part";
        else if (browser.toLowerCase().contains("chrome")) return ".crdownload";
        else return null;
    }

    public static int getRandomNumber(int maximum){
        Random rnd = new Random(System.currentTimeMillis());
        return rnd.nextInt(maximum);
    }

    public static int getRandomNumberFromInterval(int min, int max){
        Random rnd = new Random(System.currentTimeMillis());
        return rnd.ints(min, max).findFirst().getAsInt();
    }
}