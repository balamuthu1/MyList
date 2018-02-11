package geronimo.test.com.mylist.views;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import geronimo.test.com.mylist.R;
import geronimo.test.com.mylist.adapters.MyMovieAdapter;
import geronimo.test.com.mylist.models.MyMovie;

public class MainActivity extends AppCompatActivity {
    MyMovieAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    Parcelable layoutManagerSavedState;
    RecyclerView recyclerView;
    List<MyMovie> myMovies = new ArrayList<>();

    private String SAVED_LAYOUT_MANAGER = "SAVED_LAYOUT_MANAGER";
    private MyMovie myMovie;
    boolean firstTimeClicked = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.main_recycler);
        adapter = new MyMovieAdapter(this);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        //Mck the first set of data
        MyMovie myMovie1 = new MyMovie(R.drawable.one,"The king Ragnar","Vikings");
        MyMovie myMovie2 = new MyMovie(R.drawable.two,"The king of the north","Game of thrones");
        MyMovie myMovie3 = new MyMovie(R.drawable.three,"The Star","Star Wars");
        myMovies.add(myMovie1);
        myMovies.add(myMovie2);
        myMovies.add(myMovie3);

        //add all data
        adapter.addAll(myMovies);

    }
    private void addItem(){
        firstTimeClicked = true;
        myMovie = new MyMovie(R.drawable.one,"The king Ragnar","Vikings");
        adapter.add(myMovie);
        recyclerView.smoothScrollToPosition(adapter.getMovies().size()-1);
    }

    private void removeItem(){

        if(firstTimeClicked && linearLayoutManager.findFirstVisibleItemPosition() != 0){
            recyclerView.smoothScrollToPosition(0);
            firstTimeClicked = false;
        }


        if(adapter.getMovies().size() != 0){
            recyclerView.post(new Runnable() {
                @Override
                public void run() {
                    adapter.remove(adapter.getMovies().get(0));
                }
            });

        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //ajoute les entrées de menu_test à l'ActionBar
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_save:
                addItem();
                return true;
            case R.id.action_delete:
                removeItem();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Store recyclerview states
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(SAVED_LAYOUT_MANAGER, recyclerView.getLayoutManager().onSaveInstanceState());
    }

    //Restore recyclerview states
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState instanceof Bundle) {
            layoutManagerSavedState = ((Bundle) savedInstanceState).getParcelable(SAVED_LAYOUT_MANAGER);
        }
        super.onRestoreInstanceState(savedInstanceState);
    }
}
