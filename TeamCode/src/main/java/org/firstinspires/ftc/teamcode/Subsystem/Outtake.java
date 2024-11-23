package org.firstinspires.ftc.teamcode.Subsystem;

import com.acmerobotics.roadrunner.InstantAction;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Hardware.Globals;
import org.firstinspires.ftc.teamcode.Hardware.RobotHardware;

public class Outtake {

    private RobotHardware robot;

    public Outtake(RobotHardware robot) {
        this.robot = robot;
    }

    // todo: Enums for Lifter states
    public enum LifterState {
        INIT,TRANSFER, INTAKE_HEIGHT, SPECIMEN_HEIGHT, SPECIMEN_OUT_HEIGHT, LOWER_RUNG,
        LOW_CHAMBER_HEIGHT1, LOW_CHAMBER_HEIGHT2, LOW_CHAMBER_HEIGHT3,
        HIGH_CHAMBER_HEIGHT1, HIGH_CHAMBER_HEIGHT2, HIGH_CHAMBER_HEIGHT3,
        LOW_BUCKET_HEIGHT1, LOW_BUCKET_HEIGHT2, LOW_BUCKET_HEIGHT3,
        HIGH_BUCKET_HEIGHT1, HIGH_BUCKET_HEIGHT2, HIGH_BUCKET_HEIGHT3, INCREMENT, DOWN
    }

    // todo: Enums for Specimen Servo states
    public enum SpecimenState {
        INIT,HALF, GRIP, RELEASE, SAFE
    }

    // todo: Enums for Bucket Servo states
    public enum BucketState {
        INIT, HALF, IN, OUT, MID, SAFE, MID_OUT_SPECIMEN, SPECIMEN_INTAKE, SPECIMEN_OUTAKE
    }

    // todo: Methods to update Lifter state
    public void updateLifterState(LifterState state) {
        switch (state) {
            case INIT:
                setLifter(Globals.lifterInit);
                break;
            case TRANSFER:
                setLifter(Globals.lifterTransfer);
                break;
            case INTAKE_HEIGHT:
                setLifter(Globals.lifterIntakeHeight);
                break;
            case SPECIMEN_HEIGHT:
                setLifter(Globals.lifterSpecimenHeight);
                break;
            case SPECIMEN_OUT_HEIGHT:
                setLifter(Globals.lifterSpecimenOutHeight);
                break;
            case LOWER_RUNG:
                setLifter(Globals.lifterLowerRungHeight);
                break;
            case LOW_CHAMBER_HEIGHT1:
                setLifter(Globals.lifterLowChamberHeight1);
                break;
            case LOW_CHAMBER_HEIGHT2:
                setLifter(Globals.lifterLowChamberHeight2);
                break;
            case LOW_CHAMBER_HEIGHT3:
                setLifter(Globals.lifterLowChamberHeight3);
                break;
            case HIGH_CHAMBER_HEIGHT1:
                setLifter(Globals.lifterHighChamberHeight1);
                break;
            case HIGH_CHAMBER_HEIGHT2:
                setLifter(Globals.lifterHighChamberHeight2);
                break;
            case HIGH_CHAMBER_HEIGHT3:
                setLifter(Globals.lifterHighChamberHeight3);
                break;
            case LOW_BUCKET_HEIGHT1:
                setLifter(Globals.lifterLowBucketHeight1);
                break;
            case LOW_BUCKET_HEIGHT2:
                setLifter(Globals.lifterLowBucketHeight2);
                break;
            case LOW_BUCKET_HEIGHT3:
                setLifter(Globals.lifterLowBucketHeight3);
                break;
            case HIGH_BUCKET_HEIGHT1:
                setLifter(Globals.lifterHighBucketHeight1);
                break;
            case HIGH_BUCKET_HEIGHT2:
                setLifter(Globals.lifterHighBucketHeight2);
                break;
            case HIGH_BUCKET_HEIGHT3:
                setLifter(Globals.lifterHighBucketHeight3);
                break;
            case INCREMENT:
                setLifterIncrement(Globals.lifterIncrementValue);
                break;
            case DOWN:
                setLifterIncrement(Globals.lifterDown);
                break;
        }
    }

    // todo: Methods to update Specimen state
    public void updateSpecimenState(SpecimenState state) {
        switch (state) {
            case INIT:
                setSpecimen(Globals.specimenInit);
                break;
            case HALF:
                setSpecimen(Globals.specimenHalf);
                break;
            case GRIP:
                setSpecimen(Globals.specimenGrip);
                break;
            case RELEASE:
                setSpecimen(Globals.specimenRelease);
                break;
            case SAFE:
                setSpecimen(Globals.specimenSafe);
                break;
        }
    }

    // todo: Methods to update Bucket state
    public void updateBucketState(BucketState state) {
        switch (state) {
            case INIT:
                setBucket(Globals.bucketInit);
                break;
            case HALF:
                setBucket(Globals.bucketHalf);
                break;
            case IN:
                setBucket(Globals.bucketIn);
                break;
            case OUT:
                setBucket(Globals.bucketOut);
                break;
            case MID:
                setBucket(Globals.bucketMid);
                break;
            case SAFE:
                setBucket(Globals.bucketSafe);
                break;
            case MID_OUT_SPECIMEN:
                setBucket(Globals.bucketMidSpecimen);
                break;
            case SPECIMEN_INTAKE:
                setBucket(Globals.bucketSpecimenIntake);
                break;
            case SPECIMEN_OUTAKE:
                setBucket(Globals.bucketSpecimenOutake);
                break;
        }
    }

    // Private methods to set positions for different components
    private void setLifter(int position) {
        robot.lifter.setTargetPosition(position);
        robot.lifter2.setTargetPosition(position);
        robot.lifter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.lifter2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.lifter.setPower(1.0);
        robot.lifter2.setPower(1.0);
    }

    private void setLifterIncrement(int increment) {
        int currentPosition = robot.lifter.getCurrentPosition();
        robot.lifter.setTargetPosition(currentPosition + increment);
        robot.lifter2.setTargetPosition(currentPosition + increment);
        robot.lifter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.lifter2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.lifter.setPower(1.0);
        robot.lifter2.setPower(1.0);
    }
    public void resetLifter(){
        robot.lifter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.lifter2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    private void setSpecimen(double position) {
        robot.specimen.setPosition(position);
    }

    private void setBucket(double position) {
        robot.bucket.setPosition(position);
    }

    public InstantAction BucketCommands(BucketState state) {
        return new InstantAction(() -> updateBucketState(state));
    }

    public InstantAction LifterCommands(LifterState state) {
        return new InstantAction(() -> updateLifterState(state));
    }

    public InstantAction SpecimenCommands(SpecimenState state) {
        return new InstantAction(() -> updateSpecimenState(state));
    }


}
