package edu.ricm3.game.tomatower.entities;

import edu.ricm3.game.tomatower.Cell;
import edu.ricm3.game.tomatower.mvc.Model;

import java.awt.image.BufferedImage;

public class Living extends Entity {

    int hp;
    Direction direction;
    Weapon weapon;

    // TEST
    long action_time = 1000; // 1000ms = 1s
    long last_action;


    public Living(Model c_model, Boolean c_movement, BufferedImage c_sprite, double c_scale, Cell c_cell, Direction c_direction) {
        super(c_model, c_movement, c_sprite, c_scale, c_cell);
        this.direction = c_direction;
    }

    public Living(Model c_model, Boolean c_movement, BufferedImage c_sprite, double c_scale, Direction c_direction) {
        super(c_model, c_movement, c_sprite, c_scale);
        this.direction = c_direction;
    }

    public void step(long now) {

    }

    public void move(Direction d) {
        this.turn(d);
        if (this.canMove()) {
            Cell cell_destination;
            switch (d) {
                case UP:
                    cell_destination = this.model.getMainMap().getCell(this.getPosition()[0], this.getPosition()[1] - 1);
                    break;
                case RIGHT:
                    cell_destination = this.model.getMainMap().getCell(this.getPosition()[0] + 1, this.getPosition()[1]);
                    break;
                case DOWN :
                    cell_destination = this.model.getMainMap().getCell(this.getPosition()[0], this.getPosition()[1] + 1);
                    break;
                case LEFT :
                    cell_destination = this.model.getMainMap().getCell(this.getPosition()[0] - 1, this.getPosition()[1]);
                    break;
                default:
                    cell_destination = null;
            }

            if (model.getMainMap().freeCell(cell_destination)) {
                this.cell.removeEntity(this);
                this.cell = cell_destination;
                this.cell.addEntity(this);
            }
        }
        //pSystem.out.println(this.direction.toString());
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
        return this.model.getMainMap().getCell(pos_front_cell_x, pos_front_cell_y);
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public void damage(int power) {
        hp -= power;
    }

    public void hit() {
        this.weapon.hit(this.cell, this.direction);
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
