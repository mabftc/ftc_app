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
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Hardware.Hardware;

@Autonomous(name = "System Test OpMode", group = "Autonomous")
public class TestAuto extends LinearOpMode {
    Hardware.DriveHardware robot = new Hardware.DriveHardware(); // Initialize hardware.
    private ElapsedTime runtime;


    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initializing...");
        telemetry.update();
        robot.init(hardwareMap, false);
        telemetry.addData("Status", "All systems online.");
        telemetry.update();

        waitForStart(); // Wait until ready.
        runtime = new ElapsedTime(); // Start the timer

        robot.backLeft.setPower(1);
        robot.frontLeft.setPower(1);
        robot.frontRight.setPower(1);
        robot.backRight.setPower(1);

        while (opModeIsActive());

        telemetry.addData("DATA", "Okay");
        /*drive.move(10);
        drive.turn(90);
        drive.turn(-90);
        drive.turn(0);
        drive.turn(359);
        drive.turn(1);*/
    }
}