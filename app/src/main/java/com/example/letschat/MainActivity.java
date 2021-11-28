package com.example.letschat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.letschat.Adapters.FragmentAdapter;
import com.example.letschat.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        mAuth=FirebaseAuth.getInstance();
        binding.viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager()));
        binding.tabLayout.setupWithViewPager(binding.viewPager);

        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#039be5"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.setting){
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);

        }
        else if(item.getItemId()==R.id.logOut){
            mAuth.signOut();
            Intent intent = new Intent(this,SignInActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Log Out Successful", Toast.LENGTH_SHORT).show();
            //TODO
            // Deleting firebase instance on logout
        }
        else if(item.getItemId()==R.id.groupChat){
           // Intent intent = new Intent(this,GroupChatActivity.class);
            //startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}