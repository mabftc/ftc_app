package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class AutonomousHardwareController extends BaseHardwareController {
    // Instance variables
    private AutonomousHardware robot;
    private double speed;

    public static final double     DEFAULT_SPEED           = 0.50;

    public AutonomousHardwareController(LinearOpMode opMode, HardwareMap hwMap) {
        super(opMode, hwMap);
        super.init();
    }

    public void init() {
        // initialize hardware
        robot = new AutonomousHardware();
        robot.init(this.hwMap);

        this.speed = DEFAULT_SPEED;
    }

    // === Vuforia === //
    public char getSymbol() {
        return robot.getVuMark();
    }

    // === Sensors === //
    public char getJewelColor() {
        return robot.jewelColor();
    }

    public int[] testJewelColor () {
        return new int[]{robot.colorSensor.red(), robot.colorSensor.green(), robot.colorSensor.blue()};
    }

    /*
    public char getTileColor() {
        return robot.tileColor();
    }

    public void updateIMU() {
        robot.updateIMU();
    }

    public double getHeading() {
        return robot.getHeading();
    }*/

    // === Drive === //
    public void turn(int theta) {
        if (theta > 0) {
            // rightward motion increases reading from IMU
        } else {

        }
    }

    public void forward(int left, int right) {
        this.encoderDrive(left, right);
    }

    public void backward(int left, int right) {
        this.encoderDrive(-left, -right);
    }

    public void forward(int inches) {
        this.forward(inches, inches);
    }

    public void backward(int inches) {
        this.backward(inches, inches);
    }

    public void setOperationSpeed(double speed) {
        this.speed = speed;
    }

    public void setPower(double left, double right) {
        robot.left.setPower(-left);
        robot.right.setPower(-right);
    }

    public void brake() {
        robot.left.setPower(0);
        robot.right.setPower(0);
    }

    private void turnOffEncoders() {
        robot.left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    private void turnOnEncoders() {
        robot.left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    /**
     * Drives the motors forward / backward using motor encoders
     * Based heavily on encoderDrive(double speed, leftInches, rightInches)
     * by Robert Atkinson, (c) 2016
     * @param leftInches
     * @param rightInches
     */
     public void encoderDrive(double leftInches, double rightInches) {
        int newLeftTarget = (robot.left.getCurrentPosition() - (int) (leftInches * this.COUNTS_PER_INCH));
        int newRightTarget = (robot.right.getCurrentPosition() - (int) (rightInches * this.COUNTS_PER_INCH));

        turnOnEncoders();

        robot.left.setTargetPosition(newLeftTarget);
        robot.right.setTargetPosition(newRightTarget);

        if (leftInches > 0) robot.left.setPower(this.speed); else robot.left.setPower(-this.speed);
        if (rightInches > 0) robot.right.setPower(this.speed); else robot.right.setPower(-this.speed);

        while (robot.left.isBusy() || robot.right.isBusy());
        brake();
        turnOffEncoders();
    }
}