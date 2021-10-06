package main.java.engine;

import main.java.Button;
import main.java.ButtonNames;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class World {
    public static final int WORLD_SIZE_X = 8;
    public static final int WORLD_SIZE_Y = 8;

    private Tile lastOpenTile;
    private int openedTiles;

    private Tile[][] board;
    private boolean isWin;
    private boolean isExit;

    private void initBoard(Tile[][] board){
        TileCreator[] tileCreator = { new TileCreator1(), new TileCreator2(), new TileCreator3(), new TileCreator4() };
        ArrayList<Integer> X = new ArrayList<Integer>(8);
        ArrayList<Integer> Y = new ArrayList<Integer>(8);

        for (int i = 0; i < 8; i++) {
            X.add(i);
            Y.add(i);
        }

        for (TileCreator creator : tileCreator) {
            for (int j = 0; j < WORLD_SIZE_X + WORLD_SIZE_Y; j++) {
                int x = X.remove(randInt(0, X.size() - 1));
                int y = Y.remove(randInt(0, Y.size() - 1));
                board[x][y] = creator.create(x, y);
            }
        }
    }

    private void init() {
        setWin(false);
        setExit(false);

        initBoard(this.board);

        this.lastOpenTile = null;
    }

    public void setWin(boolean win) {
        this.isWin = win;
    }

    public void setExit(boolean exit) {
        this.isExit = exit;
    }

    public boolean getExit() {
        return this.isExit;
    }

    public boolean getWin() {
        return this.isWin;
    }

    private void winChek() {
        this.isWin = (openedTiles == 64);
    }

    int randInt(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public Tile[][] getBoard() {
        return board;
    }

    public void update(Button button){
        if (button.getName() == ButtonNames.BEGIN)
            init();
        else if (button.getName() == ButtonNames.EXIT)
            isExit = true;
        else {
            int x = button.getPosition().x;
            int y = button.getPosition().y;

            if (lastOpenTile != null) {
                if (!lastOpenTile.getPosition().equals(button.getPosition())) {
                    if (lastOpenTile.getType() == board[x][y].getType() && !board[x][y].isOpen()) {
                        board[x][y].open();
                        openedTiles++;
                    } else {
                        board[lastOpenTile.getPosition().x][lastOpenTile.getPosition().y].hide();
                        openedTiles--;
                    }
                    lastOpenTile = null;
                }
            } else {
                board[x][y].open();
                lastOpenTile = board[x][y];
            }
        }

        winChek();
    }
}

