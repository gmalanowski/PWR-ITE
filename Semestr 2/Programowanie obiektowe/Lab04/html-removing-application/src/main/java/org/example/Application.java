package org.example;

public class Application {
    public static void main(String[] args) {
        String siteAddress = args[0];
        String text = PageDownloadingUtility.download(siteAddress);
        text = StringHtmlCleaner.cleanHtml(text);
        System.out.println(text);
    }
}
