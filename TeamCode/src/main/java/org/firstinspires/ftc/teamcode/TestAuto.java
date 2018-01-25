package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.hardware.AutonomousHardwareController;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Debug", group = "BlueAutonomous")
public class TestAuto extends LinearOpMode {
    AutonomousHardwareController controller;

    @Override
    public void runOpMode() {
        controller = new AutonomousHardwareController(this, hardwareMap);
        controller.init();
        waitForStart();
        while (opModeIsActive()) {
            telemetry.addData("Color", controller.testJewelColor());
            telemetry.update();
        }
    }
}