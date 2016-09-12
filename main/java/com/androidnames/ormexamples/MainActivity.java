package com.androidnames.ormexamples;

//import android.database.SQLException;

import java.sql.SQLException;
import java.util.List;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testORM();
    }

    public void testORM() {

        try {

            //create DAO object
            CarOpenHelper carOpenHelper = OpenHelperManager.getHelper(this,
                    CarOpenHelper.class);
            Dao<Car, Long> carDao = carOpenHelper.getDao();

    /*
    ** create
     */
            Car car = new Car("Sonata", "Huyndai");
            carDao.create(car);


    /*
    ** read
     */
            Car car2 = carDao.queryForId((long) 1);
            List<Car> allCars = carDao.queryForAll();
            List<Car> carsHyundai = carDao.queryForEq("brand", "Huyndai");

    /*
    ** update
     */

            int updated = carDao.update(car2);
            //or
            UpdateBuilder<Car, Long> updateBuilder = carDao.updateBuilder();
            updateBuilder.where().eq("id", 1).and().eq("brand", "Huyndai");
            updateBuilder.updateColumnValue("name", "Sonata 2016");
            updateBuilder.update();


    /*
    ** delete
     */
            carDao.delete(car);
            //or
            DeleteBuilder<Car, Long> deleteBuilder = carDao.deleteBuilder();
            deleteBuilder.where().eq("name", "Sonata 2016");
            deleteBuilder.delete();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
