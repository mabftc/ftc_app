package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class BaseHardwareController {
    private BaseHardware robot;

    protected LinearOpMode opMode;
    protected HardwareMap hwMap;

    public static final double     RIGHT_CLAW_CLOSED             = 0.5;
    public static final double     RIGHT_CLAW_OPEN               = 0.1;
    public static final double     LEFT_CLAW_CLOSED              = 0.15;
    public static final double     LEFT_CLAW_OPEN                = 0.65;

    public static final double     JEWEL_ARM_UP            = 1;
    public static final double     JEWEL_ARM_DOWN          = 0.15;

    public static final double     LIFT_SENSITIVITY        = 1;
    public static final double     LIFT_LENGTH             = 17.5;

    public static final double     COUNTS_PER_MOTOR_REV    = 1120; // for AndyMark NeveRest 40 classic
    public static final double     DRIVE_GEAR_REDUCTION    = 1.0;
    public static final double     WHEEL_DIAMETER_INCHES   = 4.0;
    public static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * Math.PI);

    public BaseHardwareController(LinearOpMode opMode, HardwareMap hwMap) {
        // initialize hardware
        this.opMode = opMode;
        this.hwMap = hwMap;
    }

    public void init() {
        // initialize hardware
        robot = new BaseHardware();
        robot.init(hwMap);
    }

    // === Lift === //
    public void encoderLift(double verticalInches) {
        robot.arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        int theta = (int) Math.asin(verticalInches / LIFT_LENGTH);
        int counts = (int) (theta / (2 * Math.PI) * COUNTS_PER_MOTOR_REV);

        int targetCounts = robot.arm.getCurrentPosition() + counts;
        robot.arm.setTargetPosition(targetCounts);
        robot.arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void nextGlyph() { encoderLift(6); }

    public void previousGlyph() { encoderLift(-6); }

    public void raiseLift() {
        robot.arm.setPower(-LIFT_SENSITIVITY);
    }

    public void lowerLift() {
        robot.arm.setPower(LIFT_SENSITIVITY);
    }

    public void stopLift() {
        this.robot.arm.setPower(0);
    }

    public void setLiftSpeed(double s) {
        robot.arm.setPower(s); // rotates direction:
    }

    // === Servos === //
    public void grip() {
        robot.leftGrip.setPosition(LEFT_CLAW_CLOSED);
        robot.rightGrip.setPosition(RIGHT_CLAW_CLOSED);
    }
    
    public void release() {
        robot.leftGrip.setPosition(LEFT_CLAW_OPEN);
        robot.rightGrip.setPosition(RIGHT_CLAW_OPEN);
    }

    public void raiseJewelArm() { robot.jewelArm.setPosition(JEWEL_ARM_UP); }

    public void lowerJewelArm() { robot.jewelArm.setPosition(JEWEL_ARM_DOWN); }

    // === Drive Control === //

    public void setPower(double left, double right) {
        robot.left.setPower(left);
        robot.right.setPower(right);
    }

    public void brake() {
        robot.left.setPower(0);
        robot.right.setPower(0);
    }
}