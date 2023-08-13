import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Spark;
import org.junit.jupiter.api.Test;

import java.io.File;

import fakeHardware.FakeHardwareMapFactory;

/**
 * This class is meant to ensure that all of the motors listed in the file are on the
 * hardwareMap. Only works for XML file hardwareMaps.
 */
public class HardwareMapTest {

    @Test
    public void sampleHardwareMapTest() {
        File hardwareMapFile = new File("src/test/res/xml/sample_hardware_map.xml");
        HardwareMap hwMap = new HardwareMap(null,null);

        try {
            hwMap = FakeHardwareMapFactory.getFakeHardwareMap(hardwareMapFile);
        } catch (Exception e) {
            fail(e.getMessage());
        }

        HardwareMap finalHwMap = hwMap;
        class TeleTest extends OpMode {

            public TeleTest() {
                this.hardwareMap = finalHwMap;
            }

            @Override
            public void init() {
            }

            @Override
            public void loop() {

            }
        }

        TeleTest tele = new TeleTest();



        Spark robot = new Spark( tele, Spark.Drivetrain.MECHANUM );

        robot.rest();

        assertEquals(robot.motorFrontLeft.getPower(), 0);
        assertEquals(robot.motorFrontRight.getPower(), 0);
        assertEquals(robot.motorBackLeft.getPower(), 0);
        assertEquals(robot.motorBackRight.getPower(), 0);

        robot.move( 0, 1, 0 );

        assertEquals(robot.motorFrontLeft.getPower(), 1);
        assertEquals(robot.motorFrontRight.getPower(), 1);
        assertEquals(robot.motorBackLeft.getPower(), 1);
        assertEquals(robot.motorBackRight.getPower(), 1);


        robot.move( -0.5, 0, 0 );

        assertEquals(robot.motorFrontLeft.getPower(), -0.5);
        assertEquals(robot.motorFrontRight.getPower(), 0.5);
        assertEquals(robot.motorBackLeft.getPower(), -0.5);
        assertEquals(robot.motorBackRight.getPower(), 0.5);

        robot.rest();

        assertEquals(robot.motorFrontLeft.getPower(), 0);
        assertEquals(robot.motorFrontRight.getPower(), 0);
        assertEquals(robot.motorBackLeft.getPower(), 0);
        assertEquals(robot.motorBackRight.getPower(), 0);


    }


}
