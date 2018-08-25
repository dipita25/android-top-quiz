package com.example.jonathan.topquiz.controller;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.jonathan.topquiz.R;
import com.example.jonathan.topquiz.model.Historique;

import java.util.ArrayList;
import java.util.List;

public class HistoriqueActivity extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique);

        mListView = (ListView) findViewById(R.id.listView);

        //android.R.layout.simple_list_item_1 est une vue disponible de base dans le SDK android,
        //Contenant une TextView avec comme identifiant "@android:id/text1"

        List<Historique> historiques = genererHistoriques();

        final HistoriqueAdapter adapter = new HistoriqueAdapter(HistoriqueActivity.this, historiques);
        mListView.setAdapter(adapter);
    }

    private List<Historique> genererHistoriques(){
        List<Historique> historique = new ArrayList<Historique>();
        historique.add(new Historique(Color.BLACK, "Florent", 10));
        historique.add(new Historique(Color.BLUE, "Kevin", 15));
        historique.add(new Historique(Color.GREEN, "Logan", 20));
        historique.add(new Historique(Color.RED, "Mathieu", 6));
        historique.add(new Historique(Color.GRAY, "Willy", 2));
        return historique;
    }
}
