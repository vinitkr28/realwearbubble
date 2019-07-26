package com.vinit.realwearbubble;

public class DocumentModel {
    private int serialNo;
    private String strDocumentName;
    private String strDocumentPreview;
    private String strAuthor;
    private String strLastModified;
    private String strFileSize;
    private String strVersion;
    private String strRepositoryPath;
    private String strDocumentExtension;
    private String strDate;
    private int zoomLevel;
    private int currentPage;
    private boolean isDownloaded;
    private boolean isMarkedAsFavourite;

    public DocumentModel(int serialNo, String strDocumentName,
                         String strDocumentPreview, String strAuthor,
                         String strLastModified, String strFileSize,
                         String strVersion, String strRepositoryPath,
                         String strDocumentExtension, String strDate,
                         int zoomLevel, int currentPage, boolean isDownloaded,
                         boolean isMarkedAsFavourite) {
        this.serialNo = serialNo;
        this.strDocumentName = strDocumentName;
        this.strDocumentPreview = strDocumentPreview;
        this.strAuthor = strAuthor;
        this.strLastModified = strLastModified;
        this.strFileSize = strFileSize;
        this.strVersion = strVersion;
        this.strRepositoryPath = strRepositoryPath;
        this.strDocumentExtension = strDocumentExtension;
        this.strDate = strDate;
        this.zoomLevel = zoomLevel;
        this.currentPage = currentPage;
        this.isDownloaded = isDownloaded;
        this.isMarkedAsFavourite = isMarkedAsFavourite;
    }

    public DocumentModel(int serialNo, String strDocumentName) {
        this.serialNo = serialNo;
        this.strDocumentName = strDocumentName;
    }

    public int getSerialNo() {
        return serialNo;
    }

    public String getStrDocumentName() {
        return strDocumentName;
    }

    public String getStrDocumentPreview() {
        return strDocumentPreview;
    }

    public void setStrDocumentPreview(String strDocumentPreview) {
        this.strDocumentPreview = strDocumentPreview;
    }

    public String getStrAuthor() {
        return strAuthor;
    }

    public void setStrAuthor(String strAuthor) {
        this.strAuthor = strAuthor;
    }

    public String getStrLastModified() {
        return strLastModified;
    }

    public void setStrLastModified(String strLastModified) {
        this.strLastModified = strLastModified;
    }

    public String getStrFileSize() {
        return strFileSize;
    }

    public void setStrFileSize(String strFileSize) {
        this.strFileSize = strFileSize;
    }

    public String getStrVersion() {
        return strVersion;
    }

    public void setStrVersion(String strVersion) {
        this.strVersion = strVersion;
    }

    public String getStrRepositoryPath() {
        return strRepositoryPath;
    }

    public void setStrRepositoryPath(String strRepositoryPath) {
        this.strRepositoryPath = strRepositoryPath;
    }

    public String getStrDocumentExtension() {
        return strDocumentExtension;
    }

    public void setStrDocumentExtension(String strDocumentExtension) {
        this.strDocumentExtension = strDocumentExtension;
    }

    public String getStrDate() {
        return strDate;
    }

    public void setStrDate(String strDate) {
        this.strDate = strDate;
    }

    public int getZoomLevel() {
        return zoomLevel;
    }

    public void setZoomLevel(int zoomLevel) {
        this.zoomLevel = zoomLevel;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public boolean isDownloaded() {
        return isDownloaded;
    }

    public void setDownloaded(boolean downloaded) {
        isDownloaded = downloaded;
    }

    public boolean isMarkedAsFavourite() {
        return isMarkedAsFavourite;
    }

    public void setMarkedAsFavourite(boolean markedAsFavourite) {
        isMarkedAsFavourite = markedAsFavourite;
    }

    @Override
    public String toString() {
        return "serialNo " + serialNo + ", strDocumentName " + strDocumentName + ", strDocumentPreview " + strDocumentPreview +
                ", strAuthor " + strAuthor + ", strLastModified " + strLastModified + ", strFileSize " + strFileSize +
                ", strVersion "+ strVersion + ", strRepositoryPath " + strRepositoryPath + ", strDocumentExtension" + strDocumentExtension +
                ", strDate " + strDate + ", zoomLevel " + zoomLevel + ", currentPage " + currentPage + ", isDownloaded " + isDownloaded +
                ", isMarkedAsFavourite " + isMarkedAsFavourite;
    }
}
