import coppelia.FloatWA;

import java.util.Arrays;

public class Cuboid {

    private int id;
    private FloatWA coords;
    private String name;


    public Cuboid(int id, float x , float y, float z, String name){
        this.id = id;
        this.name = name;
        FloatWA fl = new FloatWA(3);
    }

    public Cuboid(String name, int id, FloatWA coords){
        this.id = id;
        this.name = name;
        this.coords = coords;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setX(float x){
        coords.getArray()[0] += x;
    }

    public void setY(float y){
        coords.getArray()[1] += y;
    }

    public void setZ(float z){
        coords.getArray()[2] += z;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public FloatWA getCoords() {
        return coords;
    }

    public String toString(){
        return "Name: " + getName() + ", ID: " + getId() + " ,Coordinates: " + Arrays.toString(coords.getArray());
    }
}
