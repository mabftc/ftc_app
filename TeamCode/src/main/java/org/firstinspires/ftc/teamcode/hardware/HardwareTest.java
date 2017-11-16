package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * A wrapper class around the HardwareMap inhereted from
 * OpMode classes. Makes it easier to interface with hardware
 * on the robot.
 * @author mbowman
 * @version 10/02/2017
 */
public class HardwareTest {
    private boolean usingEncoders;
    private HardwareMap hwMap;

    public DcMotor left;
    public DcMotor right;

    // sensors
    //public ModernRoboticsI2cGyro gyro;

    public HardwareTest() {

    }

    public void init(HardwareMap hwMap, boolean usingEncoders) {
        this.hwMap = hwMap;
        this.usingEncoders = usingEncoders;

        // motors
        this.left = this.hwMap.dcMotor.get("left");
        this.right = this.hwMap.dcMotor.get("right");
    }

}