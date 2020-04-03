package app.viswanath.filter;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private RecyclerView mRecyclerView;
    private List<DataModel> dataModelList = new ArrayList<>();
    private DataAdapter dataAdapter;
    private SearchView searchView;
    private SearchManager searchManager;
    private String[] name = {
            "daniel", "james", "emily", "jack", "conor", "amelia", "adam", "liam", "noah", "emma", "mia", "anna", "olivia", "cillian", "hannah", "oliver", "sophie", "aoife", "harry", "charlie", "grace", "ava", "finn"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recyclerView);
        dataAdapter = new DataAdapter(this, populateData());
        mRecyclerView.setAdapter(dataAdapter);
    }

    private List<DataModel> populateData() {
        for (String s : name) {
            DataModel dataModel = new DataModel();
            dataModel.setName(s);
            dataModelList.add(dataModel);
        }
        return dataModelList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.ic_menu_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String newText) {
        if (!TextUtils.isEmpty(newText)) {
            dataAdapter.getFilter().filter(newText);
        } else
            Toast.makeText(getApplicationContext(), "Please enter name", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (!TextUtils.isEmpty(newText)) {
            dataAdapter.getFilter().filter(newText);
        } else
            Toast.makeText(getApplicationContext(), "Please enter name", Toast.LENGTH_SHORT).show();
        return true;
    }
}
