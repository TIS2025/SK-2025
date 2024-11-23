package org.firstinspires.ftc.teamcode.Subsystem;

import com.acmerobotics.roadrunner.InstantAction;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Hardware.Globals;
import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;

public class Hang {

    private RobotHardware robot;

    public Hang(RobotHardware robot) {
        this.robot = robot;
    }
    HangState state=HangState.INIT;
    // todo: Enums for Hang states
    public enum HangState {
        INIT, LOWER_RUNG, HANG_DONE, HIGHER_RUNG
    }

    //todo: Method to update Hang state
    public void updateHangState(HangState state) {
        switch (state) {
            case INIT:
                setHangPosition(Globals.hangerInit);
                break;
            case LOWER_RUNG:
                setHangPosition(Globals.hangerLowerRung);
                break;
            case HANG_DONE:
                setHangPosition(Globals.hangerHangDone);
                break;
            case HIGHER_RUNG:
                setHangPosition(Globals.hangerHigherRung);
                break;
        }
    }

    //todo:  Private method to set positions for both motors

    private void setHangPosition(int position) {
        setMotorPosition(robot.leftHang, position);
    }

    private void setMotorPosition(DcMotor motor, int position) {
        motor.setTargetPosition(position);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.setPower(1.0);
    }


    public InstantAction hangCommands(HangState state) {
        return new InstantAction(() -> updateHangState(state));
    }
}
