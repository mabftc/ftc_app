package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.hardware.AutonomousHardwareController;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "Advanced Red Autonomous", group = "Autonomous")
public class RedAutonomous extends LinearOpMode {

    AutonomousHardwareController controller;
    char myChar = 'r';

    @Override
    public void runOpMode() throws InterruptedException {
        controller = new AutonomousHardwareController(this, hardwareMap);
        controller.init();
        waitForStart();
        if (opModeIsActive()) {
            sleep(3000);
            controller.forward(5);
            controller.lowerJewelArm();
            sleep(3000);
            char jewelColor = controller.getJewelColor();

            if (myChar != jewelColor) {
                controller.forward(4);
                controller.raiseJewelArm();
                controller.forward(19);
            } else {
                controller.backward(4);
                controller.raiseJewelArm();
                controller.forward(23);
            }
        }
    }
}