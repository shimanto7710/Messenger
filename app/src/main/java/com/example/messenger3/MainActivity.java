package com.example.messenger3;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filterable;
import android.widget.Filter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import com.example.Adapter.UserInfoAdapter;
import com.example.Model.PeopleData;
import com.example.SimpleDividerItemDecoration;
import com.example.db.DatabaseHelper;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    DatabaseHelper myDbHelper;
    UserInfoAdapter userInfoAdapter;
    private RecyclerView recyclerView;


//    private List<PeopleData> dictionaryWords;
//    private List<PeopleData> filteredList;
//    // RecycleView adapter object
//    private SimpleItemRecyclerViewAdapter mAdapter;
//    // Search edit box
//    private EditText searchBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myDbHelper=new DatabaseHelper(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        recyclerView = (RecyclerView) findViewById(R.id.recyclerView11);
        ArrayList<PeopleData>peopleDataList=new ArrayList<>();
        peopleDataList.add(new PeopleData(1,"Shimanto Ahmed","msg"));
        peopleDataList.add(new PeopleData(2,"Akash Bhaumik","msg"));
        peopleDataList.add(new PeopleData(3,"Sajjad","msg"));

        userInfoAdapter = new UserInfoAdapter(this, peopleDataList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(userInfoAdapter);




//        dictionaryWords = getListItemData();
//        filteredList = new ArrayList<PeopleData>();
//        filteredList.addAll(dictionaryWords);
//        searchBox = (EditText)findViewById(R.id.search_box);
//        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView11);
//        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));
//
//        mAdapter = new SimpleItemRecyclerViewAdapter(filteredList);
//        recyclerView.setAdapter(mAdapter);
//        // search suggestions using the edittext widget
//        searchBox.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                mAdapter.getFilter().filter(s.toString());
//            }
//            @Override
//            public void afterTextChanged(Editable s) {
//            }
//        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




//    public class SimpleItemRecyclerViewAdapter extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> implements Filterable {
//        private List<PeopleData> mValues;
//        private CustomFilter mFilter;
//        public SimpleItemRecyclerViewAdapter(List<PeopleData> items) {
//            mValues = items;
//            mFilter = new CustomFilter(SimpleItemRecyclerViewAdapter.this);
//        }
//        @Override
//        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lvl_list_layout, parent, false);
//            return new ViewHolder(view);
//        }
//        @Override
//        public void onBindViewHolder(final ViewHolder holder, int position) {
////            holder.mItem = mValues.get(position);
//            holder.nameTxt.setText(String.valueOf(mValues.get(position).getName()));
//            holder.msgTxt.setText(mValues.get(position).getMsg());
//        }
//        @Override
//        public int getItemCount() {
//            return mValues.size();
//        }
//        @Override
//        public Filter getFilter() {
//            return mFilter;
//        }
//        public class ViewHolder extends RecyclerView.ViewHolder {
//            public final View mView;
//            public final TextView nameTxt;
//            public final TextView msgTxt;
//            public PeopleData mItem;
//            public ViewHolder(View view) {
//                super(view);
//                mView = view;
//                nameTxt = (TextView) view.findViewById(R.id.multi_selected_session);
//                msgTxt = (TextView) view.findViewById(R.id.end);
//            }
//            @Override
//            public String toString() {
//                return super.toString() + " '" + msgTxt.getText() + "'";
//            }
//        }
//        public class CustomFilter extends Filter {
//            private SimpleItemRecyclerViewAdapter mAdapter;
//            private CustomFilter(SimpleItemRecyclerViewAdapter mAdapter) {
//                super();
//                this.mAdapter = mAdapter;
//            }
//            @Override
//            protected FilterResults performFiltering(CharSequence constraint) {
//                filteredList.clear();
//                final FilterResults results = new FilterResults();
//                if (constraint.length() == 0) {
//                    filteredList.addAll(dictionaryWords);
//                } else {
//                    final String filterPattern = constraint.toString().toLowerCase().trim();
//                    for (final PeopleData mWords : dictionaryWords) {
//                        if (mWords.getName().toLowerCase().startsWith(filterPattern)) {
//                            filteredList.add(mWords);
//                        }
//                    }
//                }
//                System.out.println("Count Number " + filteredList.size());
//                results.values = filteredList;
//                results.count = filteredList.size();
//                return results;
//            }
//            @Override
//            protected void publishResults(CharSequence constraint, FilterResults results) {
//                System.out.println("Count Number 2 " + ((List<PeopleData>) results.values).size());
//                this.mAdapter.notifyDataSetChanged();
//            }
//        }
//    }
//    private List<PeopleData> getListItemData(){
//        List<PeopleData> listViewItems = new ArrayList<PeopleData>();
//        listViewItems.add(new PeopleData(1, "Apple", "Apple"));
//        listViewItems.add(new PeopleData(2, "Orange", "Orange"));
//        listViewItems.add(new PeopleData(3, "Banana", "Banana"));
////        listViewItems.add(new QuizObject(4, "Grape", "Grape"));
////        listViewItems.add(new QuizObject(5, "Mango", "Mango"));
////        listViewItems.add(new QuizObject(6, "Pear", "Pear"));
////        listViewItems.add(new QuizObject(7, "Pineapple", "Pineapple"));
////        listViewItems.add(new QuizObject(8, "Strawberry", "Strawberry"));
////        listViewItems.add(new QuizObject(9, "Coconut", "Coconut"));
////        listViewItems.add(new QuizObject(10, "Almond", "Almond"));
//        return listViewItems;
//    }



}
