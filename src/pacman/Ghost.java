package pacman;



import de.ft.ledwall.SystemInterface;

import java.awt.*;

public class Ghost {
    Level myLevel;
    int x=0;
    int y=0;
    Color color;
    Direction nextDirection=Direction.NONE;
    private Direction acDirection=Direction.RIGHT;

    public Ghost(int x, int y, Color color){
        this.x=x;
        this.y=y;
        this.color=color;
    }
    public void move(){

        switch(this.nextDirection){
            case UP:{
                if(y<14)if((this.myLevel.level[this.y+1][this.x]&0x01)==0){this.acDirection=nextDirection;this.nextDirection=Direction.NONE;}
                break;
            }
            case DOWN:{
                if(y>0)if((this.myLevel.level[this.y-1][this.x]&0x01)==0){this.acDirection=nextDirection;this.nextDirection=Direction.NONE;}
                break;
            }
            case RIGHT:{
                if(x<9)if((this.myLevel.level[this.y][this.x+1]&0x01)==0){this.acDirection=nextDirection;this.nextDirection=Direction.NONE;}
                break;
            }
            case LEFT:{
                if(x>0)if((this.myLevel.level[this.y][this.x-1]&0x01)==0){this.acDirection=nextDirection;this.nextDirection=Direction.NONE;}
                break;
            }
        }
        boolean stuck=true;
        switch(this.acDirection){
            case UP:{
                if(y<14)if((myLevel.level[y+1][x]&0x01)==0){y++;stuck=false;}
                break;
            }
            case DOWN:{
                if(y>0)if((myLevel.level[y-1][x]&0x01)==0){y--;stuck=false;}
                break;
            }
            case RIGHT:{
                if(x<9)if((myLevel.level[y][x+1]&0x01)==0){x++;stuck=false;}
                break;
            }
            case LEFT:{
                if(x>0)if((myLevel.level[y][x-1]&0x01)==0){x--;stuck=false;}
                break;
            }
        }
        Direction xdir=Direction.NONE;
        Direction ydir=Direction.NONE;

        if(Math.random()>0.5f) {
            if (myLevel.player.getY() > y) {
                ydir = Direction.UP;
            } else{
                ydir = Direction.DOWN;
            }

            if (myLevel.player.getX() > x) {
                xdir = Direction.RIGHT;
            } else{
                xdir = Direction.LEFT;
            }
        }else{
            if (myLevel.player.getY() < y) {
                ydir = Direction.DOWN;
            } else{
                ydir = Direction.UP;
            }

            if (myLevel.player.getX() < x) {
                xdir = Direction.LEFT;
            } else{
                xdir = Direction.RIGHT;
            }
        }




        if(Math.random()>0.8f){
            ydir=invert(ydir);
        }
        if(Math.random()>0.8f){
            xdir=invert(xdir);
        }

        if(this.nextDirection==Direction.NONE){
            switch (this.acDirection){
                case RIGHT:{
                }
                case LEFT:{
                    if(ydir==Direction.UP){
                        if(y>0)if((myLevel.level[y-1][x]&0x01)==0x00) {
                            nextDirection=Direction.DOWN;
                        }
                        if(y<14)if((myLevel.level[y+1][x]&0x01)==0x00){
                            nextDirection=Direction.UP;
                        }
                    }else if(ydir==Direction.DOWN){
                        if(y<14)if((myLevel.level[y+1][x]&0x01)==0x00){
                            nextDirection=Direction.UP;
                        }
                        if(y>0)if((myLevel.level[y-1][x]&0x01)==0x00) {
                            nextDirection=Direction.DOWN;
                        }
                    }
                    break;
                }
                case UP:{
                }
                case DOWN:{
                    if(xdir==Direction.RIGHT) {
                        if (x > 0) if ((myLevel.level[y][x - 1] & 0x01) == 0x00) {
                            nextDirection = Direction.LEFT;
                        }
                        if (x < 9) if ((myLevel.level[y][x + 1] & 0x01) == 0x00) {
                            nextDirection = Direction.RIGHT;
                        }
                    }else if(xdir==Direction.LEFT){
                        if (x < 9) if ((myLevel.level[y][x + 1] & 0x01) == 0x00) {
                            nextDirection = Direction.RIGHT;
                        }
                        if (x > 0) if ((myLevel.level[y][x - 1] & 0x01) == 0x00) {
                            nextDirection = Direction.LEFT;
                        }
                    }
                    break;
                }
            }

        }
        if(stuck){
            nextDirection=invert(acDirection);
        }
    }
    public Direction invert(Direction dir){
        Direction o=Direction.NONE;
        switch (dir){
            case UP -> o= Direction.DOWN;
            case DOWN -> o= Direction.UP;
            case RIGHT -> o=Direction.LEFT;
            case LEFT -> o=Direction.RIGHT;
        }
        return o;
    }
    public void draw(){
        SystemInterface.table.setColor(this.color.getRed(),this.color.getGreen(),this.color.getBlue());
        SystemInterface.table.drawPixel(this.x,this.y);
    }

    public Ghost setMylevel(Level mylevel) {
        this.myLevel = mylevel;
        return this;
    }
}
