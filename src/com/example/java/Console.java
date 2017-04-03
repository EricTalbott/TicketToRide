package com.example.java;

import java.util.Scanner;

public class Console {

    private PlayerList playerList = new PlayerList();
    private int turn = 1;
    private boolean lastTurn = false;
    private boolean gameEnd = false;
    private int finalPlayer;

    public void mainMenu(){
        System.out.print("Enter Number of Players: ");
        System.out.println("2 - 6");

        Scanner sc  = new Scanner(System.in);

        while(!sc.hasNextByte()) {
            sc.next();
            System.out.println("Number only!");
            mainMenu();
        }
        byte input = sc.nextByte();
        sc.nextLine();

        switch (input) {
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                for(int i = 1; i <= input; i++){
                    Players player = new Players();
                    System.out.println("*****************************************");
                    System.out.println("Enter player " + i + "'s name:");
                    String name = sc.nextLine();
                    System.out.println("\nHow Many Routes?: ");
                    while(!sc.hasNextByte()) {
                        sc.next();
                        System.out.println("Number only!");
                        mainMenu();
                    }
                    byte routes = sc.nextByte();
                    sc.nextLine();


                    System.out.println("\n" + name + " added with " + routes + " routes\n");
                    player.setName(name);
                    player.setRoutes(routes);
                    player.setId(i);
                    playerList.addPlayer(player);
                }
                break;
            default:
                System.out.println("Invalid choice.");
                mainMenu();
                break;
        }

        System.out.println("Player list: ");
        System.out.println(playerList.toString());
        System.out.println("Let's get started!!! \n");
        gameStart();

    }

    public void gameStart(){
        System.out.println("*****************************************");
        System.out.println("                Turn #" + turn);
        System.out.println("*****************************************");
        int i = 1;
        Players player;

        while(playerList.findUserById(i) != null){
            player = playerList.findUserById(i);
            System.out.println("\n*****************************************");
            System.out.println("                " + player.getId() + ": " + player.getName());
            System.out.println("*****************************************");
            System.out.println("\n   Score: " + player.getScore() + "\n   Trains left: " +
                    player.getTrains() + "\n   Train Cards in Hand: " + player.getCards() +
                    "\n   Unused Stations: " + player.getStations() + "\n   Number of Routes: " +
                    player.getRoutes());
            playerMenu(player);
            i++;
            if(lastTurn == true) {
                gameEnd = true;
            }
        }

        turn++;
        if(gameEnd == false)
            gameStart();
        else{
            i = 1;
            while(playerList.findUserById(i) != null && i <= finalPlayer){
                player = playerList.findUserById(i);
                System.out.println("\n*****************************************");
                System.out.println("                " + player.getId() + ": " + player.getName());
                System.out.println("*****************************************");
                System.out.println("\n   Score: " + player.getScore() + "\n   Trains left: " +
                        player.getTrains() + "\n   Train Cards in Hand: " + player.getCards() +
                        "\n   Unused Stations: " + player.getStations() + "\n   Number of Routes: " +
                        player.getRoutes());
                playerMenu(player);
                i++;
            }
            gameEnd();
        }
    }

    public void playerMenu(Players player){
        Scanner sc  = new Scanner(System.in);

        System.out.println("\nMake Choice: ");
        System.out.println("1: Draw Train Card(s) ");
        System.out.println("2: Draw Route Card(s) ");
        System.out.println("3: Place route ");
        System.out.println("4: Place Train Station ");

        while(!sc.hasNextByte()) {
            sc.next();
            System.out.println("Number only!");
            playerMenu(player);
        }
        byte input = sc.nextByte();
        sc.nextLine();

        switch(input){
            case 1:
                drawCards(player);
                break;
            case 2:
                drawRoutes(player);
                break;
            case 3:
                placeTrain(player);
                break;
            case 4:
                placeStation(player);
                break;
            default:
                System.out.println("Invalid Choice");
                playerMenu(player);
                break;
        }

        if(player.getTrains() <= 2 && lastTurn == false){
            lastTurn = true;
            finalPlayer = player.getId();
            System.out.println("*****************************************");
            System.out.println("LAST TURN! LAST TURN! LAST TURN!");
            System.out.println("*****************************************");
        }
    }

    public void placeTrain(Players player){
        Scanner sc  = new Scanner(System.in);

        System.out.println("Size of Route: ");
        System.out.println("1: 1 Train: ");
        System.out.println("2: 2 Trains: ");
        System.out.println("3: 3 Trains: ");
        System.out.println("4: 4 Trains: ");
        System.out.println("6: 6 Trains: ");
        System.out.println("8: 8 Trains: ");
        System.out.println("0: GO BACK");

        while(!sc.hasNextByte()) {
            sc.next();
            System.out.println("Number only!");
            placeTrain(player);
        }
        byte input = sc.nextByte();
        sc.nextLine();

        if(input <= player.getCards() && input <= player.getTrains()) {
            switch (input) {
                case 0:
                    playerMenu(player);
                    break;
                case 1:
                    player.setTrains(input);
                    player.setScore(1);
                    player.setCards(-1);
                    break;
                case 2:
                    player.setTrains(input);
                    player.setScore(2);
                    player.setCards(-2);
                    break;
                case 3:
                    player.setTrains(input);
                    player.setScore(4);
                    player.setCards(-3);
                    break;
                case 4:
                    player.setTrains(input);
                    player.setScore(7);
                    player.setCards(-4);
                    break;
                case 6:
                    player.setTrains(input);
                    player.setScore(15);
                    player.setCards(-6);
                    break;
                case 8:
                    player.setTrains(input);
                    player.setScore(21);
                    player.setCards(-8);
                    break;
                default:
                    System.out.println("Invalid Choice!");
                    placeTrain(player);
                    break;
            }
            System.out.println(input + " Trains played!");
        }
        else{
            System.out.println("Not enough cards/trains! ");
            placeTrain(player);
        }
    }

    public void placeStation(Players player){
        Scanner sc  = new Scanner(System.in);

        System.out.println("Are you sure you want to place a station? ");
        System.out.println("1: Yes");
        System.out.println("2: No, GO BACK");

        while(!sc.hasNextByte()) {
            sc.next();
            System.out.println("Number only!");
            placeStation(player);
        }
        byte input = sc.nextByte();
        sc.nextLine();

        if(input == 1){
            switch(player.getStations()){
                case 3:
                    player.setCards(-1);
                    break;
                case 2:
                    player.setCards(-2);
                    break;
                case 1:
                    player.setCards(-3);
                    break;
                default:
                    System.out.println("No Stations left!");
                    playerMenu(player);
                    break;

            }
            player.setStations(1);
            player.setScore(-4);
            System.out.println("Station Placed. " + player.getStations() + " Remaining Stations.");
        }
        else{
            System.out.println("No Station Placed");
            playerMenu(player);
        }
    }

    public void drawCards(Players player){
        Scanner sc  = new Scanner(System.in);

        System.out.println("Number of Cards Drawn (1 or 2): ");
        System.out.println("0: GO BACK");
        while(!sc.hasNextByte()) {
            sc.next();
            System.out.println("Number only!");
            drawRoutes(player);
        }
        byte input = sc.nextByte();
        sc.nextLine();

        switch (input){
            case 0:
                playerMenu(player);
                break;
            case 1:
                player.setCards(1);
                break;
            case 2:
                player.setCards(2);
                break;
            default:
                System.out.println("Invalid Choice!");
                drawCards(player);
                break;
        }
        System.out.println(input + " Train Cards Drawn.");
    }

    public void drawRoutes(Players player) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Number of Routes Taken (1, 2, or 3): ");
        System.out.println("0: GO BACK");
        while (!sc.hasNextByte()) {
            sc.next();
            System.out.println("Number only!");
            drawRoutes(player);
        }
        byte input = sc.nextByte();
        sc.nextLine();

        switch (input) {
            case 0:
                playerMenu(player);
                break;
            case 1:
                player.setRoutes(1);
                break;
            case 2:
                player.setRoutes(2);
                break;
            case 3:
                player.setRoutes(3);
                break;
            default:
                System.out.println("Invalid Choice!");
                drawRoutes(player);
                break;
        }
        System.out.println(input + " Route Cards drawn.");
    }

    public void gameEnd(){
        System.out.println("*****************************************");
        System.out.println("                END GAME");
        System.out.println("*****************************************");
        int i = 1;
        Players player;

        while(playerList.findUserById(i) != null){
            player = playerList.findUserById(i);
            System.out.println("\n*****************************************");
            System.out.println("\n" + player.getId() + ": " + player.getName() + "\n   Score: " +
                    player.getScore() + "\n   Trains left: " + player.getTrains()  +
                    "\n   Unused Stations: " + player.getStations() + "\n   Number of Routes: " +
                    player.getRoutes());
            addRoutes(player);
            longestRoute(player);
            i++;
        }
         finalTally();
    }

    private void addRoutes(Players player){
        Scanner sc  = new Scanner(System.in);
        int value = 0;
        System.out.println("How many of " + player.getRoutes() + " routes are completed?");

        while(!sc.hasNextByte()) {
            sc.next();
            System.out.println("Number only!");
            mainMenu();
        }
        byte input = sc.nextByte();
        sc.nextLine();

        if(input > player.getStations()){
            System.out.println("Invalid input");
            addRoutes(player);
        }
        int incompleteRoutes = player.getRoutes() - input;
        for(int i = 0; i < input; i++){
            System.out.println("Value of completed route " + i+1 + ": ");
            while(!sc.hasNextByte()) {
                sc.next();
                System.out.println("Number only!");
                mainMenu();
            }
            value = sc.nextByte();
            sc.nextLine();
            player.setScore(value);
        }
        for(int i = 0; i < incompleteRoutes; i++){
            System.out.println("Value of incomplete route " + i+1 + ": ");
            while(!sc.hasNextByte()) {
                sc.next();
                System.out.println("Number only!");
                mainMenu();
            }
            value = sc.nextByte();
            sc.nextLine();
            player.setScore(-value);
        }
        System.out.println(player.getName() + "'s Updated Score: " + player.getScore());
    }

    private void longestRoute(Players player){
        Scanner sc  = new Scanner(System.in);
        System.out.println("Does " + player.getId() + ": " + player.getName() + " have the longest route?");
        System.out.println("1: Yes");
        System.out.println("2: No");

        while(!sc.hasNextByte()) {
            sc.next();
            System.out.println("Number only!");
            drawRoutes(player);
        }
        byte input = sc.nextByte();
        sc.nextLine();

        switch (input){
            case 1:
                player.setScore(10);
                System.out.println("10 points added");
                System.out.println(player.getId() + ": " + player.getName() + "'s Updated Score: "
                        + player.getScore());
                break;
            case 2:
                break;
            default:
                System.out.println("Invalid Choice!");
                drawCards(player);
                break;
        }
    }

    private void finalTally(){
        int i = 1;
        Players player;
        Players player2;
        Players winner = null;


        while(playerList.findUserById(i) != null) {
            player = playerList.findUserById(i);
            System.out.println("\n*****************************************");
            System.out.println("\n" + player.getId() + ": " + player.getName() + "\n   Score: " +
                    player.getScore());


            if(playerList.findUserById(i+1) != null){
                player2 = playerList.findUserById(i+1);
                if(player.getScore() < player2.getScore()){
                    winner = player2;
                }else{
                    winner = player;
                }
            }
            i++;
        }
        System.out.println(winner.getId() + ": " + winner.getName() + " WINS!");
        System.out.println("*****************************************");
        System.out.println("                CONGRATULATIONS!");
        System.out.println("*****************************************");
    }

}
