package id.ac.umn.uts_14910;

import java.util.Comparator;

public class BookModel {
    private String ASIN, GROUP1, FORMAT, TITLE, AUTHOR, PUBLISHER;
    private String FAVASIN, FAVTITLE, FAVAUTHOR, FAVPUBLISHER;

    public BookModel(String ASIN, String GROUP1, String FORMAT, String TITLE, String AUTHOR, String PUBLISHER) {
        this.ASIN = ASIN;
        this.GROUP1 = GROUP1;
        this.FORMAT = FORMAT;
        this.TITLE = TITLE;
        this.AUTHOR = AUTHOR;
        this.PUBLISHER = PUBLISHER;
    }

    public BookModel(String FAVASIN, String FAVTITLE, String FAVAUTHOR, String FAVPUBLISHER) {
        this.FAVASIN = FAVASIN;
        this.FAVTITLE = FAVTITLE;
        this.FAVAUTHOR = FAVAUTHOR;
        this.FAVPUBLISHER = FAVPUBLISHER;
    }

    public String getFAVASIN() {
        return FAVASIN;
    }

    public void setFAVASIN(String FAVASIN) {
        this.FAVASIN = FAVASIN;
    }

    public String getFAVTITLE() {
        return FAVTITLE;
    }

    public void setFAVTITLE(String FAVTITLE) {
        this.FAVTITLE = FAVTITLE;
    }

    public String getFAVAUTHOR() {
        return FAVAUTHOR;
    }

    public void setFAVAUTHOR(String FAVAUTHOR) {
        this.FAVAUTHOR = FAVAUTHOR;
    }

    public String getFAVPUBLISHER() {
        return FAVPUBLISHER;
    }

    public void setFAVPUBLISHER(String FAVPUBLISHER) {
        this.FAVPUBLISHER = FAVPUBLISHER;
    }

    public String getASIN() {
        return ASIN;
    }

    public void setASIN(String ASIN) {
        this.ASIN = ASIN;
    }

    public String getGROUP1() {
        return GROUP1;
    }

    public void setGROUP1(String GROUP1) {
        this.GROUP1 = GROUP1;
    }

    public String getFORMAT() {
        return FORMAT;
    }

    public void setFORMAT(String FORMAT) {
        this.FORMAT = FORMAT;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getAUTHOR() {
        return AUTHOR;
    }

    public void setAUTHOR(String AUTHOR) {
        this.AUTHOR = AUTHOR;
    }

    public String getPUBLISHER() {
        return PUBLISHER;
    }

    public void setPUBLISHER(String PUBLISHER) {
        this.PUBLISHER = PUBLISHER;
    }

    public static final Comparator<BookModel> BY_TITLE_ASCENDING = new Comparator<BookModel>() {
        @Override
        public int compare(BookModel o1, BookModel o2) {
            return o1.getTITLE().compareTo(o2.getTITLE());
        }
    };

    public static final Comparator<BookModel> BY_TITLE_DESCENDING = new Comparator<BookModel>() {
        @Override
        public int compare(BookModel o1, BookModel o2) {
            return o2.getTITLE().compareTo(o1.getTITLE());
        }
    };

}
