package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Hardware child class specific to H-drive chassis
 * @author mbowman
 * @version 10/11/2017
 */
public class OmniHardware extends DriveHardware implements Hardware {
    public DcMotor hMotor;

    public void init(HardwareMap hwMap, boolean usingEncoders) {
        super.init(hwMap, usingEncoders);
        hMotor = hwMap.dcMotor.get("h_motor");
    }
}
