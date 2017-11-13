package org.firstinspires.ftc.teamcode.hardware;

public interface Controller {
    void forward(int left, int right);
    void backward(int left, int right);
    void forward(int inches);
    void backward(int inches);
    void setOperationSpeed(double speed);
    void turn(int theta);
}