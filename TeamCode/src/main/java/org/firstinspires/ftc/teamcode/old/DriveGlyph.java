package org.firstinspires.ftc.teamcode.old;
/**
 * Test class: Gyro and Elapsed time Testing
 * A sandbox OpMode to test new systems
 * Makes it easier to create multiple autonomous strategies.
 * @author Max Bowman
 * @version 4/13/17
 */

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.hardware.Hardware;

@Autonomous(name = "Glyph Autonomous", group = "Autonomous")
public class DriveGlyph extends LinearOpMode {
    Hardware robot = new Hardware(this); // Initialize hardware.

    private final double LEFT_CLOSED = 0.2;
    private final double RIGHT_CLOSED = 0.8;
    private final double LEFT_OPEN = 0;
    private final double RIGHT_OPEN = 0;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initializing...");
        telemetry.update();
        robot.init(hardwareMap, false);
        telemetry.addData("Status", "All systems online.");
        telemetry.update();

        waitForStart(); // Wait until ready

        grip();

        this.robot.frontLeft.setPower(-1);
        this.robot.frontRight.setPower(-1);
        this.robot.backLeft.setPower(-1);
        this.robot.backRight.setPower(-1); // drive forward

        sleep(1000);

        release();

        this.robot.frontLeft.setPower(0.2);
        this.robot.frontRight.setPower(0.2);
        this.robot.backLeft.setPower(0.2);
        this.robot.backRight.setPower(0.2);

        sleep(25);

        this.robot.frontLeft.setPower(0);
        this.robot.frontRight.setPower(0);
        this.robot.backLeft.setPower(0);
        this.robot.backRight.setPower(0);

        while (opModeIsActive());

        telemetry.addData("DATA", "Okay");
    }

    public void grip() {
        robot.rightGrip.setPosition(RIGHT_CLOSED);
        robot.leftGrip.setPosition(LEFT_CLOSED);
    }

    public void release() {
        robot.rightGrip.setPosition(RIGHT_OPEN);
        robot.leftGrip.setPosition(LEFT_OPEN);
    }
}