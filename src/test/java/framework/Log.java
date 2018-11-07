package framework;

import org.apache.log4j.Logger;

public class Log {

    private static Logger Log = Logger.getLogger(org.apache.commons.logging.Log.class.getName());

    public static void startLog (String testClassName){
        info("----------------------------------------------------------------------------------------------------");
        info(String.format("=============================  Starting test '%s' ============================", testClassName));
        info("----------------------------------------------------------------------------------------------------");
    }

    public static void step(final int step) {
        info(String.format("-------------------------------------------- [ Step %s ] --------------------------------------------", step));
    }

    public static void info(final String messege){
        Log.info(messege);
    }

    public void fatal(String messege){
        Log.fatal(String.format(">>>>>>>>>>>>>> %s <<<<<<<<<<<<<<<<", messege));
    }

    public static void precondStart(String className){
        info("****************************************************************************************************");
        info(String.format("----------------------------- Preconditions for %s start-----------------------------", className));
    }

    public static void precondComplete(){
        info("---------------------------------------- Preconditions complete -----------------------------------");
    }

}
