package com.example.java;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eric on 1/10/17.
 */
public class PlayerList {
    private List<Players> playerList;

    public PlayerList() {
        playerList = new ArrayList<Players>();
    }
    public void addPlayer(Players player){
        playerList.add(player);
    }

    public Players findUserById(int id) {
        for(int i = 0; i < playerList.size(); i++) {
            if (playerList.get(i).getId() == id)
                return playerList.get(i);
        }
        return null;
    }


    @Override
    public String toString(){
        String allUsers = "\n";

        for(Players u: playerList){
            allUsers = allUsers +  u.getId() + ": " +  u.getName() + " - Score: " + u.getScore() + "\n";
        }

        return allUsers;
    }
}
