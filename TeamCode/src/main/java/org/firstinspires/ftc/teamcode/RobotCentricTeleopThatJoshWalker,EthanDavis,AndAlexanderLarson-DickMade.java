package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Spark;

@TeleOp()
public class RobotCentricTeleopThatJoshWalkerEthanDavisAndAlexanderLarsonDickMade extends OpMode {

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
        
        

        if (gamepad2.right_trigger > 0.5) {
            robot.servoClose();
        } else robot.servoPrepare();
            //Moves arm down
    
        if (gamepad2.dpad_up) {
            robot.armMotor.setPower(1);
        } else{
            robot.armMotor.setPower(0);
        } 

        if (-gamepad1.left_stick_x){
            //robot.move();
            //Movement to go right
        }
        if (gamepad1.left_stick_x){
            //robot.move();
            //Movement to go left
        }
        if(gamepad1.left_stick_y){
            //robot.move();
            //Movement to go up   
        }
        if(gamepad1.left_stick_y){
            //robot.move();
            //Movement to go down
        }
    
    

//keep this at end
        if (gamepad1.atRest()) robot.rest(){   

    }
    
    
    }  

}  


