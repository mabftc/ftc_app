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

@Autonomous(name = "Controller Only Autonomous", group = "Autonomous")
public class DriveOnly extends LinearOpMode {
    Hardware robot = new Hardware(this); // Initialize hardware.
    double test = 0;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initializing...");
        telemetry.update();
        robot.init(hardwareMap, false);
        telemetry.addData("Status", "All systems online.");
        telemetry.update();

        waitForStart(); // Wait until ready.

        this.robot.frontLeft.setPower(1);
        this.robot.frontRight.setPower(1);
        this.robot.backLeft.setPower(1);
        this.robot.backRight.setPower(1);

        sleep(1000);

        this.robot.frontLeft.setPower(0);
        this.robot.frontRight.setPower(0);
        this.robot.backLeft.setPower(0);
        this.robot.backRight.setPower(0);

        while (opModeIsActive());

        telemetry.addData("DATA", "Okay");
    }
}