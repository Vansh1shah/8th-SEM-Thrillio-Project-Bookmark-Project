package entities;

import partner.Shareable;

public class WebLink extends Bookmark implements Shareable {

    private String url;

    private String host;

    private String htmlPage;


    private DownloadStatus downloadStatus = DownloadStatus.NOT_ATTEMPTED;

    public Object getDownloadStatus() {
        return downloadStatus;
    }

    public void setDownloadStatus(DownloadStatus downloadStatus) {
        this.downloadStatus = downloadStatus;
    }



    public enum DownloadStatus {
        NOT_ATTEMPTED,
        SUCCESS,
        FAILED,
        NOT_ELIGIBLE;  // not eligible for download
    }

    @Override
    public String toString() {
        return "WebLink{" +
                "url='" + url + '\'' +
                ", host='" + host + '\'' +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getHtmlPage() {
        return htmlPage;
    }


    public void setHtmlPage(String htmlPage) {
        this.htmlPage = htmlPage;
    }

    @Override
    public boolean isKidFriendelyEligible() {

        if(url.contains("porn") || getTitle().contains("porn") || host.contains("adult")) {
            return false;
        }
        return true;
    }

    public String getItemData() {
        StringBuilder builder = new StringBuilder();
        builder.append("<item>");
        builder.append("<type>WebLink</type>");
        builder.append("<title>").append(getTitle()).append("</title");
        builder.append("<url>").append(url).append("</url");
        builder.append("<host>").append(host).append("</host");
        builder.append("</item>");
        return builder.toString();
    }
}
