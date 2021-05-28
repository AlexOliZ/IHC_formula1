package com.example.formula1;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class Settings extends Fragment {
    private View view;
    private Context context;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        context = getActivity();
        Variables.month = Variables.cal.get(Calendar.MONTH);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        view = inflater.inflate(R.layout.fragment_settings,container,false);


        EditText race_notification = (EditText) view.findViewById(R.id.add_race_notification);
        /*limpar texto quando clicar*/

        EditText championship_notification = (EditText) view.findViewById(R.id.add_championship_notification);
        /*limpar texto quando clicar*/

        ImageButton add_race_notification = (ImageButton) view.findViewById(R.id.add_race_button_notification);
        /* set favorite */

        ImageButton add_championship_notification = (ImageButton) view.findViewById(R.id.add_championship_button_notification);
        /* set year */



        EditText race_favorite = (EditText) view.findViewById(R.id.add_race);
        /*limpar texto quando clicar*/

        EditText championship_favorite = (EditText) view.findViewById(R.id.add_championship);
        /*limpar texto quando clicar*/

        ImageButton add_race_favorite = (ImageButton) view.findViewById(R.id.add_race_button);
        /* set favorite */

        ImageButton add_championship_favorite = (ImageButton) view.findViewById(R.id.add_championship_button);
        /* set year */


        /*
            criar adapters ... o mesmo para notifications e favorites
        */

        return view;
    }

}