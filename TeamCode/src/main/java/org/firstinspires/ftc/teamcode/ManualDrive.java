package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.hardware.HardwareController;

/**
 * Main Controller class
 * @author mbowman
 * @version 10/02/2017
 */
@TeleOp(name = "Manual Drive", group = "Manual")
public class ManualDrive extends LinearOpMode {
    private HardwareController controller;
    private final double DRIVE_SENSITIVITY = 1;

    /**
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP.
     */
    @Override
    public void runOpMode() {
        controller = new HardwareController(this);

        waitForStart();

        boolean grip = false;
        while (opModeIsActive()) {
            double left_y = gamepad1.left_stick_y * DRIVE_SENSITIVITY; // positive for right
            double right_y = gamepad1.right_stick_y * DRIVE_SENSITIVITY; // positive for right

            controller.setPower(left_y, right_y);

            if (gamepad1.dpad_up) {
                controller.raiseLift();
            } else if (gamepad1.dpad_down) {
                controller.lowerLift();
            } else {
                controller.stopLift();
            }

            // grip control
            if (gamepad1.right_bumper) grip = true;
            else grip = false;

            if (grip) {
                controller.grip();
            } else {
                controller.release();
            }
        }
    }
}