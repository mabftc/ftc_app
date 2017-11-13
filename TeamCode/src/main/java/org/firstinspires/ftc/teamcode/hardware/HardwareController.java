package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

public class HardwareController implements Controller {
    protected LinearOpMode robotOpMode;
    protected Hardware robot;
    protected double speed;

    // Constants
    static final double     COUNTS_PER_MOTOR_REV    = 1120;    // for AndyMark NeveRest 40 classic
    static final double     DRIVE_GEAR_REDUCTION    = 1.0;
    static final double     WHEEL_DIAMETER_INCHES   = 4.0;
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * Math.PI);

    public static final double LEFT_CLAW_CLOSED     = 0;
    public static final double RIGHT_CLAW_CLOSED    = 0;
    public static final double LEFT_CLAW_OPEN       = 0;
    public static final double RIGHT_CLAW_OPEN      = 0;

    private final double LIFT_SENSITIVITY           = 0.20;

    private VuforiaLocalizer vuforia;
    private VuforiaTrackable relicTemplate;

    public HardwareController(LinearOpMode rOpMode) {
        robotOpMode = rOpMode;
        robot = new Hardware(robotOpMode);
        this.speed = 0;

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
        this.robot.armRight.setPower(LIFT_SENSITIVITY);
    }

    public void lowerLift() {
        this.robot.armRight.setPower(-LIFT_SENSITIVITY);
    }

    public void stopLift() {
        this.robot.armRight.setPower(0);
    }

    // === Servo grips === //
    public void grip() {
        robot.rightGrip.setPosition(RIGHT_CLAW_CLOSED);
        robot.leftGrip.setPosition(LEFT_CLAW_CLOSED);
    }
    
    public void release() {
        robot.rightGrip.setPosition(RIGHT_CLAW_OPEN);
        robot.leftGrip.setPosition(LEFT_CLAW_OPEN);
    }
    
    // === Drive === //
    public void turn(int theta) {

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
        robot.frontLeft.setPower(left);
        robot.frontRight.setPower(left);
        robot.backLeft.setPower(right);
        robot.backRight.setPower(right);
    }

    public void brake() {
        robot.backLeft.setPower(0);
        robot.frontLeft.setPower(0);
        robot.backRight.setPower(0);
        robot.frontRight.setPower(0);
    }

    private void turnOffEncoders() {
        robot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    private void turnOnEncoders() {
        robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
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
        if (robotOpMode.opModeIsActive()) {
            newLeftTarget = (robot.frontLeft.getCurrentPosition() + (int) (leftInches * this.COUNTS_PER_INCH) + robot.backLeft.getCurrentPosition() + (int) (leftInches * this.COUNTS_PER_INCH)) / 2;
            newRightTarget = (robot.frontRight.getCurrentPosition() + (int) (rightInches * this.COUNTS_PER_INCH) + robot.backRight.getCurrentPosition() + (int) (rightInches * this.COUNTS_PER_INCH)) / 2;

            robot.frontLeft.setTargetPosition(newLeftTarget);
            robot.frontRight.setTargetPosition(newRightTarget);
            robot.backLeft.setTargetPosition(newLeftTarget);
            robot.backRight.setTargetPosition(newRightTarget);

            turnOnEncoders();

            robot.frontLeft.setPower(Math.abs(this.speed));
            robot.frontRight.setPower(Math.abs(this.speed));
            robot.backLeft.setPower(Math.abs(this.speed));
            robot.backRight.setPower(Math.abs(this.speed));

            while (robotOpMode.opModeIsActive() && (robot.frontLeft.isBusy() || robot.frontRight.isBusy()));
            brake();
            turnOffEncoders();
        }
    }
}