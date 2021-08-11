package com.geek.memories.ui.adapters;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geek.memories.databinding.ItemCardBinding;
import com.geek.memories.domain.Card;
import com.geek.memories.ui.EmojiGame;

import java.util.Timer;
import java.util.TimerTask;

public class EmojiAdapter extends RecyclerView.Adapter<EmojiAdapter.ViewHolder> {
    private EmojiGame emojiGame = new EmojiGame();
    private OnItemClickListener listener;

    public EmojiAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public EmojiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemCardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EmojiAdapter.ViewHolder holder, int position) {
        holder.onBind(emojiGame.getCards().get(position));

    }

    @Override
    public int getItemCount() {
        return emojiGame.getCards().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemCardBinding binding;

        public ViewHolder(@NonNull ItemCardBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void onBind(Card<String> card) {
            if (card.isFaceUp()) {
                binding.tvCard.setText(card.getContent());
                binding.tvCard.setBackgroundColor(Color.WHITE);
            } else {
                binding.tvCard.setText("");
                binding.tvCard.setBackgroundColor(Color.BLUE);
            }
            itemView.setOnClickListener(v -> {
                emojiGame.choose(card);
                notifyDataSetChanged();
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        emojiGame.checkPairs(card);
                        Log.e("size", "run: " + emojiGame.getCards().size());
                        if (emojiGame.removeMatchedCards() == emojiGame.getCards().size()){
                            listener.choose();
                        }
                        listener.onNotify();
                    }
                },500);
            });
        }
    }

    public interface OnItemClickListener {
        void choose();

        void onNotify();
    }
}
