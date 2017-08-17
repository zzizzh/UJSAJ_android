package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.myapplication.APIController.TourAPIController;
import com.example.myapplication.ProblemDomain.Location;
import com.example.myapplication.ProblemDomain.TourData;
import com.example.myapplication.R;
import com.example.myapplication.CustomClass.ListViewAdapter;

import java.util.ArrayList;

import static com.example.myapplication.ProblemDomain.Constants.AREA_CODE;
import static com.example.myapplication.ProblemDomain.Constants.AREA_TOUR_CODE;
import static com.example.myapplication.ProblemDomain.Constants.CODE;
import static com.example.myapplication.ProblemDomain.Constants.CONTENTES_TYPE_CODE;
import static com.example.myapplication.ProblemDomain.Constants.CONTENTS_TYPE;
import static com.example.myapplication.ProblemDomain.Constants.GET_LOCATION;
import static com.example.myapplication.ProblemDomain.Constants.NAME;
import static com.example.myapplication.ProblemDomain.Constants.SERVICE_CODE;

public class SearchActivity extends AppCompatActivity {

    public static SearchActivity thisActivity = null;

    TourAPIController tourAPIController;    // 관광정보 openAPI를 위한 컨트롤러

    String area_name_list[];                                // 지역 선택 스피너 아이템
    String area_code_list[];                                // 지역 선택 스피너 코드 값

    String area_name_list_s[];                              // 시군구 선택 스피너 아이템
    String area_code_list_s[];                              // 시군구 선택 스피너 코드 값

    String cat1_name_list[];                                // 대분류 선택 스피너 아이템
    String cat1_code_list[];                                // 대분류 선택 스피너 코드 값
    String cat2_name_list[];                                // 중분류 선택 스피너 아이템
    String cat2_code_list[];                                // 중분류 선택 스피너 코드 값
    String cat3_name_list[];                                // 소분류 선택 스피너 아이템
    String cat3_code_list[];                                // 소분류 선택 스피너 코드 값

    ArrayList<ArrayList<String>> name_code_list;
    ArrayList<TourData> tour_data_list;

    Spinner spinner_area_1, spinner_area_2;
    Spinner spinner_type;
    Spinner spinner_cat1, spinner_cat2, spinner_cat3;

    ArrayAdapter<String> adapter_arae_1, adapter_area_2;
    ArrayAdapter<String> adapter_type;
    ArrayAdapter<String> adapter_cat1, adapter_cat2, adapter_cat3;

    SearchActivity searchActivity = this;

    ListView listView;
    ListViewAdapter adapter;

    String cat1Code = "", cat2Code = "", cat3Code = "";
    String contentTypeIdCode = "";
    String areaCode = "", sigunguCode = "";

    Button search_btn;

    int SELECTED_LOCATION   = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        thisActivity = this;

        tourAPIController = TourAPIController.getToruAPIController();

        search_btn = (Button)findViewById(R.id.search);
        Button map=(Button)findViewById(R.id.search_map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent(SearchActivity.this,SearchLocationByMap.class);
                intent.putExtra("TYPE", contentTypeIdCode);
                startActivityForResult(intent, 0);
            }
        });

        Button.OnClickListener mClickListener = new View.OnClickListener() {
            public void onClick(View v) {
                tour_data_list = tourAPIController.queryAPI(AREA_TOUR_CODE, areaCode + sigunguCode +
                        contentTypeIdCode + cat1Code + cat2Code + cat3Code);

                if(tour_data_list != null){
                    if(tour_data_list.size() == 0){
                        Toast toast = Toast.makeText(searchActivity, "검색 결과 없음", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else{
                        Toast toast = Toast.makeText(searchActivity, "검색결과 : " + tour_data_list.size(), Toast.LENGTH_SHORT);
                        toast.show();
                        for(int i=0; i<tour_data_list.size(); i++){
                            //adapter.addItem(tour_data_list.get(i));
                        }
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        };

        search_btn.setOnClickListener(mClickListener);

        spinner_area_1 = (Spinner) findViewById(R.id.spinner_area_1);

        /*
            시군구 스피너 초기화
         */
        spinner_area_2 = (Spinner) findViewById(R.id.spinner_area_2);
        adapter_area_2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.sigungu));
        spinner_area_2.setAdapter(adapter_area_2);


        spinner_type = (Spinner) findViewById(R.id.spinner_type);

        /*
            카테고리 스피너 초기화
         */
        spinner_cat1 = (Spinner) findViewById(R.id.spinner_cat1);
        adapter_cat1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.cat1));
        spinner_cat1.setAdapter(adapter_cat1);

        spinner_cat2 = (Spinner) findViewById(R.id.spinner_cat2);
        adapter_cat2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.cat2));
        spinner_cat2.setAdapter(adapter_cat2);

        spinner_cat3 = (Spinner) findViewById(R.id.spinner_cat3);
        adapter_cat3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.cat3));
        spinner_cat3.setAdapter(adapter_cat3);

        /*
            관광지 타입 스피너 초기화. 정해진 상수값 사용.
         */
        adapter_type = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, CONTENTS_TYPE);
        adapter_type.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_type.setAdapter(adapter_type);
        spinner_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if(position != 0)
                    contentTypeIdCode = "&contentTypeId=" + String.valueOf(CONTENTES_TYPE_CODE[position]);
                else
                    contentTypeIdCode = "";
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                contentTypeIdCode = "";
            }
        });

       /*
            지역 선택 스피너 초기화
         */
        name_code_list = tourAPIController.queryAPI(AREA_CODE, "", true);

        int size = name_code_list.get(CODE).size();

        area_code_list = new String[size+1];
        area_name_list = new String[size+1];

        for(int i=0; i<size; i++){
            area_code_list[i+1] = name_code_list.get(CODE).get(i);
            area_name_list[i+1] = name_code_list.get(NAME).get(i);
        }
        // 선택 안함
        area_code_list[0] = "-1";
        area_name_list[0] = "지역 선택";

        adapter_arae_1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, area_name_list);
        adapter_arae_1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_area_1.setAdapter(adapter_arae_1);
        // 첫번째 지역선택 스피너 리스너
        spinner_area_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            // 아무것도 선택 안되면 모두 초기화
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                areaCode = "";
            }
            // 선택된 지역 코드로 "시 군 구" 스피너 리스트를 만듦
            @Override
            public void onItemSelected(AdapterView<?> adapterView, android.view.View view, int position, long id) {

                if(position != 0) {
                    areaCode = "&areaCode=" + area_code_list[position];
                     /*
                        시 군 구 스피너 초기화
                     */
                    name_code_list = tourAPIController.queryAPI(AREA_CODE, areaCode, true);

                    int size = name_code_list.get(CODE).size();

                    area_code_list_s = new String[size + 1];
                    area_name_list_s = new String[size + 1];

                    for (int i = 0; i < size; i++) {
                        area_code_list_s[i + 1] = name_code_list.get(CODE).get(i);
                        area_name_list_s[i + 1] = name_code_list.get(NAME).get(i);
                    }

                    // 선택 안함
                    area_name_list_s[0] = "시군구";
                    area_code_list_s[0] = "-1";

                    ArrayAdapter<String> adapter_area_2 = new ArrayAdapter<String>(searchActivity, android.R.layout.simple_spinner_item, area_name_list_s);
                    adapter_area_2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spinner_area_2.setAdapter(adapter_area_2);

                    /*
                        두번째 시군구 스피너 선택 리스너
                     */
                    spinner_area_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                            if(position != 0)
                                sigunguCode = "&sigunguCode=" + area_code_list_s[position];
                            else
                                sigunguCode = "";
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                            sigunguCode = "";
                        }
                    });
                }
                else
                    areaCode = "";
            }
        });

         /*
            대분류 스피너 초기화
         */
        name_code_list = tourAPIController.queryAPI(SERVICE_CODE, "", true);

        size = name_code_list.get(CODE).size();

        cat1_code_list = new String[size+1];
        cat1_name_list = new String[size+1];

        for(int i=0; i<size; i++){
            cat1_code_list[i+1] = name_code_list.get(CODE).get(i);
            cat1_name_list[i+1] = name_code_list.get(NAME).get(i);
        }
        // 선택 안함
        cat1_code_list[0] = "-1";
        cat1_name_list[0] = "대분류";

        adapter_cat1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cat1_name_list);
        adapter_cat1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_cat1.setAdapter(adapter_cat1);
        // 첫번째 지역선택 스피너 리스너
        spinner_cat1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            // 아무것도 선택 안되면 모두 초기화
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                cat1Code = "";
            }
            // 선택된 대분류 코드로 "중분류" 스피너 리스트를 만듦
            @Override
            public void onItemSelected(AdapterView<?> adapterView, android.view.View view, int position, long id) {

                if(position != 0) {
                /*
                    중분류 스피너 초기화
                 */
                    cat1Code = "&cat1=" + cat1_code_list[position];

                    name_code_list = tourAPIController.queryAPI(SERVICE_CODE, cat1Code, true);

                    int size = name_code_list.get(CODE).size();

                    cat2_code_list = new String[size + 1];
                    cat2_name_list = new String[size + 1];

                    for (int i = 0; i < size; i++) {
                        cat2_code_list[i + 1] = name_code_list.get(CODE).get(i);
                        cat2_name_list[i + 1] = name_code_list.get(NAME).get(i);
                    }

                    // 선택 안함
                    cat2_name_list[0] = "중분류";
                    cat2_code_list[0] = "-1";

                    adapter_cat2 = new ArrayAdapter<String>(searchActivity, android.R.layout.simple_spinner_item, cat2_name_list);
                    adapter_cat2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spinner_cat2.setAdapter(adapter_cat2);

                    /*
                        두번째 시군구 스피너 선택 리스너
                     */
                    spinner_cat2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                            if(position != 0) {
                                cat2Code = "&cat2=" + cat2_code_list[position];
                                /*
                                     중분류 스피너 선택 리스너
                                */
                                name_code_list = tourAPIController.queryAPI(SERVICE_CODE, cat1Code+cat2Code, true);

                                int size = name_code_list.get(CODE).size();

                                cat3_code_list = new String[size + 1];
                                cat3_name_list = new String[size + 1];

                                for (int i = 0; i < size; i++) {
                                    cat3_code_list[i + 1] = name_code_list.get(CODE).get(i);
                                    cat3_name_list[i + 1] = name_code_list.get(NAME).get(i);
                                }

                                // 선택 안함
                                cat3_name_list[0] = "소분류";
                                cat3_code_list[0] = "-1";

                                adapter_cat3 = new ArrayAdapter<String>(searchActivity, android.R.layout.simple_spinner_item, cat3_name_list);
                                adapter_cat3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                spinner_cat3.setAdapter(adapter_cat3);

                                /*
                                    소분류 스피너 선택 리스너
                                 */
                                spinner_cat3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                                        if(position != 0)
                                            cat3Code = "&cat3=" + cat3_code_list[position];
                                        else
                                            cat3Code = "";
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                        cat3Code = "";
                                    }
                                });
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                            sigunguCode = "";
                        }
                    });
                }
                else
                    cat1Code = "";
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == GET_LOCATION)
        {
            Location selectedLocation = (Location)data.getSerializableExtra("result");
            Intent intent = new Intent();
            intent.putExtra("result", selectedLocation);
            Log.d("test", "[ SearchActivity ] selected Location : " + selectedLocation.toString());
            setResult(GET_LOCATION, intent);
            finish();
        }
        return;
    }
}


