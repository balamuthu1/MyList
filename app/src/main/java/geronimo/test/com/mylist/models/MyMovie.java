package geronimo.test.com.mylist.models;

/**
 * Created by Muthu on 11/02/2018.
 */

public class MyMovie {
    private int imgSrc;
    private String title, subtitle;

    public MyMovie(int imgSrc, String title, String subtitle){
        this.imgSrc = imgSrc;
        this.title = title;
        this.subtitle = subtitle;
    }

    public int getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(int imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
}
