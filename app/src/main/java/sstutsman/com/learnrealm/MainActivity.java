package sstutsman.com.learnrealm;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import sstutsman.com.learnrealm.model.Animal;


public class MainActivity extends Activity {

    private TextView tvTransact;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTransact = (TextView) findViewById(R.id.tv_transact);

        realm = Realm.getInstance(this);

        tvTransact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Populate my database with dummy data
                firstRealmMethod();
                secondRealmMethod();

                firstQueryMethod();
                secondQueryMethod();

                Toast.makeText(MainActivity.this, "Check your LogCat!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void firstRealmMethod() {
        realm.beginTransaction();

        // Realm creates this Animal -- Anything you do to the instance after it's been committed won't save!
        Animal snake = realm.createObject(Animal.class);
        snake.setCommonName("Ball Python");
        snake.setGenus("Python");
        snake.setSpecies("Regius");
        snake.setIsTerrestrial(true);
        realm.commitTransaction();
    }

    private void secondRealmMethod() {
        Animal[] animals = new Animal[4];
        animals[0] = new Animal("House cat", "Felis", "catus", true);
        animals[1] = new Animal("Axolotl", "Ambystoma", "mexicanum", false);
        animals[2] = new Animal("Great white shark", "Carcharonodon", "carcharias", false);
        animals[3] = new Animal("Burmese python", "Python", "bivittatus", true);

        // ...
        // Make any other computations you need to
        // ...

        realm.beginTransaction();
        for (int i = 0; i < animals.length; i++) {
            // This would also return a new Animal instance -- with the same state of the Animal I passed into it!
            realm.copyToRealm(animals[i]);
        }
        realm.commitTransaction();
    }

    private void firstQueryMethod() {
        // We create a query object
        RealmQuery<Animal> query = realm.where(Animal.class);

        // From my database, I'm only looking for objects whose genus is Python
        query.equalTo("genus", "Python");

        // When I'm satisfied with my query, I'll invoke findAll() for the results
        RealmResults<Animal> results = query.findAll();

        // For this tutorial, I'm only outputting the results into LogCat.. You have to do other things!
        Log.d("demo", "Here are the results of the first query");
        for (int i = 0; i < results.size(); i++) {
            Log.d("demo", "---- Result #" + i + " ----------------------------------------------");
            Log.v("demo", "Common name : " + results.get(i).getCommonName());
            Log.v("demo", "Genus : " + results.get(i).getGenus());
            Log.v("demo", "Species : " + results.get(i).getSpecies());
            Log.v("demo", "Is Terrestrial : " + results.get(i).isTerrestrial());
        }
    }

    private void secondQueryMethod() {
        // With this method, we can do all of firstQueryMethod() but in one daisy-chained statement
        RealmResults<Animal> results = realm.where(Animal.class)
                .equalTo("isTerrestrial", false)
                .or()
                .equalTo("genus", "constrictor")
                .findAll();

        Log.d("demo", "Here are the results of the second query");
        for (int i = 0; i < results.size(); i++) {
            Log.d("demo", "---- Result #" + i + " ----------------------------------------------");
            Log.v("demo", "Common name : " + results.get(i).getCommonName());
            Log.v("demo", "Genus : " + results.get(i).getGenus());
            Log.v("demo", "Species : " + results.get(i).getSpecies());
            Log.v("demo", "Is Terrestrial : " + results.get(i).isTerrestrial());
        }
    }


}
