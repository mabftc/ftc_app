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
        controller.forward(12);
        controller.brake();
        while(opModeIsActive()) {
            telemetry.addData("Vuforia detected", controller.getSymbol());
            telemetry.update();
            sleep(100);
        }
    }
}