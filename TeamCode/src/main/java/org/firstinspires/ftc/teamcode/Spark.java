package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

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
        MECHANUM,
        MISTRO
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

    public DcMotor motorFrontLeft, motorFrontRight, motorBackLeft, motorBackRight;

    public DcMotor armMotor, spinnyMotor;

    public Servo clawServo;

    public Servo smallArmServo;

    public Servo crabServo;

    private IMU imu;

    private IMU.Parameters parameters;

    // Put CONSTANTS here

    /** Constant for the claw open position */
    static final double OPEN_CLAW_POSITION = 0.25;

    /** Constant for the close claw position */
    static final double CLOSE_CLAW_POSITION = 0;
    
    /** Consant for small arm servo depositing position */
    static final double DEPOSIT_ARM_POSITION = -1;
    
    /** Costant for resetting the small arm servo position */
    static final double RESET_ARM_POSITION = 0.78;

    /** Constant for pinching a pixel with the large arm */  
    static final double PINCH_CLAW_POSITION = 0.7;

    /** Constant for releasing a pixel with the large arm */
    static final double UNPINCH_CLAW_POSITION = 0;
       
    /** Encoder ticks for an INCH */

    static final double INCH_TICKS = 40;

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
     * This constructor is used for testing the hardwareMap and drivetrain
     * @param hardwareMap the hardwareMap being tested
     * @param drivetrain the drivetrain being tested
     */
    public Spark( HardwareMap hardwareMap, Drivetrain drivetrain ) {

        this.hwMap = hardwareMap;

        this.drive = drivetrain;

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
            case MISTRO:

                //First, setup the motors that are used for the drivetrain
                motorFrontLeft = hwMap.dcMotor.get( "motorFrontLeft" );
                motorFrontRight = hwMap.dcMotor.get( "motorFrontRight" );
                motorBackLeft = hwMap.dcMotor.get( "motorBackLeft" );
                motorBackRight = hwMap.dcMotor.get( "motorBackRight" );

                //Next, reverse motors that need to spin the other direction
                // Tip: All motors should move the robot forward if set to power 1
                motorBackLeft.setDirection(DcMotorSimple.Direction.REVERSE);

                //Here would go any additional hardware devices for the robot

                // Map the imu to the hardware device
                imu = hwMap.get( IMU.class, "imu" );

                //Set parameters for the imu.
                //Check the direction that the logo is facing.
                //Check the direction that the USB plugs are facing
                parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.FORWARD,
                        RevHubOrientationOnRobot.UsbFacingDirection.LEFT)
                );

                imu.initialize( parameters );

                //Add arm mechanism hardware devices

                armMotor = hwMap.dcMotor.get( "armMotor" );
                clawServo = hwMap.servo.get( "clawServo" );
                smallArmServo = hwMap.servo.get( "smallArmServo" );
                crabServo = hwMap.servo.get( "crabServo" );
                
                break;

            case MECHANUM:

                //First, setup the motors that are used for the drivetrain
                motorFrontLeft = hwMap.dcMotor.get( "motorFrontLeft" );
                motorFrontRight = hwMap.dcMotor.get( "motorFrontRight" );
                motorBackLeft = hwMap.dcMotor.get( "motorBackLeft" );
                motorBackRight = hwMap.dcMotor.get( "motorBackRight" );

                //Next, reverse motors that need to spin the other direction
                // Tip: All motors should move the robot forward if set to power 1
                //motorBackLeft.setDirection(DcMotorSimple.Direction.REVERSE);

                //Here would go any additional hardware devices for the robot

                // Map the imu to the hardware device
                imu = hwMap.get( IMU.class, "imu" );

                //Set parameters for the imu.
                //Check the direction that the logo is facing.
                //Check the direction that the USB plugs are facing
                parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                        RevHubOrientationOnRobot.LogoFacingDirection.FORWARD,
                        RevHubOrientationOnRobot.UsbFacingDirection.LEFT)
                );

                imu.initialize( parameters );

                //Add arm mechanism hardware devices

                armMotor = hwMap.dcMotor.get( "armMotor" );
                clawServo = hwMap.servo.get( "clawServo" );
                smallArmServo = hwMap.servo.get( "smallArmServo" );
                crabServo = hwMap.servo.get( "crabServo" );
                




                break;

            default:

                //Here would go what would happen if drive is not one of the options above.
                //Really, this just means you made a coding mistake. So nothing needs to go here.
                telem.addLine("Invalid type " + drive + " passed to Spark's init function. Nothing has been set up.");
                break;

        }

    }

    /**
     * Set motor power for all drivetrain motors on robot to 0
     */
    public void rest() {
        motorBackLeft.setPower( 0 );
        motorBackRight.setPower( 0 );
        motorFrontLeft.setPower( 0 );
        motorFrontRight.setPower( 0 );
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
                double frontLeftPower = ( y - x - turn ) / denominator;
                double backLeftPower = ( - y + x - turn ) / denominator;
                double frontRightPower = ( - y - x - turn ) / denominator;
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

    /**
     * Sets the arm motor to the given power
     * @param power the power to send to the arm motor
     */
    public void setArmMotor( double power ) {
        armMotor.setPower( power );
    }

    /**
     * Opens the clawServo using the CONSTANT OPEN_CLAW_POSITION
     * Defined near top of Spark
     */
    public void openClaw() {
        clawServo.setPosition( OPEN_CLAW_POSITION );
    }

    /**
     * Closes the clawServo using the CONSTANT CLOSE_CLAW_POSITION
     * Defined near top of Spark
     */

    public void closeClaw() {
        clawServo.setPosition( CLOSE_CLAW_POSITION );
    }
    

    /**
     * Set the claw servo to the given position
     * @param position the position to set the claw servo to
     */
    public void setClawServo( double position ) {
        clawServo.setPosition( position );
    }
    
    public void smallArmDeposit() {
        smallArmServo.setPosition( DEPOSIT_ARM_POSITION );
    }
    
    public void smallArmReset() {
        smallArmServo.setPosition( RESET_ARM_POSITION );
    }

    public void crabServoPinch() {
        crabServo.setPosition( PINCH_CLAW_POSITION );
    }
    
    public void crabServoUnPinch() {
        crabServo.setPosition( UNPINCH_CLAW_POSITION );
            
    }

    /* AUTON functions!
    public void turnRightFT(int ticks, double speed) {
        //Blocks until the robot has gotten to the desired location.
        resetDriveEncoders();

        for(DcMotor x: left){
            x.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            x.setTargetPosition(ticks);
        }
        for(DcMotor x: right){
            x.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            x.setTargetPosition(-ticks);
        }

        this.turnRight(speed);
        waitForMotors();

        resetDriveEncoders();
    }

    public void turnLeftFT(int ticks, double speed) {
        //Blocks until the robot has gotten to the desired location.
        resetDriveEncoders();

        for(DcMotor x: left){
            x.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            x.setTargetPosition(-ticks);
        }
        for(DcMotor x: right){
            x.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            x.setTargetPosition(ticks);
        }
        this.turnLeft(speed);
        waitForMotors();

        resetDriveEncoders();
    }

    public void moveRightFT(int ticks, double speed) {
        //Blocks until the robot has gotten to the desired location.

        resetDriveEncoders();

        for(DcMotor x: special){
            x.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            x.setTargetPosition(-ticks);
        }

        for(DcMotor x: unique){
            x.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            x.setTargetPosition(ticks);
        }

        this.moveRight(speed);

        waitForMotors();

        resetDriveEncoders();

    }

    public void moveLeftFT(int ticks, double speed) {
        //Blocks until the robot has gotten to the desired location.

        resetDriveEncoders();

        for(DcMotor x: special){
            x.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            x.setTargetPosition(ticks);
        }
        for(DcMotor x: unique){
            x.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            x.setTargetPosition(-ticks);
        }
        this.moveLeft(speed);
        waitForMotors();

        resetDriveEncoders();

    }

    public void moveForwardFT(int ticks, double speed) {
        //Blocks until the robot has gotten to the desired location.
        resetDriveEncoders();

        for(DcMotor x: forward){
            x.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            x.setTargetPosition(ticks);
        }
        this.moveForward(speed);
        waitForMotors();

        resetDriveEncoders();

    }

    public void moveBackwardFT(int ticks, double speed) {
        //Blocks until the robot has gotten to the desired location.

        resetDriveEncoders();

        for(DcMotor x: forward){
            x.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            x.setTargetPosition(-ticks);
        }
        this.moveBackward(speed);

        waitForMotors();

        resetDriveEncoders();
    }
    */

    public void moveDistance ( double x, double y, double turn, double distance ) {

        // Use encoder ticks to move in a certain direction. Somehow calculate the ticks needed.





    }



}