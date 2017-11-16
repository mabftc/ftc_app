package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * A wrapper class around the HardwareMap inhereted from
 * OpMode classes. Makes it easier to interface with hardware
 * on the robot.
 * @author mbowman
 * @version 10/02/2017
 */
public class Hardware {
    private HardwareMap hwMap;

    public DcMotor left;
    public DcMotor right;

    public DcMotor armLeft;
    public DcMotor armRight;

    public Servo leftGrip;
    public Servo rightGrip;

    // sensors
    //public ModernRoboticsI2cGyro gyro;

    public Hardware() {

    }

    public void init(HardwareMap hwMap) {
        this.hwMap = hwMap;

        // motors
        this.left = this.hwMap.dcMotor.get("left");
        this.right = this.hwMap.dcMotor.get("right");

        this.armLeft = this.hwMap.dcMotor.get("armLeft");
        this.armRight = this.hwMap.dcMotor.get("armRight");


        this.left.setPower(0);
        this.right.setPower(0);
        this.left.setDirection(DcMotor.Direction.REVERSE);
        this.right.setDirection(DcMotor.Direction.FORWARD);
        this.left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        this.armRight.setPower(0);
        this.armLeft.setPower(0);
        this.armLeft.setDirection(DcMotor.Direction.FORWARD); // so that it rotates upward with a positive value
        this.armRight.setDirection(DcMotor.Direction.REVERSE);
        this.armRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.armLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        this.leftGrip = hwMap.get(Servo.class, "leftGrip");
        this.rightGrip = hwMap.get(Servo.class, "rightGrip");
        this.leftGrip.setDirection(Servo.Direction.FORWARD);
        this.rightGrip.setDirection(Servo.Direction.REVERSE);
        this.leftGrip.setPosition(0);
        this.rightGrip.setPosition(0);

    }

    public int getHeading() {
        return 0;
    }

    public HardwareMap getHwMap() { return hwMap; }

}