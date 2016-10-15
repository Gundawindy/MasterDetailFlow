package com.example.matthew.masterdetailflow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.matthew.masterdetailflow.dummy.DummyContent;

import java.util.List;

import static android.R.attr.button;

/**
 * An activity representing a list of Cars. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link CarDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class CarListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*
 * Sets the first view shown to be activity_car_list which has a toolbar widget, a
 * floating action button and a frame layout which is used to inflate fragments into.
*/
        setContentView(R.layout.activity_car_list);

/*
* Sets up toolbar with id @+id/toolbar, defined in activity_car_list.xml layout. Sets it to have the title of the app
*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

/*
* Set up floating action button onClickListener to work on the fab in activity_car_list.xml with
 * the id @+id/fab. At the moment it just makes a snackbar saying to put your own action here.
*/
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

/* Sets up a recycler View to show the list of cars in the view with id @+id/car_list. This view is
* included in the activity_car_list layout and takes the whole screen on a phone but only 200dp wide
* on a tablet.*/
        View recyclerView = findViewById(R.id.car_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        if (findViewById(R.id.car_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
    }
/* Sets up the recycler view to be populated with dummy content. This needs to be changed to contain
* the actual data. */
// TODO: 15/10/2016 Setup real data to be displayed and alter this recylcerView to use the real data
    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(DummyContent.ITEMS));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<DummyContent.DummyItem> mValues;

        public SimpleItemRecyclerViewAdapter(List<DummyContent.DummyItem> items) {
            mValues = items;
        }
/* As each item is needed to be created, this inflates a view using car_list_content.xml as the layout
* This is just two textViews side by side in a LinearLayout. This is what sets the look of each individual
* item in the car list*/
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.car_list_content, parent, false);
            return new ViewHolder(view);
        }

/* When a list item is clicked this method handles it. If it is a tablet (mTwoPane is true) then it
* replaces the @+id/car_detail_container view in the w900dp\car_list.xml file with the new fragment
 * being CarDetailFragment to display the details next to the list. If it is a phone
 * (mTwoPane is false) then it starts CarDetailActivity as a new activity with CarDetailFragment as
 * a fragment in it.*/
        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.mIdView.setText(mValues.get(position).id);
            holder.mContentView.setText(mValues.get(position).content);

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
//                      Start CarDetailFragment as a fragment
                        Bundle arguments = new Bundle();
                        arguments.putString(CarDetailFragment.ARG_ITEM_ID, holder.mItem.id);
                        CarDetailFragment fragment = new CarDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.car_detail_container, fragment)
                                .commit();
                    } else {
//                      Start CarDetailFragment as a new activity
                        Context context = v.getContext();
                        Intent intent = new Intent(context, CarDetailActivity.class);
                        intent.putExtra(CarDetailFragment.ARG_ITEM_ID, holder.mItem.id);

                        context.startActivity(intent);
                    }
                }
            });
        }
/* Returns the number of items that need to be displayed.*/
        @Override
        public int getItemCount() {
            return mValues.size();
        }
/* Declares variables for the views used. */
        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;
            public DummyContent.DummyItem mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.id);
                mContentView = (TextView) view.findViewById(R.id.content);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }
}
