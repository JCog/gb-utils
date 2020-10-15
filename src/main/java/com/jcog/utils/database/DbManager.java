package com.jcog.utils.database;

import com.jcog.utils.database.emotes.BttvEmoteStatsDb;
import com.jcog.utils.database.emotes.EmoteStatsDb;
import com.jcog.utils.database.emotes.FfzEmoteStatsDb;
import com.jcog.utils.database.misc.*;
import com.jcog.utils.database.preds.SpeedySpinLeaderboardDb;
import com.jcog.utils.database.preds.SunshineTimerLeaderboardDb;
import com.jcog.utils.database.stats.StreamStatsDb;
import com.jcog.utils.database.stats.WatchTimeDb;

public class DbManager {

    private final GbDatabase gbDatabase;

    private final BttvEmoteStatsDb bttvEmoteStatsDb;
    private final EmoteStatsDb emoteStatsDb;
    private final FfzEmoteStatsDb ffzEmoteStatsDb;

    private final CommandDb commandDb;
    private final MinecraftUserDb minecraftUserDb;
    private final QuoteDb quoteDb;
    private final SocialSchedulerDb socialSchedulerDb;
    private final ViewerQueueDb viewerQueueDb;

    private final SpeedySpinLeaderboardDb speedySpinLeaderboardDb;
    private final SunshineTimerLeaderboardDb sunshineTimerLeaderboardDb;

    private final StreamStatsDb streamStatsDb;
    private final WatchTimeDb watchTimeDb;

    public DbManager(String host, int port, String dbName, String user, String password, boolean writePermission) {
        this.gbDatabase = new GbDatabase(host, port, dbName, user, password, writePermission);

        bttvEmoteStatsDb = new BttvEmoteStatsDb(gbDatabase);
        emoteStatsDb = new EmoteStatsDb(gbDatabase);
        ffzEmoteStatsDb = new FfzEmoteStatsDb(gbDatabase);

        commandDb = new CommandDb(gbDatabase);
        minecraftUserDb = new MinecraftUserDb(gbDatabase);
        quoteDb = new QuoteDb(gbDatabase);
        socialSchedulerDb = new SocialSchedulerDb(gbDatabase);
        viewerQueueDb = new ViewerQueueDb(gbDatabase);

        speedySpinLeaderboardDb = new SpeedySpinLeaderboardDb(gbDatabase);
        sunshineTimerLeaderboardDb = new SunshineTimerLeaderboardDb(gbDatabase);

        streamStatsDb = new StreamStatsDb(gbDatabase);
        watchTimeDb = new WatchTimeDb(gbDatabase);
    }

    public void closeDb() {
        gbDatabase.close();
    }

    public BttvEmoteStatsDb getBttvEmoteStatsDb() {
        return bttvEmoteStatsDb;
    }

    public EmoteStatsDb getEmoteStatsDb() {
        return emoteStatsDb;
    }

    public FfzEmoteStatsDb getFfzEmoteStatsDb() {
        return ffzEmoteStatsDb;
    }

    public CommandDb getCommandDb() {
        return commandDb;
    }

    public MinecraftUserDb getMinecraftUserDb() {
        return minecraftUserDb;
    }

    public QuoteDb getQuoteDb() {
        return quoteDb;
    }

    public SocialSchedulerDb getSocialSchedulerDb() {
        return socialSchedulerDb;
    }

    public ViewerQueueDb getViewerQueueDb() {
        return viewerQueueDb;
    }

    public SpeedySpinLeaderboardDb getSpeedySpinLeaderboardDb() {
        return speedySpinLeaderboardDb;
    }

    public SunshineTimerLeaderboardDb getSunshineTimerLeaderboardDb() {
        return sunshineTimerLeaderboardDb;
    }

    public StreamStatsDb getStreamStatsDb() {
        return streamStatsDb;
    }

    public WatchTimeDb getWatchTimeDb() {
        return watchTimeDb;
    }
}
