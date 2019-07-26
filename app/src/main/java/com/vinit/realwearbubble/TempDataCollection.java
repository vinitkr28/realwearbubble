package com.vinit.realwearbubble;

import java.util.ArrayList;

public class TempDataCollection {
    private static final String TAG = TempDataCollection.class.getName();

    public static ArrayList<DocumentModel> getDocumentModels() {
        ArrayList<DocumentModel> documentModels = new ArrayList<>();

        for (int a = 1; a < 200; a++) {
            if ((a % 3) == 0) {
                DocumentModel documentModel = new DocumentModel(a, "DocumentModel " + a);
                documentModel.setDownloaded(true);
                documentModel.setMarkedAsFavourite(false);
                documentModel.setStrDocumentPreview("This guid provides information that will assist you in planning and desigining activities, as well as the");
                documentModel.setStrAuthor("John Doe");
                documentModel.setStrLastModified("9 May 2019 at 2:15 PM");
                documentModels.add(documentModel);

            } else if ((a % 5) == 0) {
                DocumentModel documentModel = new DocumentModel(a, "DocumentModel " + a);
                documentModel.setDownloaded(false);
                documentModel.setMarkedAsFavourite(true);
                documentModel.setStrFileSize("1.1 MB");
                documentModel.setStrVersion("11.2.3");
                documentModel.setStrDocumentPreview("This document describes how to use the CL Server on the network from a server perspective. It does not");
                documentModels.add(documentModel);
            } else if ((a % 7) == 0) {
                DocumentModel documentModel = new DocumentModel(a, "DocumentModel " + a);
                documentModel.setDownloaded(false);
                documentModel.setMarkedAsFavourite(false);
                documentModel.setStrAuthor("Pandya P");
                documentModel.setStrFileSize(a + ".4 MB");
                documentModel.setStrVersion(a + ".2.3");
                documentModel.setStrLastModified("14 May 2019 at 2:15 PM");
                documentModel.setStrDocumentPreview("This document describes how to use the CL Server on the network from a server perspective. It does not");
                documentModels.add(documentModel);
            } else {
                DocumentModel documentModel = new DocumentModel(a, "DocumentModel " + a);
                documentModel.setDownloaded(true);
                documentModel.setMarkedAsFavourite(true);
                documentModel.setStrAuthor("Virat K");
                documentModel.setStrLastModified("14 June 2019 at 2:15 PM");
                documentModel.setStrDocumentPreview("This document describes how to use the something to test something when something is there but don't now something is missing or not. Basically something not need to test because something is already done");
                documentModels.add(documentModel);
            }
        }
        return documentModels;
    }
}
