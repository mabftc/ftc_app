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

    public DcMotor arm;

    public Servo grip;

    // sensors
    //public ModernRoboticsI2cGyro gyro;

    public Hardware() {

    }

    public void init(HardwareMap hwMap) {
        this.hwMap = hwMap;

        // motors
        left = this.hwMap.dcMotor.get("left");
        right = this.hwMap.dcMotor.get("right");

        arm = this.hwMap.dcMotor.get("arm");

        left.setPower(0);
        right.setPower(0);
        left.setDirection(DcMotor.Direction.REVERSE);
        right.setDirection(DcMotor.Direction.FORWARD);
        left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        arm.setPower(0);
        arm.setDirection(DcMotor.Direction.FORWARD); // so that it rotates upward with a positive value
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        grip = hwMap.get(Servo.class, "grip");
        grip.setDirection(Servo.Direction.FORWARD);
        grip.setPosition(0);

    }

    public int getHeading() {
        return 0;
    }

    public HardwareMap getHwMap() { return hwMap; }

}