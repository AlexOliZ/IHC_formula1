package com.example.formula1;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toolbar;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;


public class DashboardFragment extends Fragment {

    Toolbar toolbar;
    ViewPager viewPager;
    private static TabLayout tabLayout;

    private static ImageView img;
    private static ImageView img2;
    private static ImageView img3;
    private static ImageView img4;
    private static ImageView img5;
    private static ImageView img6;
    private static ImageView img7;
    private static ImageView img8;
    private static ImageView img9;
    private static ImageView img10;
    private static TabItem teams;
    private static TabItem pilots;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch(tab.getPosition()) {
                    case 0:
                        replaceFragment( new DashboardFragment());
                        break;
                    case 1:
                        replaceFragment( new Pilots());
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                switch(tab.getPosition()) {
                    case 0:
                        replaceFragment( new DashboardFragment());
                        break;
                    case 1:
                        replaceFragment( new Pilots());
                        break;
                }
            }
        });

        img = (ImageView) view.findViewById(R.id.teamsFerrari);
        img.setOnClickListener(this::onClick);
        img2 = (ImageView) view.findViewById(R.id.teamsRed);
        img2.setOnClickListener(this::onClick);
        img3 = (ImageView) view.findViewById(R.id.teamsMercedes);
        img3.setOnClickListener(this::onClick);
        img4 = (ImageView) view.findViewById(R.id.teamsAlpha);
        img4.setOnClickListener(this::onClick);
        img5 = (ImageView) view.findViewById(R.id.teamsAston);
        img5.setOnClickListener(this::onClick);
        img6 = (ImageView) view.findViewById(R.id.teamsMc);
        img6.setOnClickListener(this::onClick);
        img7 = (ImageView) view.findViewById(R.id.teamsHaas);
        img7.setOnClickListener(this::onClick);
        img8 = (ImageView) view.findViewById(R.id.teamsWilliams);
        img8.setOnClickListener(this::onClick);
        img9 = (ImageView) view.findViewById(R.id.teamsAlpine);
        img9.setOnClickListener(this::onClick);
        img10 = (ImageView) view.findViewById(R.id.teamsAlphaR);
        img10.setOnClickListener(this::onClick);
        return view;
    }

    public void replaceFragment(Fragment someFragment) {
        FragmentManager man = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = man.beginTransaction();
        transaction.setReorderingAllowed(true);
        transaction.replace(R.id.frameLayout6, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @SuppressLint("ResourceType")
    public void onClick(View v) {
        Fragment fragment = null;

        switch(v.getId())
        {
            case R.id.teamsFerrari:
                //nav.navigate(R.id.action_navigation_dashboard_to_ferrari);
                replaceFragment(
                        new Team("For many, Ferrari and Formula 1 racing have become inseparable. The only team to have competed in every season since the world championship began, the Prancing Horse has grown from the humble dream of founder Enzo Ferrari to become one of the most iconic and recognised brands in the world. Success came quickly through the likes of Alberto Ascari and John Surtees, and continued – in amongst leaner times – with Niki Lauda in the 1970s and then Michael Schumacher in the 2000s, when Ferrari claimed a then unprecedented five consecutive title doubles, securing their status as the most successful and decorated team in F1 history...",
                                R.drawable.profile_vrt_raw_bytes_1587515318_10187,
                                R.drawable.charles_leclerc_ferrari_1,
                                R.drawable.carlos_sainz_jr_ferrari_1));
                break;
            case R.id.teamsRed:
                replaceFragment(
                        new Team("Red Bull were no strangers to F1 - as sponsors - prior to formally entering as a works team in 2004. Nonetheless, the scale of their success over the following decade was staggering. After a first podium in 2006, the team hit their stride in 2009, claiming six victories and second in the constructors' standings. Over the next four seasons they were a tour de force, claiming consecutive title doubles between 2010 and 2013, with Sebastian Vettel emerging as the sport's youngest quadruple champion. Now their hopes of recapturing that glory lie with an equally exciting talent – one named Max Verstappen…",
                                R.drawable.red_bull_logo,
                                R.drawable.a1616676511153,
                                R.drawable.a1616669035981));
                break;
            case R.id.teamsMercedes:
                replaceFragment(
                        new Team("Mercedes’ modern F1 revival started with the creation of a works squad for 2010 - the platform for a meteoric rise up the Grand Prix order. The team generated huge excitement from the off with the sensational return of Michael Schumacher, but headlines soon followed on track: three podiums in their debut season, all via Nico Rosberg - who then claimed a breakthrough pole/victory double at China in 2012. The following season he was paired with Lewis Hamilton, the duo going on to stage some epic title battles as the Silver Arrows swept all before them to become one of the most dominant forces of the modern F1 era. And with Hamilton now partnered by steely Finn Valtteri Bottas, Mercedes remain very much the team to beat…",
                                R.drawable.transferir,
                                R.drawable.lewis_hamilton_mercedes_1,
                                R.drawable.valtteri_bottas_mercedes_1));
                break;
            case R.id.teamsAlpha:
                replaceFragment(
                        new Team("Established in 2006 as a squad in which young drivers from Red Bull’s prodigious talent pool could cut their F1 teeth, AlphaTauri – originally named Toro Rosso – were formed from the ashes of the plucky Minardi team. Sebastian Vettel gave validity to the approach almost immediately, delivering a fairy-tale win in 2008, before going on to enjoy world championship success with parent team Red Bull Racing. Today the ethos of nurturing talent still holds true, though the Italian squad are no longer simply a ‘B team’ but a constructor in their own right...",
                                R.drawable.a1582650557134,
                                R.drawable.a1616676303576,
                                R.drawable.a1616676765251));
                break;
            case R.id.teamsAston:
                replaceFragment(
                        new Team("Aston Martin’s original Formula 1 foray – over half a century ago – lasted just five races. This time, though, it’s serious. This F1 squad are no strangers to success, having won in their original guise of Jordan and most recently as Racing Point in 2020. Renowned for their ability to punch above their weight, and now with a four-time champion leading their driver line-up, Aston Martin are very much a team to watch in 2021…",
                                R.drawable.aston_martin,
                                R.drawable.aston1,
                                R.drawable.aston2));
                break;
            case R.id.teamsMc:
                replaceFragment(
                        new Team("Since entering the sport in 1966 under the guidance and restless endeavour of eponymous founder Bruce, McLaren's success has been nothing short of breathtaking. Five glittering decades have yielded countless victories, pole positions and podiums, not to mention eight constructors' championships. What's more, some of the sport's greatest drivers made their names with the team, including Emerson Fittipaldi, Ayrton Senna, Mika Hakkinen and Lewis Hamilton...",
                                R.drawable.images__1_,
                                R.drawable.mc1,
                                R.drawable.mc2));
                break;
            case R.id.teamsHaas:
                replaceFragment(
                        new Team("The youngest team on the grid, Haas made their highly impressive debut in 2016, and in the process became the first all-American-led F1 squad in three decades. Founded by industrialist Gene Haas, they are based in the United States on the same Kannapolis, North Carolina facility as his championship-winning NASCAR Sprint Cup Series team, Stewart-Haas Racing. The Ferrari-powered team, led by the charismatic Guenther Steiner, also have a UK factory in Banbury…",
                                R.drawable.haas,
                                R.drawable.haas1,
                                R.drawable.haas2));
                break;
            case R.id.teamsWilliams:
                replaceFragment(
                        new Team("Driven on by the brilliance and passion of Sir Frank Williams, Williams grew from humble beginnings to become a Formula 1 behemoth, unrivalled by all except Ferrari and McLaren in terms of enduring success. Over the past four decades the team has racked up Grand Prix wins and championship glory, and in the process nurtured some of the greatest talents in the sport, both in and out the cockpit. Now, following the Williams family's decision to step aside after the sale of the team to Dorilton Capital, a new era begins...",
                                R.drawable.wi,
                                R.drawable.wi1,
                                R.drawable.wi2));
                break;
            case R.id.teamsAlpine:
                replaceFragment(
                        new Team("Alpine may be a new name to Formula 1 for 2021, as Renault’s F1 team is rebranded, but their famous sportscar arm has plenty of motorsport heritage. The rebirth marks the next step in Renault’s F1 revival, begun in 2016 with the takeover of the then-Lotus team. They’ve been back on the podium since then, but now – with a new management and driver line-up in place – wins must be the next target…",
                                R.drawable.alpine,
                                R.drawable.alp1,
                                R.drawable.alp2));
                break;
            case R.id.teamsAlphaR:
                replaceFragment(
                        new Team("The name Alfa Romeo boasts Formula 1 connections dating back to the championship’s inception in 1950. Fast forward to the 21st century and Italian flare combines with Swiss sensibilities in a new era for the team formerly known as Sauber.\n" +
                                "\n" +
                                "Having enjoyed considerable success in world sportscars, where he helped nurture the emerging talents of future F1 stars Michael Schumacher and Heinz-Harald Frentzen, Peter Sauber guided his eponymous squad into F1 in 1993.\n" +
                                "\n" +
                                "The team has since established itself as a mainstay of the grid, becoming race winners under BMW’s brief ownership, and developing a well-earned reputation not only for producing competitive cars, but also for developing young drivers.\n" +
                                "\n",
                                R.drawable.alphar,
                                R.drawable.alpr1,
                                R.drawable.alpr2));
                break;
        }
    }

}