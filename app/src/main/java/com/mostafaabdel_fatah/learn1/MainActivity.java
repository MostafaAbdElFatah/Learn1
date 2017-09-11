package com.mostafaabdel_fatah.learn1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    Spinner  spinner;
    ListView listView;
    String[] countries;
    String[] languages;
    AutoCompleteTextView autoText;
    ArrayAdapter<String>  arrayAdapter;
    ArrayList<String> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countries = getResources().getStringArray(R.array.country);
        languages = getResources().getStringArray(R.array.languages);
        autoText = (AutoCompleteTextView) findViewById(R.id.autocompletetext);
        ArrayAdapter<String> autotextArrayAdapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_list_item_1,countries);
        autoText.setAdapter(autotextArrayAdapter);
         /***************************************************************************************/
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_spinner_item,languages);
        spinner.setAdapter(spinnerArrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getBaseContext(),languages[i],Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        /****************************************************************************************/
        listView =(ListView) findViewById(R.id.listview);

        for (String item : countries){
            arrayList.add(item);
        }
        arrayAdapter = new ArrayAdapter<String>(this,R.layout.row,R.id.textView,arrayList);
        listView.setAdapter(arrayAdapter);
        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.delete:
                arrayList.remove(menuInfo.position);
                arrayAdapter.notifyDataSetChanged();
                break;
            case R.id.Share:
                Toast.makeText(getBaseContext(),"Share item",Toast.LENGTH_SHORT).show();
                break;
            case R.id.Help:
                Toast.makeText(getBaseContext(),"Help item",Toast.LENGTH_SHORT).show();
                break;
            default:
                return  super.onContextItemSelected(item);
        }
        return  true;
    }
}
