package com.usp.kiss.server.service;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.usp.kiss.client.service.DeleteGameService;
import com.usp.kiss.server.common.CommonUtils;
import com.usp.kiss.server.meta.EpisodeMeta;
import com.usp.kiss.shared.AuthException;
import com.usp.kiss.shared.NotPermittedException;
import com.usp.kiss.shared.model.Game;

public class DeleteGameServiceImpl implements DeleteGameService {

    public void delete(Key gameKey) throws AuthException, NotPermittedException {
        String ownerEmail = CommonUtils.getOwnerEmail();
        if (ownerEmail == null) {
            throw new AuthException(CommonUtils.getGoogleLoginUrl());
        } else {
            UserService userService = UserServiceFactory.getUserService();
            User user = userService.getCurrentUser();
            Transaction tx = Datastore.beginTransaction();
            Game game = Datastore.get(Game.class, gameKey);
            if (game.getOwnerEmail().equals(user.getEmail())) {
                
                EpisodeMeta meta = EpisodeMeta.get();
                Iterable<Key> keys = Datastore.query(meta)
                        .filter(meta.gameId.equal(gameKey.getId()))
                        .asKeyList();
                Datastore.delete(tx, keys);
                Datastore.delete(tx, game.getKey());
                tx.commit();
            } else {
                throw new NotPermittedException(
                    user.getNickname() + " is not allowed to perform this operation on the given entity");
            }
          
        }
    }
}