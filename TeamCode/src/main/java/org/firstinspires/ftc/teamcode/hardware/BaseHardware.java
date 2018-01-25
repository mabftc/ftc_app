package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * A wrapper class around the HardwareMap included in
 * OpMode classes. Makes it easier to interface with hardware
 * on the robot.
 * @author mbowman
 * @version 10/02/2017
 */
public class BaseHardware {

    public DcMotor left;
    public DcMotor right;
    public DcMotor arm;

    public Servo leftGrip;
    public Servo rightGrip;
    public Servo jewelArm;


    public void init(HardwareMap hwMap) {
        // motors
        left = hwMap.dcMotor.get("left");
        right = hwMap.dcMotor.get("right");
        arm = hwMap.dcMotor.get("arm");

        left.setPower(0);
        right.setPower(0);
        left.setDirection(DcMotor.Direction.REVERSE);
        right.setDirection(DcMotor.Direction.FORWARD);
        left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        arm.setPower(0);
        arm.setDirection(DcMotor.Direction.REVERSE); // so that it rotates upward with a positive value
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftGrip = hwMap.get(Servo.class, "leftGrip");
        rightGrip = hwMap.get(Servo.class, "rightGrip");
        jewelArm = hwMap.get(Servo.class, "jewelArm");
    }
}