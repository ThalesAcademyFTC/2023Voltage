package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class Spark {

    /**
     * DECLARE the HardwareMap object used for this library
     * A HardwareMap is a mapping of the different hardware components to the
     * name set in the configuration
     */
    private HardwareMap hwMap;

    /**
     * An ENUM is a custom variable type that we can define the options for
     * This variable can be declared as a type Drivetrain, and then can be set to one of the options
     * listed in the list inside {}
     */
    public enum Drivetrain {
        MECHANUM
    }

    /**
     * Here is an example of us DECLARING the Drivetrain variable type drive
     * This drive can be set to any values inside of the Drivetrain enum list above
     */
    private Drivetrain drive;

    /**
     * This is the telemetry object that the library will be using to write to the driver hub
     * Telemetry is how we output to the drivers while they are using the robot
     * Also can be used for us to debug
     */
    private Telemetry telem;

    // Now, below you can define any global variables that you want to use in this library file.

    private DcMotor motorFrontLeft, motorFrontRight, motorBackLeft, motorBackRight;

    private DcMotor[] forward, left, right;

    private IMU imu;

    /**
     * The CONSTRUCTOR for the library class. This constructor pulls the HardwareMap from the opmode
     * and runs the setupHardware function
     * @param opmode the opmode that is being used. NOTE: This only works for TELEOP opmodes, not AUTON
     */
    public Spark( OpMode opmode, Drivetrain drivetrain ) {

        //Set the hardwaremap for this library to the opmode's hardwareMap
        this.hwMap = opmode.hardwareMap;

        // Set the drive in the library to the opmode's selected drivetrain
        this.drive = drivetrain;

        this.telem = opmode.telemetry;

        // Run the setupHardware function to map variables to their hardware object
        setupHardware();

    }

    /**
     * This function maps the variables declared above to a specific hardware object,
     * as defined by the configuration on the driver hub
     * Note: This function can only be used inside the library, since it is private
     */
    private void setupHardware() {

        // This switch statement is used to choose which drivetrain to setup,
        // depending on the drive variable.
        switch ( drive ) {

            // If drive is MECHANUM, then everything in here will setup the MECHANUM hardware variables
            case MECHANUM:

                //First, setup the motors that are used for the drivetrain
                motorFrontLeft = hwMap.dcMotor.get( "motorFrontLeft" );
                motorFrontRight = hwMap.dcMotor.get( "motorFrontRight" );
                motorBackLeft = hwMap.dcMotor.get( "motorBackLeft" );
                motorBackRight = hwMap.dcMotor.get( "motorBackRight" );

                //Next, reverse motors that need to spin the other direction
                // Tip: All motors should move the robot forward if set to power 1
                motorFrontRight.setDirection(DcMotorSimple.Direction.REVERSE);
                motorBackRight.setDirection(DcMotorSimple.Direction.REVERSE);

                //Next, add those motors to their arrays

                //The forward array contains all of the motors that need to move forward for the robot
                //to move forward
                forward = new DcMotor[]{ motorFrontLeft, motorFrontRight, motorBackLeft, motorBackRight };

                //The left array contains the motors that need to move forward for the robot to move left
                left = new DcMotor[]{ motorFrontRight, motorBackRight };

                //The right array contains the motors that need to move forward for the robot to move right
                right = new DcMotor[] { motorFrontLeft, motorBackLeft };

                //Here would go any additional hardware devices for the robot

                // Map the imu to the hardware device
                imu = hwMap.get( IMU.class, "imu" );

                //Set parameters for the imu.
                //Check the direction that the logo is facing.
                //Check the direction that the USB plugs are facing
                IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.FORWARD,
                        RevHubOrientationOnRobot.UsbFacingDirection.LEFT)
                );

                imu.initialize( parameters );



                break;

            default:

                //Here would go what would happen if drive is not one of the options above.
                //Really, this just means you made a coding mistake. So nothing needs to go here.
                telem.addLine("Invalid type " + drive + " passed to Spark's init function. Nothing has been set up.");
                break;

        }

    }

    /**
     * This function controls movement for the robot.
     * @param x the x speed value
     * @param y the y speed value
     * @param turn the turn speed value
     */
    public void move( double x, double y, double turn ) {

        switch ( drive ) {

            case MECHANUM:

                // Denominator is the largest motor power (absolute value) or 1
                // This ensures all the powers maintain the same ratio, but only when
                // at least one is out of the range [-1, 1]
                double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(turn), 1);

                // Save values for the power of each motor
                double frontLeftPower = ( y + x + turn ) / denominator;
                double backLeftPower = ( y - x + turn ) / denominator;
                double frontRightPower = ( y - x - turn ) / denominator;
                double backRightPower = ( y + x - turn ) / denominator;

                //Now, assign that motor power to each motor
                motorFrontLeft.setPower( frontLeftPower );
                motorBackLeft.setPower( backLeftPower );
                motorFrontRight.setPower( frontRightPower );
                motorBackRight.setPower( backRightPower );

                break;

        }

    }

    /**
     * Resets the yaw of the IMU. This fixes any deviation that might occur, but assumes the robot is
     * back in starting orientation.
     */
    public void resetYaw() {
        imu.resetYaw();
    }

    /**
     * Gets the yaw heading in RADIANS
     * @return the yaw heading in radians
     */
    public double getHeading() {
        return imu.getRobotYawPitchRollAngles().getYaw( AngleUnit.RADIANS );
    }

}
