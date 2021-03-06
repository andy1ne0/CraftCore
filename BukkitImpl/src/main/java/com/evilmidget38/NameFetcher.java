package com.evilmidget38;

import com.google.common.collect.ImmutableList;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;

/**
 * @author evilmidget38
 */
public class NameFetcher implements Callable<Map<UUID, String>> {
    private static final String PROFILE_URL = "https://sessionserver.mojang.com/session/minecraft/profile/";
    private final JSONParser jsonParser = new JSONParser();
    private final List<UUID> uuids;

    /**
     *
     * @param uuids A list of uuids to convert to names
     */
    public NameFetcher(List<UUID> uuids) {
        this.uuids = ImmutableList.copyOf(uuids);
    }

    /**
     * @return a map of uuids and names
     * @throws IOException
     * @throws ParseException
     */
    @Override
    public Map<UUID, String> call() throws IOException, ParseException {
        Map<UUID, String> uuidStringMap = new HashMap<>();
        for (UUID uuid: this.uuids) {
            HttpURLConnection connection = (HttpURLConnection) new URL(PROFILE_URL+uuid.toString().replace("-", "")).openConnection();
            JSONObject response = (JSONObject) this.jsonParser.parse(new InputStreamReader(connection.getInputStream()));
            String name = (String) response.get("name");
            if (name == null) {
                continue;
            }
            String cause = (String) response.get("cause");
            String errorMessage = (String) response.get("errorMessage");
            if (cause != null && cause.length() > 0) {
                throw new IllegalStateException(errorMessage);
            }
            uuidStringMap.put(uuid, name);
        }
        return uuidStringMap;
    }
}


//https://bukkit.org/threads/player-name-uuid-fetcher.250926/