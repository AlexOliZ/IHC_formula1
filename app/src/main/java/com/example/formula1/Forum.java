package com.example.formula1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class Forum extends Fragment {

    private static final ArrayList<Topic> topics = new ArrayList<>();
    Topic topic;
    public static final int TEXT_REQUEST = 1;
    private RecyclerView mRecyclerView;
    private TopicAdapter mAdapter;
    Context context;
    private SearchView search_topic;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forum, container, false);
        setUpToolbar(view);

        Topic t1 = new Topic("Expetativas Imola ","Quais são as vossas expetativas para o proximo circuito? Eu acho que o Norris vai ganhar","Imola");
        t1.addComments("O norris é pessimo");
        t1.addComments("Pessimo circuito");
        t1.addComments("péssimo comentário");
        if(topics.isEmpty()){
            topics.add(0,t1);
        }
        Topic t5 = new Topic("Viagem para Portimão","Vou para portimao e ha 2 lugares na carrinha, partida do porto","Portimao");
        if(topics.size() == 1){
            topics.add(0,t5);
        }
        Topic t2 = new Topic("Hamilton vs Senna","hamilton de longe melhor q o senna, change my mind","hamilton");
        t2.addComments("delusional");
        t2.addComments("delete this");
        t2.addComments("WHAT?");
        t2.addComments("concordo a 100%");
        t2.addComments("comparação desnecessária");
        t2.addComments("se estivesse vivo era possivel comparar, agora assim....");
        t2.addComments("delete this.");
        t2.addComments("Schumacher");
        if(topics.size() == 2){
            topics.add(0,t2);
        }
        Topic t3 = new Topic("PROXIMO CIRCUITO","IMOLA LETS GOOOOOOOOOOOOO, MELHOR CICUITO","Imola");
        t3.addComments("monaco>>>>>>>>>");
        if(topics.size() == 3){
            topics.add(0,t3);
        }
        Topic t8 = new Topic("Acidente em Monza","Depois de ter feito tempos tao bons no qualificador, é uma tristeza o Bottas ter se despistado, a pista tem que ser mudada e ja há muito que está uma miséria","Monza");
        t8.addComments("As melhoras para o Bottas");
        t8.addComments("Foi aquela curva muito apertada, ele ia lançado e pronto");
        t8.addComments("a culpa nao é da pista, foi o condutor que nao calculou bem");
        t8.addComments("vai penalisar muito no ranking da mercedes, isso é certo");
        if(topics.size() == 4){
            topics.add(0,t8);
        }
        Topic t4 = new Topic("Portimao: bilhetes","Alguem ainda tem bilhetes para portimao?","Portimao");
        t4.addComments("Sim, entre em contacto comigo no 912321332");
        t4.addComments("Eu arranjo");
        t4.addComments("Sim, adicona:Hugo Manuel");
        t4.addComments("msg para 961456258 e eu arranjo");
        t4.addComments("ja esgotaram, agora so vips");
        t4.addComments("ja vais tarde amigo");
        t4.addComments("can I get some too?");
        t4.addComments("so preciso de casa, porque ja tenho bilhete");
        if(topics.size() == 5){
            topics.add(0,t4);
        }
        Topic t6 = new Topic("Covid em Portimao","mais uma vez a dgs nao diz nada, so diz para a champions...","Portimao");
        t6.addComments("RUA COSTA");
        t6.addComments("é uma palhaçada");
        t6.addComments("este forum nao é para falar de politica");
        if(topics.size() == 6){
            topics.add(0,t6);
        }
        Topic t7 = new Topic("melhor piloto ate agora ","verstappen","pilotos");
        t7.addComments("EXATO");
        t7.addComments("nao gosto");
        t7.addComments("hamilton");
        t7.addComments("por enquanto...");
        if(topics.size() == 7){
            topics.add(0,t7);
        }

        Topic t9 = new Topic("Monza pior trajeto","Nao ha pior trajeto que o de Monza, nada faz sentido, curvas onde deviam ser retas e retas onde deviam ser curvas","Monza");
        t9.addComments("imola é pior");
        if(topics.size() == 8){
            topics.add(0,t9);
        }
        Topic t10 = new Topic("Previsões para Imola","Verstappen ou Hamilton?","Imola");
        if(topics.size() == 9){
            topics.add(0,t10);
        }



        mRecyclerView = view.findViewById(R.id.recyclerview);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new TopicAdapter(context, topics);
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        FloatingActionButton fabbutton = view.findViewById(R.id.floatingActionButton5);
        fabbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddTopic.class);
                startActivityForResult(intent,TEXT_REQUEST);
            }
        });

        return view;

    }

    private void setUpToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.search_menu);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.forum_search, menu);
        super.onCreateOptionsMenu(menu, inflater);

        MenuItem search_race_item = menu.findItem(R.id.search_topic);

        search_race_item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

        search_topic = (SearchView) search_race_item.getActionView();
        search_topic.setMaxWidth(Integer.MAX_VALUE);
        search_topic.setQueryHint("Search Topic");

        search_topic.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode,
                                 int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_REQUEST && resultCode == RESULT_OK) {
            String replyTitle = data.getStringExtra(AddTopic.title);
            String replyDesc = data.getStringExtra(AddTopic.desc);
            String replyKeyword = data.getStringExtra(AddTopic.keyword);
            topic = new Topic(replyTitle,replyDesc,replyKeyword);
            topics.add(0,topic);
            mAdapter = new TopicAdapter(context, topics);
            // Connect the adapter with the RecyclerView.
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    public static Topic giveObject() {
        return topics.get(TopicAdapter.givepos());
    }

    @Override
    public void onResume() {
        super.onResume();
        mAdapter = new TopicAdapter(context, topics);
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
    }

}