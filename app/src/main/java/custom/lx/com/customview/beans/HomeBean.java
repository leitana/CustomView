package custom.lx.com.customview.beans;

import java.io.Serializable;

/**
 * Created by 11300 on 2017/7/18.
 */

public class HomeBean implements Serializable{
    private String title;
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
