package com.example.gmail;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private LinkedList<Inbox> inboxList = new LinkedList<>();
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private InboxAdapter adapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addData();


        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new InboxAdapter(this, inboxList);
        recyclerView.setAdapter(adapter);
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(mDividerItemDecoration);

        setSupportActionBar(toolbar);
    }

    private void addData() {
        inboxList.add(new Inbox("GitHub", "[GitHub] A new public key was added to your account", "The following SSH key was added to your account. If you believe this key was added in error, you can remove the key and disable access at the", "2:57 PM", false));
        inboxList.add(new Inbox("Team Infura", "What we learned at Devconnect", "Highlights from Devconnect, sign up for Infura X Austin Griffith Office Hours, and more! infura-1 Hi Infura fam! Time passes when you're busy building the next evolution of the Web! It's time", "May 12", false));
        inboxList.add(new Inbox("Sweatcoin", "SWEAT ??? our crypto, is here! Claim yours now.", "Free crypto for steps | Don't miss our launch offer. One free SWEAT for every sweatcoin you hold. Hi Luong, Since we started Sweatcoin in 2016, we knew crypto was in our future. We knew it could", "May 1", true));
        inboxList.add(new Inbox("Grab Vi???t Nam", "B???n Ho??ng Minh L????ng ??i. T???n h?????ng ??u ????i t??? Grab nh??", "Ki???m tra ??u ????i c???a tu???n n??y ngay", "Apr 26", false));
        inboxList.add(new Inbox("MBS Online", "B???n ???? bi???t c??ch nh???n 1OOK + Margin 10% t??? MBS ch??a?", "K??nh g???i Nh?? ?????u T?? HO??NG MINH L????NG ????? nh???n 100k t??? MBS cho giao d???ch ?????u ti??n v???i kh??ch h??ng m??? t??i kho???n qua app MB Bank t??? 9/2020 - 4/2022, Nh?? ?????u T?? vui l??ng th???c hi???n theo c??c h?????ng d???n", "Apr 21", true));
        inboxList.add(new Inbox("Grammarly", "You + Grammarly = Ready for Action", "Grammarly Grammarly Welcome to Grammarly Today's a great day! By signing up for Grammarly, you're getting a tool that will help you connect with the people around you through better, clearer", "Apr 12", true));
        inboxList.add(new Inbox("Grab", "Your Grab E-Receipt", "Ch??c b???n ngon mi???ng! T???ng c???ng VND 34750 Ng??y | Gi??? 11 Apr 22 13:05 +0700 CHI TI???T ?????T XE T??i x??? c???a b???n Nguy???n V??n Huy Ng?????i d??ng Ho??ng Minh L????ng M?? ?????t xe A-3BWL54EGWEDW ??i???m ????n kh??ch: C??m T???m", "Apr 11", false));
        inboxList.add(new Inbox("MongoDB Team", "Cluster created ", "Here's what comes next. Great work Minh! You are just 4 steps away from building your app. 5 steps 1) Create your cluster 2) Manage database access 3) Add database users 4) Load sample data 5)", "Apr 4", false));
    }

}