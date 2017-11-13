package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
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
    private boolean usingEncoders;
    private HardwareMap hwMap;
    private OpMode opMode;

    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;

    public DcMotor armLeft;
    public DcMotor armRight;

    public Servo leftGrip;
    public Servo rightGrip;

    // sensors
    //public ModernRoboticsI2cGyro gyro;

    public Hardware(OpMode opMode) {
        this.opMode = opMode;
    }

    public void init(HardwareMap hwMap, boolean usingEncoders) {
        opMode.telemetry.addData("Update", "Initializing hardware...");
        this.hwMap = hwMap;
        this.usingEncoders = usingEncoders;

        // motors
        this.frontLeft = this.hwMap.dcMotor.get("frontLeft");
        this.frontRight = this.hwMap.dcMotor.get("frontRight");
        this.backLeft = this.hwMap.dcMotor.get("backLeft");
        this.backRight = this.hwMap.dcMotor.get("backRight");

        this.armLeft = this.hwMap.dcMotor.get("armLeft");
        this.armRight = this.hwMap.dcMotor.get("armRight");


        this.frontLeft.setPower(0);
        this.frontRight.setPower(0);
        this.backLeft.setPower(0);
        this.backRight.setPower(0);
        this.backLeft.setDirection(DcMotor.Direction.REVERSE);
        this.frontLeft.setDirection(DcMotor.Direction.REVERSE);
        this.backRight.setDirection(DcMotor.Direction.FORWARD);
        this.frontRight.setDirection(DcMotor.Direction.FORWARD);
        this.frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

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

        // sensors
        /*opMode.telemetry.addData("Update", "Calibrating gyro...");
        while (gyro.isCalibrating());
        gyro = (ModernRoboticsI2cGyro) hwMap.gyroSensor.get("gyro");
        gyro.calibrate();
        gyro.resetZAxisIntegrator();*/

    }

    /* sensors for sensor reading
    public int getHeading() {
        return gyro.getIntegratedZValue();
    }*/

    public HardwareMap getHwMap() { return hwMap; }

}