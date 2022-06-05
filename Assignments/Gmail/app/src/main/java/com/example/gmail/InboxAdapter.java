package com.example.gmail;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.Random;

public class InboxAdapter extends RecyclerView.Adapter<InboxAdapter.InboxViewHolder> {
    private final LinkedList<Inbox> inboxList;
    private LayoutInflater inflater;

    public InboxAdapter(Context context, LinkedList<Inbox> inboxList) {
        inflater = LayoutInflater.from(context);
        this.inboxList = inboxList;
    }

    @NonNull
    @Override
    public InboxViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = inflater.inflate(R.layout.inbox_item, parent, false);
        return new InboxViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull InboxViewHolder holder, int position) {
        Inbox inbox = inboxList.get(position);
        holder.image.setText(String.valueOf(inbox.getSender().charAt(0)));
        Random rnd = new Random();
        holder.itemView.getResources()
                .getDrawable(R.drawable.circle).setTint(Color.argb(255,
                        rnd.nextInt(256),
                        rnd.nextInt(256),
                        rnd.nextInt(256)));
        holder.image.setBackground(
                holder.itemView.getResources()
                        .getDrawable(R.drawable.circle));
        holder.sender.setText(inbox.getSender());
        holder.title.setText(inbox.getTitle());
        holder.content.setText(inbox.getContent());
        holder.time.setText(inbox.getTime());
        holder.favorite.setImageDrawable(holder.itemView.getResources()
                .getDrawable(inbox.isFavorite()
                        ? R.drawable.ic_star : R.drawable.ic_star_outline));
        holder.favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inbox.setFavorite(!inbox.isFavorite());
                holder.favorite.setImageDrawable(holder.itemView.getResources()
                        .getDrawable(inbox.isFavorite()
                                ? R.drawable.ic_star : R.drawable.ic_star_outline));
            }
        });
    }

    @Override
    public int getItemCount() {
        return inboxList.size();
    }

    class InboxViewHolder extends RecyclerView.ViewHolder {
        public final TextView image;
        public final TextView sender;
        public final TextView title;
        public final TextView content;
        public final TextView time;
        public final ImageView favorite;
        final InboxAdapter adapter;

        public InboxViewHolder(@NonNull View itemView, InboxAdapter adapter) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            sender = itemView.findViewById(R.id.sender);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
            time = itemView.findViewById(R.id.time);
            favorite = itemView.findViewById(R.id.favorite);
            this.adapter = adapter;
        }
    }
}
