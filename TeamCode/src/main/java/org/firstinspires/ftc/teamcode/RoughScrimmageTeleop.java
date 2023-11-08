package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp()


public class TeleopFileThatNeedsBetterName extends OpMode {

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
        
        //Movement
        robot.move( x, y, turn);
        
        //Claw open/close
        if (gamepad2.right_trigger > 0.3) {
            robot.openClaw();
        
        } else if (gamepad2.left_trigger < 0.3) {
            robot.closeClaw();
        
        }


        //small arm
        if ( gamepad2.right_bumper() ) {
            robot.smallArmServo(0.5);
            
      } else if ( gamepad2.left_bumper() ) {
            robot.smallArmServo(-0.5);
        
        }

        
        //Moves arm up
        if ( gamepad2.right_stick_y > 0.3 ){
           robot.setArmMotor(0.5);
        
        } else if ( gamepad2.right_stick_y < -0.3 ){
            robot.setArmMotor(-0.5);
        
        }



//keep this at end
        if (gamepad1.atRest()) robot.rest();
    
    }  

}
//among us 