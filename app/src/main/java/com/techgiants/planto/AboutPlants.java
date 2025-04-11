package com.techgiants.planto;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

public class AboutPlants extends Fragment {
 ImageView banyan,mango,peepal;
 ImageButton neem,oak,sandalwood,banana,apple,orange,tamarind,papaya,sheesham,coconut,eucalyptus,
    fig,teak,birch,confiner,beetalnut,pomegrante;
    public AboutPlants() {
        // Required empty public constructor
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_about_plants, container, false);
        init(view);

        return view;
    }
    public void init(View view){
        banyan=view.findViewById(R.id.banyantree);
        mango=view.findViewById(R.id.mangotree);
        peepal=view.findViewById(R.id.peepaltree);
        neem=view.findViewById(R.id.neemtree);
        oak=view.findViewById(R.id.oaktree);
        sandalwood=view.findViewById(R.id.sandalwoodtree);
        banana=view.findViewById(R.id.bananatree);
        apple=view.findViewById(R.id.appletree);
        orange=view.findViewById(R.id.orangetree);
        tamarind=view.findViewById(R.id.tamarindtree);
        papaya=view.findViewById(R.id.papayatree);
        sheesham=view.findViewById(R.id.sheesamtree);
        coconut=view.findViewById(R.id.coconuttree);
        eucalyptus=view.findViewById(R.id.eculyptaustree);
        fig=view.findViewById(R.id.figtree);
        teak=view.findViewById(R.id.teaktree);
        birch=view.findViewById(R.id.birchtree);
        confiner=view.findViewById(R.id.confinertree);
        beetalnut=view.findViewById(R.id.beetalnuttree);
        pomegrante=view.findViewById(R.id.pomegrantetree);
        banyan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),WebviewAboutPlants.class);
                intent.putExtra("url","https://en.wikipedia.org/wiki/Banyan");
                startActivity(intent);
            }
        });
        neem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),WebviewAboutPlants.class);
                intent.putExtra("url","https://en.wikipedia.org/wiki/Azadirachta_indica");
                startActivity(intent);
            }
        });
        oak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),WebviewAboutPlants.class);
                intent.putExtra("url","https://en.wikipedia.org/wiki/Oak");
                startActivity(intent);
            }
        });
        sandalwood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),WebviewAboutPlants.class);
                intent.putExtra("url","https://en.wikipedia.org/wiki/Sandalwood");
                startActivity(intent);
            }
        });
        banana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),WebviewAboutPlants.class);
                intent.putExtra("url","https://en.wikipedia.org/wiki/Banana");
                startActivity(intent);
            }
        });
        mango.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),WebviewAboutPlants.class);
                intent.putExtra("url","https://en.wikipedia.org/wiki/Mango");
                startActivity(intent);
            }
        });
        apple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),WebviewAboutPlants.class);
                intent.putExtra("url","https://en.wikipedia.org/wiki/Apple_Inc.");
                startActivity(intent);
            }
        });
        orange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),WebviewAboutPlants.class);
                intent.putExtra("url","https://en.wikipedia.org/wiki/Orange_(fruit)");
                startActivity(intent);
            }
        });
        peepal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),WebviewAboutPlants.class);
                intent.putExtra("url","https://en.wikipedia.org/wiki/Ficus_religiosa");
                startActivity(intent);
            }
        });
        tamarind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),WebviewAboutPlants.class);
                intent.putExtra("url","https://en.wikipedia.org/wiki/Tamarind");
                startActivity(intent);
            }
        });
        papaya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),WebviewAboutPlants.class);
                intent.putExtra("url","https://en.wikipedia.org/wiki/Papaya");
                startActivity(intent);
            }
        });
        sheesham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),WebviewAboutPlants.class);
                intent.putExtra("url","https://en.wikipedia.org/wiki/Dalbergia_sissoo");
                startActivity(intent);
            }
        });
        coconut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),WebviewAboutPlants.class);
                intent.putExtra("url","https://en.wikipedia.org/wiki/Coconut");
                startActivity(intent);
            }
        });
        eucalyptus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),WebviewAboutPlants.class);
                intent.putExtra("url","https://en.wikipedia.org/wiki/Coconut");
                startActivity(intent);
            }
        });
        fig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),WebviewAboutPlants.class);
                intent.putExtra("url","https://en.wikipedia.org/wiki/Coconut");
                startActivity(intent);
            }
        });
        teak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),WebviewAboutPlants.class);
                intent.putExtra("url","https://en.wikipedia.org/wiki/Coconut");
                startActivity(intent);
            }
        });
        birch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),WebviewAboutPlants.class);
                intent.putExtra("url","https://en.wikipedia.org/wiki/Birch");
                startActivity(intent);
            }
        });
        confiner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),WebviewAboutPlants.class);
                intent.putExtra("url","https://en.wikipedia.org/wiki/Conifer");
                startActivity(intent);
            }
        });
        beetalnut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),WebviewAboutPlants.class);
                intent.putExtra("url","https://en.wikipedia.org/wiki/Areca_nut");
                startActivity(intent);
            }
        });
        pomegrante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),WebviewAboutPlants.class);
                intent.putExtra("url","https://en.wikipedia.org/wiki/Pomegranate");
                startActivity(intent);
            }
        });
    }


}