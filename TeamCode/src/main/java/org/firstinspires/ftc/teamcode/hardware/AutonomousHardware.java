package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * A wrapper class around the HardwareMap included in
 * OpMode classes. Makes it easier to interface with hardware
 * on the robot.
 * @author mbowman
 * @version 10/02/2017
 */
public class AutonomousHardware extends BaseHardware {
    public ModernRoboticsI2cColorSensor colorSensor;
    //public ModernRoboticsI2cColorSensor tileSensor;

    public VuforiaLocalizer vuforia;
    public VuforiaTrackable relicTemplate;

    //public BNO055IMU imu;
    //Orientation angles;

    public void init(HardwareMap hwMap) {
        // Base hardware
        super.init(hwMap);

        // Vuforia
        int cameraMonitorViewId = hwMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hwMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = "AZqy8j//////AAAAGXdHb499g0jcuKn1w+ThVqF0JJE1+j2CsRU0Bgcf0Vw9EFwrkhQAGpk4PE0O5FG9DaUpGQwuwUfvincTO+tUpwFonFZiOFzoevgHwi3n13OZUrZT3CvkNgbJcPCAbG6HK/5KSK2V5e51tH06kQKBC9NRbZmqZeGyDl+uuKXnd+tXFYAWzPWr1i2BTjC6uoP4UkkiNrtJOKjyyWmbTGmqi7kqiluQJeWBZirACewukct0a6IhwHuby3aRR++LSLNB2P9f3xDv24XCxOq2cs4bW0C5vawAD68yKkdVGc5j58lxPR7Yea5jWr8GZDFcLeuEajRetAq2PEAktbd/K41GJMjyzftx6uBmMy9oK3ofv/da";

        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary
        relicTrackables.activate();

        // Sensors
        /*
        BNO055IMU.Parameters imuParams = new BNO055IMU.Parameters();
        imu = hwMap.get(BNO055IMU.class, "imu");
        imuParams.loggingEnabled = true;
        imuParams.loggingTag = "IMU";
        //imuParams.calibrationDataFile = "AdafruitIMUCalibration.json";
        imuParams.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        imuParams.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        imu.initialize(imuParams);*/

        colorSensor = (ModernRoboticsI2cColorSensor) hwMap.colorSensor.get("jewel");
        //tileSensor = (ModernRoboticsI2cColorSensor) hwMap.colorSensor.get("tile");
    }

    public char jewelColor() {
        if (colorSensor.red() > colorSensor.blue()) return 'r';
        else return 'b';
    }

    /*public char tileColor() {
        if (tileSensor.red() > tileSensor.blue()) return 'r';
        else return 'b';
    }*/

    /*public void updateIMU() {
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
    }

    public double getHeading() {
        return AngleUnit.DEGREES.normalize(angles.firstAngle);
    }*/

    public char getVuMark() {
        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
        if (vuMark == RelicRecoveryVuMark.RIGHT) return 'r';
        else if (vuMark == RelicRecoveryVuMark.LEFT) return 'l';
        else if (vuMark == RelicRecoveryVuMark.CENTER) return 'c';
        return 'u';
    }
}