package org.firstinspires.ftc.teamcode.autodrive;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.hardware.Hardware;
import org.firstinspires.ftc.teamcode.hardware.RobotHardware;

public class NormalDrive implements Drive {
    protected LinearOpMode robotOpMode;
    protected Hardware robot;
    protected double speed;

    public static final int COUNTS_PER_INCH = 1440; // for andymark motors

    public NormalDrive(LinearOpMode robotOpMode, Hardware hardware) {
        this.robotOpMode = this.robotOpMode;
        this.robot = new RobotHardware();
        this.speed = 0;
    }

    public void turn(int theta) {

    }

    public void forward(int left, int right) {

    }

    public void backward(int left, int right) {

    }

    public void forward(int inches) {

    }

    public void backward(int inches) {

    }

    public void setOperationSpeed(double speed) {
        this.speed = speed;
    }

    public void setPower(double power) {

    }

    public void setPower(double left, double right) {

    }

    public void brake() {

    }

    /**
     * Drives the motors forward / backward using motor encoders
     * Based heavily on encoderDrive(double speed, leftInches, rightInches)
     * by Robert Atkinson, (c) 2016
     * @param leftInches
     * @param rightInches
     */
    private void encoderDrive(double leftInches, double rightInches) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the OpMode is still active
        if (this.robotOpMode.opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            // Mitigate encoder error by averaging
            newLeftTarget = (this.robot.frontLeft.getCurrentPosition() + (int) (leftInches * this.COUNTS_PER_INCH) + this.robot.backLeft.getCurrentPosition() + (int) (leftInches * this.COUNTS_PER_INCH)) / 2;
            newRightTarget = (this.robot.frontRight.getCurrentPosition() + (int) (rightInches * this.COUNTS_PER_INCH) + this.robot.backRight.getCurrentPosition() + (int) (rightInches * this.COUNTS_PER_INCH)) / 2;

            this.robot.frontLeft.setTargetPosition(newLeftTarget);
            this.robot.frontRight.setTargetPosition(newRightTarget);
            this.robot.backLeft.setTargetPosition(newLeftTarget);
            this.robot.backRight.setTargetPosition(newRightTarget);

            // Turn On RUN_TO_POSITION
            this.robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.robot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.robot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            this.robot.frontLeft.setPower(Math.abs(this.speed));
            this.robot.frontRight.setPower(Math.abs(this.speed));
            this.robot.backLeft.setPower(Math.abs(this.speed));
            this.robot.backRight.setPower(Math.abs(this.speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            while (this.robotOpMode.opModeIsActive() && (this.robot.frontLeft.isBusy() || this.robot.frontRight.isBusy()));

            // Stop all motion
            brake();

            // Turn off RUN_TO_POSITION
            this.robot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            this.robot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            this.robot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            this.robot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }
}