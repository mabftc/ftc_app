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

    public DcMotor arm;

    public Servo leftGrip;
    public Servo rightGrip;

    public ModernRoboticsI2cGyro gyro;

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

        this.arm = this.hwMap.dcMotor.get("arm");

        this.frontLeft.setPower(0);
        this.frontRight.setPower(0);
        this.backLeft.setPower(0);
        this.backRight.setPower(0);
        this.arm.setPower(0);

        this.backLeft.setDirection(DcMotor.Direction.REVERSE);
        this.frontLeft.setDirection(DcMotor.Direction.REVERSE);

        this.backRight.setDirection(DcMotor.Direction.FORWARD);
        this.frontRight.setDirection(DcMotor.Direction.FORWARD);

        this.arm.setDirection(DcMotor.Direction.FORWARD); // so that it rotates upward with a positive value

        this.leftGrip = hwMap.get(Servo.class, "leftGrip");
        this.rightGrip = hwMap.get(Servo.class, "rightGrip");
        this.leftGrip.setPosition(0.5);
        this.rightGrip.setPosition(0.5);

        // sensors
        gyro.calibrate();
        opMode.telemetry.addData("Update", "Calibrating gyro...");
        while (gyro.isCalibrating());
        gyro = (ModernRoboticsI2cGyro) hwMap.gyroSensor.get("gyro");
        gyro.resetZAxisIntegrator();

    }

    // sensors for sensor reading
    public int getHeading() {
        return gyro.getIntegratedZValue();
    }

}