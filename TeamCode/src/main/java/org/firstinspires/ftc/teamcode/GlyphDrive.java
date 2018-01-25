package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.hardware.BaseHardwareController;

@Autonomous(name = "Glyph Drive", group = "BlueAutonomous")
public class GlyphDrive extends LinearOpMode {
    BaseHardwareController controller;

    @Override
    public void runOpMode() throws InterruptedException {
        controller = new BaseHardwareController(this, hardwareMap);
        controller.init();
        waitForStart();
        sleep(500);
        controller.grip();
        sleep(4000);
        controller.setPower(-0.5, -0.5);
        sleep(3000);
        controller.brake();
        controller.release();
        controller.setPower(0.4, 0.4);
        sleep(350);
        controller.brake();
    }
}