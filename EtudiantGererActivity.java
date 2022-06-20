package com.example.projet2022_212052022;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class EtudiantGererActivity extends AppCompatActivity {
    DataBaseEtudhelp  helpdatabase;
    Button bafficheEtudiant,brechercheEtudiant,bmodifierEtudiant,bajouterEtudiant;
    EditText codetud,nometud,notetud;
    ListView listviewetudiant;
    ArrayList<String> T;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etudiant_gerer);
        T=new ArrayList<String>();
        helpdatabase=new DataBaseEtudhelp(this.getApplicationContext());
        bafficheEtudiant=findViewById(R.id.bafficheEtudiant);
        brechercheEtudiant=findViewById(R.id.brechercheEtudiant);
        bmodifierEtudiant =findViewById(R.id.bmodifierEtudiant);
        bajouterEtudiant=findViewById(R.id.bajouterEtudiant);
        codetud=findViewById(R.id.codetud);
        nometud=findViewById(R.id.nometud);
        notetud=findViewById(R.id.notetud);
        listviewetudiant=findViewById(R.id.listviewetudiant);
        //commence par ajouter
        bajouterEtudiant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code=codetud.getText().toString();
                String nom=nometud.getText().toString();
                String note=notetud.getText().toString();
                String sql="insert into etudiant values("+code+","+
                        "'"+nom+"',"+note+")";
                helpdatabase.getWritableDatabase().execSQL(sql);
                AfficherMessage("etudiant ajouter");
            }
        });

        brechercheEtudiant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sql="select * from etudiant where code="+codetud.getText().toString();

             Cursor c= helpdatabase.getWritableDatabase().rawQuery(sql,null,
                        null);
             if(c.getCount()!=0) {
                 while (c.moveToNext()) {
                        nometud.setText(c.getString(1));
                        notetud.setText(c.getString(2));
                 }
                 c.close();
             }
            }
        });

        bmodifierEtudiant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sql="update etudiant set nom='"+nometud.getText().toString()+
                        "',  note="+notetud.getText().toString()+
                        "  where code="+codetud.getText().toString();

                helpdatabase.getWritableDatabase().execSQL(sql);

                AfficherMessage("modifier");
            }
        });

        bafficheEtudiant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                T.clear();
                String sql="select * from etudiant ";

                Cursor c= helpdatabase.getWritableDatabase().rawQuery(sql,null,
                        null);

                    while (c.moveToNext()) {
                      String ligne=c.getString(0)+","+c.getString(1)+
                              ","+c.getString(2);
                      T.add(ligne);


                    }
                    c.close();
                    //passer listeview
                    ArrayAdapter<String> adpt=new ArrayAdapter<String>(
                            EtudiantGererActivity.this.getApplicationContext()
                        , R.layout.ligneetudiant,T);
                    listviewetudiant.setAdapter(adpt);
                    //vider liste

                }

        });

    }

    public void AfficherMessage(String message){
        Toast.makeText(this.getApplicationContext(),
                message,Toast.LENGTH_SHORT).show();
    }
}