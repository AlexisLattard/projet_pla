package edu.ricm3.game.tomatower;

import java.awt.image.BufferedImage;

public class Living extends Entity {

    int hp;
    int speed_x;
    int speed_y;
    Direction direction;
    Weapon weapon;

    public Living(Model c_model, Boolean c_movement, BufferedImage c_sprite, double c_scale, int cell_x, int cell_y, Direction c_direction) {
        super(c_model, c_movement, c_sprite, c_scale, cell_x, cell_y);
        this.direction = c_direction;
    }

    public Living(Model c_model, Boolean c_movement, BufferedImage c_sprite, double c_scale, Direction c_direction) {
        super(c_model, c_movement, c_sprite, c_scale);
        this.direction = c_direction;
    }

    public void move(int vertical, int horizontal) {
        if (this.canMove()) {
            int dest_cell_x = cell_x + horizontal;
            int dest_cell_y = cell_y + vertical;

            if (model.freeCell(dest_cell_x, dest_cell_y)) {
                this.cell_x = dest_cell_x;
                this.cell_y = dest_cell_y;
            }

            if(horizontal < 0) { // typiquement -1
                this.direction = Direction.LEFT;
            } else if( horizontal > 0) {
                this.direction = Direction.RIGHT;
            } else if( vertical < 0) {
                this.direction = Direction.UP;
            } else if( vertical > 0) {
                this.direction = Direction.DOWN;
            }

        }
        System.out.println(this.direction.toString());
    }
    public void turn() {
        if(this.canMove()) {

        }
    }

    public int[] getPosFrontCell() {
        int pick_cell_x;
        int pick_cell_y;

        switch (this.direction) {
            case LEFT: pick_cell_x = this.cell_x -1; pick_cell_y = this.cell_y; break;
            case RIGHT: pick_cell_x = this.cell_x +1; pick_cell_y = this.cell_y; break;
            case UP: pick_cell_x = this.cell_x; pick_cell_y = this.cell_y -1; break;
            case DOWN: pick_cell_x = this.cell_x; pick_cell_y = this.cell_y +1; break;
            default: pick_cell_x = -1; pick_cell_y = -1; // Ne devrait pas arriver
        }
        System.out.println();
        return new int[]{pick_cell_x, pick_cell_y};
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public void damage(int power) {
        hp -= power;
    }

    public void hit() {
        this.weapon.hit(cell_x, cell_y);
    }

    public void pick() {

    }

    public void store() {

    }

    public void getEntity() {

    }

    public void power(int hp) {
        this.hp += hp;
    }

    public void throwAction() {

    }


}
