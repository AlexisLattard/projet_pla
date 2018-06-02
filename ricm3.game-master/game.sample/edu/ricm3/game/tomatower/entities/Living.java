package edu.ricm3.game.tomatower.entities;

import edu.ricm3.game.tomatower.Cell;
import edu.ricm3.game.tomatower.mvc.Model;

import java.awt.image.BufferedImage;
import java.net.ServerSocket;

public class Living extends Entity {

    int hp;
    int speed_x;
    int speed_y;
    Direction direction;
    Weapon weapon;

    public Living(Model c_model, Boolean c_movement, BufferedImage c_sprite, double c_scale, Cell c_cell, Direction c_direction) {
        super(c_model, c_movement, c_sprite, c_scale, c_cell);
        this.direction = c_direction;
    }

    public Living(Model c_model, Boolean c_movement, BufferedImage c_sprite, double c_scale, Direction c_direction) {
        super(c_model, c_movement, c_sprite, c_scale);
        this.direction = c_direction;
    }

    public void move(int vertical, int horizontal) {
        if (this.canMove()) {
            int dest_cell_x = this.getPosition()[0] + horizontal;
            int dest_cell_y = this.getPosition()[1]  + vertical;
            System.out.println(dest_cell_x + " " + dest_cell_y);
            Cell cell_destination = this.model.getMap().getCell(dest_cell_x, dest_cell_y);

            if (model.getMap().freeCell(cell_destination)) {
                this.cell.removeEntity(this);
                this.cell = cell_destination;
                this.cell.addEntity(this);
            }

            if(horizontal < 0) { // typiquement -1
                this.turn(Direction.LEFT);
            } else if( horizontal > 0) {
                this.turn(Direction.RIGHT);
            } else if( vertical < 0) {
                this.turn(Direction.UP);
            } else if( vertical > 0) {
                this.turn(Direction.DOWN);
            }

        }
        //System.out.println(this.direction.toString());
    }
    public void turn(Direction d) {
        if(this.canMove()) {
            this.direction = d;
        }
    }

    public Cell getFrontCell() {
        int[] current_pos = this.getPosition();
        int pos_front_cell_x = current_pos[0];
        int pos_front_cell_y = current_pos[1];

        switch (this.direction) {
            case LEFT: pos_front_cell_x = current_pos[0] -1; break;
            case RIGHT: pos_front_cell_x = current_pos[0] +1; break;
            case UP: pos_front_cell_y = current_pos[1] -1; break;
            case DOWN: pos_front_cell_y = current_pos[1] +1; break;
            default: pos_front_cell_x = -1; pos_front_cell_y = -1; // Ne devrait pas arriver
        }
        return this.model.getMap().getCell(pos_front_cell_x, pos_front_cell_y);
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public void damage(int power) {
        hp -= power;
    }

    public void hit() {
        this.weapon.hit(this.cell);
    }

    public void pick() {

    }

    public void store() {

    }

    public void getBagEntity() {

    }

    public void power(int hp) {
        this.hp += hp;
    }

    public void throwAction() {

    }


}
