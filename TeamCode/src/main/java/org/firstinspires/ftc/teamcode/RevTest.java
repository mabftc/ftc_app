package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.hardware.Hardware;
import org.firstinspires.ftc.teamcode.hardware.HardwareController;
import org.firstinspires.ftc.teamcode.hardware.HardwareTest;

@Autonomous(name = "Rev Test", group = "Autonomous")
public class RevTest extends LinearOpMode {
    HardwareTest hw;
    @Override
    public void runOpMode() {
        hw = new HardwareTest();
        hw.left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hw.right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        hw.init(hardwareMap, true);
        waitForStart();
        while (opModeIsActive()) {
            telemetry.addData("Update", hw.left.getCurrentPosition());
            telemetry.update();
            sleep(100);
        }
    }

    /*
    private void encoderDrive(double leftInches, double rightInches) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the OpMode is still active
        if (this.opModeIsActive()) {
            hw.left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            hw.right.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            newLeftTarget = (hw.left.getCurrentPosition() + (int) (leftInches * 1120));
            newRightTarget = (hw.right.getCurrentPosition() + (int) (rightInches * 1120));

            telemetry.addData("left", newLeftTarget);
            telemetry.addData("right", newRightTarget);
            telemetry.update();

            sleep(5000);

            hw.left.setTargetPosition(newLeftTarget);
            hw.right.setTargetPosition(newRightTarget);


            hw.left.setPower(Math.abs(1));
            hw.right.setPower(Math.abs(1));

            while (this.opModeIsActive() && (hw.left.isBusy() || hw.right.isBusy()));

            hw.left.setPower(0);
            hw.right.setPower(0);

            hw.right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            hw.left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }*/
}