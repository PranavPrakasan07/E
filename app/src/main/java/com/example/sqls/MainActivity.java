package com.example.sqls;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    Button button;

    private static String ip = "192.168.43.205";
    private static String port = "1433";
    private static String Classes = "net.sourceforge.jtds.jdbc.Driver";
    private static String database = "ELearn";
    private static String username = "test";
    private static String password = "test";
    private static String url = "jdbc:jtds:sqlserver://"+ip+":"+port+"/"+database;

    private Connection connection = null;

    public void sqlButton(){
        if (connection!=null){
            Statement statement = null;
            try {
                statement = connection.createStatement();

                /*
                ResultSet resultSet = statement.executeQuery("Select * from Tutor;");

                while (resultSet.next()){
                    textView.setText(resultSet.getString(1));
                    Log.d("Message", resultSet.getString(1));
                    Log.d("Message", resultSet.getString(2));
                    Log.d("Message", resultSet.getString(3));
                    Log.d("Message", resultSet.getString(4));
                    Log.d("Message", resultSet.getString(5));
                    Log.d("Message", resultSet.getString(6));
                    Log.d("Message", resultSet.getString(7));

                }
*/
                ResultSet resultSet = statement.executeQuery("Select * from Tutor where email = 'b';");
                resultSet.next();

                Log.d("SearchAbsent", resultSet.getString(1));
                resultSet.next();
                Log.d("SearchAbsent", resultSet.getString(1));

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            textView.setText("Connection is null");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text);
        button = findViewById(R.id.button);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            Class.forName(Classes);
            connection = DriverManager.getConnection(url, username,password);
            textView.setText("SUCCESS");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            textView.setText("ERROR");
        } catch (SQLException e) {
            e.printStackTrace();
            textView.setText("FAILURE");
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlButton();
            }
        });

        ResultSet resultSet = null;
        if (connection!=null){
            Statement statement = null;
            try {
                statement = connection.createStatement();
                resultSet = statement.executeQuery("Select * from tabla");
                textView.setText(resultSet.getString(2));

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            textView.setText("Connection is null");
        }

    }

}
