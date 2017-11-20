package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.hardware.HardwareController;

@Autonomous(name = "Blue Autonomous", group = "Autonomous")
public class BlueAutonomous extends LinearOpMode {
    HardwareController controller;

    @Override
    public void runOpMode() {
        controller = new HardwareController(this, hardwareMap);
        while(opModeIsActive()) {
            telemetry.addData("Vuforia detected", controller.getSymbol());
            telemetry.addData("Color detected", controller.getJewelColor());
            telemetry.update();
            sleep(1000);
            controller.raiseJewelArm();
            sleep(1000);
            controller.lowerJewelArm();
            sleep(1000);
            /*
            controller.grip();
            sleep(2000);
            controller.release();
            sleep(2000);*/
        }
    }
}