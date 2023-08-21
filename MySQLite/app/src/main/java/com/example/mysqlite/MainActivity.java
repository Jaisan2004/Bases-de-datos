package com.example.mysqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.mysqlite.clases.User;
import com.example.mysqlite.model.UserDAO;
import com.example.mysqlite.model.ManagerDB;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String TAG= "Validate";
    private EditText etDocument;
    private EditText etUsuario;
    private EditText etNombres;
    private EditText etApellidos;
    private EditText etContra;
    private ListView listUsers;
    private int documento;
    String usuario;
    String nombres;
    String apellidos;
    String contra;
    int status;

    private ManagerDB managerDB;
    SQLiteDatabase baseDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        managerDB = new ManagerDB(this);
        begin();
        userList();
    }
    private boolean checkFields(){
        documento = Integer.parseInt(etDocument.getText().toString());
        usuario =etUsuario.getText().toString();
        nombres = etNombres.getText().toString();
        apellidos = etApellidos.getText().toString();
        contra = etContra.getText().toString();
        Log.i(TAG, "checkFields: "+documento);
        return true;
    }
    public void registerUser(View view){
        if(checkFields()){
            UserDAO userDAO = new UserDAO(this,view);
            User user = new User(documento,usuario,nombres,apellidos,contra,status);
            userDAO.insertUser(user);
            userList();
        }
    }
    public void callUserList(View view){userList();}
    private void userList() {
        UserDAO userDAO=new UserDAO(this,listUsers);
        ArrayList<User> userArrayList = userDAO.getUserList();
        ArrayAdapter<User> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userArrayList);
        listUsers.setAdapter(adapter);
    }

    public void Buscar(View view) {
        String searchTerm = etDocument.getText().toString().trim();

        if (!searchTerm.isEmpty()) {
            UserDAO userDAO = new UserDAO(this, listUsers);
            ArrayList<User> userArrayList = userDAO.Buscar(searchTerm);
            ArrayAdapter<User> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userArrayList);
            listUsers.setAdapter(adapter);
        } else {
            userList();
        }
    }

    public void Actualizar(View view) {
        if (checkFields()) {
            User user = new User(documento, usuario, nombres, apellidos, contra, 1);

            Long error = managerDB.actualizar(user, this);
        }
    }

    private void begin() {
        etDocument=findViewById(R.id.etDocument);
        etApellidos=findViewById(R.id.etApellidos);
        etNombres=findViewById(R.id.etNombres);
        etUsuario=findViewById(R.id.etUsuario);
        etContra=findViewById(R.id.etCONTRASENA);
        listUsers=findViewById(R.id.lvLista);
    }

    public void limpiar(View v){
        etDocument.setText("");
        etUsuario.setText("");
        etNombres.setText("");
        etApellidos.setText("");
        etContra.setText("");
    }
}