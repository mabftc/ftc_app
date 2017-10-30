package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Main Drive class
 * @author mbowman
 * @version 10/02/2017
 */

@TeleOp(name="ManualDrive", group="Manual")
public class MecanumManualDrive extends OpMode {
    private DriveHardware robot;

    /**
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initializing...");

        robot = new DriveHardware();
        robot.init(hardwareMap, false);

    }

    /**
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
        telemetry.addData("Status", "Done. Please press play.");
    }

    /**
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        telemetry.addData("Status", "Starting...");
    }

    /**
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP.
     */
    @Override
    public void loop() {
        double left_y = -gamepad1.left_stick_y; // positive for right
        double right_y = -gamepad1.right_stick_x; // positive for right

        this.robot.frontLeft.setPower(left_y);
        this.robot.backLeft.setPower(left_y);
        this.robot.frontRight.setPower(right_y);
        this.robot.backRight.setPower(right_y);
    }

    /**
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        telemetry.addData("Status", "Stopped.");
    }
}