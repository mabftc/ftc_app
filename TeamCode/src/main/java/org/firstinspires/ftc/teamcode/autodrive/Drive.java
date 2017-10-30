package org.firstinspires.ftc.teamcode.autodrive;

public interface Drive {
    void forward(int left, int right);
    void backward(int left, int right);
    void forward(int inches);
    void backward(int inches);
    void setOperationSpeed(double speed);
    void setPower(double power);
    void setPower(double left, double right);
    void turn(int theta);
}