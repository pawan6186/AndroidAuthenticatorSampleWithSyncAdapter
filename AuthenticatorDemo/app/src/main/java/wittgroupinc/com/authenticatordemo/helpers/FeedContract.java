package wittgroupinc.com.authenticatordemo.helpers;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Pawan Gupta on 02-03-2017.
 */
public class FeedContract {
    private FeedContract() {
    }
    public static final String CONTENT_AUTHORITY = "wittgroupinc.com.authenticatordemo.basicsyncadapter";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    private static final String PATH_ENTRIES = "entries";
    public static class Entry implements BaseColumns {
        /**
         * MIME type for lists of entries.
         */
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd.basicsyncadapter.entries";
        /**
         * MIME type for individual entries.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vnd.basicsyncadapter.entry";

        /**
         * Fully qualified URI for "entry" resources.
         */
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_ENTRIES).build();

        /**
         * Table name where records are stored for "entry" resources.
         */
        public static final String TABLE_NAME = "entry";
        /**
         * Atom ID. (Note: Not to be confused with the database primary key, which is _ID.
         */
        public static final String COLUMN_NAME_ENTRY_ID = "entry_id";
        /**
         * Article title
         */
        public static final String COLUMN_NAME_TITLE = "title";
        /**
         * Article hyperlink. Corresponds to the rel="alternate" link in the
         * Atom spec.
         */
        public static final String COLUMN_NAME_LINK = "link";
        /**
         * Date article was published.
         */
        public static final String COLUMN_NAME_PUBLISHED = "published";
    }

}
