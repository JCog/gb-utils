package com.jcog.utils.database.misc;

import com.jcog.utils.database.GbCollection;
import com.jcog.utils.database.GbDatabase;
import org.bson.Document;

public class TattleDb extends GbCollection {
    private static final String COLLECTION_NAME_KEY = "tattles";
    private static final String TATTLE_KEY = "tattle";

    public TattleDb(GbDatabase gbDatabase) {
        super(gbDatabase);
    }

    @Override
    protected String getCollectionName() {
        return COLLECTION_NAME_KEY;
    }

    public void addTattle(String twitchId, String tattle) {
        Document result = findFirstEquals(ID_KEY, twitchId);
        if (result == null) {
            Document document = new Document(ID_KEY, twitchId)
                    .append(TATTLE_KEY, tattle);
            insertOne(document);
        }
        else {
            updateOne(twitchId, new Document(TATTLE_KEY, tattle));
        }
    }

    public String getTattle(String twitchId) {
        Document result = findFirstEquals(ID_KEY, twitchId);
        if (result == null) {
            return null;
        }
        else {
            return (String) result.get(TATTLE_KEY);
        }
    }
}
