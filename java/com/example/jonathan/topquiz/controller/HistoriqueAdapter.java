package com.example.jonathan.topquiz.controller;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jonathan.topquiz.R;
import com.example.jonathan.topquiz.model.Historique;

import java.util.List;

public class HistoriqueAdapter extends ArrayAdapter<Historique> {

    //tweets est la liste des models à afficher
    public HistoriqueAdapter(Context context, List<Historique> historiques) {
        super(context, 0, historiques);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_historique,parent, false);
        }

        HistoriqueHolder historiqueHolder = (HistoriqueHolder) convertView.getTag();
        if(historiqueHolder == null){
            historiqueHolder = new HistoriqueHolder();
            historiqueHolder.name = (TextView) convertView.findViewById(R.id.historique_name);
            historiqueHolder.score = (TextView) convertView.findViewById(R.id.historique_score);
            historiqueHolder.avatar = (ImageView) convertView.findViewById(R.id.avatar);
            convertView.setTag(historiqueHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Historique tweet = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        historiqueHolder.name.setText(tweet.getName());
        historiqueHolder.score.setText(""+tweet.getScore()+"");
        historiqueHolder.avatar.setImageDrawable(new ColorDrawable(tweet.getColor()));

        return convertView;
    }

    private class HistoriqueHolder{
        public TextView name;
        public TextView score;
        public ImageView avatar;
    }
}
