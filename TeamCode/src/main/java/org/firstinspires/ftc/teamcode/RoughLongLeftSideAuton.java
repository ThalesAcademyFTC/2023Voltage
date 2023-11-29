package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="RoughLongLeftSideAuton")
@Disabled
public class RoughLeftSideAuton extends LinearOpMode {

    private Spark robot;

    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new Spark(this, Spark.Drivetrain.MECHANUM);
        runtime.reset();

        waitForStart();
  
        double speed = 0.5;
        int rest = 100;

        //Code for auton
        robot.moveForwardInches(12 , speed);   //robot scans for the team spike mark
           
        
        //place purple pixel
        robot.sleep(rest); 
               
        robot.moveBackwardInches(9, speed);    
        robot.sleep(rest);
        
        robot.turnLeftDegrees(90, speed * 3/2);         
        robot.sleep(rest);
            
        robot.moveForwardInches(50 , speed); 
        robot.sleep(rest);
        
        robot.moveRightInches(24 , speed);  
        robot.sleep(rest);
        
        robot.moveForwardInches(6, speed);
        robot.sleep(rest);
        
        //place the  yellow pixel
        robot.sleep(rest);

        robot.moveLeftInches(24 , speed * 3/2);
        robot.sleep(rest);
        
        robot.moveForwardInches(18, speed);
        robot.sleep(rest);

        while (opModeIsActive() && runtime.milliseconds() > 30000){
            //If you want to use a loop, here's an example
        }
    }
}