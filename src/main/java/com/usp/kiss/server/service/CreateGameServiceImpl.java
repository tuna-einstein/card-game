package com.usp.kiss.server.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.usp.kiss.client.service.CreateGameService;
import com.usp.kiss.server.common.CommonUtils;
import com.usp.kiss.shared.AuthException;
import com.usp.kiss.shared.InvalidValueException;
import com.usp.kiss.shared.model.Episode;
import com.usp.kiss.shared.model.Game;

public class CreateGameServiceImpl implements CreateGameService {

    public Game create(int numPlayers) throws AuthException, InvalidValueException {

        String ownerEmail = CommonUtils.getOwnerEmail();
        if (numPlayers > 10 || numPlayers < 2) {
            throw new InvalidValueException("Invalid value : numPlayers " + numPlayers);
        }
        if (ownerEmail == null) {
            throw new AuthException(CommonUtils.getGoogleLoginUrl());
        } else {
            UserService userService = UserServiceFactory.getUserService();
            User user = userService.getCurrentUser();

            Key gameKey = Datastore.allocateId(Game.class);
            Game game = new Game();
            game.setKey(gameKey);
            game.setDate(new Date());
            game.setOwnerEmail(user.getEmail());
            game.setNumPlayers(numPlayers);

            List<String> players = new ArrayList<String>(numPlayers);
            for (int i = 0; i < numPlayers; i++) {
                players.add(getGreekName(i));
            }
            game.setPlayers(players);
            
            
            Transaction tx = Datastore.beginTransaction();
            
            Datastore.put(tx, game);
            for (int i = 0; i < 20; i++) {
                Key childKey = Datastore.allocateId(gameKey, Episode.class);
                Episode epi = new Episode();
                epi.setKey(childKey);
                epi.setGameId(gameKey.getId());
                epi.setExpected(getArray(numPlayers));
                epi.setActual(getArray(numPlayers));
                epi.setTitle(getTitle(i));
                epi.setId(i);
                Datastore.put(tx, epi);
            }
            tx.commit();
            return game;
        }
    }

    private String getTitle(int round) {
        switch(round) {
        case 0: return "10H";
        case 1: return "9S";
        case 2: return "8D";
        case 3: return "7C";

        case 4: return "6N";

        case 5: return "5H";
        case 6: return "4S";
        case 7: return "3D";
        case 8: return "2C";

        case 9: return "1N";

        case 10: return "1H";
        case 11: return "2S";
        case 12: return "3D";
        case 13: return "4C";

        case 14: return "5N";

        case 15: return "6H";
        case 16: return "7S";
        case 17: return "8D";
        case 18: return "9C";
        case 19: return "10N";
        }
        return "invalid";
    }

    private String getGreekName(int index) throws InvalidValueException {
        switch(index) {
        case 0: return "Alpha";
        case 1: return "Beta";
        case 2: return "Gamma";
        case 3 : return "Delta";
        case 4: return "Epsilon";
        case 5: return "Zeta";
        case 6: return "Eta";
        case 7: return "Theta";
        case 8: return "Iota";
        case 9: return "Kappa";
        case 10: return "Lambda";
        }
        throw new InvalidValueException("Index out of bound");
    }

    private int[] getArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = -1;
        }
        return array;
    }
}
