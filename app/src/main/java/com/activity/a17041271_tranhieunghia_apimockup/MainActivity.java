package com.activity.a17041271_tranhieunghia_apimockup;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String url = "https://5fcf41b84c97bb00165352a5.mockapi.io/users";
    ArrayList<User> userArrayList;
    ApdapterUserListView adt;
    ListView listViewUser;
    Button btnCreate, btnUpdate, btnDelete;
    int index = -1;
    EditText tvFristName, tvLastName, tvSalary;
    RadioGroup groupGender;
    RadioButton radioMale, radioFemale;
    TextView tvResetDS;


    //String url = "http://5b7e85ceadf2070014bfa383.mockapi.io/users/21";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userArrayList = new ArrayList<>();


        GetArrayJson(url);


        groupGender = findViewById(R.id.groupGender);
        radioMale = findViewById(R.id.radioMale);
        radioFemale = findViewById(R.id.radioFemale);
        radioMale.setChecked(true);
        tvFristName = findViewById(R.id.editTextFristName);
        tvLastName = findViewById(R.id.editTextLastName);
        tvSalary = findViewById(R.id.editTextSalary);
        listViewUser = findViewById(R.id.listViewuser);
        btnCreate = findViewById(R.id.btnCreate);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        tvResetDS=findViewById(R.id.tvResetDanhSach);

        adt = new ApdapterUserListView(MainActivity.this, R.layout.itemuser, userArrayList);
        listViewUser.setAdapter(adt);


        groupGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                doOnChangedGender(radioGroup, i);
            }
        });

        tvResetDS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userArrayList.clear();
                GetArrayJson(url);
                tvFristName.setText("");
                tvLastName.setText("");
                tvSalary.setText("");
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final User user = new User();
                String fristName = tvFristName.getText().toString().trim();
                String lastName = tvLastName.getText().toString().trim();
                String salary = tvSalary.getText().toString().trim();

                user.setFIRSTNAME(fristName);
                user.setLASTNAME(lastName);
                user.setSALARY(salary);
                user.setGENDER(doOnChangedGender(groupGender, 1).toString().trim());

                PostApi(url, user);
                index=-1;
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (index == -1) {
                    Toast.makeText(MainActivity.this, "Chưa chọn dòng cần xóa", Toast.LENGTH_SHORT).show();
                } else {
                    DeleteApi(url, userArrayList.get(index).getId());
                    index=-1;

                }

            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (index == -1) {
                    Toast.makeText(MainActivity.this, "Chưa chọn dòng cần sửa", Toast.LENGTH_SHORT).show();
                } else {
                    final User userUpdate = new User();
                    String fristName = tvFristName.getText().toString().trim();
                    String lastName = tvLastName.getText().toString().trim();
                    String salary = tvSalary.getText().toString().trim();


                    userUpdate.setFIRSTNAME(fristName);
                    userUpdate.setLASTNAME(lastName);
                    userUpdate.setSALARY(salary);
                    userUpdate.setGENDER(doOnChangedGender(groupGender, 1).toString().trim());

                    PutApi(url, userUpdate, userArrayList.get(index).getId());

                    index=-1;

                }
            }
        });
        listViewUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                index = i;
                User user = userArrayList.get(i);
                tvFristName.setText(user.getFIRSTNAME());
                tvLastName.setText(user.getLASTNAME());
                if (user.getGENDER().equals("Male")) {
                    radioMale.setChecked(true);
                } else {
                    radioFemale.setChecked(true);
                }
                tvSalary.setText(user.getSALARY());
            }
        });

    }


    private void GetData(String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                //tvDisplay.setText(response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error make by API server!", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void GetJson(String url) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //tvDisplay.setText(response.getString("LASTNAME").toString());

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error by get JsonObject...", Toast.LENGTH_SHORT).show();
            }
        }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }

    private void GetArrayJson(String url) {


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {

                        JSONObject object = (JSONObject) response.get(i);
                        int id = object.getInt("id");
                        String FIRSTNAME = object.getString("FIRSTNAME");
                        String LASTNAME = object.getString("LASTNAME");
                        String GENDER = object.getString("GENDER");
                        String SALARY = object.getString("SALARY");

                        User user = new User(id, FIRSTNAME, LASTNAME, GENDER, SALARY);
                        userArrayList.add(user);

                        adt.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error by get Json Array!", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    private void PostApi(String url, final User user) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, "Add Successfully", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error by Post data!", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("FIRSTNAME", user.getFIRSTNAME());
                params.put("LASTNAME", user.getLASTNAME());
                params.put("GENDER", user.getGENDER());
                params.put("SALARY", user.getSALARY());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void PutApi(String url, final User user, int ma) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.PUT,
                url + '/' + ma, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, "Update Successfully", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error by Put data!", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams()
                    throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("FIRSTNAME", user.getFIRSTNAME());
                params.put("LASTNAME", user.getLASTNAME());
                params.put("GENDER", user.getGENDER());
                params.put("SALARY", user.getSALARY());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void DeleteApi(String url, int ma) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.DELETE, url + '/' + ma, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MainActivity.this, "Delete Successfully", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error by Delete data!", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private String doOnChangedGender(RadioGroup group, int checkedId) {
        int checkedRadioId = group.getCheckedRadioButtonId();

        if (checkedRadioId == R.id.radioMale) {
            return "Male";
        } else {
            return "Female";
        }
    }
}
