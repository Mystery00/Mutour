package com.weily.mutour.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mystery0.ipicturechooser.iPictureChooser;
import com.mystery0.ipicturechooser.iPictureChooserListener;
import com.weily.mutour.R;

import java.util.ArrayList;

public class NewPostActivity extends AppCompatActivity
{
    private static final String TAG = "NewPostActivity";
    private static final int PERMISSION = 322;
    private Toolbar toolbar;
    private TextInputLayout book_name;
    private TextInputLayout book_price;
    private TextInputLayout book_contact;
    private TextInputLayout book_remarks;
    private iPictureChooser pictureChooser;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        checkPermission();
        initialize();
        monitor();
    }

    private void initialize()
    {
        setContentView(R.layout.activity_new_post);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        book_name = (TextInputLayout) findViewById(R.id.text_book_name);
        book_price = (TextInputLayout) findViewById(R.id.text_book_price);
        book_contact = (TextInputLayout) findViewById(R.id.text_book_contact);
        book_remarks = (TextInputLayout) findViewById(R.id.text_book_remarks);
        pictureChooser = (iPictureChooser) findViewById(R.id.picture_chooser);

        pictureChooser.setDataList(new ArrayList<String>(), new iPictureChooserListener()
        {
            @Override
            public void MainClick()
            {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, iPictureChooser.REQUEST_IMG_CHOOSE);
            }
        });

        setSupportActionBar(toolbar);
    }

    @SuppressWarnings("ConstantConditions")
    private void monitor()
    {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });
        setError(book_name);
        setError(book_price);
        setError(book_contact);
        setError(book_remarks);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.new_post, menu);
        return true;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_done:
                Log.i(TAG, "onOptionsItemSelected: book_name:" + book_name.getEditText().getText().toString());
                Log.i(TAG, "onOptionsItemSelected: book_price:" + book_price.getEditText().getText().toString());
                Log.i(TAG, "onOptionsItemSelected: book_contact:" + book_contact.getEditText().getText().toString());
                Log.i(TAG, "onOptionsItemSelected: book_remarks:" + book_remarks.getEditText().getText().toString());
                if (inputFormat(
                        new TextInputLayout[]{book_name, book_price, book_contact, book_remarks},
                        new String[]{"error name", "error price", "error contact", "error remarks"}
                ))
                {
                    Log.i(TAG, "onOptionsItemSelected: done");
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean inputFormat(TextInputLayout[] texts, String[] error_text)
    {
        if (texts.length != error_text.length)
        {
            return false;
        }
        int length = texts.length;
        int index = length;
        for (int i = 0; i < length; i++)
        {
            //noinspection ConstantConditions
            if (texts[i].getEditText().getText().length() == 0)
            {
                texts[i].setError(error_text[i]);
                index--;
            }
        }
        return length == index;
    }

    private void setError(final TextInputLayout textInputLayout)
    {
        //noinspection ConstantConditions
        textInputLayout.getEditText().addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                textInputLayout.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s)
            {
            }
        });
    }

    private void checkPermission()
    {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission_group.STORAGE)
                != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission_group.STORAGE}, PERMISSION);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch (requestCode)
        {
            case iPictureChooser.REQUEST_IMG_CHOOSE:
                Uri uri = data.getData();
                if (uri != null)
                {
                    pictureChooser.setUpdatedPicture(uri);
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION)
        {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED)
            {
                Log.i(TAG, "onRequestPermissionsResult: 权限拒绝");
                finish();
            }
        }
    }
}