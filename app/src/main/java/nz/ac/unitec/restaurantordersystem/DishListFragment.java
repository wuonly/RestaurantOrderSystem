package nz.ac.unitec.restaurantordersystem;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Kay on 27/07/2016.
 */
public class DishListFragment extends Fragment {
    private static final String TAG = "CrimeListFragment";
    private ArrayList<Dish> mCrimes;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.dish_name);
        mCrimes = DishLab.get(getActivity()).getDishes();

        CrimeAdapter adapter = new DishAdapter(mCrimes);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        //Get the Crime from the adapter
        Dish c = ((CrimeAdapter)getListAdapter()).getItem(position);

        //Start CrimeActivity
        Intent i = new Intent(getActivity(), DishActivity.class);
        i.putExtra(DishFragment.EXTRA_CRIME_ID, c.getId());
        startActivity(i);
    }

    private class DishAdapter extends ArrayAdapter<Dish> {

        public DishAdapter(ArrayList<Dish> crimes){
            super(getActivity(),0,crimes);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            //If we weren't given a view, inflate one
            if(convertView == null){
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_dish,null);
            }

            //Configure the view for this Crime
            Dish c = getItem(position);
            TextView titleTextView =
                    (TextView)convertView.findViewById(R.id.crime_list_item_titleTextView);
            titleTextView.setText(c.getTitle());
            TextView dateTextView =
                    (TextView)convertView.findViewById(R.id.crime_list_item_dateTextView);
            dateTextView.setText(c.getDate().toString());
            CheckBox solvedCheckBox =
                    (CheckBox)convertView.findViewById(R.id.crime_list_item_solvedCheckBox);
            solvedCheckBox.setChecked(c.isSolved());

            return convertView;
        }
    }


}