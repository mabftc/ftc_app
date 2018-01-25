package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.hardware.BaseHardwareController;

/**
 * Main Controller class
 * @author mbowman
 * @version 10/02/2017
 */
@TeleOp(name = "Manual Drive", group = "Manual")
public class ManualDrive extends LinearOpMode {
    private BaseHardwareController controller;
    private final double DRIVE_SENSITIVITY = 1;
    private boolean arm;

    /**
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP.
     */
    @Override
    public void runOpMode() throws InterruptedException {
        controller = new BaseHardwareController(this, hardwareMap);
        controller.init();
        arm = false;
        telemetry.addData("System", "Initialization complete. Ready.");
        telemetry.update();
        waitForStart();

        while (opModeIsActive()) {
            // Tank drive
            double left_y = gamepad1.left_stick_y * DRIVE_SENSITIVITY;
            double right_y = gamepad1.right_stick_y * DRIVE_SENSITIVITY;

            controller.setPower(left_y, right_y);

            // Emergency Jewel Arm Control
            if (gamepad1.y) {
                arm = !arm;
            }

            if (arm) {
                controller.lowerJewelArm();
            }
            else {
                controller.raiseJewelArm();
            }

            // Lift control
            if (gamepad1.left_bumper) {
                controller.raiseLift();
            } else if (gamepad1.left_trigger >= 0.7) {
                controller.lowerLift();
            } else {
                controller.stopLift();
            }

            if (gamepad1.dpad_up) controller.nextGlyph();
            if (gamepad1.dpad_down) controller.previousGlyph();

            // Grip control
            if (gamepad1.right_bumper) {
                controller.grip();
            } else {
                controller.release();
            }
        }
    }
}