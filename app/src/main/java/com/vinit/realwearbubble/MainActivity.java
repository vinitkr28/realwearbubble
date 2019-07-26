package com.vinit.realwearbubble;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ResponseOnSelectedItem{
    public static final String TAG = MainActivity.class.getName();

    //
    // Intent actions for registering voice commands and for being notified when they are triggered
    //
    public static final String ACTION_OVERRIDE_COMMANDS =
            "com.realwear.wearhf.intent.action.OVERRIDE_COMMANDS";
    public static final String ACTION_SPEECH_EVENT =
            "com.realwear.wearhf.intent.action.SPEECH_EVENT";
    public static final String ACTION_RESTORE_COMMANDS =
            "com.realwear.wearhf.intent.action.RESTORE_COMMANDS";

    // Identifier for the package that is setting the voice commands
    private static final String EXTRA_SOURCE_PACKAGE =
            "com.realwear.wearhf.intent.extra.SOURCE_PACKAGE";

    // Identifier used when registering voice commands
    private static final String EXTRA_COMMANDS =
            "com.realwear.wearhf.intent.extra.COMMANDS";

    // Identifier for when a voice command is triggered
    private static final String EXTRA_RESULT = "command";

    private RecyclerView recyclerView;
    public ArrayList<?> arrayList;
    private RecyclerView.Adapter recentSearchAdapter;
    private ArrayList<?> arrayListForAdapter;

    private TextView textViewActionbarHeading, textViewGoToPage;

    private int noOfPage, noOfRows, startIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewActionbarHeading = findViewById(R.id.text_view_actionbar_heading);
        textViewGoToPage = findViewById(R.id.text_view_go_to_page);

        noOfRows = 4;
        arrayList = TempDataCollection.getDocumentModels();
        loadRegularSearchedDocuments();
        textViewActionbarHeading.setText(getResources().getString(R.string.display_content_search_results_page) + noOfPage);
    }

    @Override
    protected void onResume() {
        super.onResume();

        registerReceiver(asrBroadcastReceiver, new IntentFilter(ACTION_SPEECH_EVENT));
        sendCommands("Go to Page", noOfPage);
    }

    /**
     * Called when activity is paused - See Android docs
     */
    @Override
    protected void onPause() {
        super.onPause();

        if (asrBroadcastReceiver != null) {
            unregisterReceiver(asrBroadcastReceiver);
        }

        Intent intent = new Intent(ACTION_RESTORE_COMMANDS);
        intent.putExtra(EXTRA_SOURCE_PACKAGE, getPackageName());
        sendBroadcast(intent);
    }

    @Override
    public void onDocumentViewItemClickedFromSearch(int documentTypeSerialNo) {
        Log.i(TAG, "Adapter documentTypeSerialNo " + documentTypeSerialNo + " | " + ((ArrayList<DocumentModel>) arrayList).get(documentTypeSerialNo-1).toString());
    }

    @Override
    public void onFavouriteClickedFromSearch(int documentTypeSerialNo) {
        ((ArrayList<DocumentModel>) arrayList).get(documentTypeSerialNo - 1).setMarkedAsFavourite(!((ArrayList<DocumentModel>) arrayList).get(documentTypeSerialNo-1).isMarkedAsFavourite());
        arrayListForAdapter = new ArrayList<>(((ArrayList<DocumentModel>) arrayList).subList(startIndex, (startIndex + noOfRows) > arrayList.size() ? arrayList.size() : (startIndex + noOfRows)));
        ((SearchedDocumentAdapter) recentSearchAdapter).notifyData((ArrayList<DocumentModel>) arrayListForAdapter);

        for (DocumentModel documentModel : ((ArrayList<DocumentModel>) arrayList)) {
            Log.i(TAG, "Adapter documentTypeSerialNo " + documentTypeSerialNo + " | " + documentModel.toString());
        }
    }

    @Override
    public void onDownloadClickedFromSearch(int documentTypeSerialNo) {
        ((ArrayList<DocumentModel>) arrayList).get(documentTypeSerialNo - 1).setDownloaded(!((ArrayList<DocumentModel>) arrayList).get(documentTypeSerialNo-1).isDownloaded());
        arrayListForAdapter = new ArrayList<>(((ArrayList<DocumentModel>) arrayList).subList(startIndex, (startIndex + noOfRows) > arrayList.size() ? arrayList.size() : (startIndex + noOfRows)));
        ((SearchedDocumentAdapter) recentSearchAdapter).notifyData((ArrayList<DocumentModel>) arrayListForAdapter);

        for (DocumentModel documentModel : ((ArrayList<DocumentModel>) arrayList)) {
            Log.i(TAG, "Adapter documentTypeSerialNo " + documentTypeSerialNo + " | " + documentModel.toString());
        }
    }

    /**
     * Broadcast receiver for being notified when voice commands are triggered
     */
    private BroadcastReceiver asrBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (action.equals(ACTION_SPEECH_EVENT)) {

                String noSpaceStr = intent.getStringExtra(EXTRA_RESULT).replaceAll("\\s", "");

                String intFromString = noSpaceStr.substring(8, noSpaceStr.length());

                startIndex = noOfRows * ((Integer.valueOf(intFromString)) - 1);

                arrayListForAdapter = new ArrayList<>(((ArrayList<DocumentModel>) arrayList).subList(startIndex, (startIndex + noOfRows) > arrayList.size() ? arrayList.size() : (startIndex + noOfRows)));
                ((SearchedDocumentAdapter) recentSearchAdapter).notifyData((ArrayList<DocumentModel>) arrayListForAdapter);
                textViewActionbarHeading.setText("Search Results: Page " + intFromString + " of " + noOfPage);


                if (intent.hasExtra(EXTRA_COMMANDS))
                    Log.i(TAG, "EXTRA_COMMANDS: " + intent.getStringExtra(EXTRA_COMMANDS));

                sendCommands("Go to Page", noOfPage);
            }
        }
    };

    private void sendCommands(String initialCommand, int toThePage) {
        String commands[] = new String[toThePage];
        for (int i = 0; i < commands.length; i++) {
            commands[i] = initialCommand + " " + (i + 1);
        }

        Intent intent = new Intent(ACTION_OVERRIDE_COMMANDS);
        intent.putExtra(EXTRA_SOURCE_PACKAGE, getPackageName());
        intent.putExtra(EXTRA_COMMANDS, commands);
        sendBroadcast(intent);
    }

    private void loadRegularSearchedDocuments() {
        noOfPage = (int) Math.ceil((double) arrayList.size() / noOfRows);
        textViewGoToPage.setText("Go to Page 1..." + noOfPage);

        arrayListForAdapter = new ArrayList<>(((ArrayList<DocumentModel>) arrayList).subList(0, noOfRows));

        recyclerView = findViewById(R.id.recycler_view_searched_content);
        recentSearchAdapter = new SearchedDocumentAdapter(this, (ArrayList<DocumentModel>) arrayListForAdapter, this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recentSearchAdapter);
    }
}
