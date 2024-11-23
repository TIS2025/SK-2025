package org.firstinspires.ftc.teamcode.Sequence;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.ftc.Actions;

import org.firstinspires.ftc.teamcode.Subsystem.Hang;
import org.firstinspires.ftc.teamcode.Subsystem.Intake;
import org.firstinspires.ftc.teamcode.Subsystem.Outtake;

import java.util.ArrayList;
import java.util.List;

public class DropHeightSequence {
    private List<Action> DropHeight = new ArrayList<>();
    public DropHeightSequence(Intake intake, Outtake outtake, Hang hanger) {
//        DropHeight.clear();
//        DropHeight.add(
        Actions.runBlocking(

                new SequentialAction(
                        intake.YawCommands(Intake.YawState.DROP),
//                       intake.YawCommands(Intake.YawState.INIT_PLUS),

                        outtake.LifterCommands(Outtake.LifterState.TRANSFER),
                        outtake.BucketCommands(Outtake.BucketState.MID),
                        new SleepAction(0.5),
                        intake.IntakeMotorCommands(Intake.IntakeMotorState.REVERSE),
                        new SleepAction(0.7),
                        outtake.BucketCommands(Outtake.BucketState.INIT),
                        outtake.LifterCommands(Outtake.LifterState.HIGH_BUCKET_HEIGHT1)


                )
        );
    }

    public static Action getDropHeightAction(Intake intake, Outtake outtake, Hang hanger) {
        return new SequentialAction(
                intake.YawCommands(Intake.YawState.DROP),
//                       intake.YawCommands(Intake.YawState.INIT_PLUS),

                outtake.LifterCommands(Outtake.LifterState.TRANSFER),
                outtake.BucketCommands(Outtake.BucketState.MID),
                new SleepAction(0.5),
                intake.IntakeMotorCommands(Intake.IntakeMotorState.REVERSE),
                new SleepAction(0.7),
                outtake.BucketCommands(Outtake.BucketState.INIT),
                outtake.LifterCommands(Outtake.LifterState.HIGH_BUCKET_HEIGHT1)


        );
    }
}
