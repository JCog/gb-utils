package com.jcog.utils.database.preds;

import com.jcog.utils.database.GbDatabase;

public class SunshineTimerLeaderboardDb extends PredsLeaderboardDb {
    private static final String COLLECTION_NAME_KEY = "sunshinetimer";

    public SunshineTimerLeaderboardDb(GbDatabase gbDatabase) {
        super(gbDatabase);
    }

    @Override
    protected String getCollectionName() {
        return COLLECTION_NAME_KEY;
    }
}
