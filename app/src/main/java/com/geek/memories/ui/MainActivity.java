package com.geek.memories.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.geek.memories.databinding.ActivityMainBinding;
import com.geek.memories.domain.CustomContent;
import com.geek.memories.ui.adapters.EmojiAdapter;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private EmojiAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        adapter = new EmojiAdapter(new EmojiAdapter.OnItemClickListener() {
            @Override
            public void choose() {
                runOnUiThread(() -> {
                    adapter.notifyDataSetChanged();
                    binding.rvCards.setVisibility(View.INVISIBLE);
                    binding.tvGameOver.setVisibility(View.VISIBLE);
                });
            }

            @Override
            public void onNotify() {
                runOnUiThread(() -> adapter.notifyDataSetChanged());
            }
        });
        binding.rvCards.setAdapter(adapter);

        /*CustomContent customContent = new CustomContent(1, "card1", 1.0);
        CustomContent customContent1 = new CustomContent(1, "card1", 1.0);
        boolean isMatch = customContent1.equals(customContent);
        Log.e("TAG", "onCreate: "+ isMatch);*/
    }
}