package com.jcog.utils.database.emotes;

import com.jcog.utils.database.GbDatabase;

public class BttvEmoteStatsDb extends EmoteStatsDb {
    private static final String COLLECTION_NAME_KEY = "bttvemotestats";

    public BttvEmoteStatsDb(GbDatabase gbDatabase) {
        super(gbDatabase);
    }

    @Override
    protected String getCollectionName() {
        return COLLECTION_NAME_KEY;
    }
}
