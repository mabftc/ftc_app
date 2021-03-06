package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.hardware.AutonomousHardwareController;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Advanced Blue Autonomous", group = "Autonomous")
public class BlueAutonomous extends LinearOpMode {

    AutonomousHardwareController controller;
    char myChar = 'b';

    @Override
    public void runOpMode() {
        controller = new AutonomousHardwareController(this, hardwareMap);
        controller.init();
        waitForStart();
        if (opModeIsActive()) {
            sleep(3000);
            controller.forward(5);
            controller.lowerJewelArm();
            sleep(3000);
            char jewelColor = controller.getJewelColor();
            telemetry.addData("Color", jewelColor);
            telemetry.update();

            if (myChar != jewelColor) {
                controller.forward(4);
                controller.raiseJewelArm();
                controller.forward(19);
            } else {
                controller.backward(4);
                controller.raiseJewelArm();
                controller.forward(23);
            }

            controller.backward(2);
            controller.encoderDrive(3, -3);
            controller.forward(7);
        }
    }
}