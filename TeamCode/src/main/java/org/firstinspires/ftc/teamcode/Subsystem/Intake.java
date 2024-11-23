package org.firstinspires.ftc.teamcode.Subsystem;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;

import org.firstinspires.ftc.teamcode.Hardware.Globals;
import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;

public class Intake {

    private RobotHardware robot;

    public Intake(RobotHardware robot) {
        this.robot = robot;
    }

    //Todo: Enums for Arm states
    public enum ArmState
    {
        INIT, UP, DOWN, SAFE, LOCK, HALF
    }

    //Todo: Enums for Yaw states
    public enum YawState
    {
        INIT, INIT_PLUS, HALF, DROP, LEFT, RIGHT, FULL_LEFT, FULL_RIGHT, MID_LEFT, MID_RIGHT
    }

    //Todo: Enums for Elbow states
    public enum ElbowState
    {
        INIT, HALF, EXTEND_LEVEL1, EXTEND_LEVEL2, EXTEND_LEVEL3, EXTEND_LEVEL4, FULL_EXTEND, SAFE
    }

    //Todo: Enums for Intake motor states
    public enum IntakeMotorState {
        OFF, FORWARD, REVERSE
    }



    //Todo: Methods to update Arm state
    public void updateArmState(ArmState state) {
        switch (state) {
            case INIT:
                setArm(Globals.armInit);
                break;
            case UP:
                setArm(Globals.armUp);
                break;
            case DOWN:
                setArm(Globals.armDown);
                break;
            case SAFE:
                setArm(Globals.armSafe);
                break;
            case LOCK:
                setArm(Globals.armLock);
                break;
            case HALF:
                setArm(Globals.armHalf);
                break;
        }
    }

    // Todo: Methods to update Yaw state
    public void updateYawState(YawState state) {
        switch (state) {
            case INIT:
                setYaw(Globals.yawInit);
                break;
            case INIT_PLUS:
                setArm(Globals.yawInitPlus);
                break;
            case HALF:
                setYaw(Globals.yawHalf);
                break;
            case DROP:
                setYaw(Globals.yawDrop);
                break;
            case LEFT:
                setYaw(Globals.yawLeft);
                break;
            case RIGHT:
                setYaw(Globals.yawRight);
                break;
            case FULL_LEFT:
                setYaw(Globals.yawFullLeft);
                break;
            case FULL_RIGHT:
                setYaw(Globals.yawFullRight);
                break;
            case MID_LEFT:
                setYaw(Globals.yawMidLeft);
                break;
            case MID_RIGHT:
                setYaw(Globals.yawMidRight);
                break;
        }
    }

    // Todo: Methods to update Elbow state
    public void updateElbowState(ElbowState state) {
        switch (state) {
            case INIT:
                setElbow(Globals.elbowInit);
                break;
            case HALF:
                setElbow(Globals.elbowHalf);
                break;
            case EXTEND_LEVEL1:
                setElbow(Globals.elbowExtendLevel1);
                break;
            case EXTEND_LEVEL2:
                setElbow(Globals.elbowExtendLevel2);
                break;
            case EXTEND_LEVEL3:
                setElbow(Globals.elbowExtendLevel3);
                break;
            case EXTEND_LEVEL4:
                setElbow(Globals.elbowExtendLevel4);
                break;
            case FULL_EXTEND:
                setElbow(Globals.elbowFullExtend);
                break;
            case SAFE:
                setElbow(Globals.elbowSafe);
                break;
        }
    }

    //todo: Methods to update Intake motor state
    public void updateIntakeMotorState(IntakeMotorState state) {
        switch (state) {
            case OFF:
                setIntakePower(Globals.intakeInitPower);
                break;
            case FORWARD:
                setIntakePower(Globals.intakePower);
                break;
            case REVERSE:
                setIntakePower(Globals.outtakePower);
                break;
        }
    }


    private void setArm(double position) {
        robot.arm.setPosition(position);
    }

    private void setYaw(double position) {
        robot.yawArm.setPosition(position);
    }

    private void setElbow(double position) {
        robot.elbow1.setPosition(position);
        robot.elbow2.setPosition(position);

    }

    private void setIntakePower(double power)
    {
        robot.intakeMotor.setPower(power);
    }
//
    public InstantAction ArmCommands(ArmState state) {
        return new InstantAction(() -> updateArmState(state));
    }

    public InstantAction ElbowCommands(ElbowState state) {
        return new InstantAction(() -> updateElbowState(state));
    }

    public Action YawCommands(YawState state) {
        return new InstantAction(() -> updateYawState(state));
    }

    public InstantAction IntakeMotorCommands(IntakeMotorState state) {
        return new InstantAction(() -> updateIntakeMotorState(state));
    }

}
