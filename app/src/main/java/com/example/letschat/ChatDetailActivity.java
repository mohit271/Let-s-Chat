package com.example.letschat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.letschat.Adapters.ChatAdapter;
import com.example.letschat.Model.MessageModel;
import com.example.letschat.databinding.ActivityChatDetailBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

public class ChatDetailActivity extends AppCompatActivity {

    ActivityChatDetailBinding binding;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityChatDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();
        firebaseDatabase= FirebaseDatabase.getInstance();
        mAuth=FirebaseAuth.getInstance();

        final String senderId =mAuth.getUid();
        String receiverId= getIntent().getStringExtra("userId");
        String userName=getIntent().getStringExtra("userName");
        String userPic=getIntent().getStringExtra("userPic");

        binding.userNameChat.setText(userName);
        Picasso.get().load(userPic).placeholder(R.drawable.userpic).into(binding.profileImageChat);
           binding.backToList.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent= new Intent(ChatDetailActivity.this,MainActivity.class);
                   startActivity(intent);
               }
           });

           final ArrayList<MessageModel> messageModels = new ArrayList<>();
           final ChatAdapter chatAdapter= new ChatAdapter(messageModels,this);
           binding.rvChatPersonal.setAdapter(chatAdapter);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        binding.rvChatPersonal.setLayoutManager(layoutManager );

        final String senderRoom = senderId+receiverId;
        final String receiverRoom = receiverId+senderId;

        firebaseDatabase.getReference().child("Chats").child(senderRoom)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        messageModels.clear();
                        for(DataSnapshot snapshot1:snapshot.getChildren()){
                            MessageModel model1=snapshot1.getValue(MessageModel.class);
                            messageModels.add(model1);
                        }
                        chatAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            binding.btnSendMessage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String message = binding.etTextMessage.getText().toString().trim();
                    //Toast.makeText(ChatDetailActivity.this, message, Toast.LENGTH_SHORT).show();
                    if(!message.equals("")) {
                        MessageModel model = new MessageModel(senderId, message);
                        model.setTimeStamp(new Date().getTime());
                        binding.etTextMessage.setText("");
                        firebaseDatabase.getReference().child("Chats").child(senderRoom).push()
                                .setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                firebaseDatabase.getReference().child("Chats").child(receiverRoom).push()
                                        .setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {

                                    }
                                });
                            }
                        });
                    }
                }
            });

    }

}