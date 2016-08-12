package com.techietitans.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by vinayjagan on 8/11/16.
 */
public class PushBotTester extends OpMode {

    public DcMotor leftMotor;
    public DcMotor rightMotor;
    public Servo servo;
    public double servoUp = 140D / 255D;
    public double servoDown = 0;

    @Override
    public void init() {
        leftMotor = hardwareMap.dcMotor.get("left_motor");
        rightMotor = hardwareMap.dcMotor.get("right_motor");
        leftMotor.setDirection(DcMotor.Direction.REVERSE);

        servo = hardwareMap.servo.get("servo");
    }

    @Override
    public void loop() {
        leftMotor.setPower(scaleInput(gamepad1.left_stick_y));
        rightMotor.setPower(scaleInput(gamepad1.right_stick_y));

        if (gamepad1.x)
            servo.setPosition(servoUp);
        if (gamepad1.b)
            servo.setPosition(servoDown);


    }

    double scaleInput(double dVal)  {
        double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };

        // get the corresponding index for the scaleInput array.
        int index = (int) (dVal * 16.0);

        // index should be positive.
        if (index < 0) {
            index = -index;
        }

        // index cannot exceed size of array minus 1.
        if (index > 16) {
            index = 16;
        }

        // get value from the array.
        double dScale = 0.0;
        if (dVal < 0) {
            dScale = -scaleArray[index];
        } else {
            dScale = scaleArray[index];
        }

        // return scaled value.
        return dScale;
    }

}
