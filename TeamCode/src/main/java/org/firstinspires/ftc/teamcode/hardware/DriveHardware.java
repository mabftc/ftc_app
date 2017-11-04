package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * A wrapper class around the HardwareMap inhereted from
 * OpMode classes. Makes it easier to interface with hardware
 * on the robot.
 * @author mbowman
 * @version 10/02/2017
 */
public class DriveHardware implements Hardware {
    private boolean usingEncoders;
    private HardwareMap hwMap;

    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;

    public DriveHardware() {

    }

    public void init(HardwareMap hwMap, boolean usingEncoders) {
        this.hwMap = hwMap;
        this.usingEncoders = usingEncoders;

        this.frontLeft = this.hwMap.dcMotor.get("frontLeft");
        this.frontRight = this.hwMap.dcMotor.get("frontRight");
        this.backLeft = this.hwMap.dcMotor.get("backLeft");
        this.backRight = this.hwMap.dcMotor.get("backRight");

        this.backLeft.setDirection(DcMotor.Direction.REVERSE);
        this.frontLeft.setDirection(DcMotor.Direction.REVERSE);

        this.backRight.setDirection(DcMotor.Direction.FORWARD);
        this.frontRight.setDirection(DcMotor.Direction.FORWARD);

    }
}