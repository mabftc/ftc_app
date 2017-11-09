package org.firstinspires.ftc.teamcode;
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

@Autonomous(name = "System Test OpMode", group = "Autonomous")
public class TestAuto extends LinearOpMode {
    Hardware robot = new Hardware(this); // Initialize hardware.

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initializing...");
        telemetry.update();
        robot.init(hardwareMap, false);
        telemetry.addData("Status", "All systems online.");
        telemetry.update();

        waitForStart(); // Wait until ready.

        robot.frontLeft.setPower(1);
        robot.backLeft.setPower(1);

        while (opModeIsActive());

        telemetry.addData("DATA", "Okay");
    }
}