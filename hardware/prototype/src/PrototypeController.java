import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import coppelia.*;

import static java.lang.Thread.sleep;

public class PrototypeController {

    public static List<Cuboid> cuboids = new ArrayList<Cuboid>();
    public static String[] names = new String[]{"Base", "Middle", "Head"};

    public static void readCubes(int clientID, remoteApi vrep){
        FloatWA out = new FloatWA(3);
        IntW cuboid_handle = new IntW(1);
        if (clientID != -1) {
            for (String name: names) {
                int obj = vrep.simxGetObjectHandle(clientID, name, cuboid_handle, vrep.simx_opmode_oneshot_wait);
                if (obj == vrep.simx_return_ok) {
                    System.out.println("Name: " + name + "  has ID: " + cuboid_handle.getValue());
                    vrep.simxGetObjectPosition(clientID, cuboid_handle.getValue(), -1, out, vrep.simx_opmode_blocking);
                    System.out.println("Coordinates: " + Arrays.toString(out.getArray()));
                    Cuboid c = new Cuboid(name, cuboid_handle.getValue(), out);
                    System.out.println("Coordinates: " + Arrays.toString(out.getArray()));
                    cuboids.add(c);
                } else {
                    System.out.println("ERROR");
                }
            }
        }
    }

    public static void main(String[] args) {

        remoteApi vrep = new remoteApi();
        int clientID = vrep.simxStart("127.0.0.1", 19997, true, true, 5000, 5);

        readCubes(clientID, vrep);
        System.out.println(cuboids);

        IntW spin_id = new IntW(1);
        var code = vrep.simxGetObjectHandle(clientID, "Spinning", spin_id, vrep.simx_opmode_oneshot_wait);

        if (code == vrep.simx_return_ok){
            vrep.simxStartSimulation(clientID, vrep.simx_opmode_oneshot_wait);
            vrep.simxSetJointTargetVelocity(clientID, spin_id.getValue(), 0.1f, vrep.simx_opmode_blocking);
        }
        else{
            System.out.println("ERROR motor");
            System.out.println(code);
        }
    }
}
47xz