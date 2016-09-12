package com.androidnames.ormexamples;

import android.content.Context;
import java.sql.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;


public class CarOpenHelper extends OrmLiteSqliteOpenHelper{
    private static final String DATABASE_NAME = "cars_db";
    private static final int DATABASE_VERSION = 1;

    private Dao<Car, Long> carDao;

    public CarOpenHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }
    //@Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {

           //creates the Car table
            TableUtils.createTable(connectionSource, Car.class);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //@Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            //delete then create new database when there is new schema update
            TableUtils.dropTable(connectionSource, Car.class, false);
            onCreate(database, connectionSource);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<Car, Long> getDao() throws SQLException {
        if(carDao == null) {
            carDao = getDao(Car.class);
        }
        return carDao;
    }
}
