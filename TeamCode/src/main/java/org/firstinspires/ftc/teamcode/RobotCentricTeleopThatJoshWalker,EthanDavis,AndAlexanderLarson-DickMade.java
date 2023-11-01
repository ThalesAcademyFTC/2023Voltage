package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Spark;

@TeleOp()
public class RobotCentricTeleopThatJoshWalker,EthanDavis,AndAlexanderLarson-DickMade extends OpMode {

    Spark robot;
    //explain strafe factor here
    static final double STRAFE_FACTOR = 1.1;

    @Override
    public void init() {

        // INITIALIZE the library object
        robot = new Spark( this, Spark.Drivetrain.MECHANUM );

    }

    @Override
    public void loop() {

        //Buttons for actions on gamepad1
        
        //Right trigger arm up
        //Left trigger arm down
        //Right bumper claw open
        //Left bumper claw close


        //First, define some key variables for movement
        double y = -gamepad1.left_stick_y; // Y gamepad is reversed, so reverse this value
        double x = gamepad1.left_stick_x * STRAFE_FACTOR; // Scaling to fix
        double turn = gamepad1.right_stick_x; // Turn value
       
        //Now, set motor powers using x, y, and turn variables
        robot.move( x, y, turn ); /*pls explain how this work ??*/
        
        if (gamepad1.right_bumper){
            robot.claw(1);
        }
        if (gamepad1.left_bumper){
            robot.claw(0);

        }
    
    
        if (gamepad1.right_trigger > 0.3){
            robot.arm_up(0.5);
        }
        if (gamepad1.left_trigger > 0.3){
            robot.arm_down(-0.5);    
    
        }
    

    }    
}

