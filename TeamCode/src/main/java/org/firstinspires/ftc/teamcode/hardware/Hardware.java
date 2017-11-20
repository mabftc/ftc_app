package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

/**
 * A wrapper class around the HardwareMap included in
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
    public Servo jewelArm;

    public ModernRoboticsI2cColorSensor colorSensor;

    public void init(HardwareMap hwMap) {
        this.hwMap = hwMap;

        // motors
        left = hwMap.dcMotor.get("left");
        right = hwMap.dcMotor.get("right");

        arm = hwMap.dcMotor.get("arm");

        colorSensor = (ModernRoboticsI2cColorSensor) hwMap.colorSensor.get("jewel");

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
        jewelArm = hwMap.get(Servo.class, "jewelArm");
        jewelArm.setDirection(Servo.Direction.FORWARD);
        jewelArm.setPosition(0);

    }

    public int getHeading() {
        return 0;
    }

    public HardwareMap getHwMap() { return hwMap; }

}