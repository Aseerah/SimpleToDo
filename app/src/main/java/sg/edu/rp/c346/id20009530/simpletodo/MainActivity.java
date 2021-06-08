package sg.edu.rp.c346.id20009530.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // create variable
    EditText inputTask;
    Spinner spinner;
    ListView listView;
    Button btnAdd;
    Button btnDelete;
    Button btnClear;
    //create arrayList
    ArrayList<String> array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // findbyid
        inputTask = findViewById(R.id.inputTask);
        spinner = findViewById(R.id.spinner);
        listView = findViewById(R.id.listView);
        btnAdd = findViewById(R.id.btnAdd);
        btnDelete = findViewById(R.id.btnDelete);
        btnClear = findViewById(R.id.btnClear);


        array = new ArrayList<String>();

        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, array);
        listView.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        btnAdd.setEnabled(true);
                        btnDelete.setEnabled(false);
                        inputTask.setText("");
                        inputTask.setHint("Type in a new Task");
                        inputTask.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;
                    case 1:
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        inputTask.setText("");
                        inputTask.setHint("Type in the index of the task to be removed");
                        inputTask.setInputType(InputType.TYPE_CLASS_NUMBER);
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputString = inputTask.getText().toString();
                if (inputString.equalsIgnoreCase("")) {
                    Toast.makeText(MainActivity.this, "Enter something", Toast.LENGTH_LONG).show();

                } else {
                    array.add(inputString);
                    adapter.notifyDataSetChanged();
                    inputTask.setText("");

                }

            }
        });


        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (array.size() == 0) { //checks if the array is empty
                    Toast.makeText(MainActivity.this, "No input to be cleared", Toast.LENGTH_LONG).show();

                } else {
                    array.clear();
                    adapter.notifyDataSetChanged();
                    inputTask.setText("");

                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int inputInt = Integer.parseInt(inputTask.getText().toString());
                String inputString = inputTask.getText().toString();

                if (inputInt >= array.size()) {// checking if the index entered is  larger than the array
                    Toast.makeText(MainActivity.this, "index not found", Toast.LENGTH_SHORT).show();

                } else if (inputString.isEmpty() == true) { // check if there isnt any input by user
                    Toast.makeText(MainActivity.this, "Please enter something", Toast.LENGTH_SHORT).show();

                } else if (array.size() == 0) { // check if the isnt anything stored that needs  to be deleted
                    Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                } else {
                    array.remove(inputInt);
                    inputTask.setText("");
                }
                adapter.notifyDataSetChanged();

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (btnDelete.isEnabled()) {
                    inputTask.setText(String.valueOf(position));
                }
            }
        });

    }
}