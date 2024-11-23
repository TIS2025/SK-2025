package org.firstinspires.ftc.teamcode.Sequence;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;

import org.firstinspires.ftc.teamcode.Subsystem.Hang;
import org.firstinspires.ftc.teamcode.Subsystem.Intake;
import org.firstinspires.ftc.teamcode.Subsystem.Outtake;

import java.util.ArrayList;
import java.util.List;

public class PickSequenceAuto {
    private List<Action> PickIn = new ArrayList<>();
    public PickSequenceAuto(Intake intake, Outtake outtake, Hang hanger) {
//        PickIn.clear();
//        PickIn.add(
        Actions.runBlocking(
                new SequentialAction(
                        new ParallelAction(
                                intake.IntakeMotorCommands(Intake.IntakeMotorState.FORWARD),
                                intake.YawCommands(Intake.YawState.FULL_LEFT),
                                intake.ArmCommands(Intake.ArmState.HALF),
                                outtake.LifterCommands(Outtake.LifterState.TRANSFER),
                                outtake.BucketCommands(Outtake.BucketState.IN))


                )
        );
    }
}
