package com.androidnames.ormexamples;


import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;


public class OrmLiteConfigureUtils extends OrmLiteConfigUtil {


    //models to use for creating ormlite_config.txt file
    private static final Class<?>[] classes = new Class[]{Car.class};


    public static void main(String[] args) throws IOException, SQLException {


        //create full path to ormlite_config.txt file
        String pathToConfig = System.getProperty("user.dir") + "/app/src/main/res/raw/ormlite_config.txt";

        File configFile = new File(pathToConfig);

        //we delete and update with new file for every new run
        if (configFile.exists()) {
            configFile.delete();
            configFile = new File(pathToConfig);
        }

        //writeConfigFile writes all necessary info to the configuration file
        writeConfigFile(configFile, classes);
    }
}
