package main.java.engine;

import main.java.Position;

public class Tile1 implements Tile{
    private boolean isOpen;
    private int type;
    private Position position;

    public Tile1(int x, int y){
        isOpen = false;
        this.type = 1;
        this.position.x = x;
        this.position.y = y;
    }

    @Override
    public Position getPosition(){
        return this.position;
    }

    @Override
    public void hide() {
        isOpen = false;
    }

    @Override
    public void open() {
        isOpen = true;
    }

    @Override
    public boolean isOpen() {
        return isOpen;
    }

    @Override
    public int getType(){
        return type;
    }
}