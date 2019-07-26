package com.vinit.realwearbubble;

public interface ResponseOnSelectedItem {
    void onDocumentViewItemClickedFromSearch(int documentTypeSerialNo);
    void onFavouriteClickedFromSearch(int documentTypeSerialNo);
    void onDownloadClickedFromSearch(int documentTypeSerialNo);
}
