package com.ohiostate.pickup;

import java.util.Date;

public class DatabaseSchema {

    public static final class DropTable {
        public static final String NAME = "drops";

        public static final class Cols {
            public static final String PLAYER_ID= "player_id";
            public static final String SPORT = "sport";
            public static final String LOCATION = "location";
            public static final String PLAY_TIME = "play_time";
            public static final String DIFFICULTY = "difficulty_level";
            public static final String DATE = "date";
            public static final String PLAYERS = "num_players";
            public static final String GENDER = "preferred_gender";
            public static final String ID = "drop_id";
            public static final String MESSAGE = "message";

        }
    }

    public static final class PlayerTable {
        public static final String NAME = "players";

        public static final class Cols {
            public static final String ID = "player_id";
            public static final String FIRST_NAME = "first_name";
            public static final String LAST_NAME = "last_name";
            public static final String EMAIL = "email";
            public static final String GENDER = "gender";
        }
    }

}
