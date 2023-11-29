package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="RoughRightSideAuton")
@Disabled
public class RoughRightSideAuton extends LinearOpMode {

    private Spark robot;

    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Spark(this, Spark.Drivetrain.MECHANUM);
        runtime.reset();

        waitForStart();

        //variables for auton

        double speed = 0.5;
        int rest = 100;

        //robot moves forward, scans for the spike mark, and places purple pixel
        //more code needed where indicated

        robot.moveForwardInches(12, speed);
        robot.rest(rest);

        /*robot scans for spike mark
        robot rests

        robot places purple pixel
        robot rests*/


        //robot moves back and approaches backboard

        robot.moveBackwardInches(9, speed);
        robot.rest(rest);

        robot.turnRightDegrees(90, 0.75);
        robot.rest(rest);

        robot.moveForwardInches(24, speed);
        robot.rest(rest);

        robot.moveRightInches(24, speed);
        robot.rest(rest);


        //robot scans for the right april tag, moves forward to the right position, and places the yellow pixel
        //more code needed where indicated

        //robot scans for the right april tag
        robot.rest(rest);

        robot.moveForwardInches(6, speed);
        robot.rest(rest);

        //robot turns to the right position
        robot.rest(rest);

        //robot places yellow pixel
        robot.rest(rest);


        //robot then moves into the parking area

        robot.moveLeftInches(24, speed);
        robot.rest(rest);

        robot.moveForwardInches(18, speed);

        /end 

        while (opModeIsActive() && runtime.milliseconds() > 30000){
            //If you want to use a loop, here's an example
        }
    }
}
//name robot billy dignam            