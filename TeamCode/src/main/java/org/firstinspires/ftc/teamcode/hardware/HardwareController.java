package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

public class HardwareController implements Controller {
    // Instance variables
    private Hardware robot;
    private double speed;
    private LinearOpMode opMode;
    private VuforiaLocalizer vuforia;
    private VuforiaTrackable relicTemplate;

    // Constants
    public static final double     COUNTS_PER_MOTOR_REV    = 1120; // for AndyMark NeveRest 40 classic
    public static final double     DRIVE_GEAR_REDUCTION    = 1.0;
    public static final double     WHEEL_DIAMETER_INCHES   = 4.0;
    public static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * Math.PI);

    public static final double CLAW_CLOSED          = 0;
    public static final double CLAW_OPEN            = 0;

    public static final double LIFT_SENSITIVITY     = 0.30;


    public HardwareController(LinearOpMode rOpMode, HardwareMap hwMap) {
        // initialize hardware
        robot = new Hardware();
        robot.init(hwMap);
        this.speed = 0.5;
        this.opMode = rOpMode;
        rOpMode.telemetry.addData("Update", "Passed hardware initialization.");
        rOpMode.telemetry.update();
        // set up vuforia
        int cameraMonitorViewId = robot.getHwMap().appContext.getResources().getIdentifier("cameraMonitorViewId", "id", robot.getHwMap().appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
        parameters.vuforiaLicenseKey = "AZqy8j//////AAAAGXdHb499g0jcuKn1w+ThVqF0JJE1+j2CsRU0Bgcf0Vw9EFwrkhQAGpk4PE0O5FG9DaUpGQwuwUfvincTO+tUpwFonFZiOFzoevgHwi3n13OZUrZT3CvkNgbJcPCAbG6HK/5KSK2V5e51tH06kQKBC9NRbZmqZeGyDl+uuKXnd+tXFYAWzPWr1i2BTjC6uoP4UkkiNrtJOKjyyWmbTGmqi7kqiluQJeWBZirACewukct0a6IhwHuby3aRR++LSLNB2P9f3xDv24XCxOq2cs4bW0C5vawAD68yKkdVGc5j58lxPR7Yea5jWr8GZDFcLeuEajRetAq2PEAktbd/K41GJMjyzftx6uBmMy9oK3ofv/da";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);
        // load trackables this season
        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        this.relicTemplate = relicTemplate;
        relicTrackables.activate();
        rOpMode.telemetry.addData("Update","Passed vuforia initialization.");
        rOpMode.telemetry.update();
    }

    // === Vuforia === //
    public String getSymbol() {
        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
        if (vuMark != RelicRecoveryVuMark.UNKNOWN) {
            if (vuMark == RelicRecoveryVuMark.LEFT) return "l";
            else if (vuMark == RelicRecoveryVuMark.CENTER) return "c";
            else if (vuMark == RelicRecoveryVuMark.RIGHT) return "r";
        }
        return "u";
    }

    // === Lift === //
    public void raiseLift() {
        robot.arm.setPower(LIFT_SENSITIVITY);
    }

    public void lowerLift() {
        robot.arm.setPower(-LIFT_SENSITIVITY);
    }

    public void stopLift() {
        this.robot.arm.setPower(0);
    }

    // === Servo grips === //
    public void grip() {
        robot.grip.setPosition(CLAW_CLOSED);
    }
    
    public void release() {
        robot.grip.setPosition(CLAW_OPEN);
    }
    
    // === Drive === //
    public void turn(int theta) {
        if (theta > 0) {
            // rightward motion increases reading from IMU
        } else {

        }
    }

    public void forward(int left, int right) {
        this.encoderDrive(left, right);
    }

    public void backward(int left, int right) {
        this.encoderDrive(-left, -right);
    }

    public void forward(int inches) {
        this.forward(inches, inches);
    }

    public void backward(int inches) {
        this.backward(inches, inches);
    }

    public void setOperationSpeed(double speed) {
        this.speed = speed;
    }

    public void setPower(double left, double right) {
        robot.left.setPower(left);
        robot.right.setPower(left);
    }

    public void brake() {
        robot.left.setPower(0);
        robot.right.setPower(0);
    }

    public void turnOnManualMode() {
        robot.left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    private void turnOffEncoders() {
        robot.left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    private void turnOnEncoders() {
        robot.left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    /**
     * Drives the motors forward / backward using motor encoders
     * Based heavily on encoderDrive(double speed, leftInches, rightInches)
     * by Robert Atkinson, (c) 2016
     * @param leftInches
     * @param rightInches
     */
    private void encoderDrive(double leftInches, double rightInches) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the OpMode is still active
        if (opMode.opModeIsActive()) {
            newLeftTarget = (robot.left.getCurrentPosition() + (int) (leftInches * this.COUNTS_PER_INCH));
            newRightTarget = (robot.right.getCurrentPosition() + (int) (rightInches * this.COUNTS_PER_INCH));

            robot.left.setTargetPosition(newLeftTarget);
            robot.right.setTargetPosition(newRightTarget);

            turnOnEncoders();

            robot.left.setPower(Math.abs(this.speed));
            robot.right.setPower(Math.abs(this.speed));

            while (opMode.opModeIsActive() && (robot.left.isBusy() || robot.right.isBusy()));
            brake();
            turnOffEncoders();
        }
    }
}