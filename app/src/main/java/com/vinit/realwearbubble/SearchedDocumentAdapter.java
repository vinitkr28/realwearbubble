package com.vinit.realwearbubble;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;

public class SearchedDocumentAdapter extends RecyclerView.Adapter<SearchedDocumentAdapter.ViewHolder> {
    private ArrayList<DocumentModel> documentModels;
    private ResponseOnSelectedItem responseOnSelectedItem;
    private Context context;

    public SearchedDocumentAdapter(Context context, ArrayList<DocumentModel> documentModels, ResponseOnSelectedItem responseOnSelectedItem){
        this.context = context;
        this.documentModels = documentModels;
        this.responseOnSelectedItem = responseOnSelectedItem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.list_row_documents, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final DocumentModel documentModel = documentModels.get(position);

        holder.documentSerialNo.setText(documentModel.getSerialNo() + ".");
        holder.documentName.setText(documentModel.getStrDocumentName());
        holder.documentPreview.setText(documentModel.getStrDocumentPreview());
        holder.documentView.setText("View Item " + documentModel.getSerialNo());
        holder.documentView.setClickable(true);
        if (documentModel.isMarkedAsFavourite()) {
            holder.favourireUnfavourire.setText("Unfavourite Item " + documentModel.getSerialNo());
//            holder.documentFileSize.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_unfavourite, 0, 0, 0);
        } else {
            holder.favourireUnfavourire.setText("Favourite Item " + documentModel.getSerialNo());
        }
        if (documentModel.isDownloaded()) {
            holder.downloadDelete.setText("Delete Item " + documentModel.getSerialNo());
        } else {
            holder.downloadDelete.setText("Download Item " + documentModel.getSerialNo());
        }

        holder.documentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                responseOnSelectedItem.onDocumentViewItemClickedFromSearch(documentModel.getSerialNo());
            }
        });

        holder.favourireUnfavourire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                responseOnSelectedItem.onFavouriteClickedFromSearch(documentModel.getSerialNo());
            }
        });

        holder.downloadDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                responseOnSelectedItem.onDownloadClickedFromSearch(documentModel.getSerialNo());
            }
        });
    }

    @Override
    public int getItemCount() {
        return documentModels.size();
    }

    public void notifyData(ArrayList<DocumentModel> myList) {
        this.documentModels = myList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView documentSerialNo, documentName, documentPreview, documentView, favourireUnfavourire, downloadDelete;


        public ViewHolder(View view){
            super(view);
            this.documentSerialNo = view.findViewById(R.id.text_view_document_serial_no);
            this.documentName = view.findViewById(R.id.text_view_document_searched_text);
            this.documentPreview = view.findViewById(R.id.text_view_document_previewed_text);
            this.documentView = view.findViewById(R.id.text_view_document_view_item);
            this.favourireUnfavourire = view.findViewById(R.id.text_view_document_mark_favourite);
            this.downloadDelete = view.findViewById(R.id.text_view_document_mark_download);
        }
    }
}

