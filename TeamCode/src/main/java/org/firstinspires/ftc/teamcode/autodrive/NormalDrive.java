package org.firstinspires.ftc.teamcode.autodrive;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.OmniHardware;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.Hardware;

public class NormalDrive implements Drive {
    protected LinearOpMode robotOpMode;
    protected OmniHardware hardware;
    protected double speed;

    public static final int COUNTS_PER_INCH = 1440; // for andymark motors

    public NormalDrive(LinearOpMode robotOpMode, Hardware hardware) {
        this.robotOpMode = this.robotOpMode;
        this.hardware = (OmniHardware) hardware;
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
            newLeftTarget = (this.hardware.frontLeft.getCurrentPosition() + (int) (leftInches * this.COUNTS_PER_INCH) + this.hardware.backLeft.getCurrentPosition() + (int) (leftInches * this.COUNTS_PER_INCH)) / 2;
            newRightTarget = (this.hardware.frontRight.getCurrentPosition() + (int) (rightInches * this.COUNTS_PER_INCH) + this.hardware.backRight.getCurrentPosition() + (int) (rightInches * this.COUNTS_PER_INCH)) / 2;

            this.hardware.frontLeft.setTargetPosition(newLeftTarget);
            this.hardware.frontRight.setTargetPosition(newRightTarget);
            this.hardware.backLeft.setTargetPosition(newLeftTarget);
            this.hardware.backRight.setTargetPosition(newRightTarget);

            // Turn On RUN_TO_POSITION
            this.hardware.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.hardware.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.hardware.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.hardware.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            this.hardware.frontLeft.setPower(Math.abs(this.speed));
            this.hardware.frontRight.setPower(Math.abs(this.speed));
            this.hardware.backLeft.setPower(Math.abs(this.speed));
            this.hardware.backRight.setPower(Math.abs(this.speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            //while (this.robotOpMode.opModeIsActive() && (this.hardware.leftMotor.isBusy() || this.hardware.rightMotor.isBusy()));

            // Stop all motion
            //brake();

            // Turn off RUN_TO_POSITION
            //this.hardware.leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            //this.hardware.rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }
}