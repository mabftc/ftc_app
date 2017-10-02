package org.firstinspires.ftc.teamcode.autodrive;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.Hardware;

/**
 * Mecanum Wheel Drive Class
 * Allows user to easily add mecanum drive capabilities
 * to any autonomous OpMode.
 * @author M. Aksel Bowman
 * @version 8/24/2017
 */
public class MecanumDrive extends NormalDrive implements Drive {
    /**
     * MecanumDrive Constructor
     * @param robotOpMode OpMode
     * @param hardware Hardware Map Wrapper
     */
    public MecanumDrive(LinearOpMode robotOpMode, Hardware hardware) {
        super(robotOpMode, hardware);
    }

    /**
     * Drives to the left
     * @param inches distance
     */
    public void left(int inches) {

    }

    /**
     * Drives to the right
     * @param inches distance
     */
    public void right(int inches) {

    }
}