package id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.ui.play;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;

import id.ac.ui.cs.mobileprogramming.yusuftriardho.twofastfingers.R;

public class ContactFragment extends Fragment implements AdapterView.OnItemClickListener {

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;

    private ArrayList<String> contacts;
    private TextView notesBox;
    private ListView listView;

    public static ContactFragment newInstance() {
        return new ContactFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contacts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        notesBox = getView().findViewById(R.id.fragment_contacts_notes);
        listView = getView().findViewById(R.id.listContact);

        requestReadContact();

        try {
            fetchContact();
            if (contacts.size() == 0) {
                listView.setVisibility(View.GONE);
                notesBox.setVisibility(View.VISIBLE);
                notesBox.setText(R.string.no_contact_found);
            }
        } catch (Exception e) {
            listView.setVisibility(View.GONE);
            notesBox.setVisibility(View.VISIBLE);
            notesBox.setText(R.string.read_contact_per_disabled);
        }

        Button backBtn = getView().findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.PlayActivity, ResultFragment.newInstance())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void fetchContact() {
        contacts = new ArrayList<>();
        ContentResolver resolver = getActivity().getContentResolver();

        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        String[] projection = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = null;
        Cursor cursor = resolver.query(uri, projection, selection, selectionArgs, sortOrder);

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String num = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            Log.i("fetch contact", "Name: " + name + " - Num: " + num);
            contacts.add(name + "\n" + num);
        }

        listView.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, contacts));
        listView.setOnItemClickListener(this);
    }

    private void requestReadContact() {
        if (!isPermissionChecked()) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            getFragmentManager().beginTransaction()
                    .replace(R.id.PlayActivity, PlayFragment.newInstance())
                    .replace(R.id.PlayActivity, ResultFragment.newInstance())
                    .addToBackStack(null)
                    .commit();
        }
    }

    private boolean isPermissionChecked() {
        return (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        PlayViewModel pViewModel = ViewModelProviders.of(getActivity()).get(PlayViewModel.class);
        String name = contacts.get(position).split("\n")[0];
        String numPhone = contacts.get(position).split("\n")[1];
        String msg = String.format(getString(R.string.share_msg), name, pViewModel.getCurrentScore());

        Intent i = new Intent(android.content.Intent.ACTION_VIEW);
        i.putExtra("address", numPhone);
        i.putExtra("sms_body", msg);
        i.setType("vnd.android-dir/mms-sms");

        startActivity(i);

    }

}