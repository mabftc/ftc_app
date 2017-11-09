package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.hardware.Hardware;

/**
 * Main Drive class
 * @author mbowman
 * @version 10/02/2017
 */
@TeleOp(name="Manual Drive", group="Manual")
public class ManualDrive extends OpMode {
    private Hardware robot;
    private boolean grip;

    private final double LEFT_CLOSED = 0.2;
    private final double RIGHT_CLOSED = 0.8;
    private final double LEFT_OPEN = 0.8;
    private final double RIGHT_OPEN = 0.2;

    /**
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initializing...");

        robot = new Hardware(this);
        robot.init(hardwareMap, false);

        grip = false;

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
        double left_y = gamepad1.left_stick_y; // positive for right
        double right_y = gamepad1.right_stick_y; // positive for right
        double arm_y = gamepad2.right_stick_y;

        this.robot.frontLeft.setPower(left_y);
        this.robot.backLeft.setPower(left_y);
        this.robot.frontRight.setPower(right_y);
        this.robot.backRight.setPower(right_y);

        this.robot.arm.setPower(arm_y);

        if (gamepad2.a) grip = !grip; // toggle grip

        if (grip) {
            robot.rightGrip.setPosition(RIGHT_CLOSED);
            robot.leftGrip.setPosition(LEFT_CLOSED);
        } else {
            robot.rightGrip.setPosition(RIGHT_OPEN);
            robot.leftGrip.setPosition(LEFT_OPEN);
        } // set grips accordingly
    }

    /**
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        telemetry.addData("Status", "Stopped.");
    }
}